/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nativo;

/**
 *
 * @author CARITO
 */
public class Insercion {

    public Insercion() {
    }
    
    public Retorno insercion(int[] arreglo) {
        long ini, fin;
        ini = System.currentTimeMillis();
        int pos = 0, aux = 0;
        long listaTiempos[] = new long[arreglo.length];
        
        for (int i = 0; i < arreglo.length; i++) {
            pos = i;
            aux = arreglo[i];
            while ((pos > 0) && (arreglo[pos - 1] > aux)) {
                arreglo[pos] = arreglo[pos - 1];
                pos--;
            }
            fin = System.currentTimeMillis() - ini;
            listaTiempos[i] = fin; 
            arreglo[pos] = aux;
        }
        Retorno resutado = new Retorno(arreglo, listaTiempos);
        return resutado;
    }

}
