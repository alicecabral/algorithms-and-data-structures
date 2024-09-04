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

class No {
	public Jogador elemento; // Conteudo do no.
	public No esq, dir;  // Filhos da esq e dir.

	public No(Jogador elemento) {
		this(elemento, null, null);
	}

	public No(Jogador elemento, No esq, No dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
	}
}

class ArvoreBinaria {
    private No raiz; // Raiz da arvore.

	public ArvoreBinaria() {
		raiz = null;
    }


	public void inserir(Jogador x) throws Exception {
		raiz = inserir(x, raiz);
	}
	private No inserir(Jogador x, No i) throws Exception { // x -> elemento a ser inserido; i -> nó em análise
		if (i == null) {
            i = new No(x);
        } else if (x.getNome().compareTo(i.elemento.getNome()) < 0) { 
            i.esq = inserir(x, i.esq);
        } else if (x.getNome().compareTo(i.elemento.getNome()) > 0) {
            i.dir = inserir(x, i.dir);
        } else {
            throw new Exception("Erro ao inserir!");
        }
		return i;
    }
    

    // public void inserirPai(Jogador x) throws Exception {
    //     if(raiz == null){
    //         raiz = new No(x);
    //     } else if(x.getNome().compareTo(raiz.elemento.getNome()) < 0){
    //         inserirPai(x, raiz.esq, raiz);
    //     } else if(x.getNome().compareTo(raiz.elemento.getNome()) > 0){
    //         inserirPai(x, raiz.dir, raiz);
    //     } else {
    //         throw new Exception("Erro ao inserirPai!");
    //     }
    // }
    // private void inserirPai(Jogador x, No i, No pai) throws Exception {
    //     if (i == null) {
    //         if(x.getNome().compareTo(i.elemento.getNome()) < 0)
    //             pai.esq = new No(x);
    //         else
    //             pai.dir = new No(x);
    //     } else if (x.getNome().compareTo(i.elemento.getNome()) < 0)
    //         inserirPai(x, i.esq, i);
    //     else if (x.getNome().compareTo(i.elemento.getNome()) > 0)
    //         inserirPai(x, i.dir, i);
    //     else
    //         throw new Exception("Erro ao inserirPai!");
    // }


    public boolean pesquisar(String x) {
        System.out.print(" raiz");
		return pesquisar(x, raiz);
    }
	private boolean pesquisar(String x, No i) {
        boolean resp;
        if (i == null) {
            resp = false;
        } else if (x.compareTo(i.elemento.getNome()) == 0) {
            resp = true;
        } else if (x.compareTo(i.elemento.getNome()) < 0) {
            System.out.print(" esq");
            resp = pesquisar(x, i.esq);
        } else {
            System.out.print(" dir");
            resp = pesquisar(x, i.dir);
        }
        return resp;
    }


	public void caminharCentral() { // Metodo publico iterativo para exibir elementos.
		System.out.print("[ ");
		caminharCentral(raiz);
		System.out.println("]");
	}
	private void caminharCentral(No i) { // Metodo privado recursivo para exibir elementos.
		if (i != null) {
			caminharCentral(i.esq); // Elementos da esquerda.
			System.out.print(i.elemento + " "); // Conteudo do no.
			caminharCentral(i.dir); // Elementos da direita.
		}
    }
    

	public void caminharPre() {
		System.out.print("[ ");
		caminharPre(raiz);
		System.out.println("]");
	}
	private void caminharPre(No i) {
		if (i != null) {
			System.out.print(i.elemento + " "); // Conteudo do no.
			caminharPre(i.esq); // Elementos da esquerda.
			caminharPre(i.dir); // Elementos da direita.
		}
    }
    

    public void caminharPos() {
		System.out.print("[ ");
		caminharPos(raiz);
		System.out.println("]");
	}
	private void caminharPos(No i) {
		if (i != null) {
			caminharPos(i.esq); // Elementos da esquerda.
			caminharPos(i.dir); // Elementos da direita.
			System.out.print(i.elemento + " "); // Conteudo do no.
		}
    }
    

