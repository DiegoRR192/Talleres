/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nativo;

import java.applet.Applet;
import java.awt.*;

import javax.swing.*;

/*import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
/**
 *
 * @author CARITO
 */
public class BurbujaVSBurbujaMejorado_1 extends Applet {

    int numeroDatos;
    int[] datos;
    JTextField datosJT;
    Retorno datosTiempos;

    @Override
    public void init() {
        setSize(1200, 760);
        datosJT = new JTextField();
        Object[] muestra = {"Datos", datosJT};
        int panel = JOptionPane.showConfirmDialog(null, muestra, "Ingrese la cantidad de datos para ejecucion", JOptionPane.OK_CANCEL_OPTION);
        if (panel == JOptionPane.OK_OPTION) {
            datosJT.setText(datosJT.getText());
        } else {
            datosJT.setText("0");
        }
        numeroDatos = Integer.parseInt(datosJT.getText());
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Tahoma", Font.ITALIC, 15));
        g.drawString("Datos (cantidad)", 500, 550);
        g.drawString("tiempo (segundos)", 0, 250);
        g.drawLine(200, 500, 200, 50);
        g.drawLine(200, 500, 900, 500);
        g.setFont(new Font("Tahoma", Font.BOLD, 10));

        //Acomodar titulos del numero de datos en el eje X
        for (int i = 1, j = 850; i <= 14; i++, j = j - 50) {
            g.drawString(numeroDatos / i + "", j, 520);
        }

     
        datosTiempos = ejecucionBurbuja();
        
        dibujaFuncion1(datosTiempos.getDatos(), datosTiempos.getTiempos());
        
        
        //Acomodar titulos del numero de datos en el eje Y
        g.setColor(Color.BLACK);
        g.setFont(new Font("Tahoma", Font.BOLD, 10));
        long ultimoDato = datosTiempos.getTiempos()[numeroDatos - 2];
        for (int i = 1, j = 100; i <= 10; i++, j = j + 42) {
            g.drawString(ultimoDato / i + "", 150, j);
        }
        getAppletContext().showStatus("Grafica lista.");
    }

    public void dibujaFuncion1(int[] vPrincipal1, long[] vTiempo1) {
        Graphics g = getGraphics();
        //dibujaEjes(g, datos);
        Graphics g1 = getGraphics();
        g1.setColor(Color.RED);
        long escalaTamanopulso = 1000000;
       // int escalaTamanopulso = (int) vTiempo1[vTiempo1.length - 3];
        double escalaX = 1;
        double escalaY = 1;
        for (int i = 0; i < vPrincipal1.length - 1; i++) {
            if (vTiempo1[i] == 0) {
            } else {
                double xinicial = 200 + vPrincipal1[i] * escalaX;
                double xfinal = 200 + vPrincipal1[i + 1] * escalaX;
        //        double yinicial = 500 - vTiempo1[i] * escalaY;
        //        double yfinal = 500 - vTiempo1[i + 1] * escalaY;
               
                long yinicial = (( vTiempo1[i] / escalaTamanopulso) * -1) + (500 - (i / 4));
                long yfinal = (( vTiempo1[i + 1] / escalaTamanopulso) * -1) + (500 - (i / 4));
                g1.drawOval((int) xinicial,(int) yinicial, 0, 0);
                g1.drawOval((int) xfinal,(int) yfinal, 0, 0);
              //  g1.drawLine((int) xinicial,(int) yinicial,(int) xfinal,(int) yfinal);        // drawOval(x,y,ancho,alto)
            }
        }
        Graphics g2 = getGraphics();
        g1.setColor(Color.RED);
    
    }
      

    public Retorno ejecucionBurbuja() {
        // Variables
        Retorno informacion = new Retorno();
        Burbuja burbuja = new Burbuja();
        escrituraArchivo escribo = new escrituraArchivo();

        //llenar datosJT
        datos = informacion.rellenarDatos(numeroDatos);
        getAppletContext().showStatus("Datos creados");

        // organizar los datosJT anteriores
        informacion = burbuja.BurbujaClasica(datos);
        getAppletContext().showStatus("Datos ordenados");

        //Escribir datosJT en archivo
        escribo.escribirArchivo(informacion.getTiempos(), informacion.getDatos(), "BurbujaNormal");
        getAppletContext().showStatus("Datos en documento .txt");

        return informacion;
    }

    public int[] organizarSegundos(long[] tiempos) {

        int[] resultado = new int[tiempos.length];

        for (int i = 1; i < tiempos.length; i++) {
            if (tiempos[i] != tiempos[i - 1]) {
                resultado[i] = (int) tiempos[i];
                System.out.println("- " + tiempos[i]);
            }
        }
        return resultado;
    }
}
