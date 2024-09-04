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

class Q13 {
    public static int comp = 0, mov = 0;

    public static void mergesort(Jogador array[], int esq, int dir) {
        if (esq < dir){
           int meio = (esq + dir) / 2;
           mergesort(array, esq, meio);
           mergesort(array, meio + 1, dir);
           intercalar(array, esq, meio, dir);
        }
    }

    public static void intercalar(Jogador array[], int esq, int meio, int dir){
        int n1, n2, i, j, k;

        //Definir tamanho dos dois subarrays
        n1 = meio-esq+1;
        n2 = dir - meio;

        Jogador[] a1 = new Jogador[n1+1];
        Jogador[] a2 = new Jogador[n2+1];

        //Inicializar primeiro subarray
        for(i = 0; i < n1; i++){
            a1[i] = array[esq+i];
            mov++;
        }

        //Inicializar segundo subarray
        for(j = 0; j < n2; j++){
            a2[j] = array[meio+j+1];
            mov++;
        }

        Jogador sentinela = new Jogador();
        sentinela.setUniversidade("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        //Sentinela no final dos dois arrays
        a1[i] = a2[j] = sentinela;

        //Intercalacao propriamente dita
        for(i = j = 0, k = esq; k <= dir; k++){
            comp+=3;
            array[k] = (a1[i].getUniversidade().compareTo(a2[j].getUniversidade()) < 0 ||
            a1[i].getUniversidade().compareTo(a2[j].getUniversidade()) == 0 &&  
            a1[i].getNome().compareTo(a2[j].getNome()) < 0) ? a1[i++] : a2[j++];
        }
    }

    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }
  
    public static void main(String[] args){
        int numEntrada = 0;
        Jogador jogadores[] = new Jogador[3922];
        Jogador jogadores2[] = new Jogador[1000];

        Jogador.ler(jogadores);

        //Leitura da ids padrao
        String string = MyIO.readLine();
        while(!isFim(string)){
            jogadores2[numEntrada] = jogadores[Integer.parseInt(string)];
            numEntrada++;
            string = MyIO.readLine();
        }
        
        long time = System.currentTimeMillis();

        mergesort(jogadores2, 0, numEntrada-1);

        // Imprimindo o vetor ordenado
        for(int i = 0; i < numEntrada; i++){
            jogadores2[i].imprimir();
        }

        Arq.openWrite("589690_mergesort.txt");

        Arq.println("Matricula: 589690\tTempo de execução: " + (System.currentTimeMillis() - time) + 
        "ms" + "\tComparações: " + comp + "\tMovimentações: " + mov);

        Arq.close();

    }

}
