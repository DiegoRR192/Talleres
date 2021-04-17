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
public class Burbuja {

    public Burbuja() {
    }

    public Retorno BurbujaClasica(int vecb[]) {
        long ini, fin = 0;
        int i, j, aux;
        long listaTiempos[] = new long[vecb.length];
        Retorno respuesta;
        ini = System.nanoTime();
        for (i = 0; i < (vecb.length - 1); i++) {
            for (j = 0; j < (vecb.length - 1); j++) {
                if (vecb[j] > vecb[j + 1]) {
                    aux = vecb[j];
                    vecb[j] = vecb[j + 1];
                    vecb[j + 1] = aux;
                }
            }
            fin = System.nanoTime()- ini;
            listaTiempos[i] = fin;
        }
        respuesta = new Retorno(vecb, listaTiempos);
        return respuesta;
    }

}