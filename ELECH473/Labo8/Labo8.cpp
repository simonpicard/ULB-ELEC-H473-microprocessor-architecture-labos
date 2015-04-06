#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <Windows.h>

#define NEIGHBOUR_SIZE 3
#define IMG_WIDTH 1024
#define IMG_HEIGHT 1024

double PCFreq = 0.0;
__int64 CounterStart = 0;

void StartCounter() {
	LARGE_INTEGER li;
	if (!QueryPerformanceFrequency(&li))
		printf("QueryPerformanceFrequency failed !\n");

	PCFreq = ((double)li.QuadPart) / 1000.0;

	QueryPerformanceCounter(&li);
	CounterStart = li.QuadPart;
}

double GetCounter() {
	LARGE_INTEGER li;
	QueryPerformanceCounter(&li);
	return ((double)(li.QuadPart - CounterStart)) / PCFreq;
}

unsigned char *parseImgFile(char source_path[], unsigned int *size) {
	FILE *source_file = NULL;
	unsigned char *img = NULL;
	source_file = fopen(source_path, "rb");
	if (source_file == NULL) {
		perror("Error while opening the file");
	}


	fseek(source_file, 0, SEEK_END);
	long int end_of_file = ftell(source_file);
	fseek(source_file, 0, SEEK_SET);

	unsigned int max_size = (1024 * 1024);
	*size = 0;
	img = new unsigned char[max_size*sizeof(unsigned char)];

	unsigned char current_pixel = fgetc(source_file); // char is on 8 bit
	long int current_location_in_file = ftell(source_file);
	while (current_location_in_file < end_of_file){
		img[*size] = current_pixel;
		*size = *size + 1;
		if (*size == max_size) {
			unsigned char *new_memory_area = new unsigned char[(max_size + 1024)*sizeof(unsigned char)];
			memcpy(new_memory_area, img, max_size);
			max_size = max_size + 1024;
			img = new_memory_area;
		}
		current_pixel = fgetc(source_file);
		current_location_in_file = ftell(source_file);
	}

	fflush(source_file);
	fclose(source_file);

	return img;
}


void writeResultInFile(char dest_path[], unsigned char *img, int size) {
	FILE *dest_file = NULL;
	dest_file = fopen(dest_path, "wb");
	if (dest_file == NULL) {
		perror("Error while opening the file");
	}

	int i = 0;
	while (i<size) {
		fputc(img[i], dest_file);
		i = i + 1;
	}

	fflush(dest_file);
	fclose(dest_file);
}

void addNoise(unsigned char *img) {

}

void asmMaxFilter(unsigned char *src, unsigned char *dest, unsigned int asize) {
	unsigned char *zeroes = new unsigned char[16];
	unsigned char *amask = new unsigned char[16];
	for (int i = 0; i < 15; ++i) {
		amask[i] = 255;
	}
	amask[15] = 0;
	dest = dest + 1024;

	for (int i = 0; i < 16; ++i) {
		zeroes[i] = 0;
	}

	_asm {
		mov esi, src;
		mov ecx, asize;
		mov edi, dest;
		mov eax, zeroes;
		movdqu xmm5, [eax];//xmm5 = previous last result in xmm5[0]
		mov ebx, 0;//ebx = modulo 1024 de edi
	l1:
		cmp ebx, 1022;
		jne suite;
		mov eax, zeroes;
		movdqu xmm5, [eax];
		mov ebx, 0;
		add esi, 2;
		add edi, 2;
		sub ecx, 2;
		cmp ecx, 2048;
		jle end;
	suite:	
		movdqu xmm0, [esi];
		movdqu xmm1, [esi+1024];
		movdqu xmm2, [esi+2048];
		pmaxub xmm0, xmm1;
		pmaxub xmm0, xmm2;
		movdqu xmm6, xmm0;
		movdqu xmm7, xmm0;
		psrldq xmm6, 1;
		psrldq xmm7, 2;
		pmaxub xmm6, xmm7;
		pmaxub xmm6, xmm0;
		pslldq xmm6, 1;
		por xmm6, xmm5;
		mov eax, amask;
		movdqu xmm4, [eax];
		pand xmm6, xmm4;
		movdqu [edi], xmm6;
		movdqu xmm5, xmm6;
		pslldq xmm5, 1;
		psrldq xmm5, 15;
		add edi, 14;
		add esi, 14;
		sub ecx, 14;
		add ebx, 14;
		cmp ecx, 2048;
		jg l1;
	end:

	}
}

void asmRun(char input_file[], char output_file[]) {
	unsigned int size = 0;
	unsigned char *src = NULL;

	src = parseImgFile(input_file, &size);

	unsigned char *dest = new unsigned char[size*sizeof(unsigned char)];
	for (int i = 0; i < size; ++i) {
		dest[i] = 0;
	}


	CounterStart = 0;
	StartCounter();

	asmMaxFilter(src, dest, size);

	double dt = GetCounter();
	printf("Computing time asm : %f\n", dt);
	int anumber = 0;
	scanf("%d", &anumber);

	writeResultInFile(output_file, dest, size);
}

void cRun(char input_file[], char output_file[]){
	unsigned int size = 0;
	unsigned char *src = NULL;

	src = parseImgFile(input_file, &size);

	unsigned char *dest = new unsigned char[size*sizeof(unsigned char)];

	CounterStart = 0;
	StartCounter();

	int i = 0;
	while (i < size){
		unsigned char max_neighbour = 0;
		int current_row = i / IMG_WIDTH, current_col = i % IMG_WIDTH;
		if (current_row != 0 && current_col != 0 && current_row != 1023 && current_col != 1023) {
			for (int row = -NEIGHBOUR_SIZE / 2; row <= NEIGHBOUR_SIZE / 2; ++row) {
				for (int col = -NEIGHBOUR_SIZE / 2; col <= NEIGHBOUR_SIZE / 2; ++col) {
					int row_index = (current_row + row);
					int col_index = (current_col + col);
					int index = row_index*IMG_WIDTH + col_index;
					if (0 <= row_index && row_index < IMG_HEIGHT &&
						0 <= col_index && col_index < IMG_WIDTH &&
						0 <= index && index < size &&
						src[index] > max_neighbour) {
						max_neighbour = src[index];
					}
				}
			}
		}

		dest[i] = max_neighbour;
		i = i + 1;
	}

	double dt = GetCounter();
	printf("Computing time c : %f\n", dt);
	int anumber = 0;
	scanf("%d", &anumber);

	writeResultInFile(output_file, dest, size);

	delete dest;
}

int main(int argc, char* argv[])
{
	char input_file[] = "C:/Users/arnaud/Documents/ma1-2014-2015/microprocessor/ELECH473/Labo8/Labo8/test.raw";
	char output_file[] = "C:/Users/arnaud/Documents/ma1-2014-2015/microprocessor/ELECH473/Labo8/Labo8/res-asm.raw";

	asmRun(input_file, output_file);

	return 0;
}

