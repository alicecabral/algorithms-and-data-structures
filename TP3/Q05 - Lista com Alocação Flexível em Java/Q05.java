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

class Lista {
	private Celula primeiro;
	private Celula ultimo;

	/**
	 * Construtor da classe que cria uma lista sem elementos (somente no cabeca).
	 */
	public Lista() {
		primeiro = new Celula();
		ultimo = primeiro;
	}

	/**
	 * Insere um elemento na primeira posicao da lista.
     * @param x int elemento a ser inserido.
	 */
	public void inserirInicio(Jogador x) {
	    Celula tmp = new Celula(x);
        tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if (primeiro == ultimo) {                 
			ultimo = tmp;
		}
        tmp = null;
	}

	/**
	 * Insere um elemento na ultima posicao da lista.
    * @param x int elemento a ser inserido.
	 */
	public void inserirFim(Jogador x) {
		ultimo.prox = new Celula(x);
		ultimo = ultimo.prox;
	}

	/**
	 * Remove um elemento da primeira posicao da lista.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Jogador removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

        Celula tmp = primeiro;
		primeiro = primeiro.prox;
		Jogador resp = primeiro.elemento;
        tmp.prox = null;
        tmp = null;
		return resp;
	}

	/**
	 * Remove um elemento da ultima posicao da lista.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista nao contiver elementos.
	 */
	public Jogador removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} 

		// Caminhar ate a penultima celula:
        Celula i;
        for(i = primeiro; i.prox != ultimo; i = i.prox);

        Jogador resp = ultimo.elemento; 
        ultimo = i; 
        i = ultimo.prox = null;
      
		return resp;
	}

	/**
    * Insere um elemento em uma posicao especifica considerando que o 
    * primeiro elemento valido esta na posicao 0.
    * @param x int elemento a ser inserido.
	 * @param pos int posicao da insercao.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
    public void inserir(int pos, Jogador x) throws Exception {
        int tamanho = tamanho();

        if(pos < 0 || pos > tamanho){
			throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
        } else if (pos == 0){
            inserirInicio(x);
        } else if (pos == tamanho){
            inserirFim(x);
        } else {
		// Caminhar ate a posicao anterior a insercao
        Celula i = primeiro;
        for(int j = 0; j < pos; j++, i = i.prox);
        Celula tmp = new Celula(x);
        tmp.prox = i.prox;
        i.prox = tmp;
        tmp = i = null;
        }
    }

	/**
    * Remove um elemento de uma posicao especifica da lista
    * considerando que o primeiro elemento valido esta na posicao 0.
	 * @param posicao Meio da remocao.
    * @return resp int elemento a ser removido.
	 * @throws Exception Se <code>posicao</code> invalida.
	 */
	public Jogador remover(int pos) throws Exception {
        Jogador resp;
        int tamanho = tamanho();

		if (primeiro == ultimo){
			throw new Exception("Erro ao remover (vazia)!");

        } else if(pos < 0 || pos >= tamanho){
			throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
        } else if (pos == 0){
            resp = removerInicio();
        } else if (pos == tamanho - 1){
            resp = removerFim();
        } else {
		   // Caminhar ate a posicao anterior a insercao
            Celula i = primeiro;
            for(int j = 0; j < pos; j++, i = i.prox);
		
            Celula tmp = i.prox;
            resp = tmp.elemento;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
        }

		return resp;
	}

	/**
	 * Mostra os elementos da lista separados por espacos.
	 */
	public void mostrar() {
        int a = 0;
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.print("["+ a + "]" +" ## " + i.elemento.getNome() + " ## " + i.elemento.getAltura() 
            + " ## " + i.elemento.getPeso() + " ## " + i.elemento.getAnoNascimento() + " ## " + 
            i.elemento.getUniversidade() + " ## " + i.elemento.getCidadeNascimento()  + " ## " + 
            i.elemento.getEstadoNascimento()  + " ##" + "\n" );
            a++;
        }
	}

	/**
	 * Procura um elemento e retorna se ele existe.
	 * @param x Elemento a pesquisar.
	 * @return <code>true</code> se o elemento existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(Jogador x) {
		boolean resp = false;
		for (Celula i = primeiro.prox; i != null; i = i.prox) {
            if(i.elemento == x){
                resp = true;
                i = ultimo;
            }
		}
		return resp;
	}

	/**
	 * Calcula e retorna o tamanho, em numero de elementos, da lista.
	 * @return resp int tamanho
	 */
    public int tamanho() {
        int tamanho = 0; 
        for(Celula i = primeiro; i != ultimo; i = i.prox, tamanho++);
        return tamanho;
    }
}

class Q05 {
    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main (String[] args){
        Lista listaJogadores = new Lista(); 
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
