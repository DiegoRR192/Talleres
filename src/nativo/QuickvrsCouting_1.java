package nativo;

import java.applet.Applet;
import java.awt.*;
import java.text.DecimalFormat;
import javax.swing.*;

/*import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
/**
 *
 * @author CARITO
 */
public class QuickvrsCouting_1 extends Applet {

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
        g.drawString("tiempo (segundos)", 0, 80);
        g.drawLine(200, 500, 200, 50);
        g.drawLine(200, 500, 900, 500);
        g.setFont(new Font("Tahoma", Font.BOLD, 10));

        //Acomodar titulos del numero de datos en el eje X
        for (int i = 1, j = 850; i <= 14; i++, j = j - 50) {
            g.drawString(numeroDatos / i + "", j, 520);
        }
        // couting sourt - graficando
        datosTiempos = ejecucionCouting();
        //quicksort - graficando
        //datosTiempos = ejecucionQuick();
        //dibujaFuncion1(datosTiempos.getDatos(), datosTiempos.getTiempos());
        //Acomodar titulos del numero de datos en el eje Y
        g.setColor(Color.BLACK);
        g.setFont(new Font("Tahoma", Font.BOLD, 10));
        long ultimoDato = datosTiempos.getTiempos()[numeroDatos - 2];
        /*for (int i = 1, j = 100; i <= 10; i++, j = j + 42) {
            g.drawString(ultimoDato / i + "", 90, j);
        }*/

        int divi = (int) (ultimoDato / 10);
        for (int i = 0, j = 1; i < datosTiempos.getTiempos().length; i++, j++) {
            //if ((datosTiempos.getTiempos()[i] % divi) == 0) {

            //}
        }

        getAppletContext().showStatus("Grafica lista.");

        dibujaFuncion1(datosTiempos.getDatos(), datosTiempos.getTiempos());
    }

    public static Double formatearDecimales(Double numero, Integer numeroDecimales) {
        return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
    }

    public void dibujaFuncion1(int[] vPrincipal1, long[] vTiempo1) {
        Graphics g = getGraphics();
        //dibujaEjes(g, datos);
        Graphics g1 = getGraphics();
        g1.setColor(Color.RED);

        int tam = vPrincipal1.length;
        int timeMax = (int) vTiempo1[tam - 1];
        DecimalFormat formato = new DecimalFormat("#.000");
        double escalaX = (double) 700 / tam;
        double escalaY = (double) 500 / timeMax;
        //g.drawString("aquí", 200, 500);
        int salto = vTiempo1.length / 5;
        for (int i = 0; i < vPrincipal1.length - 1; i++) {
            if (vTiempo1[i] == 0) {
            } else {
                double xinicial = 200 + (i * escalaX);
                double xfinal = 200 + ((i + 1) * escalaX);
                double yinicial = 600 - (vTiempo1[i] * escalaY);
                double yfinal = 500 - (vTiempo1[i + 1] * escalaY);
                //long yinicial = (((int) vTiempo1[i] / escalaTamanopulso) * -1) + (500 - (i / 4));
                //long yfinal = (((int) vTiempo1[i + 1] / escalaTamanopulso) * -1) + (500 - (i / 4));
                //g1.drawLine((int) xinicial,(int) yinicial,(int) xfinal,(int) yfinal);        
                g1.drawOval((int) xinicial, (int) (yinicial + 1), 0, 0);
                if ((i == 0) || (i % salto == 0) || (i == vTiempo1.length - 2)) {
                    g1.drawString(vTiempo1[i] + "", 90, (int) (600 - (vTiempo1[i] * escalaY)));
                }
            }
        }
        Graphics g2 = getGraphics();
        g1.setColor(Color.RED);

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

        //llenar datosJT
        datos = informacion.rellenarDatos(numeroDatos);
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
