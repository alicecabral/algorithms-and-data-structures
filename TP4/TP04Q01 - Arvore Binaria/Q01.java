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

class Q01 {
    public static int comp;

    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main (String[] args){
        Jogador jogadores[] = new Jogador[3922];
        ArvoreBinaria arvoreJogadores = new ArvoreBinaria();
        long time = System.currentTimeMillis();

        Jogador.ler(jogadores);

        // Criação da árvore com os jogadores lidos da entrada
        String id;
        do {
            id = MyIO.readLine();
            if (!isFim(id)){
                try {
                    arvoreJogadores.inserir(jogadores[Integer.parseInt(id)]);
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
                    System.out.print(nome + " raiz");
                    if (arvoreJogadores.pesquisar(nome) == true)
                        System.out.println(" SIM");
                    else
                        System.out.println(" NAO");
                }
                catch (Exception e) {
                    System.out.println("ERRO2");
                }
            }
        } while (!isFim(nome));

        Arq.openWrite("589690_arvoreBinaria.txt");

        Arq.println("Matricula: 589690\tTempo de execução: " + (System.currentTimeMillis() - time) + 
        "ms" + "\tComparações: " + comp);

        Arq.close();
    }
}
