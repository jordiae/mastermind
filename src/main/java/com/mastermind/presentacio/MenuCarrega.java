package com.mastermind.presentacio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


    }


}
