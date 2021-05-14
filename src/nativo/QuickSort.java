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
public class QuickSort {

    public QuickSort() {
    }

    Retorno resutado = new Retorno();
    private long tiempos[];

    public long[] getTiempos() {
        return tiempos;
    }

    public void setTiempos(long[] tiempos) {
        this.tiempos = tiempos;
    }

    public void asignarTamnio(int arreglo) {
        tiempos = new long[arreglo];
    }
    int cont = 0;

    public Retorno quicksort(int arreglo[], int primero, int ultimo) {
        int central, i, j, pivote;
        long ini, fin = 0;
        ArrayList listaTiempos = new ArrayList(arreglo.length);
        ini = System.nanoTime();
        central = (primero + ultimo) / 2;
        pivote = arreglo[central];
        i = primero;
        j = ultimo;
        do {
            while (arreglo[i] < pivote) {
                i++;
            }
            while (arreglo[j] > pivote) {
                j--;
            }
            if (i <= j) {
                int temp;
                temp = arreglo[i];
                arreglo[i] = arreglo[j];
                /*intercambia A[i] con A[j] */
                arreglo[j] = temp;
                i++;
                j--;
                fin = System.nanoTime() - ini;
                this.tiempos[i] = fin;
            }
        } while (i <= j);

        if (primero < j) {
            quicksort(arreglo, primero, j);
            /*mismo proceso con sublista izquierda*/
        }
        if (i < ultimo) {
            quicksort(arreglo, i, ultimo);
            /*mismo proceso con sublista derecha*/
        }

        return resutado = new Retorno(arreglo, tiempos);
    }

}
