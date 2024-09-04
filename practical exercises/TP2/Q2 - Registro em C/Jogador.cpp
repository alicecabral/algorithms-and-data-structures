#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define max 120
#define TAM 1000

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

struct Jogador{
    int id, altura, peso, anoNascimento;
    char nome[max], universidade[max], cidadeNascimento[max], estadoNascimento[max];
};
typedef struct Jogador Jogador;

// Construtor vazio
void jogador(Jogador jogador[]){
    jogador->id = jogador->altura = jogador->peso = jogador->anoNascimento = 0;
    strcpy(jogador->nome, "");
    strcpy(jogador->universidade, "");
    strcpy(jogador->cidadeNascimento, ""); 
    strcpy(jogador->estadoNascimento, "");
}

// Construtor que recebe parâmetros
void jogador(Jogador jogador[], int id, char nome[], int altura, int peso, char universidade[],
            int anoNascimento, char cidadeNascimento[], char estadoNascimento[]){
    jogador->id = id;
    strcpy(jogador->nome, nome);
    jogador->altura = altura;
    jogador->peso = peso;
    strcpy(jogador->universidade, universidade);
    jogador->anoNascimento = anoNascimento;
    strcpy(jogador->cidadeNascimento, cidadeNascimento);
    strcpy(jogador->estadoNascimento, estadoNascimento);
}

// Gets e sets
int getId(Jogador jogador[]){
    return jogador->id;
}

void setId(Jogador jogador[], int id){
    jogador->id = id;
}

char* getNome(Jogador jogador[]){
    return jogador->nome;
}

void setNome(Jogador jogador[], char nome[]){
    strcpy(jogador->nome, nome);
}

int getAltura(Jogador jogador[]){
    return jogador->altura;
}

void setAltura(Jogador jogador[], int altura){
    jogador->altura = altura;
}

int getPeso(Jogador jogador[]){
    return jogador->peso;
}

void setPeso(Jogador jogador[], int peso){
    jogador->peso = peso;
}

char* getUniversidade(Jogador jogador[]){
    return jogador->universidade;
}

void setUniversidade(Jogador jogador[], char universidade[]){
    strcpy(jogador->universidade, universidade);
}

int getAnoNascimento(Jogador jogador[]){
    return jogador->anoNascimento;
}

void setAnoNascimento(Jogador jogador[], int anoNascimento){
    jogador->anoNascimento = anoNascimento;
}

char* getCidadeNascimento(Jogador jogador[]){
    return jogador->cidadeNascimento;
}

void setCidadeNascimento(Jogador jogador[], char cidadeNascimento[]){
    strcpy(jogador->cidadeNascimento, cidadeNascimento);
}

char* getEstadoNascimento(Jogador jogador[]){
    return jogador->estadoNascimento;
}

void setEstadoNascimento(Jogador jogador[], char estadoNascimento[]){
    strcpy(jogador->estadoNascimento, estadoNascimento);
}

// Clonar
Jogador clone(Jogador jogador[]){
    Jogador jogadorClone;

    jogadorClone.id = jogador->id;
    strcpy(jogadorClone.nome, jogador->nome);
    jogadorClone.altura = jogador->altura;
    jogadorClone.peso = jogador->peso;
    strcpy(jogadorClone.universidade, jogador->universidade);
    jogadorClone.anoNascimento = jogador->anoNascimento;
    strcpy(jogadorClone.cidadeNascimento, jogador->cidadeNascimento);
    strcpy(jogadorClone.estadoNascimento, jogador->estadoNascimento);
    
    return jogadorClone;
}

void lerJogador(Jogador *jogador, char linha[]){
    char* str = strsep(&linha, ",");
    jogador->id = atoi(str);
    
    str = strsep(&linha, ",");
    strcpy(jogador->nome, str);
    
    str = strsep(&linha, ",");
    jogador->altura = atoi(str);
    
    str = strsep(&linha, ",");
    jogador->peso = atoi(str);
    
    str = strsep(&linha, ",");
    strcpy(jogador->universidade, strlen(str) == 0 ? "nao informado" : str);
    
    str = strsep(&linha, ",");
    jogador->anoNascimento = atoi(str);
    
    str = strsep(&linha, ",");
    strcpy(jogador->cidadeNascimento, strlen(str) == 0 ? "nao informado" : str);
    
    str = strsep(&linha, ",");
    strcpy(jogador->estadoNascimento, strlen(str) == 0 ? "nao informado" : str);
}

// Ler
void ler(Jogador jogador[]){
    char str[max];
    int i = 0;
    FILE *file = fopen("/tmp/players.csv", "r");
    fgets(str, sizeof(str), file); // Desconsiderar primeira linha do csv
    
    readLine(str, sizeof(str), file);

    while(!feof(file)){
        lerJogador(&jogador[i], str);
        readLine(str, sizeof(str), file);
        i++;
    }
    fclose(file);
}

// Imprimir
void imprimir(Jogador *jogador){
    printf("[%i ## %s ## %i ## %i ## %i ## %s ## %s ## %s]\n",
    getId(jogador), getNome(jogador), getAltura(jogador), getPeso(jogador), getAnoNascimento(jogador), 
    getUniversidade(jogador), getCidadeNascimento(jogador), getEstadoNascimento(jogador));
}

int main(){
    char palavra[TAM];
    Jogador jogador[3922];

    ler(jogador);

    readLine(palavra, TAM, stdin);
    while(strstr(palavra, "FIM") == NULL){
        imprimir(&jogador[atoi(palavra)]);
        readLine(palavra, TAM, stdin);
    }
}
