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

class CelulaDupla {
	public Jogador elemento;
	public CelulaDupla ant;
	public CelulaDupla prox;

	public static Jogador jog= new Jogador();  //criar um novo objeto de Jogador padrão para ser inserido quando o método for chamado
	public CelulaDupla() {
		this(jog);
	}

	public CelulaDupla(Jogador elemento) {
		this.elemento = elemento;
		this.ant = this.prox = null;
	}
}


class ListaDupla {
	private CelulaDupla primeiro;
	private CelulaDupla ultimo;

	public ListaDupla() {
		primeiro = new CelulaDupla();
		ultimo = primeiro;
	}

	public void inserirInicio(Jogador x) {
		CelulaDupla tmp = new CelulaDupla(x);

      tmp.ant = primeiro;
      tmp.prox = primeiro.prox;
      primeiro.prox = tmp;
      if(primeiro == ultimo){
         ultimo = tmp;
      }else{
         tmp.prox.ant = tmp;
      }
      tmp = null;
	}

	public void inserirFim(Jogador x) {
		ultimo.prox = new CelulaDupla(x);
      ultimo.prox.ant = ultimo;
		ultimo = ultimo.prox;
	}

	public Jogador removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

      CelulaDupla tmp = primeiro;
		primeiro = primeiro.prox;
		Jogador resp = primeiro.elemento;
        tmp.prox = primeiro.ant = null;
        tmp = null;
		return resp;
	}

	public Jogador removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} 
      Jogador resp = ultimo.elemento;
      ultimo = ultimo.ant;
      ultimo.prox.ant = null;
      ultimo.prox = null;
	  return resp;
	}
	
   public void inserir(Jogador x, int pos) throws Exception {

      int tamanho = tamanho();

      if(pos < 0 || pos > tamanho){
			throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
      } else if (pos == 0){
         inserirInicio(x);
      } else if (pos == tamanho){
         inserirFim(x);
      } else {
		   // Caminhar ate a posicao anterior a insercao
         CelulaDupla i = primeiro;
         for(int j = 0; j < pos; j++, i = i.prox);
		
         CelulaDupla tmp = new CelulaDupla(x);
         tmp.ant = i;
         tmp.prox = i.prox;
         tmp.ant.prox = tmp.prox.ant = tmp;
         tmp = i = null;
      }
   }

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
         CelulaDupla i = primeiro.prox;
         for(int j = 0; j < pos; j++, i = i.prox);
		
         i.ant.prox = i.prox;
         i.prox.ant = i.ant;
         resp = i.elemento;
         i.prox = i.ant = null;
         i = null;
      }

		return resp;
	}

	public void mostrar() {
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
            System.out.print("["+ i.elemento.getId() +" ## " + i.elemento.getNome() + " ## " + i.elemento.getAltura() 
            + " ## " + i.elemento.getPeso() + " ## " + i.elemento.getAnoNascimento() + " ## " + 
            i.elemento.getUniversidade() + " ## " + i.elemento.getCidadeNascimento()  + " ## " + 
            i.elemento.getEstadoNascimento() + "]\n" );
		}
	}

	public boolean pesquisar(Jogador x) {
		boolean resp = false;
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
         if(i.elemento.getId() == x.getId()){
            resp = true;
            i = ultimo;
         }
		}
		return resp;
	}

   public int tamanho() {
      int tamanho = 0; 
      for(CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++);
      return tamanho;
   }
   
   // QUICKSORT

    public void swap(CelulaDupla i, CelulaDupla j) {
        Jogador tmp = j.elemento ;
        j.elemento = i.elemento;
        i.elemento = tmp;
    }

    public void quicksort(){
        CelulaDupla i=primeiro.prox;
        CelulaDupla j=ultimo;
        quicksort(i,j,0, tamanho()-1);
    }
    //parâmetros indexesq, e indexdir funcionam como contadores para contar qual posição da lista estamos analisando 
     public void quicksort(CelulaDupla esq, CelulaDupla dir, int indexesq, int indexdir) {
            int indexI = indexesq;  
            int indexJ = indexdir;
            CelulaDupla i=esq;
            CelulaDupla j=dir;
            Jogador pivo = dir.elemento;
            while (indexI <= indexJ) {
                while (i.elemento.getEstadoNascimento().compareTo(pivo.getEstadoNascimento())<0 || 
                i.elemento.getEstadoNascimento().compareTo(pivo.getEstadoNascimento())==0 && 
                i.elemento.getNome().compareTo(pivo.getNome())<0) 
               {
                   i=i.prox;
                   indexI++;

               } 
                while (j.elemento.getEstadoNascimento().compareTo(pivo.getEstadoNascimento())>0 || 
                j.elemento.getEstadoNascimento().compareTo(pivo.getEstadoNascimento())==0 && 
                j.elemento.getNome().compareTo(pivo.getNome())>0) 
                {
                     j=j.ant;
                     indexJ--;
                }
                if (indexI <= indexJ) {
                    swap(i, j);
                    indexI++;
                    i=i.prox;
                    j=j.ant;
                    indexJ--;
                }
            }
           if ( indexesq< indexJ)  quicksort(esq,j, indexesq, indexJ);
           if (indexI < indexdir)  quicksort(i,dir,indexI, indexdir);
        
    }
}

class Q11 {
    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main (String[] args){
        ListaDupla listaDuplaJogadores = new ListaDupla(); 
        Jogador jogadores[] = new Jogador[3922];

        Jogador.ler(jogadores);

        // Criação da lista com os jogadores lidos da entrada
        String id;
        do {
            id = MyIO.readLine();
            if (!isFim(id)){
                try {
                    listaDuplaJogadores.inserirFim(jogadores[Integer.parseInt(id)]);
                }
                catch (Exception e) {
                    System.out.println("ERRO1");
                }
            }
        } while (!isFim(id));

        listaDuplaJogadores.quicksort();
        listaDuplaJogadores.mostrar();
    }
}
