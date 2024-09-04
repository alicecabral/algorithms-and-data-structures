import java.io.IOException;
import java.util.Scanner;
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

class Celula {
	public Jogador elemento; // Elemento inserido na celula.
	public Celula prox; // Aponta a celula prox.


	/**
	 * Construtor da classe.
	 */
	public Celula() {
		this(null);
	}

	/**
	 * Construtor da classe.
	 * @param elemento int inserido na celula.
	 */
	public Celula(Jogador elemento) {
      this.elemento = elemento;
      this.prox = null;
	}
}

class Pilha {
    private Celula topo;
    int a = 0;

	/**
	 * Construtor da classe que cria uma fila sem elementos.
	 */
	public Pilha() {
		topo = null;
	}

	/**
	 * Insere elemento na pilha (politica FILO).
	 * 
	 * @param x int elemento a inserir.
	 */
	public void inserir(Jogador x) {
		Celula tmp = new Celula(x);
		tmp.prox = topo;
		topo = tmp;
		tmp = null;
	}

	/**
	 * Remove elemento da pilha (politica FILO).
	 * 
	 * @return Elemento removido.
	 * @trhows Exception Se a sequencia nao contiver elementos.
	 */
	public Jogador remover() throws Exception {
		if (topo == null) {
			throw new Exception("Erro ao remover!");
		}
		Jogador resp = topo.elemento;
		Celula tmp = topo;
		topo = topo.prox;
		tmp.prox = null;
		tmp = null;
		return resp;
    }
    
    public void mostrarRecursivo(Celula i){
        if(i != null){
            mostrarRecursivo(i.prox);
            System.out.print("["+ a + "]" +" ## " + i.elemento.getNome() + " ## " + i.elemento.getAltura() 
            + " ## " + i.elemento.getPeso() + " ## " + i.elemento.getAnoNascimento() + " ## " + 
            i.elemento.getUniversidade() + " ## " + i.elemento.getCidadeNascimento()  + " ## " + 
            i.elemento.getEstadoNascimento()  + " ##" + "\n" );
            a++;
        }
    }

	/**
	 * Mostra os elementos separados por espacos, comecando do topo.
	 */
	public void mostrar() {
        mostrarRecursivo(topo);
	}

	// public int getSoma() {
	// 	return getSoma(topo);
	// }

	// private int getSoma(Celula i) {
	// 	int resp = 0;
	// 	if (i != null) {
	// 		resp += i.elemento + getSoma(i.prox);
	// 	}
	// 	return resp;
	// }

	// public int getMax() {
	// 	int max = topo.elemento;
	// 	for (Celula i = topo.prox; i != null; i = i.prox) {
	// 		if (i.elemento > max)
	// 			max = i.elemento;
	// 	}
	// 	return max;
	// }

	// public void mostraPilha() {
	// 	mostraPilha(topo);
	// }

	// private void mostraPilha(Celula i) {
	// 	if (i != null) {
	// 		mostraPilha(i.prox);
	// 		System.out.println("" + i.elemento);
	// 	}
	// }
}

class Q06 {
    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main (String[] args){
        Pilha pilhaJogadores = new Pilha(); 
        Jogador jogadores[] = new Jogador[3922];
        Scanner scan = new Scanner(System.in);

        Jogador.ler(jogadores);

        // Criação da pilha com os jogadores lidos da entrada
        String id;
        do {
            id = scan.nextLine();
            if (!isFim(id)){
                try {
                    pilhaJogadores.inserir(jogadores[Integer.parseInt(id)]);
                }
                catch (Exception e) {
                    System.out.println("ERRO1");
                    System.exit(0);
                }
            }
        } while (!isFim(id));

        //pilhaJogadores.mostrar();

        int n = Integer.parseInt(scan.nextLine());
        String string;
        String comando[] = new String[10];

        // Leitura e execução da segunda parte da entrada
        for(int i = 0; i < n; i++){
            string = scan.nextLine();
            comando = string.split(" ");

            if(comando[0].compareTo("I") == 0){
                try{
                    pilhaJogadores.inserir(jogadores[Integer.parseInt(comando[1])]);
                }
                catch(Exception e){
                    System.out.println("ERRO2");
                }
            }
            else if(comando[0].compareTo("R") == 0){
                try{
                    System.out.println("(R) " + pilhaJogadores.remover().getNome());
                }
                catch(Exception e){
                    e.printStackTrace();
                    System.out.println("ERRO3");
                }
            }
        }
        pilhaJogadores.mostrar();
        scan.close();
    }
}
