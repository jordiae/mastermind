package com.mastermind.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PantallaPartida {
    private ControladorPresentacio controladorPresentacio;

    private JFrame framePresentacio = new JFrame("Pantalla Principal");
    private JPanel panelPartida;
    private JButton confirmaButton;
    private JButton sortirButton;
    private JTextField nBlanques;
    private JTextField nNegres;
    private JLabel nBlanquesText;
    private JLabel nNegresText;
    private JButton redButton;
    private JButton greenButton;
    private JButton blueButton;
    private JButton yellowButton;
    private JButton orangeButton;
    private JButton purpleButton;
    private ArrayList<JPanel> codiColors;
    private ArrayList<Integer> codiColorsNum;
    private String codiAnterior;
    private String codiAEsbrinar;
    private boolean codemaker;
    private int midaTaulell;
    private int numTirada;
    private int offsetCodi;

    public PantallaPartida(ControladorPresentacio controladorPresentacio, int mida, String codi, String firstGuess, boolean codemaker) {
        this.controladorPresentacio = controladorPresentacio;
        midaTaulell = mida;
        numTirada = 0;
        codiAEsbrinar = codi;
        this.codemaker = codemaker;
        inicialitzaComponents();
        if (codemaker) {
            codiAnterior = parseCodiToString(firstGuess);
            novaTirada(codiAnterior);
        }
    }

    public void visualitza() {
        framePresentacio.pack();
        framePresentacio.setVisible(true);
    }

    public void desactivar() {
        framePresentacio.setVisible(false);
        framePresentacio.setEnabled(false);
    }

    public void inicialitzaComponents() {
        System.out.println(codiAEsbrinar);
        panelPartida = new JPanel();
        framePresentacio.setContentPane(this.panelPartida);
        framePresentacio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        confirmaButton = new JButton(  "Confirmar");
        confirmaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                confirmaButtonAction();
            }
        });
        sortirButton = new JButton(  "Sortir");
        sortirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                sortirButtonAction();
            }
        });
        panelPartida.add(confirmaButton);
        panelPartida.add(sortirButton);
        if (codemaker) {
            JPanel tirada = new JPanel();
            parseCodiToPanel(codiAEsbrinar, tirada);
            panelPartida.add(tirada);
        } else {
            confirmaButton.setEnabled(false);
            offsetCodi = 0;
            codiColorsNum = new ArrayList<>();
            for (int i = 0; i < midaTaulell; ++i) codiColorsNum.add(0);
            codiColors = new ArrayList<>();
            JPanel panelCodi = new JPanel();
            for (int i = 0; i < midaTaulell; ++i) {
                JPanel panel = new JPanel();
                panel.setBackground(Color.LIGHT_GRAY);
                panel.setMinimumSize(new Dimension(50,50));
                panel.setMaximumSize(new Dimension(50,50));
                panel.setPreferredSize(new Dimension(50,50));
                panelCodi.add(panel);
                codiColors.add(panel);
            }
            panelPartida.add(panelCodi);
            JPanel panelBotons = new JPanel();
            redButton = new JButton();
            redButton.setBackground(Color.RED);
            redButton.setMinimumSize(new Dimension(30,30));
            redButton.setMaximumSize(new Dimension(30,30));
            redButton.setPreferredSize(new Dimension(30,30));
            panelBotons.add(redButton);
            redButton.addActionListener(new ActionListener() {
                public void actionPerformed (ActionEvent event) {
                    redButtonAction();
                }
            });

            greenButton = new JButton();
            greenButton.setBackground(Color.GREEN);
            greenButton.setMinimumSize(new Dimension(30,30));
            greenButton.setMaximumSize(new Dimension(30,30));
            greenButton.setPreferredSize(new Dimension(30,30));
            panelBotons.add(greenButton);
            greenButton.addActionListener(new ActionListener() {
                public void actionPerformed (ActionEvent event) {
                    greenButtonAction();
                }
            });

            blueButton = new JButton();
            blueButton.setBackground(Color.BLUE);
            blueButton.setMinimumSize(new Dimension(30,30));
            blueButton.setMaximumSize(new Dimension(30,30));
            blueButton.setPreferredSize(new Dimension(30,30));
            panelBotons.add(blueButton);
            blueButton.addActionListener(new ActionListener() {
                public void actionPerformed (ActionEvent event) {
                    blueButtonAction();
                }
            });

            yellowButton = new JButton();
            yellowButton.setBackground(Color.YELLOW);
            yellowButton.setMinimumSize(new Dimension(30,30));
            yellowButton.setMaximumSize(new Dimension(30,30));
            yellowButton.setPreferredSize(new Dimension(30,30));
            panelBotons.add(yellowButton);
            yellowButton.addActionListener(new ActionListener() {
                public void actionPerformed (ActionEvent event) {
                    yellowButtonAction();
                }
            });

            orangeButton = new JButton();
            orangeButton.setBackground(new Color(255,145,0));
            orangeButton.setMinimumSize(new Dimension(30,30));
            orangeButton.setMaximumSize(new Dimension(30,30));
            orangeButton.setPreferredSize(new Dimension(30,30));
            panelBotons.add(orangeButton);
            orangeButton.addActionListener(new ActionListener() {
                public void actionPerformed (ActionEvent event) {
                    orangeButtonAction();
                }
            });

            purpleButton = new JButton();
            purpleButton.setBackground(new Color(235,0,255));
            purpleButton.setMinimumSize(new Dimension(30,30));
            purpleButton.setMaximumSize(new Dimension(30,30));
            purpleButton.setPreferredSize(new Dimension(30,30));
            panelBotons.add(purpleButton);
            purpleButton.addActionListener(new ActionListener() {
                public void actionPerformed (ActionEvent event) {
                    purpleButtonAction();
                }
            });

            panelPartida.add(panelBotons);

        }

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

    private void afegeixColor(int color) {
        int numPanel = offsetCodi;
        codiColorsNum.set(offsetCodi, color);
        ++offsetCodi;
        if (midaTaulell == offsetCodi) {
            offsetCodi = 0;
            confirmaButton.setEnabled(true);
        }
        Color colorPanel = Color.LIGHT_GRAY;
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
        codiColors.get(numPanel).setBackground(colorPanel);
    }

    private void confirmaButtonAction() {
        if (codemaker) {
            int blanques = Integer.parseInt(nBlanques.getText());
            int negres = Integer.parseInt(nNegres.getText());
            nNegres.disable();
            nBlanques.disable();
            codiAnterior = parseCodiToString(controladorPresentacio.nextGuessIA(codiAnterior, blanques, negres));
            novaTirada(codiAnterior);
        } else {
            codiAnterior = "";
            for (int i = 0; i < midaTaulell; ++i) {
                codiAnterior += String.valueOf(codiColorsNum.get(i));
            }
            String resposta = controladorPresentacio.generarResposta(codiAnterior);
            novaTirada(codiAnterior);
            nNegres.setText(String.valueOf(resposta.charAt(0)));
            nBlanques.setText(String.valueOf(resposta.charAt(1)));
            nNegres.setEnabled(false);
            nBlanques.setEnabled(false);
            reiniciarInputs();
            if (Integer.parseInt(String.valueOf(resposta.charAt(0))) == midaTaulell) {
                JOptionPane.showMessageDialog(framePresentacio, "Has guanyat la partida. Felicitats!");
                controladorPresentacio.surtPartida();
            } else if (numTirada == 10){
                JOptionPane.showMessageDialog(framePresentacio, "Oh vaja! Has perdut la partida");
                controladorPresentacio.surtPartida();
            }
        }

    }
    private void sortirButtonAction() {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int resultat = JOptionPane.showConfirmDialog (framePresentacio, "Voldries desar la partida actual?","Atenció",dialogButton);
        if (resultat == JOptionPane.YES_OPTION) {
            if (controladorPresentacio.desarPartida()) {
                JOptionPane.showMessageDialog(framePresentacio, "Eggs are not supposed to be green.");
            } else {
                JOptionPane.showMessageDialog(framePresentacio, "S'ha produït un error inesperat i la partida serà borrada permanentment.");
            }

        } else {
            JOptionPane.showMessageDialog(framePresentacio, "La partida serà borrada per sempre!");
        }
        controladorPresentacio.surtPartida();
    }

    public void novaTirada(String codi) {
        System.out.println(codi);
        ++numTirada;
        JPanel tirada = new JPanel();
        JLabel nombreTirada = new JLabel(String.valueOf(numTirada) + ":");
        tirada.add(nombreTirada);
        parseCodiToPanel(codi, tirada);
        reinicialitzaCamps(tirada);
        panelPartida.add(tirada,0);
        panelPartida.updateUI();
        framePresentacio.repaint();

    }

    private void parseCodiToPanel(String codi, JPanel tirada) {
        for (int i = 0; i < midaTaulell; ++i) {
            int color = Character.getNumericValue(codi.charAt(i));
            JPanel panel = new JPanel();
            panel.setMinimumSize(new Dimension(50,50));
            panel.setMaximumSize(new Dimension(50,50));
            panel.setPreferredSize(new Dimension(50,50));
            Color colorPanel = Color.GRAY;
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
            panel.setBackground(colorPanel);
            tirada.add(panel);
        }
    }

    private void reinicialitzaCamps(JPanel tirada) {
        nBlanquesText = new JLabel("W:");
        nBlanques = new JTextField();
        nBlanques.setMinimumSize(new Dimension(20,20));
        nBlanques.setMaximumSize(new Dimension(20,20));
        nBlanques.setPreferredSize(new Dimension(20,20));
        nNegresText = new JLabel("B:");
        nNegres = new JTextField();
        nNegres.setMinimumSize(new Dimension(20,20));
        nNegres.setMaximumSize(new Dimension(20,20));
        nNegres.setPreferredSize(new Dimension(20,20));
        tirada.add(nBlanquesText);
        tirada.add(nBlanques);
        tirada.add(nNegresText);
        tirada.add(nNegres);

    }

    private void reiniciarInputs() {
        confirmaButton.setEnabled(false);
        offsetCodi = 0;
        for (int i = 0; i < midaTaulell; ++i) {
            codiColors.get(i).setBackground(Color.LIGHT_GRAY);
        }
    }

    private String parseCodiToString(String codi) {
        String resultat = "";
        for(int i = 1; i < codi.length(); i += 3) {
            resultat += Character.toString(codi.charAt(i));
        }
        return resultat;
    }

}
