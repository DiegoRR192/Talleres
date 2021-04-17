/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.*;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author CARITO
 */
public class Bolita extends Applet implements MouseListener, MouseMotionListener {

    int punto, mx, my;
    int segundosEjecucion, tiempoPasado;
    LocalDateTime locaDate;
    JTextField tiempo;
            
    @Override
    public void init() {
        setSize(500, 500);
        addMouseListener(this);
        addMouseMotionListener(this);
        punto = 0;
        tiempo = new JTextField();
        Object[] muestra = {"Segundos",tiempo};
        int panel = JOptionPane.showConfirmDialog(null, muestra, "Ingrese tiempo de ejecucion", JOptionPane.OK_CANCEL_OPTION);
        if (panel == JOptionPane.OK_OPTION) {
            tiempo.setText(tiempo.getText());
        } else{
            tiempo.setText("0");
        }
        segundosEjecucion = Integer.parseInt(tiempo.getText());
    }

    @Override
    public void paint(Graphics g) {
        mx = (int) (Math.random() * 429) + 1;
        my = (int) (Math.random() * 429) + 1;
        g.drawOval(mx, my, 70, 70);
    }

    public void tiempoEjecucion() {
        LocalDateTime date = LocalDateTime.now();
        tiempoPasado =  (date.getSecond() + 100) - (locaDate.getSecond() + 100);
        if (tiempoPasado > segundosEjecucion) {
            JOptionPane.showConfirmDialog(null, "Aciertos " + punto, "Su puntaje", JOptionPane.OK_CANCEL_OPTION);
            System.exit(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        e.consume();
        requestFocus();
        if ((mx < x && x < mx + 70) && (my < y && y < my + 70)) {
            punto++;
            getAppletContext().showStatus("Vas muy bien:" + punto + " Acertados" + "          Tiempo: " + tiempoPasado);
            System.out.println("Acertó!");
            repaint();
        } else {
            getAppletContext().showStatus("Sigue intentando: (" + x + " , " + y + " )" + "          Tiempo: " + tiempoPasado);
        }
        tiempoEjecucion();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        locaDate = LocalDateTime.now();
        e.consume();
        getAppletContext().showStatus("Presiona el circulo" + "          Tiempo: " + tiempoPasado);
        repaint(); //To change body of generated methods, choose Tools | Templates.
        tiempoEjecucion();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        e.consume();
        getAppletContext().showStatus("Puntos aciertos: " + punto + "          Tiempo: " + tiempoPasado);
        tiempoEjecucion();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        e.consume();
        if ((e.getX() % 3 == 0) && (e.getY() % 3 == 0)) {
            repaint();
        }
        tiempoEjecucion();
        getAppletContext().showStatus("Presiona el circulo" + "          Tiempo: " + tiempoPasado);
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
        removeMouseListener(this);
    }

}
