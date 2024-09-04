import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

class Jogador {
  private int id, altura, peso, anoNascimento;
  private String nome, universidade, cidadeNascimento, estadoNascimento;

  // Construtor vazio
  public Jogador() {
    this.id = this.altura = this.peso = this.anoNascimento = 0;
    this.nome = this.universidade = this.cidadeNascimento = this.estadoNascimento = "";
  }

  // Construtor que recebe parâmetros
  public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento,
      String cidadeNascimento, String estadoNascimento) {
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
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getAltura() {
    return altura;
  }

  public void setAltura(int altura) {
    this.altura = altura;
  }

  public int getPeso() {
    return peso;
  }

  public void setPeso(int peso) {
    this.peso = peso;
  }

  public String getUniversidade() {
    return universidade;
  }

  public void setUniversidade(String universidade) {
    this.universidade = universidade;
  }

  public int getAnoNascimento() {
    return anoNascimento;
  }

  public void setAnoNascimento(int anoNascimento) {
    this.anoNascimento = anoNascimento;
  }

  public String getCidadeNascimento() {
    return cidadeNascimento;
  }

  public void setCidadeNascimento(String cidadeNascimento) {
    this.cidadeNascimento = cidadeNascimento;
  }

  public String getEstadoNascimento() {
    return estadoNascimento;
  }

  public void setEstadoNascimento(String estadoNascimento) {
    this.estadoNascimento = estadoNascimento;
  }

  // Ler
  public static void ler(Jogador jogadores[]) {
    try {
      BufferedReader br = new BufferedReader(new FileReader("/tmp/players.csv"));
      String str = br.readLine();
      int i = 0;

      while ((str = br.readLine()) != null) {
        jogadores[i] = new Jogador();
        str = str.replace(",,", ",nao informado,");
        if (str.charAt(str.length() - 1) == ',')
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
    } catch (IOException e) {
      System.out.println("File Read Error");
    }
  }

  // Imprimir
  public void imprimir() {
    System.out.println(
        "[" + getId() + " ## " + getNome() + " ## " + getAltura() + " ## " + getPeso() + " ## " + getAnoNascimento()
            + " ## " + getUniversidade() + " ## " + getCidadeNascimento() + " ## " + getEstadoNascimento() + "]");
  }
}

class No {
  public int chave; // Conteudo do no.
  public No esq; // No da esquerda.
  public No dir; // No da direita.
  public No2 outro;

  /**
   * Construtor da classe.
   * 
   * @param chave Conteudo do no.
   */
  No(int chave) {
    this.chave = chave;
    this.esq = this.dir = null;
    this.outro = null;
  }

  /**
   * Construtor da classe.
   * 
   * @param chave Conteudo do no.
   * @param esq   No da esquerda.
   * @param dir   No da direita.
   */
  No(int chave, No esq, No dir) {
    this.chave = chave;
    this.esq = esq;
    this.dir = dir;
    this.outro = null;
  }
}

class No2 {
  public String elemento; // Conteudo do no.
  public No2 esq; // No da esquerda.
  public No2 dir; // No da direita.

  /**
   * Construtor da classe.
   * 
   * @param elemento Conteudo do no.
   */
  No2(String elemento) {
    this.elemento = elemento;
    this.esq = this.dir = null;
  }

  /**
   * Construtor da classe.
   * 
   * @param elemento Conteudo do no.
   * @param esq      No2 da esquerda.
   * @param dir      No2 da direita.
   */
  No2(String elemento, No2 esq, No2 dir) {
    this.elemento = elemento;
    this.esq = esq;
    this.dir = dir;
  }
}

class ArvoreArvore {
    private No raiz; // Raiz da primeira arvore.

    /**
     * Construtor da classe.
     */
    public ArvoreArvore() {
        raiz = null;
        inserirChave(7);
        inserirChave(3);
        inserirChave(11);
        inserirChave(1);
        inserirChave(5);
        inserirChave(9);
        inserirChave(12);
        inserirChave(0);
        inserirChave(2);
        inserirChave(4);
        inserirChave(6);
        inserirChave(8);
        inserirChave(10);
        inserirChave(13);
        inserirChave(14);
    }

    /**
     * Metodo publico iterativo para pesquisar elemento.
     * 
     * @param elemento Elemento que sera procurado.
     * @return <code>true</code> se o elemento existir, <code>false</code> em caso
     *         contrario.
     */
	public boolean pesquisar(String x) {
		return pesquisar(raiz, x);
	}
	private boolean pesquisar(No i, String x) {
        boolean resp = false;
        if(i != null) {
            resp = pesquisarSegundaArvore(i.outro, x);
            if(resp == false){
                System.out.print(" esq");
                resp = pesquisar(i.esq, x);
                if(resp == false){
                    System.out.print(" dir");
                    resp = pesquisar(i.dir, x);
                }
            }
        }
        return resp;
	}
	private boolean pesquisarSegundaArvore(No2 i, String x) {
        boolean resp = false;
        if(i != null) {
            if(x.equals(i.elemento)){
                resp = true;
            }
            if(resp == false){
                System.out.print(" ESQ");
                resp = pesquisarSegundaArvore(i.esq, x);
                if(resp == false){
                    System.out.print(" DIR");
                    resp = pesquisarSegundaArvore(i.dir, x);
                }
            }
        }
        return resp;
    }
    
	// public boolean pesquisar(String x) {
	// 	return pesquisar(raiz, x);
	// }
	// private boolean pesquisar(No i, String x) {
    //     boolean resp;
    //     if(i == null) {
    //         resp = false;
    //     } else if(pesquisarSegundaArvore(i.outro, x) == true) {
    //         resp = true;
    //     } else {
    //         System.out.print(" esq");
    //         resp = pesquisar(i.esq, x);
    //         if(resp == false){
    //             System.out.print(" dir");
    //             resp = pesquisar(i.dir, x);
    //         }
    //     }
    //     return resp;
	// }
	// private boolean pesquisarSegundaArvore(No2 i, String x) {
    //     boolean resp;
	// 	if (i == null) { 
    //         resp = false;
    //     } else if (x.equals(i.elemento)) { 
    //         resp = true; 
    //     } else if (x.compareTo(i.elemento) < 0) { 
    //         System.out.print(" ESQ");
    //         resp = pesquisarSegundaArvore(i.esq, x); 
    //     } else { 
    //         System.out.print(" DIR");
    //         resp = pesquisarSegundaArvore(i.dir, x); 
    //     }
    //     return resp;
	// }

    private void inserirChave(int chave) {
        raiz = inserirChave(chave, raiz);
    }
    private No inserirChave(int x, No i) {
        if (i == null) {
        i = new No(x);
        } else if (x < i.chave) {
        i.esq = inserirChave(x, i.esq);
        } else if (x > i.chave) {
        i.dir = inserirChave(x, i.dir);
        } else {
        System.out.println("Erro ao inserir!");
        }
        return i;
    }

    public void inserir(Jogador x) throws Exception {
        raiz = inserir(x, raiz);
    }
    private No inserir(Jogador x, No i) throws Exception {
        if (x.getAltura() % 15 == i.chave) {
            i.outro = inserirSegundaArvore(x.getNome(), i.outro);
        } else if (x.getAltura() % 15 < i.chave) {
            i.esq = inserir(x, i.esq);
        } else if (x.getAltura() % 15 > i.chave) {
            i.dir = inserir(x, i.dir);
        } else {
            throw new Exception("Erro ao inserir!");
        }
        return i;
    }
    private No2 inserirSegundaArvore(String x, No2 i) throws Exception {
        if (i == null) {
            i = new No2(x);
        } else if (x.compareTo(i.elemento) < 0) {
            i.esq = inserirSegundaArvore(x, i.esq);
        } else if (x.compareTo(i.elemento) > 0) {
            i.dir = inserirSegundaArvore(x, i.dir);
        } else {
            throw new Exception("Erro ao inserir!");
        }
        return i;
    }
}

public class Q02 {
  public static int comp;

  public static boolean isFim(String s) {
    return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
  }

  public static void main(String[] args) {
    Jogador jogadores[] = new Jogador[3922];
    ArvoreArvore arvoreJogadores = new ArvoreArvore();
    long time = System.currentTimeMillis();

    Jogador.ler(jogadores);

    // Criação da árvore com os jogadores lidos da entrada
    String id;
    do {
      id = MyIO.readLine();
      if (!isFim(id)) {
        try {
          arvoreJogadores.inserir(jogadores[Integer.parseInt(id)]);
        } catch (Exception e) {
          System.out.println("ERRO1");
        }
      }
    } while (!isFim(id));

    // Leitura e pesquisa dos nomes lidos da entrada
    String nome;
    do {
      nome = MyIO.readLine();
      if (!isFim(nome)) {
        try {
          System.out.print(nome + " raiz");
          if (arvoreJogadores.pesquisar(nome) == true)
            System.out.println(" SIM");
          else
            System.out.println(" NAO");
        } catch (Exception e) {
          System.out.println("ERRO2");
        }
      }
    } while (!isFim(nome));

    Arq.openWrite("589690_arvoreArvore.txt");

    Arq.println("Matricula: 589690\tTempo de execução: " + (System.currentTimeMillis() - time) + "ms"
        + "\tComparações: " + comp);

    Arq.close();
  }
}
