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

    public void escribirArchivo(long[] tiempos, int[] datos, String tipo) {
        try {
            PrintStream ps = new PrintStream(tipo + ".txt");
            for (int i = 0; i < tiempos.length; i++) {
                ps.println(datos[i] + "/" + tiempos[i]);
            }
            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
