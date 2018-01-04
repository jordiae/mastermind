package com.mastermind.presentacio;

import com.mastermind.domini.*;

public class ControladorPresentacio {

    private ControladorUsuari controladorUsuari;
    private ControladorRanking controladorRanking;
    private PantallaPrincipal pantallaPrincipal = null;

    public ControladorPresentacio() {
        controladorUsuari = new ControladorUsuari();
        controladorRanking = new ControladorRanking();
        pantallaPrincipal = new PantallaPrincipal(this);
    }

    public void inicialitzar() {
        pantallaPrincipal.visualitza();
    }
}
