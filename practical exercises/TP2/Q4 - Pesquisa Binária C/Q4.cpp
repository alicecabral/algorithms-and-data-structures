#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#define max 120
#define TAM 1000

int comp = 0;

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
void jogador(Jogador *jogadores){
    jogadores->id = jogadores->altura = jogadores->peso = jogadores->anoNascimento = 0;
    strcpy(jogadores->nome, "");
    strcpy(jogadores->universidade, "");
    strcpy(jogadores->cidadeNascimento, ""); 
    strcpy(jogadores->estadoNascimento, "");
}

// Construtor que recebe parâmetros
void jogador(Jogador *jogadores, int id, char nome[], int altura, int peso, char universidade[],
            int anoNascimento, char cidadeNascimento[], char estadoNascimento[]){
    jogadores->id = id;
    strcpy(jogadores->nome, nome);
    jogadores->altura = altura;
    jogadores->peso = peso;
    strcpy(jogadores->universidade, universidade);
    jogadores->anoNascimento = anoNascimento;
    strcpy(jogadores->cidadeNascimento, cidadeNascimento);
    strcpy(jogadores->estadoNascimento, estadoNascimento);
}

// Gets e sets
int getId(Jogador *jogadores){
    return jogadores->id;
}

void setId(Jogador *jogadores, int id){
    jogadores->id = id;
}

char* getNome(Jogador *jogadores){
    return jogadores->nome;
}

void setNome(Jogador *jogadores, char nome[]){
    strcpy(jogadores->nome, nome);
}

int getAltura(Jogador *jogadores){
    return jogadores->altura;
}

void setAltura(Jogador *jogadores, int altura){
    jogadores->altura = altura;
}

int getPeso(Jogador *jogadores){
    return jogadores->peso;
}

void setPeso(Jogador *jogadores, int peso){
    jogadores->peso = peso;
}

char* getUniversidade(Jogador *jogadores){
    return jogadores->universidade;
}

void setUniversidade(Jogador *jogadores, char universidade[]){
    strcpy(jogadores->universidade, universidade);
}

int getAnoNascimento(Jogador *jogadores){
    return jogadores->anoNascimento;
}

void setAnoNascimento(Jogador *jogadores, int anoNascimento){
    jogadores->anoNascimento = anoNascimento;
}

char* getCidadeNascimento(Jogador *jogadores){
    return jogadores->cidadeNascimento;
}

void setCidadeNascimento(Jogador *jogadores, char cidadeNascimento[]){
    strcpy(jogadores->cidadeNascimento, cidadeNascimento);
}

char* getEstadoNascimento(Jogador *jogadores){
    return jogadores->estadoNascimento;
}

void setEstadoNascimento(Jogador *jogadores, char estadoNascimento[]){
    strcpy(jogadores->estadoNascimento, estadoNascimento);
}

// Clonar
Jogador clone(Jogador *jogadores){
    Jogador jogadorClone;

    jogadorClone.id = jogadores->id;
    strcpy(jogadorClone.nome, jogadores->nome);
    jogadorClone.altura = jogadores->altura;
    jogadorClone.peso = jogadores->peso;
    strcpy(jogadorClone.universidade, jogadores->universidade);
    jogadorClone.anoNascimento = jogadores->anoNascimento;
    strcpy(jogadorClone.cidadeNascimento, jogadores->cidadeNascimento);
    strcpy(jogadorClone.estadoNascimento, jogadores->estadoNascimento);
    
    return jogadorClone;
}

void lerJogador(Jogador *jogadores, char linha[]){
    char* str = strsep(&linha, ",");
    jogadores->id = atoi(str);
    
    str = strsep(&linha, ",");
    strcpy(jogadores->nome, str);
    
    str = strsep(&linha, ",");
    jogadores->altura = atoi(str);
    
    str = strsep(&linha, ",");
    jogadores->peso = atoi(str);
    
    str = strsep(&linha, ",");
    strcpy(jogadores->universidade, strlen(str) == 0 ? "nao informado" : str);
    
    str = strsep(&linha, ",");
    jogadores->anoNascimento = atoi(str);
    
    str = strsep(&linha, ",");
    strcpy(jogadores->cidadeNascimento, strlen(str) == 0 ? "nao informado" : str);
    
    str = strsep(&linha, ",");
    strcpy(jogadores->estadoNascimento, strlen(str) == 0 ? "nao informado" : str);
}

// Ler
void ler(Jogador *jogadores){
    char linha[max];
    int i = 0;
    FILE *file = fopen("/tmp/players.csv", "r");
    fgets(linha, sizeof(linha), file); // Desconsiderar primeira linha do csv
    
    readLine(linha, sizeof(linha), file);

    while(!feof(file)){
        lerJogador(&jogadores[i], linha);
        readLine(linha, sizeof(linha), file);
        i++;
    }
    fclose(file);
}


void ordenar(Jogador *array, int n) {
    for (int i = 1; i < n; i++) {
        Jogador tmp = array[i];
        int j = i - 1;

        comp++;
        while ( (j >= 0) && (strcmp(array[j].nome, tmp.nome) > 0)){
            array[j + 1] = array[j];
            j--;
        }
        array[j + 1] = tmp;
    }
}

void pesquisaBinaria(Jogador *ids, char *nome, int n){
    int resp = 0;
    int dir = n - 1, esq = 0, meio;

    while (esq <= dir) {
        meio = (esq + dir) / 2;
    comp+=2;
    if (strcmp(nome, ids[meio].nome) == 0){
        resp = 1;
        esq = n;
    } else if (strcmp(nome, ids[meio].nome) > 0){
        esq = meio + 1;
    } else {
        dir = meio - 1;
    } 
    }
    
    if(resp == 0)
        printf("NAO\n");
    else
        printf("SIM\n");
}

int main(){
    Jogador ids[TAM];
    char entrada[TAM];
    int numIds = 0;
    Jogador jogadores[3922];

    ler(jogadores);

    readLine(entrada, TAM, stdin);
    while(strstr(entrada, "FIM") == NULL){
        ids[numIds] = jogadores[atoi(entrada)];
        readLine(entrada, TAM, stdin);
        numIds++;
    }

    clock_t inicio, fim;
    inicio = clock();

    ordenar(ids, numIds);

    readLine(entrada, TAM, stdin);
    while(strstr(entrada, "FIM") == NULL){
        pesquisaBinaria(ids, entrada, numIds);
        readLine(entrada, TAM, stdin);
    }

    fim = clock();

    
    FILE *file;
    file = fopen("589690_binaria.txt", "w");
    fprintf(file, "Matricula: 589690\tTempo de Execução: %fs\tComparações: %d",
    ((fim-inicio)/(double)CLOCKS_PER_SEC), comp);
    fclose(file);

}
