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
        textArea1.setText("Ranking");
        ArrayList<Record> rec = controladorPresentacio.getRecords();
        int size = -1;
        if (rec != null) size = rec.size();
        if (size >= 1){
            Record r = rec.get(0);
            textArea2.setText(r.getName() + " " + r.getScore());
        }
        else {textArea2.setText("No s'ha aconseguit cap record");}
        if (size >= 2){
            Record r = rec.get(1);
            textArea3.setText(r.getName() + " " + r.getScore());
        }
        else {textArea3.setText("No s'ha aconseguit cap record");}
        if (size >= 3){
            Record r = rec.get(2);
            textArea4.setText(r.getName() + " " + r.getScore());
        }
        else {textArea4.setText("No s'ha aconseguit cap record");}
        if (size >= 4){
            Record r = rec.get(3);
            textArea5.setText(r.getName() + " " + r.getScore());
        }
        else {textArea5.setText("No s'ha aconseguit cap record");}
        if (size >= 5){
            Record r = rec.get(4);
            textArea6.setText(r.getName() + " " + r.getScore());
        }
        else {textArea6.setText("No s'ha aconseguit cap record");}
        if (size >= 6){
            Record r = rec.get(5);
            textArea7.setText(r.getName() + " " + r.getScore());
        }
        else {textArea7.setText("No s'ha aconseguit cap record");}
        if (size >= 7){
            Record r = rec.get(6);
            textArea8.setText(r.getName() + " " + r.getScore());
        }
        else {textArea8.setText("No s'ha aconseguit cap record");}
        if (size >= 8){
            Record r = rec.get(7);
            textArea9.setText(r.getName() + " " + r.getScore());
        }
        else {textArea9.setText("No s'ha aconseguit cap record");}
        if (size >= 9){
            Record r = rec.get(8);
            textArea10.setText(r.getName() + " " + r.getScore());
        }
        else {textArea10.setText("No s'ha aconseguit cap record");}
        if (size >= 10){
            Record r = rec.get(9);
            textArea11.setText(r.getName() + " " + r.getScore());
        }
        else {textArea11.setText("No s'ha aconseguit cap record");}

    }





}