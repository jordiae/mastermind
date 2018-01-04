package com.mastermind.presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfiguracioPartida {

    private ControladorPresentacio controladorPresentacio;

    private JFrame framePresentacio = new JFrame("Pantalla Principal");
    private JPanel panelConfiguracio;
    private JRadioButton facilRadioButton;
    private JRadioButton dificilRadioButton;
    private JRadioButton normalRadioButton;
    private JRadioButton codebreakerRadioButton;
    private JRadioButton codemakerRadioButton;
    private JButton comencaButton;


    public ConfiguracioPartida(ControladorPresentacio controladorPresentacio) {
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
        framePresentacio.setContentPane(this.panelConfiguracio);
        framePresentacio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ActionListener listenerDificultat = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String dificultat = ((JRadioButton) event.getSource()).getText();
                if (dificultat.equals("Fàcil")) {
                    normalRadioButton.setSelected(false);
                    dificilRadioButton.setSelected(false);
                } else if (dificultat.equals("Normal")) {
                    facilRadioButton.setSelected(false);
                    dificilRadioButton.setSelected(false);
                } else if (dificultat.equals("Difícil")) {
                    facilRadioButton.setSelected(false);
                    normalRadioButton.setSelected(false);
                }
            }
        };
        facilRadioButton.addActionListener(listenerDificultat);
        normalRadioButton.addActionListener(listenerDificultat);
        dificilRadioButton.addActionListener(listenerDificultat);

        ActionListener listenerRol = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String rol = ((JRadioButton) event.getSource()).getText();
                if (rol.equals("Codebreaker")) {
                    codemakerRadioButton.setSelected(false);
                } else if (rol.equals("Codemaker")){
                    codebreakerRadioButton.setSelected(false);
                }
            }
        };
        codebreakerRadioButton.addActionListener(listenerRol);
        codemakerRadioButton.addActionListener(listenerRol);


        comencaButton.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        comencaButtonAction();
                    }
                });

    }

    private void comencaButtonAction () {
        boolean correcte = true;
        //seleccio de dificultat
        int dificultat;
        if (facilRadioButton.isSelected()) {
            dificultat = 0;
        } else if (normalRadioButton.isSelected()) {
            dificultat = 1;
        } else if (dificilRadioButton.isSelected()) {
            dificultat = 2;
        } else correcte = false;
        //seleccio de rol
        boolean codemaker;
        if (codemakerRadioButton.isSelected()) {
            codemaker = true;
        } else if (codebreakerRadioButton.isSelected()) {
            codemaker = false;
        } else correcte = false;
        if (correcte) {

        } else {

        }
    }

}
