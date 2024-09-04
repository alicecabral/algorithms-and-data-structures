import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

class Jogador {
    private int id, altura, peso, anoNascimento;
    private String nome, universidade, cidadeNascimento, estadoNascimento;

    // Construtor vazio
    public Jogador(){
        this.id = this.altura = this.peso = this.anoNascimento = 0;
        this.nome = this.universidade = this.cidadeNascimento = this.estadoNascimento = "";
    }

    // Construtor que recebe parâmetros
    public Jogador(int id, String nome, int altura, int peso, String universidade,
                   int anoNascimento, String cidadeNascimento, String estadoNascimento){
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
    }

    // Gets e sets
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public int getAltura(){
        return altura;
    }

    public void setAltura(int altura){
        this.altura = altura;
    }

    public int getPeso(){
        return peso;
    }

    public void setPeso(int peso){
        this.peso = peso;
    }

    public String getUniversidade(){
        return universidade;
    }

    public void setUniversidade(String universidade){
        this.universidade = universidade;
    }

    public int getAnoNascimento(){
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento){
        this.anoNascimento = anoNascimento;
    }

    public String getCidadeNascimento(){
        return cidadeNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento){
            this.cidadeNascimento = cidadeNascimento;
    }

    public String getEstadoNascimento(){
        return estadoNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento){
            this.estadoNascimento = estadoNascimento;
    }

    // Ler
    public static void ler(Jogador jogadores[]){
        try { 
            BufferedReader br = new BufferedReader(new FileReader("players.csv")); 
            String str = br.readLine(); 
            int i = 0;
            
            while ((str = br.readLine()) != null) { 
                jogadores[i] = new Jogador();
                str = str.replace(",,", ",nao informado,");
                if(str.charAt(str.length()-1) == ',')
                    str = str + "nao informado";
                String[] array = str.split(",");

                jogadores[i].setId(Integer.parseInt(array[0]));
                jogadores[i].setNome(array[1]);
                jogadores[i].setAltura(Integer.parseInt(array[2]));
                jogadores[i].setPeso(Integer.parseInt(array[3]));
                jogadores[i].setUniversidade(array[4]);
                jogadores[i].setAnoNascimento(Integer.parseInt(array[5]));
                jogadores[i].setCidadeNascimento(array[6]);
                jogadores[i].setEstadoNascimento(array[7]);
                i++;
            }
            br.close(); 
        } 
        catch (IOException e) { 
            System.out.println("File Read Error"); 
        } 
    }

    // Imprimir
    public void imprimir(){
        System.out.println("[" + getId() + " ## " + getNome() + " ## " + getAltura() + 
        " ## " + getPeso() + " ## " + getAnoNascimento() + " ## " +  getUniversidade() + 
        " ## " + getCidadeNascimento() + " ## " + getEstadoNascimento() + "]");
    }
}

class No {
    public char elemento;
    public int tamanho = 255;
    public No[] prox;
    public boolean folha;
    
    public No (){
       this(' ');
    }
 
    public No (char elemento){
       this.elemento = elemento;
       prox = new No [tamanho];
       for (int i = 0; i < tamanho; i++) prox[i] = null;
       folha = false;
    }
 
    public static int hash (char x){
       return (int)x;
    }
}
 
class ArvoreTrie {
    private No raiz;

    public ArvoreTrie(){
        raiz = new No();
    }

    public void inserir(String s) throws Exception {
        inserir(s, raiz, 0);
    }

    private void inserir(String s, No no, int i) throws Exception {
        //System.out.print("\nEM NO(" + no.elemento + ") (" + i + ")");
        if(no.prox[s.charAt(i)] == null){
            //System.out.print("--> criando filho(" + s.charAt(i) + ")");
            no.prox[s.charAt(i)] = new No(s.charAt(i));

            if(i == s.length() - 1){
                //System.out.print("(folha)");
                no.prox[s.charAt(i)].folha = true;
            }else{
                inserir(s, no.prox[s.charAt(i)], i + 1);
            }

        } else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1){
            inserir(s, no.prox[s.charAt(i)], i + 1);

        } else {
            throw new Exception("Erro ao inserir!");
        } 
    }


    public boolean pesquisar(String s) throws Exception {
        return pesquisar(s, raiz, 0);
    }

    public boolean pesquisar(String s, No no, int i) throws Exception {
        boolean resp;
        if(no.prox[s.charAt(i)] == null){
            resp = false;
        } else if(i == s.length() - 1){
            resp = (no.prox[s.charAt(i)].folha == true);
        } else if(i < s.length() - 1 ){
            resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
        } else {
            throw new Exception("Erro ao pesquisar!");
        }
        return resp;
    }


    public void mostrar(){
        mostrar("", raiz);
    }

    public void mostrar(String s, No no) {
        if(no.folha == true){
            System.out.println("Palavra: " + (s + no.elemento));
        } else {
            for(int i = 0; i < no.prox.length; i++){
                if(no.prox[i] != null){
                    //System.out.println("ESTOU EM (" + no.elemento + ") E VOU PARA (" + no.prox[i].elemento + ")");
                    mostrar(s + no.elemento, no.prox[i]);
                }
            }
        }
    }
}

public class Q06{
    public static int comp;

    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main (String[] args){
        Jogador jogadores[] = new Jogador[3922];
        ArvoreTrie arvoreJogadores1 = new ArvoreTrie();
        long time = System.currentTimeMillis();

        Jogador.ler(jogadores);

        // Criação da primeira árvore com os jogadores lidos da entrada
        String id;
        do {
            id = MyIO.readLine();
            if (!isFim(id)){
                try {
                    System.out.println(id + "\t" + jogadores[Integer.parseInt(id)].getNome());
                    arvoreJogadores1.inserir(jogadores[Integer.parseInt(id)].getNome());
                }
                catch (Exception e) {
                    System.out.println("ERRO1");
                }
            }
        } while (!isFim(id));

        // Criação da segunda árvore com os jogadores lidos da entrada
        String id2;
        do {
            id2 = MyIO.readLine();
            if (!isFim(id2)){
                try {
                    System.out.println(id2 + "\t" + jogadores[Integer.parseInt(id2)].getNome());
                    arvoreJogadores1.inserir(jogadores[Integer.parseInt(id2)].getNome());
                }
                catch (Exception e) {
                    System.out.println("ERRO2");
                }
            }
        } while (!isFim(id2));

        // Leitura e pesquisa dos nomes lidos da entrada
        String nome;
        do {
            nome = MyIO.readLine();
            if (!isFim(nome)){
                try {
                    System.out.print(nome);
                    if (arvoreJogadores1.pesquisar(nome) == true)
                        System.out.println(" SIM");
                    else
                        System.out.println(" NAO");
                }
                catch (Exception e) {
                    System.out.println("ERRO3");
                }
            }
        } while (!isFim(nome));

        Arq.openWrite("589690_arvoreTrie.txt");

        Arq.println("Matricula: 589690\tTempo de execução: " + (System.currentTimeMillis() - time) + 
        "ms" + "\tComparações: " + comp);

        Arq.close();
    }
}