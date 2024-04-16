package arquivos;

import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;

//... classe Entrada (possui métodos para entrada de dados) ....
class Entrada
{
    public static String leString(String msg)
    {
        String line = "";
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try
        {
            System.out.println(msg);
            line = br.readLine();
        } catch (Exception e)
        {  }
        return line;
    }

    public static int leInteger(String msg)
    {
        String line = "";
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try
        {
            System.out.println(msg);
            line = br.readLine();
            int retorno = Integer.valueOf(line);
            return retorno;
        } catch (Exception e)
        {  return -1; }
    }
}
