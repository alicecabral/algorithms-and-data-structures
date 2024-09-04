#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <err.h>

#define max 120
#define TAM 1000
#define MAXTAM    6
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

//TIPO CELULA ===================================================================
typedef struct Celula {
	Jogador elemento;        // Elemento inserido na celula.
	struct Celula* prox; // Aponta a celula prox.
} Celula;

Celula* novaCelula(Jogador elemento) {
   Celula* nova = (Celula*) malloc(sizeof(Celula));
   nova->elemento = elemento;
   nova->prox = NULL;
   return nova;
}

//PILHA PROPRIAMENTE DITA =======================================================
Celula* topo; // Sem celula cabeca.


/**
 * Cria uma fila sem elementos.
 */
void start () {
   topo = NULL;
}


/**
 * Insere elemento na pilha (politica FILO).
 * @param x int elemento a inserir.
 */
void inserir(Jogador x) {
   Celula* tmp = novaCelula(x);
   tmp->prox = topo;
   topo = tmp;
   tmp = NULL;
}

/**
 * Remove elemento da pilha (politica FILO).
 * @return Elemento removido.
 */
Jogador remover() {
   if (topo == NULL) {
      errx(1, "Erro ao remover!");
   }

   Jogador resp = topo->elemento;
   Celula* tmp = topo;
   topo = topo->prox;
   tmp->prox = NULL;
   free(tmp);
   tmp = NULL;
   return resp;
}

int a = 0;

void mostrarRecursivo(Celula* i){
    if(i != NULL){
        mostrarRecursivo(i->prox);      
        printf("[%d] ## %s ## %i ## %i ## %i ## %s ## %s ## %s ##\n", a, i->elemento.nome, i->elemento.altura,
        i->elemento.peso, i->elemento.anoNascimento, i->elemento.universidade, i->elemento.cidadeNascimento,
        i->elemento.estadoNascimento);
        a++;
    }
}

/**
 * Mostra os elementos separados por espacos, comecando do topo.
 */
void mostrar() {
    mostrarRecursivo(topo);
}

int main(){
    char id[TAM];
    Jogador jogadores[3922];

    ler(jogadores);
    
    start();

    readLine(id, TAM, stdin);
    while(strstr(id, "FIM") == NULL){
        inserir(jogadores[atoi(id)]);
        readLine(id, TAM, stdin);
    }

    int n;
    scanf("%d", &n);

    char *comando;
    char entrada[20];
    char string[10][20];
    int k = 0;

    for(int i = 0; i <= n; i++){
        k = 0;
        readLine(entrada, 20, stdin);
        //printf("%s\n", entrada);
        comando = strtok(entrada, " ");

        while(comando != NULL){
            strcpy(string[k], comando);
            comando = strtok(NULL, " ");
            k++;
        }
        //printf ("\nstring[0]: %s", string[0]);
        //printf ("\nstring[1]: %s", string[1]);

        if(strcmp(string[0], "I") == 0){
            inserir(jogadores[atoi(string[1])]);
        }
        else if(strcmp(string[0], "R") == 0){
            printf("(R) %s\n", remover().nome);
        }
    }
    mostrar();
}