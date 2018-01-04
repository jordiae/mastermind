package com.mastermind.presentacio;

import com.mastermind.domini.ControladorUsuari;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuUsuari {

    private ControladorPresentacio controladorPresentacio;

    private JFrame framePresentacio = new JFrame("Menú Usuari");
    private JButton logInButton;
    private JButton registerButton;
    private JPanel initView;
    private JButton rankingButton;
    private JPanel panel1;
    private JButton novaPartidaButton;
    private JButton carregarPartidaButton;
    private JButton tutorialButton;

    public MenuUsuari(ControladorPresentacio controladorPresentacio) {
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

        novaPartidaButton.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        novaPartidaButtonAction();
                    }
                });

        carregarPartidaButton.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        carregarPartidaButtonAction();
                    }
                });

        rankingButton.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        rankingButtonAction();
                    }
                });

        tutorialButton.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        tutorialButtonAction();
                    }
                });

    }

    private void novaPartidaButtonAction() {

    }

    private void carregarPartidaButtonAction() {

    }

    private void rankingButtonAction() {

    }

    private void tutorialButtonAction() {

    }

}
