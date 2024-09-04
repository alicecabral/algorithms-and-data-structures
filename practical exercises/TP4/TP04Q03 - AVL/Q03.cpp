#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <err.h>
#include <math.h>
#include <time.h>
#define max 120
#define TAM 1000
#define bool   short
#define true   1
#define false  0
#define MAXTAM 4000
#define TAM_LINHA 500

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

typedef struct No {
    Jogador elemento;
    int nivel; 
    struct No *esq, *dir;
} No;

No *novoNo(Jogador elemento) {
    No *novo = (No *)malloc(sizeof(No));
    novo->elemento = elemento;
    novo->esq = NULL;
    novo->dir = NULL;
    return novo;
}

/*
 * Variavel global
 */
No* raiz;

/**
 * Criar arvore binaria.
 */
void start() {
   raiz = NULL;
}

int getNivel(No *no){
    return (no == NULL) ? 0 : no->nivel;
}

void setNivel(No *no){
    no->nivel = 1 + fmax(getNivel(no->esq), getNivel(no->dir));
}

No *rotacionarDir(No *no){
    No *noEsq = no->esq;
    No *noEsqDir = noEsq->dir;

    noEsq->dir = no;
    no->esq = noEsqDir;
    setNivel(no);
    setNivel(noEsq);
    return noEsq;
}

No *rotacionarEsq(No *no){
    No *noDir = no->dir;
    No *noDirEsq = noDir->esq;

    noDir->esq = no;
    no->dir = noDirEsq;
    setNivel(no);
    setNivel(noDir);
    return noDir;
}

No *balanceamento(No *no){
    if (no != NULL){
        int fator = getNivel(no->dir) - getNivel(no->esq);
        //Se balanceada
        if (abs((long)fator) <= 1){
            setNivel(no);
            //Se desbalanceada para a direita
        }
        else if (fator == 2){
            int fatorFilhoDir = getNivel(no->dir->dir) - getNivel(no->dir->esq);
            //Se o filho a direita tambem estiver desbalanceado
            if (fatorFilhoDir == -1){
                no->dir = rotacionarDir(no->dir);
            }
            no = rotacionarEsq(no);
            //Se desbalanceada para a esquerda
        }
        else if (fator == -2){
            int fatorFilhoEsq = getNivel(no->esq->dir) - getNivel(no->esq->esq);
            //Se o filho a esquerda tambem estiver desbalanceado
            if (fatorFilhoEsq == 1){
                no->esq = rotacionarEsq(no->esq);
            }
            no = rotacionarDir(no);
        }
    }
    return no;
}

/**
 * Metodo privado recursivo para pesquisar elemento.
 * @param x Elemento que sera procurado.
 * @param i No em analise.
 * @return <code>true</code> se o elemento existir,
 * <code>false</code> em caso contrario.
 */
bool pesquisarRec(char* x, No* i) {
    bool resp;
    if (i == NULL) {
        resp = false;
    } else if (strcmp(x, i->elemento.nome) == 0) {
        resp = true;
    } else if (strcmp(x, i->elemento.nome) < 0) {
        printf(" esq");
        resp = pesquisarRec(x, i->esq);
    } else {
        printf(" dir");
        resp = pesquisarRec(x, i->dir);
    }
    return resp;
}

/**
 * Metodo publico iterativo para pesquisar elemento.
 * @param x Elemento que sera procurado.
 * @return <code>true</code> se o elemento existir,
 * <code>false</code> em caso contrario.
 */
bool pesquisar(char* x) {
   return pesquisarRec(x, raiz);
}

/**
 * Metodo privado recursivo para inserir elemento.
 * @param x Elemento a ser inserido.
 * @param i No** endereco do ponteiro No
 */
void inserirRec(Jogador x, No** i) {
    if (*i == NULL) {
        *i = novoNo(x);
    } else if (strcmp(x.nome, (*i)->elemento.nome) < 0) {
        inserirRec(x, &((*i)->esq));
    } else if (strcmp(x.nome, (*i)->elemento.nome) > 0) {
        inserirRec(x, &((*i)->dir));
    } else {
        errx(1, "Erro ao inserir!");
    }
    *i = balanceamento(*i);
}

/**
 * Metodo publico iterativo para inserir elemento.
 * @param x Elemento a ser inserido.
 */
void inserir(Jogador x) {
   inserirRec(x, &raiz);
}

int comp = 0;

int main(){
    char entrada[TAM];
    int numEntrada = 0;
    Jogador jogadores[3922];
    Jogador jogadores2[TAM];

    clock_t inicio, fim;
    inicio = clock();

    ler(jogadores);

    start();

    readLine(entrada, TAM, stdin);
    while(strstr(entrada, "FIM") == NULL){
        inserir(jogadores[atoi(entrada)]);
        readLine(entrada, TAM, stdin);
        numEntrada++;
    }

    char *nomes = (char *)malloc(100 * sizeof(char));
    int numNomes = 0;

    readLine(nomes, TAM, stdin);
    while(strstr(nomes, "FIM") == NULL){
        printf("%s raiz", nomes);
        if (pesquisar(nomes)){
            printf(" SIM\n");
        }
        else{
            printf(" NAO\n");
        }
        readLine(nomes, TAM, stdin);
        numNomes++;
    }

    fim = clock();

    FILE *file;
    file = fopen("589690_heapsortParcial.txt", "w");
    fprintf(file, "Matricula: 589690\tTempo de Execução: %fs\tComparações: %d",
    ((fim-inicio)/(double)CLOCKS_PER_SEC), comp);
    fclose(file);

}