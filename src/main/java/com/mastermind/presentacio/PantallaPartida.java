package com.mastermind.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PantallaPartida {
    private ControladorPresentacio controladorPresentacio;

    private JFrame framePresentacio = new JFrame("Pantalla Partida");
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
    private JButton ajudaButton;
    private ArrayList<JPanel> codiColors;
    private ArrayList<Integer> codiColorsNum;
    private String codiAnterior;
    private String codiAEsbrinar;
    private String ajudaString;
    private boolean codemaker;
    private int midaTaulell;
    private int numTirada;
    private int offsetCodi;
    private int longitudFrame;

    public PantallaPartida(ControladorPresentacio controladorPresentacio, int mida, String codi, String firstGuess, boolean codemaker) {
        this.controladorPresentacio = controladorPresentacio;
        midaTaulell = mida;
        numTirada = 0;
        codiAEsbrinar = codi;
        this.codemaker = codemaker;
        longitudFrame = 300/midaTaulell;
        inicialitzaComponents();
        if (codemaker) {
            novaTirada(firstGuess);
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
        panelPartida.setPreferredSize(new Dimension(500,700));
        framePresentacio.setResizable(false);
        ajudaButton = new JButton("Ajuda");
        ajudaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) { ajudaButtonAction(); }
        });
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
        panelPartida.add(ajudaButton);
        panelPartida.add(confirmaButton);
        panelPartida.add(sortirButton);
        if (codemaker) {
            JPanel tirada = new JPanel();
            parseCodiToPanel(codiAEsbrinar, tirada);
            panelPartida.add(tirada);
        } else {
            if (controladorPresentacio.usuariAjudat()) {
                String colorsCodi = controladorPresentacio.nombreColorsCodi();
                ajudaString = "En aquesta partida hi ha:\n";
                if (colorsCodi.charAt(0) != '0') ajudaString += String.valueOf(colorsCodi.charAt(0)) + " peces vermelles.\n";
                if (colorsCodi.charAt(1) != '0') ajudaString += String.valueOf(colorsCodi.charAt(1)) + " peces verdes.\n";
                if (colorsCodi.charAt(2) != '0') ajudaString += String.valueOf(colorsCodi.charAt(2)) + " peces blaves.\n";
                if (colorsCodi.charAt(3) != '0') ajudaString += String.valueOf(colorsCodi.charAt(3)) + " peces grogues.\n";
                if (colorsCodi.charAt(4) != '0') ajudaString += String.valueOf(colorsCodi.charAt(4)) + " peces taronges.\n";
                if (colorsCodi.charAt(5) != '0') ajudaString += String.valueOf(colorsCodi.charAt(5)) + " peces liles.\n";
            }
            confirmaButton.setEnabled(false);
            offsetCodi = 0;
            codiColorsNum = new ArrayList<>();
            for (int i = 0; i < midaTaulell; ++i) codiColorsNum.add(0);
            codiColors = new ArrayList<>();
            JPanel panelCodi = new JPanel();
            for (int i = 0; i < midaTaulell; ++i) {
                JPanel panel = new JPanel();
                panel.setBackground(Color.LIGHT_GRAY);
                panel.setMinimumSize(new Dimension(longitudFrame,30));
                panel.setMaximumSize(new Dimension(longitudFrame,30));
                panel.setPreferredSize(new Dimension(longitudFrame,30));
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

    private void ajudaButtonAction() {
        if (codemaker) {
            String resposta = controladorPresentacio.generarResposta(codiAnterior);
            nNegres.setText(String.valueOf(resposta.charAt(0)));
            nBlanques.setText(String.valueOf(resposta.charAt(1)));
        } else {
            if (!controladorPresentacio.usuariAjudat()){
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int resultat = JOptionPane.showConfirmDialog (framePresentacio, "Si demanes ajuda no tindràs l'opció de sortir al nostre rànquing\n Estas segur que la necessites?","Atenció",dialogButton);
                if (resultat == JOptionPane.YES_OPTION) {
                    String colorsCodi = controladorPresentacio.nombreColorsCodi();
                    ajudaString = "En aquesta partida hi ha:\n";
                    if (colorsCodi.charAt(0) != '0') ajudaString += String.valueOf(colorsCodi.charAt(0)) + " peces vermelles.\n";
                    if (colorsCodi.charAt(1) != '0') ajudaString += String.valueOf(colorsCodi.charAt(1)) + " peces verdes.\n";
                    if (colorsCodi.charAt(2) != '0') ajudaString += String.valueOf(colorsCodi.charAt(2)) + " peces blaves.\n";
                    if (colorsCodi.charAt(3) != '0') ajudaString += String.valueOf(colorsCodi.charAt(3)) + " peces grogues.\n";
                    if (colorsCodi.charAt(4) != '0') ajudaString += String.valueOf(colorsCodi.charAt(4)) + " peces taronges.\n";
                    if (colorsCodi.charAt(5) != '0') ajudaString += String.valueOf(colorsCodi.charAt(5)) + " peces liles.\n";
                    JOptionPane.showMessageDialog(framePresentacio, ajudaString);
                }
            } else {
                JOptionPane.showMessageDialog(framePresentacio, ajudaString);
            }
        }
    }

    private void confirmaButtonAction() {
        if (codemaker) {
            String negresStr = nNegres.getText();
            String blanquesStr = nBlanques.getText();
            if (checkBlanquesNegresCorrecte(negresStr+blanquesStr)) {
                nNegres.disable();
                nBlanques.disable();
                int blanques = Integer.parseInt(blanquesStr);
                int negres = Integer.parseInt(negresStr);
                codiAnterior = controladorPresentacio.nextGuessIA(codiAnterior, blanques, negres);
                novaTirada(codiAnterior);
                if (codiAnterior.equals(codiAEsbrinar)) {
                    JOptionPane.showMessageDialog(framePresentacio, "La IA ha guanyat la partida! És un Mastermind!");
                    controladorPresentacio.borraPartida();
                    controladorPresentacio.surtPartida();
                } else if (numTirada == 10) {
                    JOptionPane.showMessageDialog(framePresentacio, "La IA ha perdut la partida. Llàstima...");
                    controladorPresentacio.borraPartida();
                    controladorPresentacio.surtPartida();
                }
            } else {
                JOptionPane.showMessageDialog(framePresentacio, "Has introduït malament la informació.");

            }
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
                String s = "";
                if (!controladorPresentacio.usuariAjudat()) {
                    if (controladorPresentacio.afegirRecord()) {
                        s = "I a més, estàs entre els 10 nostres millors jugadors!";
                    }
                }
                JOptionPane.showMessageDialog(framePresentacio, "Has guanyat la partida. Felicitats!" + s);
                controladorPresentacio.borraPartida();
                controladorPresentacio.surtPartida();
            } else if (numTirada == 10){
                JOptionPane.showMessageDialog(framePresentacio, "Oh vaja! Has perdut la partida");
                controladorPresentacio.borraPartida();
                controladorPresentacio.surtPartida();
            }
        }

    }

    private boolean checkBlanquesNegresCorrecte(String res) {
        return res.equals(controladorPresentacio.generarResposta(codiAnterior));
    }

    private void sortirButtonAction() {
        controladorPresentacio.borraPartida();
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
        codiAnterior = codi;
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

    public void colocaBlanquesNegres(String negreBlanc) {
        nNegres.setText(String.valueOf(negreBlanc.charAt(0)));
        nBlanques.setText(String.valueOf(negreBlanc.charAt(1)));
        nNegres.setEnabled(false);
        nBlanques.setEnabled(false);
    }

    private void parseCodiToPanel(String codi, JPanel tirada) {
        for (int i = 0; i < midaTaulell; ++i) {
            int color = Character.getNumericValue(codi.charAt(i));
            JPanel panel = new JPanel();
            panel.setMinimumSize(new Dimension(longitudFrame,40));
            panel.setMaximumSize(new Dimension(longitudFrame,40));
            panel.setPreferredSize(new Dimension(longitudFrame,40));
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

}
