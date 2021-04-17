package nativo;


import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Santiago Vargas
 */
public class escrituraArchivo {

    FileWriter fichero = null;
    PrintWriter pw = null;
    
    public void escribirArchivo(long[] tiempos,int[] datos, String tipo){
        try
        {
            PrintStream ps = new PrintStream(tipo+".txt"); 
            for(int i = 0; i < tiempos.length; i++)
            {
                ps.println(i+"/" + tiempos[i]);
            }
            ps.close();
              
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    /*public void escribirArchivo(Vector<Long> vectNum){
        try
        {
            fichero = new FileWriter("numeros.txt");
            pw = new PrintWriter(fichero);

            for (int i = 0; i < vectNum.size(); i++)
                pw.print(vectNum.get(i)+";");
            
           

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }}
    }
    */
}
