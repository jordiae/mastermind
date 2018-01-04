package com.mastermind.presentacio;

import com.mastermind.domini.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ControladorPresentacio {

    private ControladorUsuari controladorUsuari;
    private ControladorRanking controladorRanking;
    private PantallaPrincipal pantallaPrincipal = null;
    private MenuUsuari menuUsuari = null;
    private ConfiguracioPartida configuracioPartida = null;
    private Ranking ranking = null;
    private MenuCarrega  menuC = null;

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
        if (configuracioPartida == null) {
            configuracioPartida = new ConfiguracioPartida(this);
            configuracioPartida.visualitza();
        } else configuracioPartida.activar();
    }

    public void tancaSessio() {
        menuUsuari.desactivar();
        pantallaPrincipal.activar();
    }

    public ArrayList<String> getRecords(){
        ArrayList<String> r = controladorRanking.carregaInfoRecords();
        return r;
    }

    public void mostraRanking(){
        menuUsuari.desactivar();
        if (ranking == null) {
            ranking = new Ranking(this);
            ranking.visualitza();
        } else ranking.activar();
    }

    public void surtRanking(){
        ranking.desactivar();
        menuUsuari.activar();
    }

    public void mostraPantallaCarga(){
        menuUsuari.desactivar();
        if (menuC == null) {
            menuC = new MenuCarrega(this);
            menuC.visualitza();
        } else menuC.activar();
    }

    public void surtPantallaCarga(){
        menuC.desactivar();
        menuUsuari.activar();
    }
}
