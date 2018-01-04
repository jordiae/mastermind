package com.mastermind.presentacio;

import com.mastermind.domini.ControladorUsuari;

import javax.swing.*;

public class PantallaPrincipal {

    private ControladorPresentacio controladorPresentacio;

    private JFrame framePresentacio = new JFrame("Pantalla Principal");
    private JButton logInButton;
    private JButton registerButton;
    private JPanel initView;

    public PantallaPrincipal(ControladorPresentacio controladorPresentacio) {
        this.controladorPresentacio = controladorPresentacio;
        inicialitzaComponents();
    }

    public void visualitza() {
        framePresentacio.pack();
        framePresentacio.setVisible(true);

    }

    public void inicialitzaComponents() {
        framePresentacio.setContentPane(this.initView);
        framePresentacio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
