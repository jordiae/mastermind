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
        int size = rec.size();
        if (size >= 1){
            Record r = rec.get(0);
            textArea2.setText(r.getName() + " " + r.getScore());
        }
        else {}

    }





}