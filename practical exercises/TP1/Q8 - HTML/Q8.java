import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Q8{
    public static String getHtml(String endereco){
        URL url;
        InputStream is = null;
        String resp = "", line;

        try{
            url = new URL(endereco);
            is = url.openStream(); // throws an IOException

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))){
                line = br.readLine();

                while (line != null){
                    resp += line + "\n";
                    line = br.readLine();
                }
            }
        }

        catch (IOException e){
            e.printStackTrace();
        }

        finally{
            if (is != null){
                try{
                    is.close();
                }

                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        return resp;
    }

    public static boolean isFim(String str){
        return str.length() >= 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I'
                && str.charAt(2) == 'M';
    }

    /**
     * Encontra o índice de uma substring numa string.
     * 
     * @param substr Substring a ser procurada.
     * @param str String a ser percorrida.
     * @param start Índice inicial da busca.
     * 
     * @return -1 caso {@code substr} não esteja em {@code str}. Caso contrário,
     * retorna o índice onde {@code substr} começa em {@code str}.
     */
    public static int indexOf(String substr, String str, int start){
        int substrIndex = 0;
        int index = -1;

        if (substr.isEmpty() && !str.isEmpty()) index = 0;

        for (int i = start; substrIndex < substr.length() && i < str.length(); i++){
            if (str.charAt(i) == substr.charAt(substrIndex)){
                if (substrIndex == 0)
                    index = i;

                substrIndex++;
            }

            else if (str.charAt(i) == substr.charAt(0)){
                index = i;
                substrIndex = 1;
            }

            else{
                index = -1;
                substrIndex = 0;
            }
        }

        return substrIndex == substr.length() ? index : -1;
    }

    /**
     * Conta quantas vezes {@code substr} está em {@code str}.
     * 
     * @param substr Substring a ser contada.
     * @param str String onde {@code substr} será procurada.
     * 
     * @return quantas vezes {@code substr} está em {@code str}. 
     */
    public static int countSubstr(String substr, String str){
        int count = 0;
        int index = indexOf(substr, str, 0);

        while (index != -1){
            count++;
            index = indexOf(substr, str, index + substr.length());
        }

        return count;
    }

    /**
     * Percorre {@code charsToCount} e, para cada caractere, conta quantas vezes
     * ele aparece em {@code str}, acumulando o resultado de cada caractere.
     * 
     * @param charsToCount Caracteres a serem contados.
     * @param str String a ser percorrida.
     * 
     * @return a soma das vezes que os caracteres apareceram.
     */
    public static int countChars(String charsToCount, String str){
        int count = 0;

        for (int i = 0; i < charsToCount.length(); i++)
            count += countSubstr("" + charsToCount.charAt(i), str);

        return count;
    }

    public static void main(String[] args){
        //MyIO.setCharset("UTF-8");
        String nome = MyIO.readLine();
        String endereco;
        String html;
        final String consoantesMinusculas = "bcdfghjklmnpqrstvwxyz";

        while (!isFim(nome)){
            endereco = MyIO.readLine();
            html = getHtml(endereco);

            int numBrs = countSubstr("<br>", html);
            int numTables = countSubstr("<table>", html);

            MyIO.print("a(" + (countSubstr("a", html) - numTables) + ")");
            MyIO.print(" e(" + (countSubstr("e", html) - numTables) + ")");
            MyIO.print(" i(" + countSubstr("i", html) + ")");
            MyIO.print(" o(" + countSubstr("o", html) + ")");
            MyIO.print(" u(" + countSubstr("u", html) + ")");

            MyIO.print(" á(" + countSubstr("" + (char)225, html) + ")");
            MyIO.print(" é(" + countSubstr("" + (char)233, html) + ")");
            MyIO.print(" í(" + countSubstr("" + (char)237, html) + ")");
            MyIO.print(" ó(" + countSubstr("" + (char)243, html) + ")");
            MyIO.print(" ú(" + countSubstr("" + (char)250, html) + ")");

            MyIO.print(" à(" + countSubstr("" + (char)224, html) + ")");
            MyIO.print(" è(" + countSubstr("" + (char)232, html) + ")");
            MyIO.print(" ì(" + countSubstr("" + (char)236, html) + ")");
            MyIO.print(" ò(" + countSubstr("" + (char)242, html) + ")");
            MyIO.print(" ù(" + countSubstr("" + (char)249, html) + ")");

            MyIO.print(" ã(" + countSubstr("" + (char)227, html) + ")");
            MyIO.print(" õ(" + countSubstr("" + (char)245, html) + ")");

            MyIO.print(" â(" + countSubstr("" + (char)226, html) + ")");
            MyIO.print(" ê(" + countSubstr("" + (char)234, html) + ")");
            MyIO.print(" î(" + countSubstr("" + (char)238, html) + ")");
            MyIO.print(" ô(" + countSubstr("" + (char)244, html) + ")");
            MyIO.print(" û(" + countSubstr("" + (char)251, html) + ")");

            MyIO.print(" consoante(" + (countChars(consoantesMinusculas, html) - 3 * numTables - 3 * numBrs)+ ")");

            MyIO.print(" <br>(" + numBrs + ")");
            MyIO.print(" <table>(" + numTables + ")");
            MyIO.println(" " + nome);

            nome = MyIO.readLine();
        }
    }
}
