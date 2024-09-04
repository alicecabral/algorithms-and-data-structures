#include <stdio.h>
#include <string.h>
#define TAM 500

// Procedimento para substituir os caracteres finais por \0
void replaceChar(char toSearch, char toReplace, char* str){
    for(int i = 0; i < strlen(str); i++){
        if(str[i] == toSearch)
            str[i] = toReplace;
    }
}

// Procedimento para leitura
void readLine(char* str, int maxSize, FILE* file){
    fgets(str, maxSize, file);
    replaceChar('\r', '\0', str);
    replaceChar('\n', '\0', str);
}

int isPalindromo(char* str){
    int is = 1;

    for(int i = 0, j = strlen(str)-1; i < j && is; i++, j--)
        is = str[i] == str[j];

    return is;
}

int main(){
    char palavra[TAM];

    readLine(palavra, TAM, stdin);
    while(strstr(palavra, "FIM") == NULL){
        printf("%s\n", isPalindromo(palavra) ? "SIM" : "NAO");
        readLine(palavra, TAM, stdin);
    }
}



