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

class NoAN{
    public boolean cor;
    public Jogador elemento;
    public NoAN esq, dir;

    public NoAN (){
        this(null);
    }

    public NoAN (Jogador elemento){
        this(elemento, false, null, null);
    }

    public NoAN (Jogador elemento, boolean cor){
        this(elemento, cor, null, null);
    }

    public NoAN (Jogador elemento, boolean cor, NoAN esq, NoAN dir){
        this.cor = cor;
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class Alvinegra{
	private NoAN raiz; // Raiz da arvore.

	/**
	 * Construtor da classe.
	 */
	public Alvinegra() {
		raiz = null;
	}

	/**
	 * Metodo publico iterativo para pesquisar nome.
	 * @param nome nome que sera procurado.
	 * @return <code>true</code> se o nome existir,
	 * <code>false</code> em caso contrario.
	 */
	public boolean pesquisar(String nome) {
		return pesquisar(nome, raiz);
	}

	/**
	 * Metodo privado recursivo para pesquisar nome.
	 * @param nome nome que sera procurado.
	 * @param i NoAN em analise.
	 * @return <code>true</code> se o nome existir,
	 * <code>false</code> em caso contrario.
	 */
	private boolean pesquisar(String nome, NoAN i) {
        boolean resp;
        if (i == null) {
            resp = false;
        } else if (nome.compareTo(i.elemento.getNome()) == 0) {
            resp = true;
        } else if (nome.compareTo(i.elemento.getNome()) < 0) {
            System.out.print(" esq");
            resp = pesquisar(nome, i.esq);
        } else {
            System.out.print(" dir");
            resp = pesquisar(nome, i.dir);
        }
        return resp;
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void mostrarCentral() {
		System.out.print("[ ");
		mostrarCentral(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i NoAN em analise.
	 */
	private void mostrarCentral(NoAN i) {
		if (i != null) {
			mostrarCentral(i.esq); // Elementos da esquerda.
			System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
			mostrarCentral(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void mostrarPre() {
		System.out.print("[ ");
		mostrarPre(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i NoAN em analise.
	 */
	private void mostrarPre(NoAN i) {
		if (i != null) {
			System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
			mostrarPre(i.esq); // Elementos da esquerda.
			mostrarPre(i.dir); // Elementos da direita.
		}
	}

	/**
	 * Metodo publico iterativo para exibir elementos.
	 */
	public void mostrarPos() {
		System.out.print("[ ");
		mostrarPos(raiz);
		System.out.println("]");
	}

	/**
	 * Metodo privado recursivo para exibir elementos.
	 * @param i NoAN em analise.
	 */
	private void mostrarPos(NoAN i) {
		if (i != null) {
			mostrarPos(i.esq); // Elementos da esquerda.
			mostrarPos(i.dir); // Elementos da direita.
			System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) ")); // Conteudo do no.
		}
	}


	/**
	 * Metodo publico iterativo para inserir jogador.
	 * @param elemento jogador a ser inserido.
	 * @throws Exception Se o jogador existir.
	 */
	public void inserir(Jogador elemento) throws Exception {
        //Se a arvore estiver vazia
        if(raiz == null){
            raiz = new NoAN(elemento, false);
            //System.out.println("Antes, zero elementos. Agora, raiz(" + raiz.elemento + ").");

        //Senao, se a arvore tiver um elemento 
        } else if (raiz.esq == null && raiz.dir == null){
            if (raiz.elemento.getNome().compareTo(elemento.getNome()) > 0){ 
                raiz.esq = new NoAN(elemento, true);
                //System.out.println("Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e esq(" + raiz.esq.elemento +").");
            } else {
                raiz.dir = new NoAN(elemento, true);
                //System.out.println("Antes, um elemento. Agora, raiz(" + raiz.elemento + ") e dir(" + raiz.dir.elemento +").");
            }

      //Senao, se a arvore tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null){
            if(raiz.elemento.getNome().compareTo(elemento.getNome()) > 0){
                raiz.esq = new NoAN(elemento);
                //System.out.println("Antes, dois elementos(A). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");

            } else if (raiz.dir.elemento.getNome().compareTo(elemento.getNome()) > 0){
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = elemento;
                //System.out.println("Antes, dois elementos(B). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");

            } else {
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = elemento;
                //System.out.println("Antes, dois elementos(C). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");
            }
            raiz.esq.cor = raiz.dir.cor = false;
         
        //Senao, se a arvore tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null){
            if(raiz.elemento.getNome().compareTo(elemento.getNome()) < 0){
                raiz.dir = new NoAN(elemento);
                //System.out.println("Antes, dois elementos(D). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");
            } else if (raiz.esq.elemento.getNome().compareTo(elemento.getNome()) < 0){
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = elemento;
                //System.out.println("Antes, dois elementos(E). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");
            } else {
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = elemento;
                //System.out.println("Antes, dois elementos(F). Agora, raiz(" + raiz.elemento + "), esq (" + raiz.esq.elemento +") e dir(" + raiz.dir.elemento +").");
            }

            raiz.esq.cor = raiz.dir.cor = false;

        //Senao, a arvore tem tres ou mais elementos
        } else {
            //System.out.println("Arvore com tres ou mais elementos...");
            inserir(elemento, null, null, null, raiz);
        }

        raiz.cor = false;
    }

    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i){
        //Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if(pai.cor == true){
            //4 tipos de reequilibrios e acoplamento
            if(pai.elemento.getNome().compareTo(avo.elemento.getNome()) > 0){ // rotacao a esquerda ou direita-esquerda
                if(i.elemento.getNome().compareTo(pai.elemento.getNome()) > 0){
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }

            } else { // rotacao a direita ou esquerda-direita
                if(i.elemento.getNome().compareTo(pai.elemento.getNome()) < 0){
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }

            if (bisavo == null){
                raiz = avo;
            } else {
                if(avo.elemento.getNome().compareTo(bisavo.elemento.getNome()) < 0){
                    bisavo.esq = avo;
                } else {
                    bisavo.dir = avo;
                }
            }

            //reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
            //System.out.println("Reestabeler cores: avo(" + avo.elemento + "->branco) e avo.esq / avo.dir(" + avo.esq.elemento + "," + avo.dir.elemento + "-> pretos)");
        } //if(pai.cor == true)
    }

	/**
	 * Metodo privado recursivo para inserir jogador.
	 * @param elemento Jogador a ser inserido.
	 * @param avo NoAN em analise.
	 * @param pai NoAN em analise.
	 * @param i NoAN em analise.
	 * @throws Exception Se o jogador existir.
	 */
	private void inserir(Jogador elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
		if (i == null) {

            if(elemento.getNome().compareTo(pai.elemento.getNome()) < 0){
                i = pai.esq = new NoAN(elemento, true);
            } else {
                i = pai.dir = new NoAN(elemento, true);
            }

            if(pai.cor == true){
                balancear(bisavo, avo, pai, i);
            }

        } else {
            //Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if(i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true){
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if(i == raiz){
                i.cor = false;
                }else if(pai.cor == true){
                balancear(bisavo, avo, pai, i);
                }
            }
            if (elemento.getNome().compareTo(i.elemento.getNome()) < 0) {
                inserir(elemento, avo, pai, i, i.esq);
            } else if (elemento.getNome().compareTo(i.elemento.getNome()) > 0) {
                inserir(elemento, avo, pai, i, i.dir);
            } else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }
        }
	}

    private NoAN rotacaoDir(NoAN no) {
        //System.out.println("Rotacao DIR(" + no.elemento + ")");
        NoAN noEsq = no.esq;
        NoAN noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private NoAN rotacaoEsq(NoAN no) {
        //System.out.println("Rotacao ESQ(" + no.elemento + ")");
        NoAN noDir = no.dir;
        NoAN noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private NoAN rotacaoDirEsq(NoAN no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private NoAN rotacaoEsqDir(NoAN no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }
}

public class Q04{
    public static int comp;

    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main (String[] args){
        Jogador jogadores[] = new Jogador[3922];
        Alvinegra alvinegraJogadores = new Alvinegra();
        long time = System.currentTimeMillis();

        Jogador.ler(jogadores);

        // Criação da árvore com os jogadores lidos da entrada
        String id;
        do {
            id = MyIO.readLine();
            if (!isFim(id)){
                try {
                    alvinegraJogadores.inserir(jogadores[Integer.parseInt(id)]);
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
                    if (alvinegraJogadores.pesquisar(nome) == true)
                        System.out.println(" SIM");
                    else
                        System.out.println(" NAO");
                }
                catch (Exception e) {
                    System.out.println("ERRO2");
                }
            }
        } while (!isFim(nome));

        Arq.openWrite("589690_alvinegra.txt");

        Arq.println("Matricula: 589690\tTempo de execução: " + (System.currentTimeMillis() - time) + 
        "ms" + "\tComparações: " + comp);

        Arq.close();
    }
}