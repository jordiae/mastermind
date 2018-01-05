package com.mastermind.presentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaTutorial {
    private JFrame framePresentacio = new JFrame("PantallaTutorial");
    private ControladorPresentacio controladorPresentacio;
    private JPanel panelTutorial;
    private JTextArea codeMakerTextArea;
    private JTextArea textArea1;
    private JTextArea codeBreakerTextArea;
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
        panelTutorial.setPreferredSize(new Dimension(500,750));
        framePresentacio.setResizable(false);
        textArea1.setText("si jugues com a CodeMaker, nomes cal que triis una combinació de colors qualsevol \n i esperis a veure si la nostra IA és capaç d'endevinar-lo en 5 torns com a molt!");
        textArea3.setText("si jugues com a CodeBreaker, la IA inventarà un codi que has d'endevinar. \n Per a fer-ho disposes d'un màxim de 5 torns. Per cada suposició que facis la IA \n et donarà un feed" +
                "back en forma de peçes blanques i negres. Cada peça blanca simbolitza que has encertat un color pero no la seva \n posició. Cada peça negra simbolitza que has encertat tant el color" +
                " com la posicio d'alguna de les peçes. Sort!");

        surtButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorPresentacio.surtTutorial();
            }
        });
    }
}
