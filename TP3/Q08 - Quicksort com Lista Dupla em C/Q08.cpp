#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <err.h>
#include <time.h>
#include <limits.h>

//#ifndef LISTADUPLA_H
#define LISTADUPLA_H

#define max 120
#define TAM 1000
#define MAXTAM    1000
#define bool      short
#define true      1
#define false     0

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

Jogador jogadores[3922];

//TIPO CELULA ===================================================================
typedef struct Celula{
    Jogador elemento;
    struct Celula *prox;
}Celula;
//=============================================================================
typedef struct CelulaDupla
{
    Jogador elemento;         // Elemento inserido na celula.
    struct CelulaDupla *prox; // Aponta a celula prox.
    struct CelulaDupla *ant;  // Aponta a celula anterior.
} CelulaDupla;

CelulaDupla *novaCelulaDupla(Jogador elemento)
{
    CelulaDupla *nova = (CelulaDupla *)malloc(sizeof(CelulaDupla));
    nova->elemento = elemento;
    nova->ant = nova->prox = NULL;
    return nova;
}

/* -------------- Estrutura de Lista -------------- */
CelulaDupla *primeiro;
CelulaDupla *ultimo;

//Cria uma lista dupla sem elementos (somente no cabeca).
void start(Jogador j)
{
    primeiro = novaCelulaDupla(j);
    ultimo = primeiro;
}

//Insere um elemento na ultima posicao da lista.
void inserirFim(Jogador x)
{
    ultimo->prox = novaCelulaDupla(x);
    ultimo->prox->ant = ultimo;
    ultimo = ultimo->prox;
}

//Calcula e retorna o tamanho, em numero de elementos, da lista.
int tamanho()
{
    int tamanho = 0;
    CelulaDupla *i;
    for (i = primeiro; i != ultimo; i = i->prox, tamanho++)
        ;
    return tamanho;
}

//Mostra os elementos da lista separados por espacos.
void mostrar()
{
    CelulaDupla *i;
    for (i = primeiro; i != NULL; i = i->prox)
    {
        printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n", i->elemento.id, i->elemento.nome,
               i->elemento.altura, i->elemento.peso, i->elemento.anoNascimento, i->elemento.universidade,
               i->elemento.cidadeNascimento, i->elemento.estadoNascimento);
    }
}

//======== QUICKSORT ================================================

void swap(CelulaDupla *i, CelulaDupla *j)
{
   Jogador tmp = i->elemento;
   i->elemento = j->elemento;
   j->elemento = tmp;
}

void quicksortRec(CelulaDupla *celulaEsq, CelulaDupla *celulaDir, int esq, int dir) {
        int i = esq;
        int j = dir;

        CelulaDupla *esquerda = celulaEsq;
        CelulaDupla *direita = celulaDir;

        int meio = (esq+dir)/2;

        CelulaDupla *tmp = primeiro;
        for (int i = 0; i != meio; i++, tmp = tmp->prox);
        Jogador pivo = tmp->elemento;

        while (i <= j) {
            while ((strcmp(esquerda->elemento.estadoNascimento, pivo.estadoNascimento)) < 0 || 
            ((strcmp(esquerda->elemento.estadoNascimento,pivo.estadoNascimento) == 0) && 
            (strcmp(esquerda->elemento.nome, pivo.nome) < 0))){ 
                if(!strcmp(esquerda->elemento.estadoNascimento, pivo.estadoNascimento) > 0);
                i++;
                esquerda = esquerda->prox;
            }   
            while ((strcmp(direita->elemento.estadoNascimento, pivo.estadoNascimento)) > 0 || 
            ((strcmp(direita->elemento.estadoNascimento,pivo.estadoNascimento) == 0) && 
            (strcmp(direita->elemento.nome, pivo.nome) > 0))){
                if(!strcmp(direita->elemento.estadoNascimento, pivo.estadoNascimento) > 0);
                j--;
                direita = direita->ant;
            }
            if (i <= j) {
                swap(esquerda, direita); 
                i++;
                j--;
                esquerda = esquerda->prox;
                direita = direita->ant;
            }
        }

        if (esq < j) {
            quicksortRec(celulaEsq, direita, esq, j);
        }
        if (i < dir) {
            quicksortRec(esquerda, celulaDir, i, dir);
        }
}

int main(){
    char id[TAM];
    int numEntrada = 0;

    ler(jogadores);

    readLine(id, TAM, stdin);
    while(strstr(id, "FIM") == NULL){
        if(numEntrada == 0)
            start(jogadores[atoi(id)]);
        else
        inserirFim(jogadores[atoi(id)]);
    readLine(id, TAM, stdin);
    numEntrada++;
    }

    clock_t inicio, fim;
    inicio = clock();

    quicksortRec(primeiro, ultimo, 0, tamanho());

    fim = clock();

    mostrar();

}