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

    public void activar() {
        framePresentacio.setVisible(true);
        framePresentacio.setEnabled(true);
    }

    public void desactivar() {
        framePresentacio.setVisible(false);
        framePresentacio.setEnabled(false);
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
        campContrasenya.setText("");
        if (checkUserIsCorrect(nom, contrasenya))
            if (!controladorPresentacio.logInUsuari(nom, contrasenya)) {
                JOptionPane.showMessageDialog(framePresentacio, "L'usuari i/o la contrasenya no existeixen en la base de dades.");
            }
    }

    private void registerButtonAction() {
        String nom = campUsuari.getText();
        String contrasenya = campContrasenya.getText();
        campContrasenya.setText("");
        if (checkUserIsCorrect(nom, contrasenya))
            if(!controladorPresentacio.registraUsuari(nom, contrasenya)) {
                JOptionPane.showMessageDialog(framePresentacio, "L'usuari ja està registrat en la base de dades.");
            }
    }

    private boolean checkUserIsCorrect(String nom, String contrasenya) {
        if (4 <= nom.length() && nom.length() <= 20 && 4 <= contrasenya.length() && contrasenya.length() <= 20)
            return true;
        else {
            JOptionPane.showMessageDialog(framePresentacio, "L'usuari i la contrasenya han de tenir entre 4 i 20 caràcters.");
            return false;
        }
    }

}