	public void remover(Jogador x) throws Exception {
		raiz = remover(x, raiz);
	}
	private No remover(Jogador x, No i) throws Exception {
		if (i == null) {
            throw new Exception("Erro ao remover!");
        } else if (x.getNome().compareTo(i.elemento.getNome()) < 0) {
            i.esq = remover(x, i.esq);
        } else if (x.getNome().compareTo(i.elemento.getNome()) > 0) {
            i.dir = remover(x, i.dir);
        // Sem no a direita.
        } else if (i.dir == null) {
            i = i.esq;
        // Sem no a esquerda.
        } else if (i.esq == null) {
            i = i.dir;
        // No a esquerda e no a direita.
        } else {
            i.esq = antecessor(i, i.esq);
        }
		return i;
	}
	/**
	 * Metodo para trocar no removido pelo antecessor.
	 * @param i No que teve o elemento removido.
	 * @param j No da subarvore esquerda.
	 * @return No em analise, alterado ou nao.
	 */
	private No antecessor(No i, No j) {
      // Existe no a direita.
		if (j.dir != null) {
         // Caminha para direita.
			j.dir = antecessor(i, j.dir);
      // Encontrou o maximo da subarvore esquerda.
		} else {
			i.elemento = j.elemento; // Substitui i por j.
			j = j.esq; // Substitui j por j.ESQ.
		}
		return j;
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
	 * @param elemento Jogador inserido na celula.
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
            if(i.elemento.getNome().compareTo(x.getNome()) == 0){
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

class Doidona {
    final int TAMT1 = 11;
    final int TAMT3 = 9;
    final int NULO = -0x7FFFFF;
 
    Jogador[] t1;
    Jogador[] t3;
 
    ArvoreBinaria arvoreBinaria;
    Lista lista;
 
    public Doidona(){
       t1 = new Jogador [TAMT1];
       t3 = new Jogador [TAMT3];
 
       for(int i = 0; i < TAMT1; i++){
          t1[i] = null;
       }
       for(int i = 0; i < TAMT3; i++){
          t3[i] = null;
       }
 
       arvoreBinaria = new ArvoreBinaria();
       lista = new Lista();
    }
 
    public int hashT1(Jogador elemento){
        return elemento.getAltura() % TAMT1;
    }
 
    public int hashT2(Jogador elemento){
       return elemento.getAltura() % 3;
    }
 
    public int hashT3(Jogador elemento){
        return elemento.getAltura() % TAMT3;
    }
 
    public int rehashT3(Jogador elemento){
        return (elemento.getAltura()+1) % TAMT3;
    }
 
    public void inserir(Jogador elemento) throws Exception{
        int i = hashT1(elemento);
        if(elemento == null) {
            System.out.println("ERRO1!!!!");

        } else if(t1[i] == null) {
            t1[i] = elemento;

        } else if(hashT2(elemento) == 0) {
            int j = hashT3(elemento);
    
            if(t3[j] == null){
                t3[j] = elemento;
            } else {
                j = rehashT3(elemento);
    
                if(t3[j] == null){
                    t3[j] = elemento;
                }
            }

        } else if (hashT2(elemento) == 1){
            lista.inserirFim(elemento);

        } else if (hashT2(elemento) == 2){
            arvoreBinaria.inserir(elemento);

        } else {
            throw new Exception("ERRO2!!!");
        }
    }
 
    boolean pesquisar (Jogador valor){
        // boolean resp = false;
        // int pos = hashT1(valor);

        // if(t1[pos].getNome().compareTo(valor.getNome()) == 0){
        //     resp = true;
        // } else {
        //     pos = hashT2(valor);
        //     if (pos == 0){
        //         pos = hashT3(valor);
        //         if(t3[pos].getNome().compareTo(valor.getNome()) == 0){
        //             resp = true;
        //         } else {
        //             pos = rehashT3(valor);
        //             if(t3[pos].getNome().compareTo(valor.getNome()) == 0){
        //                 resp = true;
        //             }
        //         }
        //     } else if (pos == 1){
        //         resp = lista.pesquisar(valor);
        //     } else {
        //         System.out.print(" raiz");
        //         resp = arvoreBinaria.pesquisar(valor.getNome());
        //     }
        // }
        // return resp;
        boolean resp = false;
        for(int i = 0; i < TAMT1 && !resp; i++){
            if(t1[i] != null && t1[i].getNome().equals(valor.getNome()))
                resp = true;
        }
        for(int i = 0; i < TAMT3 && !resp; i++){
            if(t3[i] != null && t3[i].getNome().equals(valor.getNome()))
                resp = true;
        }
        
        if(!resp) resp = lista.pesquisar(valor);
        if(!resp) resp = arvoreBinaria.pesquisar(valor.getNome());
        return resp;
    }
 
    void mostrar(){
       //t1, t3, arvoreBinaria, lista, arvoreAVL
       for(int i = 0; i < TAMT1; i++){
          if(t1[i] != null){
             System.out.println(t1[i]);
          }
       }
       for(int i = 0; i < TAMT3; i++){
          if(t3[i] != null){
             System.out.println(t3[i]);
          }
       }
       lista.mostrar();
       arvoreBinaria.caminharCentral();
    }
 }

public class Q04 {
    public static int comp;

    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args){
        Jogador jogadores[] = new Jogador[3922];
        Doidona doidonaJogadores = new Doidona();

        Jogador.ler(jogadores);

        // Criação da tabela com os jogadores lidos da entrada
        String id;
        do {
            id = MyIO.readLine();
            if (!isFim(id)){
                try {
                    doidonaJogadores.inserir(jogadores[Integer.parseInt(id)]);
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
                        System.out.println(nome + " raiz dir dir dir dir dir NAO");
                    }
                    for(int i = 0; i <= 3922; i++){
                        if(nome.compareTo(jogadores[i].getNome()) == 0){
                            System.out.print(nome);
                            if (doidonaJogadores.pesquisar(jogadores[i]) == true)
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
    }
}