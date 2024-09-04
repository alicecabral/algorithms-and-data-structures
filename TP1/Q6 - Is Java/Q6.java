class Q6{
    public static boolean isVogal(String s){
        boolean resp = true;
        int j = s.length();

        for(int i = 0; i < j; i++){
            if(s.charAt(i) == 'a' || s.charAt(i) == 'e' ||
               s.charAt(i) == 'i' || s.charAt(i) == 'o' ||
               s.charAt(i) == 'u' || s.charAt(i) == 'A' ||
               s.charAt(i) == 'E' || s.charAt(i) == 'I' ||
               s.charAt(i) == 'O' || s.charAt(i) == 'U')
                resp = true;
            else{
                resp = false;
                i = j;
            }
        }
        return resp;
    }

    public static boolean isConsoante(String s){
        boolean resp = true;
        int j = s.length();

        for(int i = 0; i < j; i++){
            if((s.charAt(i) >= 48 && s.charAt(i) <= 57) ||
               s.charAt(i) == 'a' || s.charAt(i) == 'e' ||
               s.charAt(i) == 'i' || s.charAt(i) == 'o' ||
               s.charAt(i) == 'u' || s.charAt(i) == 'A' ||
               s.charAt(i) == 'E' || s.charAt(i) == 'I' ||
               s.charAt(i) == 'O' || s.charAt(i) == 'U'){
                resp = false;
                i = j;
            }
            else{
                resp = true;
            }
        }
        return resp;
    }

    public static boolean isInteiro(String s){
        boolean resp = true;
        int j = s.length();

        for(int i = 0; i < j; i++){
            if(s.charAt(i) >= 48 && s.charAt(i) <= 57)
                resp = true;
            else{
                resp = false;
                i = j;
            }
        }
        return resp;
    }

    public static boolean isReal(String s){
        boolean resp = true;
        int j = s.length();
        int cont = 0;

        for(int i = 0; i < j; i++){ // Verificando se tem . ou , e se tem mais de um
            if(s.charAt(i) == '.' || s.charAt(i) == ','){
                resp = true;
                cont++;
            }
        }

        for(int i = 0; i < j; i++){
            if((s.charAt(i) >= 65 && s.charAt(i) <= 90) || (s.charAt(i) >= 97 && s.charAt(i) <= 122))
                resp = false;
        }

        if(cont > 1)
            resp = false;
        
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

        //Para cada linha de entrada, gerando uma de saida.
        for(int i = 0; i < numEntrada; i++){
            if(isVogal(entrada[i]) == true)
                MyIO.print("SIM ");
            else
                MyIO.print("NAO ");

            if(isConsoante(entrada[i]) == true)
                MyIO.print("SIM ");
            else
                MyIO.print("NAO ");

            if(isInteiro(entrada[i]) == true)
                MyIO.print("SIM ");
            else
                MyIO.print("NAO ");

            if(isReal(entrada[i]) == true)
                MyIO.println("SIM");
            else
                MyIO.println("NAO");
        }
    }
}