import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

class CelulaQuadrupla {
    public int elemento;
    public CelulaQuadrupla prox, ant, sup, inf;
    
    public CelulaQuadrupla(){
       this(0, null, null, null, null);
    }

    public CelulaQuadrupla (int x){
       this (x, null, null, null, null);
    }

    public CelulaQuadrupla(int x, CelulaQuadrupla prox, CelulaQuadrupla ant, CelulaQuadrupla sup, CelulaQuadrupla inf){
       this.elemento = x;
       this.prox = prox;
       this.ant = ant;
       this.sup = sup;
       this.inf = inf;
    }

    public void setEelemento(int e){
       this.elemento = e;
    }

    public int getElemento(){
       return this.elemento;
    }
}

class Matriz {
    private CelulaQuadrupla primeira = new CelulaQuadrupla();
    private CelulaQuadrupla ultima;
    private CelulaQuadrupla auxiliar;
    private CelulaQuadrupla embaixo;
    public int linhas, colunas;
 
    public Matriz(){
       this(2, 2);
    }

    public Matriz (int linhas, int colunas){ 
       this.linhas = linhas;
       this.colunas = colunas;

       for (int i = 0; i < linhas; i++){
          for (int j = 0; j < colunas; j++){
             // preenchendo a primeira linha:
             if (i == 0){
                if (j == 0){
                   this.primeira = new CelulaQuadrupla();
                   embaixo = primeira;
                   auxiliar = primeira;
                } else {
                   CelulaQuadrupla celula = new CelulaQuadrupla(); 
                   auxiliar.prox = celula; 
                   auxiliar.prox.ant = auxiliar; 
                   auxiliar = auxiliar.prox; 
                }
             
             // criando o restante das linhas
             }
             else{ 
                CelulaQuadrupla celula = new CelulaQuadrupla(); 
                auxiliar.inf = celula; 
                celula.sup = auxiliar; 
  
                if (j != 0){
                   auxiliar.ant.inf.prox = celula; 
                   celula.ant = auxiliar.ant.inf; 
                }
                if (auxiliar.prox != null)
                   auxiliar = auxiliar.prox;
             }
          } 
          if (i != 0){
             embaixo = embaixo.inf;
          }
 
          auxiliar = embaixo;
       }   
    }

    public void imprimir() {
       CelulaQuadrupla i;
       CelulaQuadrupla k = primeira;

       for (int l = 0; l < linhas; k = k.inf, l++){
          i = k;
          for (int j = 0; j < colunas; i = i.prox, j++){ 
             MyIO.print(i.getElemento() + " ");
          }
 
          MyIO.println("");
       }
    }

    public void set() {
       int elemento = 0;
       CelulaQuadrupla PercorrerColuna = new CelulaQuadrupla();
       CelulaQuadrupla PercorrerLinha = primeira;
       
       for (int i = 0; i < this.linhas; i++, PercorrerLinha = PercorrerLinha.inf){
          PercorrerColuna = PercorrerLinha;
          for (int j = 0; j < this.colunas; j++, PercorrerColuna = PercorrerColuna.prox){
             elemento = MyIO.readInt();
             PercorrerColuna.setEelemento(elemento);
          }
       } 
    }

    public int get(int pLinha, int pColuna){
       int resp = 0;
       CelulaQuadrupla i;
       CelulaQuadrupla k = primeira;
       
       for (int l = 0; l < pLinha; k = k.inf, l++){
          i = k;
          for (int j = 0; j < pColuna; i = i.prox, j++){
             MyIO.println (i.getElemento());
             resp = i.getElemento();
          }
       }
       return resp;
    }

    public Matriz soma (Matriz m){
       Matriz resp = new Matriz(this.linhas, this.colunas);
     
       if(this.linhas == m.linhas && this.colunas == m.colunas){
          CelulaQuadrupla A1 = this.primeira; // percorre as linhas da matriz 1
          CelulaQuadrupla A2 = A1; // percorre as colunas da matriz 1
        
          CelulaQuadrupla B1 = m.primeira; // percorre as colunas da matriz 2
          CelulaQuadrupla B2 = B1; // percorre as colunas da matriz 2
 
          CelulaQuadrupla C1 = resp.primeira;
          CelulaQuadrupla C2 = C1;
          
 
          for (int i = 0; i < this.linhas; i++, A1 = A1.inf, B1 = B1.inf, C1 = C1.inf ){
             A2 = A1;
             B2 = B1;
             C2 = C1;
             for (int j = 0; j < this.colunas; j++, A2 = A2.prox, B2 = B2.prox, C2 = C2.prox){
                 C2.elemento = A2.elemento + B2.elemento;
             }
          }
       }
       return resp;
    }

    public Matriz multiplicacao(Matriz m) { 
       Matriz resp = new Matriz(this.linhas, this.colunas);
     
       if(this.linhas == m.linhas && this.colunas == m.colunas){
          CelulaQuadrupla A = this.primeira;
          CelulaQuadrupla i = A;
        
          CelulaQuadrupla B = m.primeira;
          CelulaQuadrupla j = B;         
          CelulaQuadrupla manterB = B; 
 
          CelulaQuadrupla C = resp.primeira;
          CelulaQuadrupla manterC = C;
          CelulaQuadrupla aux = C;  
          
          int elemento = 0;

          for (int conti = 0; conti < this.linhas; A = A.inf, B = manterB, C = manterC.inf, conti++){
             manterB = B;
             manterC = C;

             for(int cont = 0; cont < this.colunas; cont++, B = B.prox, C = C.prox){      
                i = A;
                j = B;
                aux = C;
                elemento = 0;
        
                for( ; i != null; i = i.prox, j = j.inf){
                   elemento = elemento + (i.elemento * j.elemento);
                }
           
                aux.elemento = elemento;
                aux = aux.prox;
             }
          }
       }
       return resp;
    }

    public void mostrarDiagonalPrincipal(){
        if (isQuadrada()){
           for (CelulaQuadrupla i = primeira; i != null; i = i.inf){
              MyIO.print (i.getElemento() + " ");
              if (i.prox != null){
                 i = i.prox;
              }
           }
           MyIO.println ("");
        }         
    }

    public void mostrarDiagonalSecundaria(){
        if (isQuadrada()){
           CelulaQuadrupla i = primeira;

           for ( ; i.prox != null; i = i.prox); 
           for ( ; i != null; i = i.inf){
              MyIO.print (i.elemento + " ");
              if (i.ant != null){
                 i = i.ant;
              }
           }
           MyIO.println ("");
        }        
    }

    public boolean isQuadrada(){
        return (this.linhas == this.colunas);
    }
}

class Q09 {
    public static void main (String[] args){
       int numeroTestes = MyIO.readInt();
 
       for (int i = 0; i < numeroTestes; i++){
          int linhas = MyIO.readInt();
          int colunas = MyIO.readInt();
       
          Matriz matriz = new Matriz(linhas, colunas);
          matriz.set();
       
          linhas = MyIO.readInt();
          colunas = MyIO.readInt();
       
          Matriz matriz2 = new Matriz(linhas, colunas);
          matriz2.set();
          
          matriz.mostrarDiagonalPrincipal();
          matriz.mostrarDiagonalSecundaria();
 
          Matriz resp = matriz.soma(matriz2);
          resp.imprimir();
  
          resp = matriz.multiplicacao(matriz2);
          resp.imprimir();
       }
    }
}
