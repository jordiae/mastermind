package com.mastermind.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private JButton redButton;
    private JButton greenButton;
    private JButton blueButton;
    private JButton yellowButton;
    private JButton orangeButton;
    private JButton purpleButton;
    private JPanel color1;
    private JPanel color2;
    private JPanel color3;
    private JPanel color4;
    private JPanel color5;
    private JPanel color6;
    private JLabel codiHint;
    private JLabel codiText;
    private JButton enrereButton;

    private int dificultat;
    private int codemaker;
    private boolean colorSet;
    private ArrayList<Integer> codi;
    private int offsetCodi;
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
        panelConfiguracio.setPreferredSize(new Dimension(500,750));
        framePresentacio.setResizable(false);
        dificultat = -1;
        codemaker = -1;
        colorSet = false;
        desactivaCodemaker();
        ActionListener listenerDificultat = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String nomDificultat = ((JRadioButton) event.getSource()).getText();
                if (nomDificultat.equals("Fàcil")) {
                    dificultat = 0;
                    normalRadioButton.setSelected(false);
                    dificilRadioButton.setSelected(false);
                    codemakerRadioButton.setEnabled(true);
                    codebreakerRadioButton.setEnabled(true);
                    reiniciaCodi();
                } else if (nomDificultat.equals("Normal")) {
                    dificultat = 1;
                    facilRadioButton.setSelected(false);
                    dificilRadioButton.setSelected(false);
                    codemakerRadioButton.setEnabled(true);
                    codebreakerRadioButton.setEnabled(true);
                    reiniciaCodi();
                } else if (nomDificultat.equals("Difícil")) {
                    dificultat = 2;
                    facilRadioButton.setSelected(false);
                    normalRadioButton.setSelected(false);
                    codemakerRadioButton.setEnabled(true);
                    codebreakerRadioButton.setEnabled(true);
                    reiniciaCodi();
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
                    codemaker = 0;
                    codemakerRadioButton.setSelected(false);
                    desactivaCodemaker();
                } else if (rol.equals("Codemaker")){
                    codemaker = 1;
                    codebreakerRadioButton.setSelected(false);
                    activaCodemaker();
                    reiniciaCodi();
                }
            }
        };
        codebreakerRadioButton.addActionListener(listenerRol);
        codemakerRadioButton.addActionListener(listenerRol);

        redButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                redButtonAction();
            }
        });

        greenButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                greenButtonAction();
            }
        });

        blueButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                blueButtonAction();
            }
        });

        yellowButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                yellowButtonAction();
            }
        });

        orangeButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                orangeButtonAction();
            }
        });

        purpleButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent event) {
                purpleButtonAction();
            }
        });

        comencaButton.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        comencaButtonAction();
                    }
                });

        enrereButton.addActionListener
                (new ActionListener() {
                    public void actionPerformed (ActionEvent event) {
                        controladorPresentacio.surtConfigurarPartida();
                    }
                });

    }

    private void redButtonAction() {
        afegeixColor(1);
    }

    private void greenButtonAction() {
        afegeixColor(2);
    }

    private void blueButtonAction() {
        afegeixColor(3);
    }

    private void yellowButtonAction() {
        afegeixColor(4);
    }

    private void orangeButtonAction() {
        afegeixColor(5);
    }

    private void purpleButtonAction() {
        afegeixColor(6);
    }

    private void comencaButtonAction () {
        if (dificultat == -1 || codemaker == -1 || (codemaker == 1 && !colorSet)) {
            System.out.println("Posaho be.");
        } else {
            boolean isCodemaker;
            String codiStr = "";
            if (codemaker == 1) {
                isCodemaker = true;
                for (int i = 0; i < dificultat+4; ++i) {
                    codiStr += String.valueOf(codi.get(i));
                }
            }
            else isCodemaker = false;
            controladorPresentacio.comencaNovaPartida(dificultat, isCodemaker, codiStr);
        }
    }

    private void activaCodemaker() {
        redButton.setEnabled(true);
        greenButton.setEnabled(true);
        blueButton.setEnabled(true);
        orangeButton.setEnabled(true);
        yellowButton.setEnabled(true);
        purpleButton.setEnabled(true);
    }

    private void desactivaCodemaker() {
        redButton.setEnabled(false);
        greenButton.setEnabled(false);
        blueButton.setEnabled(false);
        orangeButton.setEnabled(false);
        yellowButton.setEnabled(false);
        purpleButton.setEnabled(false);
    }

    private void reiniciaCodi() {
        if (codemaker == 1) {
            colorSet = false;
            color1.setBackground(Color.LIGHT_GRAY);
            color2.setBackground(Color.LIGHT_GRAY);
            color3.setBackground(Color.LIGHT_GRAY);
            color4.setBackground(Color.LIGHT_GRAY);
            color5.setBackground(Color.LIGHT_GRAY);
            color6.setBackground(Color.LIGHT_GRAY);
            if (dificultat == 0) {
                color1.setVisible(false);
                color6.setVisible(false);
            } else if (dificultat == 1) {
                color1.setVisible(true);
                color6.setVisible(false);
            } else {
                color1.setVisible(true);
                color6.setVisible(true);
            }
            offsetCodi = 0;
            codi = new ArrayList<>();
            for (int i = 0; i < dificultat+4; ++i) codi.add(0);
        }
    }

    private void afegeixColor(int color) {
        codi.set(offsetCodi, color);
        int numPanel = offsetCodi+1;
        ++offsetCodi;
        if (dificultat == 0) {
            ++numPanel;
            if (offsetCodi == 4) {
                colorSet = true;
                offsetCodi = 0;
            }
        } else if (dificultat == 1) {
            if (offsetCodi == 5) {
                colorSet = true;
                offsetCodi = 0;
            }
        } else {
            if (offsetCodi == 6) {
                colorSet = true;
                offsetCodi = 0;
            }
        }
        Color colorPanel = null;
        switch (color) {
            case 1:
                colorPanel = Color.RED;
                break;
            case 2:
                colorPanel = Color.GREEN;
                break;
            case 3:
                colorPanel = Color.BLUE;
                break;
            case 4:
                colorPanel = Color.YELLOW;
                break;
            case 5:
                colorPanel = new Color(255, 145,0);
                break;
            case 6:
                colorPanel = new Color(235, 0, 255);
                break;
        }
        switch (numPanel) {
            case 1:
                color1.setBackground(colorPanel);
                break;
            case 2:
                color2.setBackground(colorPanel);
                break;
            case 3:
                color3.setBackground(colorPanel);
                break;
            case 4:
                color4.setBackground(colorPanel);
                break;
            case 5:
                color5.setBackground(colorPanel);
                break;
            case 6:
                color6.setBackground(colorPanel);
                break;
        }
    }
}
