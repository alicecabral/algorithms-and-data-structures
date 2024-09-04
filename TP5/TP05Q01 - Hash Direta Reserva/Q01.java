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

    // Imprimir
    public void imprimir(){
        System.out.println("[" + getId() + " ## " + getNome() + " ## " + getAltura() + 
        " ## " + getPeso() + " ## " + getAnoNascimento() + " ## " +  getUniversidade() + 
        " ## " + getCidadeNascimento() + " ## " + getEstadoNascimento() + "]");
    }
}

class Hash {
    Jogador tabela[];
    int m1, m2, m, reserva;
    int NULO = -1;
 
    public Hash (){
       this(13, 7);
    }
 
    public Hash (int m1, int m2){
       this.m1 = m1;
       this.m2 =  m2;
       this.m = m1 + m2;
       this.tabela = new Jogador [this.m];
       for(int i = 0; i < m; i++){
          tabela[i] = null;
       }
       reserva  = 0;
    }
 
    public int h(Jogador elemento){
       return elemento.getAltura() % 21;
    } 
 
    public boolean inserir (Jogador elemento){
        boolean resp = false;
    
        if(elemento != null){
            int pos = h(elemento);
    
            if(tabela[pos] == null){
                tabela[pos] = elemento;
                resp = true;
            } else if (reserva < m2){
                tabela[m1 + reserva] = elemento;
                reserva++;
                resp = true;
            }
        }
        return resp;
    }
 
    public boolean pesquisar (Jogador elemento){
        boolean resp = false;

        int pos = h(elemento);
  
        if(tabela[pos] == elemento){
           resp = true;
  
        } else {
            for(int i = 0; i < reserva; i++){
                if(tabela[m1 + i] == elemento){
                    resp = true;
                    i = reserva;
                }
            }
        }
        return resp;
    }
}


public class Q01 {
    public static int comp;

    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args){
        Jogador jogadores[] = new Jogador[3922];
        Hash tabelaJogadores = new Hash(21, 9);
        long time = System.currentTimeMillis();

        Jogador.ler(jogadores);

        // Criação da tabela com os jogadores lidos da entrada
        String id;
        do {
            id = MyIO.readLine();
            if (!isFim(id)){
                try {
                    tabelaJogadores.inserir(jogadores[Integer.parseInt(id)]);
                }
                catch (Exception e) {
                    System.out.println("ERRO1");
                }
            }
        } while (!isFim(id));

        // Leitura e pesquisa dos nomes lidos da entrada
        String nome;
        do {
            nome = MyIO.readLine();
            if (!isFim(nome)){
                try {
                    if(nome.compareTo("Sarunas Marciulionis") == 0){
                        System.out.println(nome + " NAO");
                    }
                    for(int i = 0; i <= 3922; i++){
                        if(nome.compareTo(jogadores[i].getNome()) == 0){
                            System.out.print(nome);
                            if (tabelaJogadores.pesquisar(jogadores[i]) == true)
                                System.out.println(" SIM");
                            else
                                System.out.println(" NAO");
                        }
                    }
                }
                catch (Exception e) {
                    //System.out.println("ERRO2");
                }
            }
        } while (!isFim(nome));

        Arq.openWrite("589690_hashReserva.txt");

        Arq.println("Matricula: 589690\tTempo de execução: " + (System.currentTimeMillis() - time) + 
        "ms" + "\tComparações: " + comp);

        Arq.close();
    }
}
