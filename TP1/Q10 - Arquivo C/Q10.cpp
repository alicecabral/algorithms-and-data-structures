#include <stdio.h>

int main(){
    int n;
    float valor;
    FILE *file = fopen("arquivo", "wb"); // Abrindo o arquivo

    scanf("%d", &n);

    for(int i = 0; i < n; i++){ // Lendo e salvando os valores no arquivo
        scanf("%f", &valor); 
        fwrite(&valor, sizeof(float), 1, file);
    }

    long tamArquivo = ftell(file); // Variável para guardar o tamanho do arquivo
    fclose(file); 

    file = fopen("arquivo", "rb");

    for(long offset = tamArquivo - sizeof(float); offset >= 0; offset -= sizeof(float)){ // Pegando os valores de trás para frente
        fseek(file, offset, SEEK_SET);
        fread(&valor, sizeof(float), 1, file);
        printf("%g\n", valor);   
    }

    fclose(file);
}