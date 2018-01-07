package com.mastermind.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PantallaRanking {

    private ControladorPresentacio controladorPresentacio;

    private JFrame framePresentacio = new JFrame("Ranking");
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextArea textArea4;
    private JTextArea textArea5;
    private JTextArea textArea6;
    private JTextArea textArea7;
    private JTextArea textArea8;
    private JTextArea textArea9;
    private JTextArea textArea10;
    private JTextArea textArea11;
    private JPanel panelRanking;
    private JButton button1;

    public PantallaRanking(ControladorPresentacio controladorPresentacio) {
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
        framePresentacio.setContentPane(this.panelRanking);
        framePresentacio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelRanking.setPreferredSize(new Dimension(500,700));
        framePresentacio.setResizable(false);
        button1.setText("Surt");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String texto = ((JButton) e.getSource()).getText();
                System.out.println("Has clickado el boton con texto: " + texto);
                surtRanking();
            }
        });

        textArea1.setText("Ranking");
        textArea1.setEnabled(false);
        ArrayList<String> rec = controladorPresentacio.getRecords();

        textArea2.setText(rec.get(0));
        textArea2.setEnabled(false);
        textArea3.setText(rec.get(1));
        textArea3.setEnabled(false);
        textArea4.setText(rec.get(2));
        textArea4.setEnabled(false);
        textArea5.setText(rec.get(3));
        textArea5.setEnabled(false);
        textArea6.setText(rec.get(4));
        textArea6.setEnabled(false);
        textArea7.setText(rec.get(5));
        textArea7.setEnabled(false);
        textArea8.setText(rec.get(6));
        textArea8.setEnabled(false);
        textArea9.setText(rec.get(7));
        textArea9.setEnabled(false);
        textArea10.setText(rec.get(8));
        textArea10.setEnabled(false);
        textArea11.setText(rec.get(9));
        textArea11.setEnabled(false);

    }

    private void surtRanking(){controladorPresentacio.surtRanking();}





}