class Q15{
    public static boolean isVogal(String s){
        return isVogal(s, 0);
    }
    public static boolean isConsoante(String s){
        return isConsoante(s, 0);
    }
    public static boolean isInteiro(String s){
        return isInteiro(s, 0);
    }
    public static boolean isReal(String s){
        return isReal(s, 0, 0);
    }
    
    public static boolean isVogal(String s, int i){
        boolean resp = true;

        if(i < s.length()){
            if(s.charAt(i) == 'a' || s.charAt(i) == 'e' ||
                s.charAt(i) == 'i' || s.charAt(i) == 'o' ||
                s.charAt(i) == 'u' || s.charAt(i) == 'A' ||
                s.charAt(i) == 'E' || s.charAt(i) == 'I' ||
                s.charAt(i) == 'O' || s.charAt(i) == 'U')
                resp = isVogal(s, i+1);
            else
                resp = false;
        }
        return resp;
    }

    public static boolean isConsoante(String s, int i){
        boolean resp = true;

        if(i < s.length()){
            if((s.charAt(i) >= 48 && s.charAt(i) <= 57) ||
                s.charAt(i) == 'a' || s.charAt(i) == 'e' ||
                s.charAt(i) == 'i' || s.charAt(i) == 'o' ||
                s.charAt(i) == 'u' || s.charAt(i) == 'A' ||
                s.charAt(i) == 'E' || s.charAt(i) == 'I' ||
                s.charAt(i) == 'O' || s.charAt(i) == 'U')
                resp = false;
            else
                resp = isConsoante(s, i+1);
        }
        return resp;
    }

    public static boolean isInteiro(String s, int i){
        boolean resp = true;

        if(i < s.length()){
            if(s.charAt(i) >= 48 && s.charAt(i) <= 57)
                resp = isInteiro(s, i+1);
            else
                resp = false;
        }
        return resp;
    }

    public static boolean isReal(String s, int i, int cont){
        boolean resp = false;

        if(i < s.length()){
            if(s.charAt(i) == '.' || s.charAt(i) == ','){
                resp = isReal(s, i+1, cont++);
            }
            
            if((s.charAt(i) >= 65 && s.charAt(i) <= 90) || (s.charAt(i) >= 97 && s.charAt(i) <= 122))
                resp = isReal(s, i+1, cont++);

            if(cont > 1)
                resp = false;
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