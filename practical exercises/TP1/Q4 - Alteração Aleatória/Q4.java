import java.util.Random;

class Q4{
   public static boolean isFim (String s){
      return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
   }

   public static String Troca(String s, char primeiraLetra, char segundaLetra){
      String nova = "";
      for (int i = 0; i < s.length(); i++){
         if (s.charAt(i) == primeiraLetra)  
            nova += segundaLetra;
         else
            nova += s.charAt(i);
      }

      return nova;
   }

   public static char AlteracaoAleatoria(Random gerador){
      char letra = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
      return letra;
   }

   public static void main (String[] args) { 
      String[] entrada = new String[2000];
         
      Random gerador = new Random();
      gerador.setSeed(4);
                     
      int i = 0;

      do{
         entrada[i] = MyIO.readLine();
      }while (!(isFim(entrada[i++])));
      i--;
      
      char primeiraLetra;
      char segundaLetra;

      for (int cont = 0; cont < i; cont++) {
         primeiraLetra = AlteracaoAleatoria(gerador);
         segundaLetra = AlteracaoAleatoria(gerador);

         MyIO.println (Troca(entrada[cont], primeiraLetra, segundaLetra));   
      }
    
   }
}
