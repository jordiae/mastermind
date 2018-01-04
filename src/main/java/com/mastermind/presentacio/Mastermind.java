package com.mastermind.presentacio;

public class Mastermind {
    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (
                new Runnable() {
                    public void run() {
                        ControladorPresentacio presentacio = new ControladorPresentacio();
                        presentacio.inicialitzar();
                    }});
    }
}