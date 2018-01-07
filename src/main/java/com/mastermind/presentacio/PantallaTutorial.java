package com.mastermind.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaTutorial {
    private JFrame framePresentacio = new JFrame("PantallaTutorial");
    private ControladorPresentacio controladorPresentacio;
    private JPanel panelTutorial;
    private JTextArea textArea1;
    private JTextArea textArea3;
    private JButton surtButton;

    public PantallaTutorial(ControladorPresentacio controladorPresentacio) {
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
        framePresentacio.setContentPane(this.panelTutorial);
        framePresentacio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelTutorial.setPreferredSize(new Dimension(500,700));
        framePresentacio.setResizable(false);
        textArea1.setText("Si jugues com a CodeMaker, només cal que triïs una combinació de colors \nqualsevol i esperis a veure si la nostra IA és capaç d'endevinar-lo en 5 \ntorns com a molt!");
        textArea1.setEnabled(false);
        textArea3.setText("Si jugues com a CodeBreaker, la IA inventarà un codi que has d'endevinar. \nPer a fer-ho disposes d'un màxim de 10 torns. Per cada suposició que facis \nla IA et donarà un feed" +
                "back en forma de peces blanques i negres.Cada peça \nblanca simbolitza que has encertat un color però no la seva posició. Cada \npeça negra simbolitza que has encertat tant el color" +
                " com la posició d'alguna \nde les peces. Sort!");
        textArea3.setEnabled(false);
        surtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorPresentacio.surtTutorial();
            }
        });
    }
}
