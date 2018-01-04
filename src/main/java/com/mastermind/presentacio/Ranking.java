package com.mastermind.presentacio;

import com.mastermind.domini.Record;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Ranking {

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

    public Ranking(ControladorPresentacio controladorPresentacio) {
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

        button1.setText("surt");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String texto = ((JButton) e.getSource()).getText();
                System.out.println("Has clickado el boton con texto: " + texto);
                surtRanking();
            }
        });

        textArea1.setText("Ranking");
        ArrayList<Record> rec = controladorPresentacio.getRecords();
        Record r;
        r = rec.get(0);
        if (r != null){
            textArea2.setText(r.getName() + " " + r.getScore());
        }
        else {textArea2.setText("No s'ha aconseguit cap record");}
        r = rec.get(1);
        if (r != null){
            textArea3.setText(r.getName() + " " + r.getScore());
        }
        else {textArea3.setText("No s'ha aconseguit cap record");}
        r = rec.get(2);
        if (r != null){
            textArea4.setText(r.getName() + " " + r.getScore());
        }
        else {textArea4.setText("No s'ha aconseguit cap record");}
        r = rec.get(3);
        if (r != null){
            textArea5.setText(r.getName() + " " + r.getScore());
        }
        else {textArea5.setText("No s'ha aconseguit cap record");}
        r = rec.get(4);
        if (r != null){
            textArea6.setText(r.getName() + " " + r.getScore());
        }
        else {textArea6.setText("No s'ha aconseguit cap record");}
        r = rec.get(5);
        if (r != null){
            textArea7.setText(r.getName() + " " + r.getScore());
        }
        else {textArea7.setText("No s'ha aconseguit cap record");}
        r = rec.get(6);
        if (r != null){
            textArea8.setText(r.getName() + " " + r.getScore());
        }
        else {textArea8.setText("No s'ha aconseguit cap record");}
        r = rec.get(7);
        if (r != null){
            textArea9.setText(r.getName() + " " + r.getScore());
        }
        else {textArea9.setText("No s'ha aconseguit cap record");}
        r = rec.get(8);
        if (r != null){
            textArea10.setText(r.getName() + " " + r.getScore());
        }
        else {textArea10.setText("No s'ha aconseguit cap record");}
        r = rec.get(9);
        if (r != null){
            textArea11.setText(r.getName() + " " + r.getScore());
        }
        else {textArea11.setText("No s'ha aconseguit cap record");}

    }

    private void surtRanking(){controladorPresentacio.surtRanking();}





}