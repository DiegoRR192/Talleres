/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nativo;

import java.io.*;

/**
 *
 * @author CARITO
 */
public class LecturaArchivo {

    FileReader fr = null;
    BufferedReader br = null;

    public Retorno leerArchivo(String nombreDocumento) {
        try {
            fr = new FileReader(nombreDocumento);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            int cont = 0;
            while ((linea = br.readLine()) != null) {
                cont++;
            }
            int[] datos = new int[cont];
            long[] tiempos = new long[cont];
            cont=0;
            while ((linea = br.readLine()) != null) {
                String informacion[] = br.readLine().split("/");
                datos[cont] = Integer.parseInt(informacion[0]);
                tiempos[cont] = Long.parseLong(informacion[1]);
                System.out.println(informacion[0] + informacion[1]);
                cont++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        Retorno resultado = new Retorno(datos, tiempos);
        return resultado;
    }
}
