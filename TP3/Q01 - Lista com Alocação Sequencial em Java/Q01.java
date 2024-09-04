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
            System.out.println("File Read Error"); 
        } 
    }
}

class Lista {
    private Jogador[] array;
    private int n;
 
 
    /**
     * Construtor da classe.
     */
    public Lista () {
       this(1000);
    }
 
 
    /**
     * Construtor da classe.
     * @param tamanho Tamanho da lista.
     */
    public Lista (int tamanho){
       array = new Jogador[tamanho];
       n = 0;
    }
 
 
    /**
     * Insere um jogador na primeira posicao da lista e move os demais
     * jogadores para o fim da lista.
     * @param jogador Jogador jogador a ser inserido.
     * @throws Exception Se a lista estiver cheia.
     */
    public void inserirInicio(Jogador jogador) throws Exception {
 
       //validar insercao
       if(n >= array.length){
          throw new Exception("Erro ao inserir!");
       } 
 
       //levar elementos para o fim do array
       for(int i = n; i > 0; i--){
          array[i] = array[i-1];
       }
 
       array[0] = jogador;
       n++;
    }
 
 
    /**
     * Insere um jogador na ultima posicao da lista.
     * @param jogador Jogador jogador a ser inserido.
     * @throws Exception Se a lista estiver cheia.
     */
    public void inserirFim(Jogador jogador) throws Exception {
 
       //validar insercao
       if(n >= array.length){
          throw new Exception("Erro ao inserir!");
       }
 
       array[n] = jogador;
       n++;
    }
 
 
    /**
     * Insere um jogador em uma posicao especifica e move os demais
     * jogadores para o fim da lista.
     * @param jogador Jogador jogador a ser inserido.
     * @param pos Posicao de insercao.
     * @throws Exception Se a lista estiver cheia ou a posicao invalida.
     */
    public void inserir (int pos, Jogador jogador) throws Exception {
 
       //validar insercao
       if(n >= array.length || pos < 0 || pos > n){
          throw new Exception("Erro ao inserir!");
       }
 
       //levar elementos para o fim do array
       for(int i = n; i > pos; i--){
          array[i] = array[i-1];
       }
 
       array[pos] = jogador;
       n++;
    }
 
 
    /**
     * Remove um jogador da primeira posicao da lista e movimenta 
     * os demais jogadores para o inicio da mesma.
     * @return resp Jogador jogador a ser removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public Jogador removerInicio() throws Exception {
 
       //validar remocao
       if (n == 0) {
          throw new Exception("Erro ao remover!");
       }
 
       Jogador resp = array[0];
       n--;
 
       for(int i = 0; i < n; i++){
          array[i] = array[i+1];
       }
 
       return resp;
    }
 
 
    /**
     * Remove um jogador da ultima posicao da lista.
     * @return resp Jogador jogadora ser removido.
     * @throws Exception Se a lista estiver vazia.
     */
    public Jogador removerFim() throws Exception {
 
       //validar remocao
       if (n == 0) {
          throw new Exception("Erro ao remover!");
       }
 
       return array[--n];
    }
 
 
    /**
     * Remove um jogador de uma posicao especifica da lista e 
     * movimenta os demais jogadores para o inicio da mesma.
     * @param pos Posicao de remocao.
     * @return resp Jogador jogador a ser removido.
     * @throws Exception Se a lista estiver vazia ou a posicao for invalida.
     */
    public Jogador remover(int pos) throws Exception {
 
       //validar remocao
       if (n == 0 || pos < 0 || pos >= n) {
          throw new Exception("Erro ao remover!");
       }
 
       Jogador resp = array[pos];
       n--;
 
       for(int i = pos; i < n; i++){
          array[i] = array[i+1];
       }
 
       return resp;
    }
 
 
    /**
     * Mostra os jogadores da lista.
     */
    public void mostrar (){
        for(int i = 0; i < n; i++){
            System.out.print("["+ i + "]" +" ## " + array[i].getNome() + " ## " + array[i].getAltura() 
            + " ## " + array[i].getPeso() + " ## " + array[i].getAnoNascimento() + " ## " + 
            array[i].getUniversidade() + " ## " + array[i].getCidadeNascimento()  + " ## " + 
            array[i].getEstadoNascimento()  + " ##" + "\n" );
        }
    }
 
 
    /**
     * Procura um jogador e retorna se ele existe.
     * @param jogador Jogador jogador a ser pesquisado.
     * @return <code>true</code> se o array existir,
     * <code>false</code> em caso contrario.
     */
    public boolean pesquisar(Jogador jogador) {
       boolean retorno = false;
       for (int i = 0; i < n && retorno == false; i++) {
          retorno = (array[i] == jogador);
       }
       return retorno;
    }
 }

class Q01 {
    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main (String[] args){
        Lista listaJogadores = new Lista(1000); 
        Jogador jogadores[] = new Jogador[3922];

        Jogador.ler(jogadores);

        // Criação da lista com os jogadores lidos da entrada
        String id;
        do {
            id = MyIO.readLine();
            if (!isFim(id)){
                try {
                    listaJogadores.inserirFim(jogadores[Integer.parseInt(id)]);
                }
                catch (Exception e) {
                    System.out.println("ERRO1");
                }
            }
        } while (!isFim(id));

        //listaJogadores.mostrar();

        int n = MyIO.readInt();
        String string;
        String comando[] = new String[10];

        // Leitura e execução da segunda parte da entrada
        for(int i = 0; i < n; i++){
            string = MyIO.readLine();
            comando = string.split(" ");

            if(comando[0].compareTo("II") == 0){
                try{
                    listaJogadores.inserirInicio(jogadores[Integer.parseInt(comando[1])]);
                }
                catch(Exception e){
                    System.out.println("ERRO2");
                }
            }
            else if(comando[0].compareTo("I*") == 0){
                try{
                    listaJogadores.inserir(Integer.parseInt(comando[1]), jogadores[Integer.parseInt(comando[2])]);
                }
                catch(Exception e){
                    System.out.println("ERRO3");
                }
            }
            else if(comando[0].compareTo("IF") == 0){
                try{
                    listaJogadores.inserirFim(jogadores[Integer.parseInt(comando[1])]);
                }
                catch(Exception e){
                    System.out.println("ERRO4");
                }
            }
            else if(comando[0].compareTo("RI") == 0){
                try{
                    System.out.println("(R) " + listaJogadores.removerInicio().getNome());
                }
                catch(Exception e){
                    System.out.println("ERRO5");
                }
            }
            else if(comando[0].compareTo("R*") == 0){
                try{
                    System.out.println("(R) " + listaJogadores.remover(Integer.parseInt(comando[1])).getNome());
                }
                catch(Exception e){
                    System.out.println("ERRO6");
                }
            }
            else if(comando[0].compareTo("RF") == 0){
                try{
                    System.out.println("(R) " + listaJogadores.removerFim().getNome());
                }
                catch(Exception e){
                    System.out.println("ERRO7");
                }
            }
        }

        listaJogadores.mostrar();
    }
}