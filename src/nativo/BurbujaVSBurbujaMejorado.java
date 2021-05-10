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
public class BurbujaVSBurbujaMejorado extends Applet {

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

        //Grafica metodo burbuja  x=650  y=400
        getAppletContext().showStatus("Graficando...");
        datosTiempos = ejecucionCouting();
        g.setColor(Color.RED);
        int k = numeroDatos / 650;
        //System.out.println(k);

        LecturaArchivo leer = new LecturaArchivo();
        Retorno datostiempos1 = leer.leerArchivo("BurbujaNormal.txt");

        //Grafica puntos
        long tiempo1[] = datosTiempos.getTiempos();
        int datos1[] = datostiempos1.getDatos();

        int maxX = numeroDatos / 14;
        double porcenX = (46 / (maxX * 1.0));

        System.out.println(tiempo1[(tiempo1.length) - 2]);
        long maxY = (tiempo1[numeroDatos - 2]) / 14;
        double porcenY = (28 / (maxY * 1.0));
        System.out.println("porcey" + porcenY);
        System.out.println("porcex" + porcenX);
        int divide = numeroDatos / 650;
        int tiempofin[] = new int[650];
        long acu = 0;
        int cambio = (650);
        int p = 0;
        for (int i = 0; i < tiempo1.length; i++) {
            acu = tiempo1[i] + acu;
            if (i >= cambio) {
                cambio = cambio + (650);
                tiempofin[p] = (int) acu / divide;
                p++;
                acu = 0;
            }
        }

        for (int i = 0, j = 500; i <= 650; i++) {
            g.drawOval(((int) (i * porcenX)) + 200, j - ((int) (tiempofin[i] * porcenY)), 5, 5);
        }

        //Acomodar titulos del numero de datos en el eje Y
        g.setColor(Color.BLACK);
        g.setFont(new Font("Tahoma", Font.BOLD, 10));
        long ultimoDato = datosTiempos.getTiempos()[numeroDatos - 2];
        for (int i = 1, j = 100; i <= 10; i++, j = j + 42) {
            g.drawString(ultimoDato / i + "", 150, j);
        }
        getAppletContext().showStatus("Grafica lista.");
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

/*    Font ft10 = new Font("Arial", Font.PLAIN, 10);
    JTextField tffun;
    JLabel mensaje;
    JPanel SP; //Slider Panel
    JPanel ZG; //aquí se va a poner la ZonaGrafica
    JPanel ControlPanel; //panel para botones y campos de texto,etc
    JPanel LogoPanel;
    JPanel DisplayPanel1 = new JPanel(); //aquí va ZG para obtener un buen borde
    JPanel DisplayPanel2 = new JPanel(); //aquívan los Sliders y controlPanel
    JButton BtnAyuda, BtnGraficar;
    JFrame fFrame;      //ventana de ayuda
    int Galto, Gancho;   //dimesiones de la zona de graficación
    ImageIcon logocrv; //para introducir imagen como un icono

    @Override
    public void init() {
        setSize(1000,1000);
        Container Contenedor = getContentPane();
        logocrv = new ImageIcon(getClass().getResource("/images/LogoUniversidad.png"));
        //comentario 1
        Gancho = getSize().width - 40;
        Galto = 50 * getSize().height / 100;

        //comentario 2
        ZG = new ZonaGrafica(); // panel zona gráfica
        SP = new SliderPanel(); // panel para Sliders de escala
        LogoPanel = new JPanel();  //panel para el logo
        ControlPanel = new JPanel();

        //Graficar
        Graphics g = null;
        ZG.add(g.drawString("Hola", 50, 50));
        
        //comentario 3
        LogoPanel.add(new JLabel(logocrv)); //el logo se incluye como  icono

        //comentario 4
        ControlPanel.setLayout(new GridBagLayout()); // administrador de diseño
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;

        tffun = new JTextField(" ", 8);
        c.gridy = 1; // fila
        c.gridx = 0; // columna
        ControlPanel.add(tffun, c);

        //espacio en blanco
        JLabel esp1 = new JLabel(" ");
        c.gridy = 1; // fila
        c.gridx = 1; // columna
        ControlPanel.add(esp1, c);

        BtnGraficar = new JButton("Graficar");
        c.gridy = 1; // fila
        c.gridx = 2; // columna
        ControlPanel.add(BtnGraficar, c);

        BtnAyuda = new JButton("Limpiar");
        c.gridy = 2; // fila
        c.gridx = 2; // columna
        ControlPanel.add(BtnAyuda, c);

        mensaje = new JLabel("", JLabel.LEFT);
        c.gridwidth = 3;
        c.gridy = 4; // fila
        c.gridx = 0; // columna
        ControlPanel.add(mensaje, c);
        //fin del administrador de diseño ControlPanel

        //comentario 5: Bordes
        Border colorline = BorderFactory.createLineBorder(new Color(220, 220, 220));
        DisplayPanel1.setBorder(colorline);
        TitledBorder rotulo;
        rotulo = BorderFactory.createTitledBorder(" Escala");
        rotulo.setTitleColor(new Color(0, 0, 128));
        rotulo.setTitleFont(ft10);
        SP.setBorder(rotulo);

        rotulo = BorderFactory.createTitledBorder(" Creditos ");
        rotulo.setTitleFont(ft10);
        rotulo.setTitleColor(new Color(0, 0, 128));
        LogoPanel.setBorder(rotulo);

        rotulo = BorderFactory.createTitledBorder(" Datos ");
        rotulo.setTitleFont(ft10);
        rotulo.setTitleColor(new Color(0, 0, 128));
        ControlPanel.setBorder(rotulo);
        //fin de Bordes

        //comentario 6
        DisplayPanel1.setPreferredSize(new Dimension(Gancho, Galto));
        ControlPanel.setPreferredSize(new Dimension(20 * Gancho / 100, 20 * getSize().height / 100));
        SP.setPreferredSize(new Dimension(20 * Gancho / 100, 20 * getSize().height / 100));

        //comentario 7
        DisplayPanel1.setLayout(new BorderLayout()); //administrador de diseño BorderLayout
        DisplayPanel1.add("Center", ZG);

        DisplayPanel2.setLayout(new BorderLayout(1, 1));
        DisplayPanel2.add("West", LogoPanel);
        DisplayPanel2.add("Center", ControlPanel);
        DisplayPanel2.add("East", SP);

        Contenedor.setLayout(new BorderLayout(1, 1));
        Contenedor.add("Center", DisplayPanel1);
        Contenedor.add("South", DisplayPanel2);
    }//fin init
    
    class ZonaGrafica extends JPanel {
        
        Graphics g;
        
        public ZonaGrafica(){
            g.drawString("Datos", 50, 50);
        }
        //implementación
    }

    class SliderPanel extends JPanel {
        //implementación
    }
 */
