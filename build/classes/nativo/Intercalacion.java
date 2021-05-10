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
public class Intercalacion {

    public Intercalacion() { 
    }

    public void ordenar(int n, Long[] v) {
        //times.add(((ti = System.nanoTime()) - ti) / 1000000);
        for (int k = 1; k <= n; k++) {
            ///Preparar
            this.array = Arrays.copyOfRange(v, 0, k);
            this.length = this.array.length;
            this.tempMergArr = new Long[length];
            Long ini = System.nanoTime() / 1000000;
            //Ordenar
            doMergeSort(0, length - 1);
            ///
            Long fin = System.nanoTime() / 1000000;
            times.add(fin - ini);
        }
    }

    /**
     * **
     * @descripcion genera submatrices de tamanio mas pequenio para realizar el
     * ordenamiento intercalado
     * @param lowerIndex
     * @param higherIndex
     */
    private void doMergeSort(int lowerIndex, int higherIndex) {

        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
            // Ordena el lado izquierdo del arreglo
            doMergeSort(lowerIndex, middle);
            // Oordena el lado derecho del arreglo
            doMergeSort(middle + 1, higherIndex);
            // Une ambos lados
            mergeParts(lowerIndex, middle, higherIndex);
        }
    }

    /**
     * **
     * @descripcion une las submatrices que se generaron para hacer el
     * ordenamiento intercalado
     * @param lowerIndex
     * @param middle
     * @param higherIndex
     */
    private void mergeParts(int lowerIndex, int middle, int higherIndex) {

        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = array[i];
        }
        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;
        while (i <= middle && j <= higherIndex) {
            if (tempMergArr[i] <= tempMergArr[j]) {
                array[k] = tempMergArr[i];
                i++;
            } else {
                array[k] = tempMergArr[j];
                j++;
            }
            k++;
        }
        while (i <= middle) {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }
    }
}
