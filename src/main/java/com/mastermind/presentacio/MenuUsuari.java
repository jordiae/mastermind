package com.mastermind.presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuUsuari {

    private ControladorPresentacio controladorPresentacio;

    private JFrame framePresentacio = new JFrame("Menú Usuari");
    private JButton rankingButton;
    private JPanel panelMenu;
    private JButton novaPartidaButton;
    private JButton carregarPartidaButton;
    private JButton tutorialButton;
    private JLabel usuariText;
    private JButton tancarSessioButton;

    public MenuUsuari(ControladorPresentacio controladorPresentacio) {
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

    public void setNomUsuari(String nomUsuari) {
        usuariText.setText("Benvingut, " + nomUsuari + ".");
    }

    public void inicialitzaComponents() {
        framePresentacio.setContentPane(this.panelMenu);
        framePresentacio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

        tancarSessioButton.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        String texto = ((JButton) event.getSource()).getText();
                        System.out.println("Has clickado el boton con texto: " + texto);
                        tancarSessioButtonAction();
                    }
                });

    }

    private void novaPartidaButtonAction() {
        controladorPresentacio.novaPartida();
    }

    private void carregarPartidaButtonAction() {

    }

    private void rankingButtonAction() {

    }

    private void tutorialButtonAction() {

    }

    private void tancarSessioButtonAction() {
        controladorPresentacio.tancaSessio();
    }

}
