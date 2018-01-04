package com.mastermind.presentacio;

import com.mastermind.domini.*;

import java.util.ArrayList;
import java.util.Arrays;

public class ControladorPresentacio {

    private ControladorUsuari controladorUsuari;
    private ControladorRanking controladorRanking;
    private PantallaPrincipal pantallaPrincipal = null;
    private MenuUsuari menuUsuari = null;
    private Ranking ranking = null;

    public ControladorPresentacio() {
        controladorUsuari = new ControladorUsuari();
        controladorRanking = new ControladorRanking();
        pantallaPrincipal = new PantallaPrincipal(this);

    }

    public void inicialitzar() {
        pantallaPrincipal.visualitza();
    }

    public void registraUsuari(String nom, String contrasenya) {
        if (controladorUsuari.creaUsuari(nom,contrasenya)) {
            carregaMenuUsuari();
        }

    }

    public void logInUsuari (String nom, String contrasenya) {
        if (controladorUsuari.carregaUsuari(nom, contrasenya)) {
            carregaMenuUsuari();
        }

    }

    public void carregaMenuUsuari() {
        pantallaPrincipal.desactivar();
        if (menuUsuari == null) {
            menuUsuari = new MenuUsuari(this);
            menuUsuari.visualitza();
        } else menuUsuari.activar();
        String nomUsuari = controladorUsuari.getUsuari().getNom();
        menuUsuari.setNomUsuari(nomUsuari);
    }

    public void novaPartida() {
        menuUsuari.desactivar();

    }

    public void tancaSessio() {
        menuUsuari.desactivar();
        pantallaPrincipal.activar();
    }

    public ArrayList<Record> getRecords(){
        Record[] r = controladorRanking.carregaInfoRecords();
        return new ArrayList<>(Arrays.asList(r));
    }


    public void mostraRanking(){
        menuUsuari.desactivar();
        if (ranking == null) {
            ranking = new Ranking(this);
            ranking.visualitza();
        } else ranking.activar();
    }
}
