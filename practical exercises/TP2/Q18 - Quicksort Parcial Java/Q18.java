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

class Q18 {
    public static int comp = 0, mov = 0;

    public static void swap(Jogador array[], int i, int j){
        Jogador temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void quicksortParcial(Jogador array[], int esq, int dir) {
        int i = esq, j = dir;
        Jogador pivo = array[(esq+dir)/2];

        while (i <= j) {
            comp+=3;
            while (array[i].getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) < 0 ||
            array[i].getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) == 0 &&
            array[i].getNome().compareTo(pivo.getNome()) < 0)
                i++;
            comp+=3;
            while (array[j].getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) > 0 ||
            array[j].getEstadoNascimento().compareTo(pivo.getEstadoNascimento()) == 0 &&
            array[j].getNome().compareTo(pivo.getNome()) > 0)
                j--;
            if (i <= j){ 
                swap(array, i, j);
                mov+=3;
                i++; 
                j--; 
            }
        }

        if (esq < j)
            quicksortParcial(array, esq, j);
        if (i < 10 && i < dir)
            quicksortParcial(array, i, dir);
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

        quicksortParcial(jogadores2, 0, numEntrada-1);

        for(int i = 0; i < 10; i++){
            MyIO.println("[" + jogadores2[i].getId() + " ## " + jogadores2[i].getNome() + " ## " + jogadores2[i].getAltura() + 
            " ## " + jogadores2[i].getPeso() + " ## " + jogadores2[i].getAnoNascimento() + " ## " +  jogadores2[i].getUniversidade() + 
            " ## " + jogadores2[i].getCidadeNascimento() + " ## " + jogadores2[i].getEstadoNascimento() + "]");
        }

        Arq.openWrite("589690_quicksortParcial.txt");

        Arq.println("Matricula: 589690\tTempo de execução: " + (System.currentTimeMillis() - time) + 
        "ms" + "\tComparações: " + comp + "\tMovimentações: " + mov);

        Arq.close();
    }

}
