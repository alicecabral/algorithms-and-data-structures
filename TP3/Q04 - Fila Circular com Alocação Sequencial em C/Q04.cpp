#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

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

Jogador array[MAXTAM+1];   // Elementos da fila 
int primeiro;          // Remove do indice "primeiro".
int ultimo;             // Insere no indice "ultimo".


/**
 * Inicializacoes
 */
void start(){
   primeiro = ultimo = 0;
}


/**
 * Insere um elemento na ultima posicao da 
 * @param x int elemento a ser inserido.
 * @Se a fila estiver cheia.
 */
void inserir(Jogador x) {

   //validar insercao
   if (((ultimo + 1) % MAXTAM) == primeiro) {
      printf("Erro ao inserir!");
      exit(1);
   }

   array[ultimo] = x;
   ultimo = (ultimo + 1) % MAXTAM;
}


/**
 * Remove um elemento da primeira posicao da fila e movimenta 
 * os demais elementos para o primeiro da mesma.
 * @return resp int elemento a ser removido.
 * @Se a fila estiver vazia.
 */
Jogador remover() {

   //validar remocao
   if (primeiro == ultimo) {
      printf("Erro ao remover!");
      exit(1);
   }

   Jogador resp = array[primeiro];
   primeiro = (primeiro + 1) % MAXTAM;
   return resp;
}


/**
 * Mostra os array separados por espacos.
 */
void mostrar (){
   for(int i = primeiro; i != ultimo; i = ((i + 1) % MAXTAM)) {
      printf("[%d] ## %s ## %i ## %i ## %i ## %s ## %s ## %s ##\n", i, array[i].nome, array[i].altura,
      array[i].peso, array[i].anoNascimento, array[i].universidade, array[i].cidadeNascimento,
      array[i].estadoNascimento);
   }
}


/**
 * Retorna um bool indicando se a fila esta vazia
 * @return bool indicando se a fila esta vazia
 */
bool isVazia() {
   return (primeiro == ultimo); 
}


int media(){
    int i = primeiro;
    float vezes = 0;
    int soma = 0;
    float media = 0;

    while(i != ultimo){
        soma = soma + array[i].altura;
        i = ((i+1) % MAXTAM);
        vezes++;
    }

    media = soma/vezes;
    return round(media);
}


int main(){
    char id[TAM];
    Jogador jogadores[3922];

    ler(jogadores);
    
    start();

    readLine(id, TAM, stdin);
    while(strstr(id, "FIM") == NULL){
        if(((ultimo+1) % MAXTAM) == primeiro){
            remover();
            inserir(jogadores[atoi(id)]);
        }
        else
            inserir(jogadores[atoi(id)]);
        
        printf("%d\n", media());
        
        readLine(id, TAM, stdin);
    }

    //mostrar();
    
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
        // printf ("\nstring[0]: %s", string[0]);
        // printf ("\nstring[1]: %s", string[1]);

        if(strcmp(string[0], "I") == 0){
            if(((ultimo+1) % MAXTAM) == primeiro){
                remover();
                inserir(jogadores[atoi(string[1])]);
            }
            else
                inserir(jogadores[atoi(string[1])]);
            
            printf("%d\n", media());
        }
        else if(strcmp(string[0], "R") == 0){
            printf("(R) %s\n", remover().nome);
        }
    }
    mostrar();
}
