package com.mastermind.presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaPartida {
    private ControladorPresentacio controladorPresentacio;

    private JFrame framePresentacio = new JFrame("Pantalla Principal");
    private JPanel panelPartida;
    private JButton redButton;
    private JButton greenButton;
    private JButton blueButton;
    private JButton yellowButton;
    private JButton orangeButton;
    private JButton purpleButton;


    public PantallaPartida(ControladorPresentacio controladorPresentacio) {
        this.controladorPresentacio = controladorPresentacio;
        inicialitzaComponents();
    }

    public void visualitza() {
        framePresentacio.pack();
        framePresentacio.setVisible(true);
    }

    public void activar() {
        framePresentacio.setVisible(true);
        framePresentacio.setEnabled(true);
    }

    public void desactivar() {
        framePresentacio.setVisible(false);
        framePresentacio.setEnabled(false);
    }

    public void inicialitzaComponents() {
        framePresentacio.setContentPane(this.panelPartida);
        framePresentacio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }




}
