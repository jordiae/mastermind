package com.mastermind.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private String codiAnterior;
    private int midaTaulell;
    private int numTirada;

    public PantallaPartida(ControladorPresentacio controladorPresentacio, int mida, String codi, String firstGuess) {
        this.controladorPresentacio = controladorPresentacio;
        midaTaulell = mida;
        numTirada = 0;
        inicialitzaComponents();
        JPanel tirada = new JPanel();
        parseCodiToPanel(codi, tirada);
        panelPartida.add(tirada);
        codiAnterior = parseCodiToString(firstGuess);
        novaTirada(codiAnterior);
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
    }

    private void confirmaButtonAction() {
        int blanques = Integer.parseInt(nBlanques.getText());
        int negres = Integer.parseInt(nNegres.getText());
        nNegres.disable();
        nBlanques.disable();
        codiAnterior = parseCodiToString(controladorPresentacio.nextGuessIA(codiAnterior, blanques, negres));
        novaTirada(codiAnterior);

    }
    private void sortirButtonAction() {


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

    private String parseCodiToString(String codi) {
        String resultat = "";
        for(int i = 1; i < codi.length(); i += 3) {
            resultat += Character.toString(codi.charAt(i));
        }
        return resultat;
    }

}
