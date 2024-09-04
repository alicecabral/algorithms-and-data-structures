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

    //Clonar
    public Jogador clone(){
        return new Jogador(id, nome, altura, peso, universidade, anoNascimento,
                           cidadeNascimento, estadoNascimento);
    }

    // Ler
    public static void ler(Jogador jogadores[]){
        try { 
            BufferedReader br = new BufferedReader(new FileReader("/tmp/players.csv")); 
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
            System.out.println("Erro na leitura do arquivo"); 
        } 
    }

    // Imprimir
    public void imprimir(){
        System.out.println("[" + getId() + " ## " + getNome() + " ## " + getAltura() + 
        " ## " + getPeso() + " ## " + getAnoNascimento() + " ## " +  getUniversidade() + 
        " ## " + getCidadeNascimento() + " ## " + getEstadoNascimento() + "]");
    }
}

// ------------------------------------------------------------------------------------

class Q9 {
    public static int comp = 0, mov = 0;

    public static void heapSort(Jogador jogadores2[], int n) {
        //Alterar o vetor ignorando a posicao zero
        Jogador[] tmp = new Jogador[n+1];
        for(int i = 0; i < n; i++){
            tmp[i+1] = jogadores2[i];
            mov++;
        }
        jogadores2 = tmp;
  
        //Contrucao do heap
        for(int tamHeap = 2; tamHeap <= n; tamHeap++)
            construir(jogadores2, tamHeap);

        //Ordenacao propriamente dita
        int tamHeap = n;
        while(tamHeap > 1){
            swap(jogadores2, 1, tamHeap--);
            mov+=3;
            reconstruir(jogadores2, tamHeap);
        }
  
        //Alterar o vetor para voltar a posicao zero
        tmp = jogadores2;
        jogadores2 = new Jogador[n];
        for(int i = 0; i < n; i++)
            jogadores2[i] = tmp[i+1];

        for(int i = 0; i < n; i++){
            MyIO.println("[" + jogadores2[i].getId() + " ## " + jogadores2[i].getNome() + " ## " + jogadores2[i].getAltura() + 
            " ## " + jogadores2[i].getPeso() + " ## " + jogadores2[i].getAnoNascimento() + " ## " +  jogadores2[i].getUniversidade() + 
            " ## " + jogadores2[i].getCidadeNascimento() + " ## " + jogadores2[i].getEstadoNascimento() + "]");
        }
    }
  
    public static void construir(Jogador jogadores2[], int tamHeap){
        comp+=3;
        for(int i = tamHeap; (i > 1) && (jogadores2[i].getAltura() > jogadores2[i/2].getAltura() ||
        jogadores2[i].getAltura() == jogadores2[i/2].getAltura() && 
        jogadores2[i].getNome().compareTo(jogadores2[i/2].getNome()) > 0); i /= 2)
            swap(jogadores2, i, i/2);
            mov+=3;
    }
  
    public static void reconstruir(Jogador jogadores2[], int tamHeap){
        int i = 1;
        while(i <= (tamHeap/2)){
            int filho = getMaiorFilho(jogadores2, i, tamHeap);
            comp+=3;
            if(jogadores2[i].getAltura() < jogadores2[filho].getAltura() || 
            jogadores2[i].getAltura() == jogadores2[filho].getAltura() && 
            jogadores2[i].getNome().compareTo(jogadores2[filho].getNome()) < 0){
              swap(jogadores2, i, filho);
              mov+=3;
              i = filho;
            }else
              i = tamHeap;
        }
    }
  
    public static int getMaiorFilho(Jogador jogadores2[], int i, int tamHeap){
        int filho;
        comp+=3;
        if (2*i == tamHeap || jogadores2[2*i].getAltura() > jogadores2[2*i+1].getAltura() ||
        jogadores2[2*i].getAltura() == jogadores2[2*i+1].getAltura() && 
        jogadores2[2*i].getNome().compareTo(jogadores2[2*i+1].getNome()) > 0 )
            filho = 2*i;
        else
            filho = 2*i + 1;
        
        return filho;
    }

    public static void swap(Jogador jogadores2[], int i, int j){
        Jogador temp = jogadores2[i];
        jogadores2[i] = jogadores2[j];
        jogadores2[j] = temp;
    }


    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
  
    public static void main(String[] args){
        int numEntrada = 0;
        Jogador jogadores[] = new Jogador[3922];
        Jogador jogadores2[] = new Jogador[1000];
        long time = System.currentTimeMillis();

        Jogador.ler(jogadores);

        //Leitura da ids padrao
        String string = MyIO.readLine();
        while(!isFim(string)){
            jogadores2[numEntrada] = jogadores[Integer.parseInt(string)];
            numEntrada++;
            string = MyIO.readLine();
        }
        
        heapSort(jogadores2, numEntrada);

        Arq.openWrite("589690_heapsort.txt");

        Arq.println("Matricula: 589690\tTempo de execução: " + (System.currentTimeMillis() - time) + 
        "ms" + "\tComparações: " + comp + "\tMovimentações: " + mov);

        Arq.close();
    }

}
