#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#define max 120
#define TAM 1000

int comp = 0, mov = 0;

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

// Imprimir
void imprimir(Jogador *jogadores){
    printf("[%i ## %s ## %i ## %i ## %i ## %s ## %s ## %s]\n",
    getId(jogadores), getNome(jogadores), getAltura(jogadores), getPeso(jogadores), getAnoNascimento(jogadores), 
    getUniversidade(jogadores), getCidadeNascimento(jogadores), getEstadoNascimento(jogadores));
}

void insercaoParcial(Jogador *array, int n) {
    for (int i = 1; i < n; i++) {
        Jogador tmp = array[i];
        mov++;
        int j = (i < 10) ? i - 1 : 10 - 1;

        comp+=3;
        while ( (j >= 0) && (array[j].anoNascimento > tmp.anoNascimento ||
         array[j].anoNascimento == tmp.anoNascimento &&
         strcmp(array[j].nome, tmp.nome) > 0)){
            array[j + 1] = array[j];
            mov++;
            j--;
        }
        array[j + 1] = tmp;
        mov++;
    }
}

int main(){
    char entrada[TAM];
    int numEntrada = 0;
    Jogador jogadores[3922];
    Jogador jogadores2[TAM];

    ler(jogadores);

    readLine(entrada, TAM, stdin);
    while(strstr(entrada, "FIM") == NULL){
        jogadores2[numEntrada] = jogadores[atoi(entrada)];
        readLine(entrada, TAM, stdin);
        numEntrada++;
    }
    
    clock_t inicio, fim;
    inicio = clock();

    insercaoParcial(jogadores2, numEntrada);

    fim = clock();

    for(int i = 0; i < 10; i++){
        printf("[%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s]\n",
        jogadores2[i].id, jogadores2[i].nome, jogadores2[i].altura, jogadores2[i].peso, jogadores2[i].anoNascimento, 
        jogadores2[i].universidade, jogadores2[i].cidadeNascimento, jogadores2[i].estadoNascimento);
    }    

    FILE *file;
    file = fopen("589690_insercaoParcial.txt", "w");
    fprintf(file, "Matricula: 589690\tTempo de Execução: %fs\tComparações: %d\tMovimentações: %d",
    ((fim-inicio)/(double)CLOCKS_PER_SEC), comp, mov);
    fclose(file);

}
