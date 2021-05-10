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
public class QuickSort {

    public QuickSort() {
    }

    public Retorno quicksort(int arreglo[], int primero, int ultimo) {
        int central, i, j, pivote, cont=0;
        long ini, fin = 0;
        long listaTiempos[] = new long[arreglo.length];
        ini = System.currentTimeMillis();
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
                fin = System.currentTimeMillis() - ini;
                listaTiempos[cont] = fin;
                cont++;
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
        Retorno resutado = new Retorno(arreglo, listaTiempos);
        return resutado;
    }

}
