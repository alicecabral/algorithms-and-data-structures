#include <stdio.h>
#include <string.h>

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

int isVogal(char s[]){
    int resp = 1;
    int j = strlen(s);

    for(int i = 0; i < j; i++){
        if(s[i] == 'a' || s[i] == 'e' ||
           s[i] == 'i' || s[i] == 'o' ||
           s[i] == 'u' || s[i] == 'A' ||
           s[i] == 'E' || s[i] == 'I' ||
           s[i] == 'O' || s[i] == 'U')
            resp = 1;
        else{
            resp = 0;
            i = j;
        }           
    }
    return resp;
}

int isConsoante(char s[]){
    int resp = 1;
    int j = strlen(s);

    for(int i = 0; i < j; i++){
        if((s[i] >= 48 && s[i] <= 57) ||
            s[i] == 'a' || s[i] == 'e' ||
            s[i] == 'i' || s[i] == 'o' ||
            s[i] == 'u' || s[i] == 'A' ||
            s[i] == 'E' || s[i] == 'I' ||
            s[i] == 'O' || s[i] == 'U'){
            resp = 0;
            i = j;
        }
        else
            resp = 1;
    }
    return resp;
}

int isInteiro(char s[]){
    int resp = 1;
    int j = strlen(s);

    for(int i = 0; i < j; i++){
        if(s[i] >= 48 && s[i] <= 57)
            resp = true;
        else{
            resp = false;
            i = j;
        }
    }
    return resp;   
}

int isReal(char s[]){
    int resp = 1;
    int j = strlen(s);
    int cont = 0;

    for(int i = 0; i < j; i++){ // Verificando se tem . ou , e se tem mais de um
        if(s[i] == '.' || s[i] == ','){
            resp = 1;
            cont++;
        }
    }

    for(int i = 0; i < j; i++){
        if((s[i] >= 65 && s[i] <= 90) || (s[i] >= 97 && s[i] <= 122))
            resp = 0;
    }

    if(cont > 1)
        resp = 0;

    return resp;    
}

int main(){
    char entrada[1024];
    int numEntrada = 0;

    readLine(entrada, 1024, stdin);
    while(strstr(entrada, "FIM") == NULL){
        printf("%s ", isVogal(entrada) ? "SIM" : "NAO");
        printf("%s ", isConsoante(entrada) ? "SIM" : "NAO");
        printf("%s ", isInteiro(entrada) ? "SIM" : "NAO");
        printf("%s\n", isReal(entrada) ? "SIM" : "NAO");
        readLine(entrada, 1024, stdin);
    }
}
