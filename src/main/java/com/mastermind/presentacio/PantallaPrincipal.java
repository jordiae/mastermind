package com.mastermind.presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaPrincipal {

    private ControladorPresentacio controladorPresentacio;

    private JFrame framePresentacio = new JFrame("Pantalla Principal");
    private JButton logInButton;
    private JButton registerButton;
    private JPanel initView;
    private JTextField campContrasenya;
    private JTextField campUsuari;

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
        // Listeners para los botones

        logInButton.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        loginButtonAction();
                    }
                });

        registerButton.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        registerButtonAction();
                    }
                });

    }

    private void loginButtonAction() {
        String nom = campUsuari.getText();
        String contrasenya = campContrasenya.getText();
        controladorPresentacio.logInUsuari(nom, contrasenya);
    }

    private void registerButtonAction() {
        String nom = campUsuari.getText();
        String contrasenya = campContrasenya.getText();
        controladorPresentacio.registraUsuari(nom, contrasenya);
    }



}
