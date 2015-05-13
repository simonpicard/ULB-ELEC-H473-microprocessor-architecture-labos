#include <windows.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <conio.h>
#include <process.h>


// Fonction provenant du labo 7
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

// Fonction provenant du labo 7
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

// Fonction provenant du labo 7
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
		movdqu[edi], xmm0;
		add edi, 16;
		add esi, 16;
		sub ecx, 16;
		cmp ecx, 0;
		jg l1;
	}
	delete anoffset;
	anoffset = NULL;
}

// Fonction provenant du labo 8
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
		movdqu xmm1, [esi + 1024];
		movdqu xmm2, [esi + 2048];
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
		movdqu[edi], xmm6;
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

struct Arg
{
	unsigned char *src;
	unsigned char *dest;
	unsigned char *th;
	unsigned int asize;
};

void asmImageThresholdThread(void *arg) {
	struct Arg *args = (struct Arg *) arg;
	printf("Thread %d begin and will process %d pixels\n", args->src, args->asize);
	asmImageThreshold(args->src, args->dest, args->th, args->asize);
	printf("Thread %d end\n", args->src);
}

void asmMaxFilterThread(void *arg) {
	struct Arg *args = (struct Arg *) arg;
	printf("Thread %d begin and will process %d pixels\n", args->src, args->asize);
	asmMaxFilter(args->src, args->dest, args->asize);
	printf("Thread %d end\n", args->src);
}

void asmThreadLauncherImageThreshold(char input_file[], char output_file[], unsigned char th, unsigned int threadNumber) {
	unsigned int size = 0;
	unsigned char *src = NULL;
	src = parseImgFile(input_file, &size);

	unsigned char *dest = new unsigned char[size*sizeof(unsigned char)];

	unsigned char *threshold = new unsigned char[16 * sizeof(unsigned char)];
	for (int i = 0; i < 16; ++i) {
		threshold[i] = th;
	}

	unsigned int numberOfPixelPerThread = size / threadNumber;
	unsigned int restOfPixels = size % threadNumber;

	HANDLE *threads = new HANDLE[threadNumber];
	struct Arg *args = new struct Arg[threadNumber];

	for (unsigned int i = 0; i < (threadNumber - 1); ++i) {
		args[i].src = src + (i*numberOfPixelPerThread);
		args[i].dest = dest + (i*numberOfPixelPerThread);
		args[i].th = threshold;
		args[i].asize = numberOfPixelPerThread;
		threads[i] = (HANDLE)_beginthread(asmImageThresholdThread, 0, args + i);
	}
	args[threadNumber - 1].src = src + ((threadNumber - 1)*numberOfPixelPerThread);
	args[threadNumber - 1].dest = dest + ((threadNumber - 1)*numberOfPixelPerThread);
	args[threadNumber - 1].th = threshold;
	args[threadNumber - 1].asize = numberOfPixelPerThread + restOfPixels;
	threads[threadNumber - 1] = (HANDLE)_beginthread(asmImageThresholdThread, 0, args + (threadNumber - 1));

	for (int i = 0; i < threadNumber; ++i) {
		WaitForSingleObject(threads[i], INFINITE);
	}

	writeResultInFile(output_file, dest, size);
}

void asmThreadLauncherMaxFilter(char input_file[], char output_file[], unsigned char th, unsigned int threadNumber) {
	unsigned int size = 0;
	unsigned char *src = NULL;
	src = parseImgFile(input_file, &size);

	unsigned char *dest = new unsigned char[size*sizeof(unsigned char)];

	unsigned char *threshold = new unsigned char[16 * sizeof(unsigned char)];
	for (int i = 0; i < 16; ++i) {
		threshold[i] = th;
	}

	unsigned int numberOfPixelPerThread = size / threadNumber;
	unsigned int restOfPixels = size % threadNumber;

	HANDLE *threads = new HANDLE[threadNumber];
	struct Arg *args = new struct Arg[threadNumber];

	for (unsigned int i = 0; i < (threadNumber - 1); ++i) {
		args[i].src = src + (i*numberOfPixelPerThread);
		args[i].dest = dest + (i*numberOfPixelPerThread);
		args[i].th = threshold;
		args[i].asize = numberOfPixelPerThread;
		threads[i] = (HANDLE)_beginthread(asmMaxFilterThread, 0, args + i);
	}
	args[threadNumber - 1].src = src + ((threadNumber - 1)*numberOfPixelPerThread);
	args[threadNumber - 1].dest = dest + ((threadNumber - 1)*numberOfPixelPerThread);
	args[threadNumber - 1].th = threshold;
	args[threadNumber - 1].asize = numberOfPixelPerThread+restOfPixels;
	threads[threadNumber - 1] = (HANDLE) _beginthread(asmMaxFilterThread, 0, args + (threadNumber - 1));

	for (int i = 0; i < threadNumber; ++i) {
		WaitForSingleObject(threads[i], INFINITE);
	}

	writeResultInFile(output_file, dest, size);
}


int main(int argc, char* argv[])
{
	char a;
	char input_file[] = "C:/Users/arnaud/Documents/ma1-2014-2015/microprocessor/ELECH473/Labo9/Labo9/test.raw";
	char output_file[] = "C:/Users/arnaud/Documents/ma1-2014-2015/microprocessor/ELECH473/Labo9/Labo9/res-asm.raw";

	asmThreadLauncherImageThreshold(input_file, output_file, 64, 4);
	scanf("%c", &a);
	return 0;
}

