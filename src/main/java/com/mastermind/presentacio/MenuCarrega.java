package com.mastermind.presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuCarrega {

    private JFrame framePresentacio = new JFrame("MenuCarrega");
    private ControladorPresentacio controladorPresentacio;
    private JPanel panelCarrega;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton surtButton;

    public MenuCarrega(ControladorPresentacio controladorPresentacio) {
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

    private void inicialitzaComponents(){
        framePresentacio.setContentPane(this.panelCarrega);
        framePresentacio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        surtButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controladorPresentacio.surtPantallaCarga();
            }
        });

        ArrayList<String> partides = controladorPresentacio.getPartidesGuardades();
        button1.setText(partides.get(0).split(" ")[0]);
        button2.setText(partides.get(1).split(" ")[0]);
        button3.setText(partides.get(2).split(" ")[0]);
        button4.setText(partides.get(3).split(" ")[0]);
        button5.setText(partides.get(4).split(" ")[0]);
        button6.setText(partides.get(5).split(" ")[0]);
        button7.setText(partides.get(6).split(" ")[0]);
        button8.setText(partides.get(7).split(" ")[0]);
        button9.setText(partides.get(8).split(" ")[0]);
        button10.setText(partides.get(9).split(" ")[0]);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!partides.get(0).equals("empty")){
                    String[] p = partides.get(0).split(" ");
                    //fer coses amb info partida
                }
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!partides.get(1).equals("empty")){
                    String[] p = partides.get(1).split(" ");
                    //fer coses amb info partida
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!partides.get(2).equals("empty")){
                    String[] p = partides.get(2).split(" ");
                    //fer coses amb info partida
                }
            }
        });

        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!partides.get(3).equals("empty")){
                    String[] p = partides.get(3).split(" ");
                    //fer coses amb info partida
                }
            }
        });

        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!partides.get(4).equals("empty")){
                    String[] p = partides.get(4).split(" ");
                    //fer coses amb info partida
                }
            }
        });

        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!partides.get(5).equals("empty")){
                    String[] p = partides.get(5).split(" ");
                    //fer coses amb info partida
                }
            }
        });

        button7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!partides.get(6).equals("empty")){
                    String[] p = partides.get(6).split(" ");
                    //fer coses amb info partida
                }
            }
        });

        button8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!partides.get(7).equals("empty")){
                    String[] p = partides.get(7).split(" ");
                    //fer coses amb info partida
                }
            }
        });

        button9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!partides.get(8).equals("empty")){
                    String[] p = partides.get(8).split(" ");
                    //fer coses amb info partida
                }
            }
        });

        button10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!partides.get(9).equals("empty")){
                    String[] p = partides.get(9).split(" ");
                    //fer coses amb info partida
                }
            }
        });
    }


}
