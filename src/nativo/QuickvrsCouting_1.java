package nativo;

import java.applet.Applet;
import java.awt.*;
import javax.swing.*;

public class QuickvrsCouting_1 extends Applet {

    int numeroDatos;
    int[] datos;
    JTextField datosJT;
    Retorno datosTiempos, datosTiempos1;
    double escala_Y = 0;

    @Override
    public void init() {
        setSize(1000, 760);
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
        g.setFont(new Font("Tahoma", Font.BOLD, 15));
        g.drawString("Datos (cantidad)", 500, 550);
        g.drawString("tiempo (NanoSegundos)", 60, 30);
        g.drawLine(200, 500, 200, 50);
        g.drawLine(200, 500, 900, 500);
        g.setFont(new Font("Tahoma", Font.BOLD, 10));

        g.setColor(Color.RED);
        g.drawLine(200, 630, 400, 630);
        g.drawString("Couting Sort", 450, 630);

        g.setColor(Color.BLUE);
        for (int i = 0, y = 600; i < 8; i++, y = y + 20) {
            g.fillOval(y, 630, 5, 5);
        }
        g.drawString("Quick Sort", 800, 630);

        //Acomodar titulos del numero de datos en el eje X
        g.setColor(Color.BLACK);
        g.setFont(new Font("Tahoma", Font.BOLD, 10));
        int div = numeroDatos/14;
        for (int i = numeroDatos, j = 850, k=1; k <= 14 ; k++, i = i - div, j = j - 50) {
            g.drawString( i + "", j, 520);
        }
        // couting sourt - graficando
        datosTiempos1 = ejecucionCouting();

        //quicksort - graficando
        datosTiempos = ejecucionQuick();
        //Acomodar titulos del numero de datos en el eje Y
        g.setColor(Color.BLACK);
        g.setFont(new Font("Tahoma", Font.BOLD, 10));
        long ultimoDato = datosTiempos.getTiempos()[numeroDatos - 2];

        getAppletContext().showStatus("Grafica lista.");
        dibujaFuncion1(datosTiempos1.getDatos(), datosTiempos1.getTiempos());
        dibujaFuncion(datosTiempos.getDatos(), datosTiempos.getTiempos());
    }

    public static Double formatearDecimales(Double numero, Integer numeroDecimales) {
        return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
    }

    public void dibujaFuncion1(int[] vPrincipal1, long[] vTiempo1) {
        Graphics g1 = getGraphics();
        int tam = vPrincipal1.length;
        int timeMax = (int) vTiempo1[tam - 1];

        double escalaX = (double) 700 / tam;
        double escalaY = (double) 500 / timeMax;
        escala_Y = escalaY;
        int salto = vTiempo1.length / 5;
        long salto2 = vTiempo1[0]/3;        
        long salto3 = salto2;        
        for (int i = 0; i < vPrincipal1.length - 1; i++) {
            g1.setColor(Color.RED);
            if (vTiempo1[i] != 0) {
                double xinicial = 200 + (i * escalaX);
                double yinicial = 600 - (vTiempo1[i] * escalaY);
                g1.drawOval((int) xinicial, (int) (yinicial + 1), 0, 0);
                if ((i == 0) || (i % salto == 0) || (i == vTiempo1.length - 2)) {
                    g1.setColor(Color.BLACK);
                    g1.drawString(vTiempo1[i] + "", 90, (int) (600 - (vTiempo1[i] * escalaY)));
                }
                if(i==0){
                    g1.drawString(salto3 + "", 90, (int) (600 - (salto3 * escalaY)));
                    salto3=salto3+salto2;
                    g1.drawString(salto3 + "", 90, (int) (600 - (salto3 * escalaY)));
                    salto3=salto3+salto2;
                    g1.drawString(salto3 + "", 90, (int) (600 - (salto3 * escalaY)));
                }
            }
        }
    }

    public void dibujaFuncion(int[] vPrincipal1, long[] vTiempo1) {
        Graphics g1 = getGraphics();
        int tam = vPrincipal1.length;
        int timeMax = 0;
        double escalaX = (double) 700 / tam;

        int salto = vTiempo1.length / 5;
        for (int i = 0; i < vPrincipal1.length - 1; i++) {
            g1.setColor(Color.BLUE);
            if (vTiempo1[i] != 0) {
                double xinicial = 200 + (i * escalaX);
                double yinicial = 497 - (vTiempo1[i] * escala_Y);
                g1.drawOval((int) xinicial, (int) (yinicial + 1), 3, 3);
                if ((i == vTiempo1.length - 2)) {
                    g1.setColor(Color.BLACK);
                    g1.drawString(vTiempo1[i] + "", 90, (int) (500 - (vTiempo1[i] * escala_Y)));
                }
            }
        }
    }

    public Retorno ejecucionCouting() {
        // Variables
        Retorno informacion = new Retorno();
        CoutingSort couting = new CoutingSort();
        escrituraArchivo escribo = new escrituraArchivo();

        //llenar datosJT
        datos = informacion.rellenarDatos(numeroDatos);
        getAppletContext().showStatus("Datos creados");

        // organizar los datosJT anteriores
        informacion = couting.countingSort(datos);
        getAppletContext().showStatus("Datos ordenados");

        //Escribir datosJT en archivo
        escribo.escribirArchivo(informacion.getTiempos(), informacion.getDatos(), "CoutingSort");
        getAppletContext().showStatus("Datos en documento .txt");

        return informacion;
    }

    public Retorno ejecucionQuick() {
        // Variables
        Retorno informacion = new Retorno();
        QuickSort quick = new QuickSort();
        escrituraArchivo escribo = new escrituraArchivo();

        //asignar tamaño a lista de tiempos
        //llenar datosJT
        datos = informacion.rellenarDatos(numeroDatos);
        quick.asignarTamnio(datos.length);
        getAppletContext().showStatus("Datos creados");

        // organizar los datosJT anteriores
        informacion = quick.quicksort(datos, 0, datos.length - 1);
        getAppletContext().showStatus("Datos ordenados");

        //Escribir datosJT en archivo
        escribo.escribirArchivo(informacion.getTiempos(), informacion.getDatos(), "QuickSort");
        getAppletContext().showStatus("Datos en documento .txt");

        return informacion;
    }
}
