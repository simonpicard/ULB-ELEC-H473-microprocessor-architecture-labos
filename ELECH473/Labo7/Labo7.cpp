#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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

void asmImageThreshold(unsigned char *src, unsigned char *dest, unsigned char *th, unsigned int asize) {

	unsigned char aVal = 128;
	unsigned char *anoffset = new unsigned char[16];
	for (int i = 0; i < 16; ++i) {
		anoffset[i] = aVal;
	}

	_asm {
		mov esi, src;
		mov edi, dest;
		mov eax, th;
		mov ecx, asize;
		mov edx, anoffset;
		movdqu xmm6, [edx];
		movdqu xmm7, [eax];
		paddb xmm7, xmm6;
	l1:
		movdqu xmm0, [esi];
		paddb xmm0, xmm6;
		pcmpgtb xmm0, xmm7;
		movdqu [edi], xmm0;
		add edi, 16;
		add esi, 16;
		sub ecx, 16;
		cmp ecx, 0;
		jg l1;
	}
	delete anoffset;
	anoffset = NULL;
}

void asmRun(char input_file[], char output_file[], unsigned char th) {
	unsigned int size = 0;
	unsigned char *src = NULL;
	src = parseImgFile(input_file, &size);

	unsigned char *dest = new unsigned char[size*sizeof(unsigned char)];

	unsigned char *threshold = new unsigned char[16 * sizeof(unsigned char)];
	for (int i = 0; i < 16; ++i) {
		threshold[i] = th;
	}

	time_t start_time, end_time;
	float dt;
	start_time = clock();

	asmImageThreshold(src, dest, threshold, size);

	end_time = clock();
	dt = (end_time - start_time) / (float)(CLOCKS_PER_SEC);
	printf("Computing time %f\n", dt);
	int anumber = 0;
	scanf("%d", &anumber);

	writeResultInFile(output_file, dest, size);
}


void cImageThreshold(char source_path[], char dest_path[], unsigned char th){
	unsigned int size = 0;
	unsigned char *img = NULL;

	img = parseImgFile(source_path, &size);

	time_t start_time, end_time;
	float dt;
	start_time = clock();

	unsigned int i = 0;
	while (i<size) {
		if (img[i] > th)
			img[i] = 255;
		else
			img[i] = 0;
		i = i + 1;
	}

	end_time = clock();
	dt = (end_time - start_time) / (float)(CLOCKS_PER_SEC);
	printf("Computing time %f\n", dt);
	int anumber = 0;
	scanf("%d", &anumber);

	writeResultInFile(dest_path, img, size);
	delete img;
	img = NULL;
}

void cRun(char input_file[], char output_file[], unsigned char th){
	cImageThreshold(input_file, output_file, th);
}



int main(int argc, char* argv[])
{
	char input_file[] = "C:/Users/arnaud/Documents/ma1-2014-2015/microprocessor/ELECH473/Labo7/Labo7/test.raw";
	char output_file[] = "C:/Users/arnaud/Documents/ma1-2014-2015/microprocessor/ELECH473/Labo7/Labo7/res-c.raw";
	
	cRun(input_file, output_file, 200);

	return 0;
}
