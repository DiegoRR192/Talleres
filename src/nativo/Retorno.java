/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nativo;

import java.util.ArrayList;

/**
 *
 * @author CARITO
 */
public class Retorno {

    private int[] datos;
    private long[] tiempos;

    public Retorno() {
    }

    public Retorno(int[] datos, long[] tiempos) {
        this.datos = datos;
        this.tiempos = tiempos;
    }

    public int[] getDatos() {
        return datos;
    }

    public void setDatos(int[] datos) {
        this.datos = datos;
    }

    public long[] getTiempos() {
        return tiempos;
    }

    public void setTiempos(long[] tiempos) {
        this.tiempos = tiempos;
    }

    public int[] rellenarDatos(int tamaño) {
        int datos[] = new int[tamaño];

        for (int i = 0; i < tamaño; i++) {
            int x = (int) (Math.random() * 499 + 1);
            datos[i] = x;
        }
        return datos;
    }
    
    public long[] rellenarDatosLista(int tamaño) {
        long datos[] = new long[tamaño];

        for (int i = 0; i < tamaño; i++) {
            long x = (long) (Math.random() * 499 + 1);
            datos[i] = x;
        }
        return datos;
    }

    public long[] convertirArrayAList(ArrayList array){
        long resultado[] = new long[array.size()];
        int i;
        
        for( i = 0; i < array.size() ;  i++){
            resultado[i] = (long) array.get(i);
            i++;            
        }
        
        return resultado;
    }
}
