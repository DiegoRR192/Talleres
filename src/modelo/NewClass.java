/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import nativo.Burbuja;
import nativo.BurbujaMejorado;
import nativo.Retorno;
import nativo.escrituraArchivo;

/**
 *
 * @author CARITO
 */
public class NewClass {

    public static void main(String[] args) {
        nativo.BurbujaMejorado bur = new BurbujaMejorado();
        nativo.escrituraArchivo escribo = new escrituraArchivo();
        int datos[] = new int[50000];
        Retorno datosTiempos;
        
        for (int i = 0; i < 50000; i++) {
        int x = (int) (Math.random() * 299 + 1);
        datos[i] = x;
        }
        System.out.println("Datos llenos");
        
        datosTiempos = bur.BurbujaMejorado(datos);
        
       // for (int i = 0; i < datosTiempos.getTiempos().length; i++) {
        //System.out.println(datosTiempos.getDatos()[i]);
       // }
        
        System.out.println(datosTiempos.getTiempos().length);
        escribo.escribirArchivo(datosTiempos.getTiempos(), datosTiempos.getDatos(),"prueba");
        
        ArrayList resultado = new ArrayList(datosTiempos.getTiempos().length);

        for (int i = 1; i < datosTiempos.getTiempos().length; i++) {
            if (datosTiempos.getTiempos()[i] != datosTiempos.getTiempos()[i - 1]) {
                resultado.add(datosTiempos.getTiempos()[i]);
                System.out.println("- "+ datosTiempos.getTiempos()[i]);
            }
        }
        System.out.println("tamaño " + resultado.size());
    }
}
