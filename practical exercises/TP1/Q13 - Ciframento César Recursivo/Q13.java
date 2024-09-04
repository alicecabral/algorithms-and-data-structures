class Q13{
    public static String ciframentoCesar(String s){
        int[] stringCifrada = new int [s.length()];
        return ciframentoCesar(s, 0, stringCifrada);
    }

    public static String arrayToString(int[] stringCifrada, int pos){
        if(pos < stringCifrada.length){
            return "";
        }
        else{
            return (char)stringCifrada[pos] + arrayToString(stringCifrada, pos+1);
        }
    }

    public static String ciframentoCesar(String s, int i, int[] stringCifrada){
        String resp = "";

        if (i == s.length()){
            resp = arrayToString(stringCifrada, 0);
        }
        else{
            stringCifrada[i] = ((int) s.charAt(i)) + 3;
            resp = (char)stringCifrada[i] + ciframentoCesar(s, i+1, stringCifrada);   
        }
        return resp;
    }

    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    public static void main(String[] args){
        String entrada[] = new String[1000];
         int numEntrada = 0;
 
         do {
             entrada[numEntrada] = MyIO.readLine(); 
         } while (isFim(entrada[numEntrada++]) == false);
         numEntrada--; //Desconsiderar última linha com FIM
 
         //Para cada linha de entrada, gerando uma de saida contendo a palavra criptografada.
         for(int i = 0; i < numEntrada; i++){
             MyIO.println(ciframentoCesar(entrada[i]));
         }
     }
}