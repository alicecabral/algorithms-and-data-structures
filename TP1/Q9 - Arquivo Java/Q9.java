import java.io.*;

class Q9{
    public static void main(String[] args){
        try{
            RandomAccessFile acess = new RandomAccessFile("arquivo", "rw"); // Abrindo o arquivo
            double num, real;
            int numEntrada = MyIO.readInt();
            int i = 0;
            
            while (i < numEntrada){ // Lendo e salvando no arquivo
                num = MyIO.readDouble();
                acess.writeDouble(num);
                i++;
            }

            acess.close(); 

            acess = new RandomAccessFile("arquivo", "r"); // Reabertura do arquivo

            for (int j = numEntrada-1; j >= 0 ; j--){ // Lendo de trás para frente
                acess.seek(j*8);
                real = acess.readDouble();
                if (real - (long)real > 0) 
                    MyIO.println(real);
                else 
                    MyIO.println((long)real);
            }

            acess.close();
        }
        
        catch (Exception IOException){
        }
    }
}