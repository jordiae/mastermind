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
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        loginButtonAction();
                    }
                });

        registerButton.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        registerButtonAction();
                    }
                });

    }

    private void loginButtonAction() {
        System.exit(0);
    }

    private void registerButtonAction() {
        System.exit(0);
    }

}
