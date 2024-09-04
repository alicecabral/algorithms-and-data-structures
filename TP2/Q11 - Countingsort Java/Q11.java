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

class Q11 {
    public static int comp = 0, mov = 0;

    public static void countingSort(Jogador jogadores2[], int n){
        Jogador maior = jogadores2[0];
        for(int i = 1; i < n; i++){
            comp++;
            if(maior.getAltura() < jogadores2[i].getAltura())
                maior = jogadores2[i];
        }

        //Array para contar o numero de ocorrencias de cada elemento
        int[] count = new int[maior.getAltura() + 1];
        Jogador[] ordenado = new Jogador[n];

        //Inicializar cada posicao do array de contagem 
        for (int i = 0; i < count.length; count[i] = 0, i++);

        //Agora, o count[i] contem o numero de elemento iguais a i
        for (int i = 0; i < n; count[jogadores2[i].getAltura()]++, i++);

        //Agora, o count[i] contem o numero de elemento menores ou iguais a i
        for(int i = 1; i < count.length; count[i] += count[i-1], i++);

        //Ordenando
        for(int i = n-1; i >= 0; ordenado[count[jogadores2[i].getAltura()]-1] = jogadores2[i],
            count[jogadores2[i].getAltura()]--, i--){
                comp++;
                mov++;
        }

        //Copiando para o array original
        for(int i = 0; i < n; jogadores2[i] = ordenado[i], i++)
            buscaMenorNome(ordenado, i);
    }

    public static void buscaMenorNome(Jogador ordenado[], int i){
        int menor = i;

        comp++;
        for(int j = i+1; j < ordenado.length && ordenado[j].getAltura() == ordenado[i].getAltura(); j++){
            comp++;
            if(ordenado[menor].getNome().compareTo(ordenado[j].getNome()) > 0)
                menor = j;
        }
        Jogador temp = ordenado[menor];
        ordenado[menor] = ordenado[i];
        ordenado[i] = temp;
        mov+=3;
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
        
        countingSort(jogadores2, numEntrada);

        // Imprimindo o vetor ordenado
        for(int i = 0; i < numEntrada; i++){
            jogadores2[i].imprimir();
        }

        Arq.openWrite("589690_countingsort.txt");

        Arq.println("Matricula: 589690\tTempo de execução: " + (System.currentTimeMillis() - time) + 
        "ms" + "\tComparações: " + comp + "\tMovimentações: " + mov);

        Arq.close();

    }

}
