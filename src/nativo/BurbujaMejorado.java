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
public class BurbujaMejorado {

    public BurbujaMejorado() {
    }

    public Retorno BurbujaMejorado(int[] arreglo) {
        long ini, fin;
        int p = 0, j;
        boolean bandera = false;
        long listaTiempos[] = new long[arreglo.length];
        ini = System.currentTimeMillis();
        
        while (bandera != true) {
            fin = System.currentTimeMillis() - ini;
            listaTiempos[p] = fin; 
            bandera = true;
            for (j = 0; j < ((arreglo.length - p) - 1); j++) {
                if (arreglo[j] > arreglo[j + 1]) {
                    bandera = false;
                    int aux = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = aux;
                }
            }
            p++;
        }
        Retorno resutado = new Retorno(arreglo, listaTiempos);
        return resutado;
    }
}
