package com.juego;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SpaceInvaders extends JPanel implements Runnable {

    // Configuración de la pantalla
    final int ANCHO_PANTALLA = 1366;
    final int ALTO_PANTALLA = 728;
    int FPS = 60;

    BufferedImage spriteJugador = nave.cargarImagen("/sprites/nave.png");

    Thread hiloJuego;

    public SpaceInvaders() {
        this.setPreferredSize(new Dimension(ANCHO_PANTALLA, ALTO_PANTALLA));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // Ayuda a que los gráficos no parpadeen
    }

    public void iniciarHiloJuego() {
        hiloJuego = new Thread(this);
        hiloJuego.start();
    }

    @Override
    public void run() {
        // Este es el famoso GAME LOOP
        double intervaloDibujo = 1000000000 / FPS; // 1 segundo en nanosegundos / 60
        double siguienteTiempoDibujo = System.nanoTime() + intervaloDibujo;

        while (hiloJuego != null) {
            // 1. ACTUALIZAR: modificar posiciones y estados lógicos
            actualizar();

            // 2. DIBUJAR: repintar la pantalla con la nueva información
            repaint();

            // Control de FPS: dormir el hilo el tiempo exacto para mantener los 60 FPS estables
            try {
                double tiempoRestante = siguienteTiempoDibujo - System.nanoTime();
                tiempoRestante = tiempoRestante / 1000000; // Convertir a milisegundos

                if (tiempoRestante < 0) {
                    tiempoRestante = 0;
                }

                Thread.sleep((long) tiempoRestante);
                siguienteTiempoDibujo += intervaloDibujo;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void actualizar() {
        // Aquí pondremos la lógica pronto: mover nave, mover aliens, etc.
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Limpia la pantalla
        Graphics2D g2 = (Graphics2D) g;

        if (spriteJugador != null){

            g2.drawImage(spriteJugador,650,600,null);

        }
    }

    public static void main(String[] args) {
        JFrame ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setTitle("Space Invaders");

        SpaceInvaders panelJuego = new SpaceInvaders();
        ventana.add(panelJuego);

        ventana.pack();
        ventana.setLocationRelativeTo(null); // Centra la ventana en tu monitor
        ventana.setVisible(true);

        panelJuego.iniciarHiloJuego();
    }
}