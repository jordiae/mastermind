package com.mastermind.presentacio;

import com.mastermind.domini.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ControladorPresentacio {

    private ControladorUsuari controladorUsuari;
    private ControladorRanking controladorRanking;
    private ControladorPartida controladorPartida;
    private PantallaPrincipal pantallaPrincipal = null;
    private MenuUsuari menuUsuari = null;
    private ConfiguracioPartida configuracioPartida = null;
    private PantallaPartida pantallaPartida = null;
    private Ranking ranking = null;
    private MenuCarrega  menuC = null;
    private PantallaTutorial pantallaTut = null;

    public ControladorPresentacio() {
        controladorUsuari = new ControladorUsuari();
        controladorRanking = new ControladorRanking();
        pantallaPrincipal = new PantallaPrincipal(this);

    }

    public void inicialitzar() {
        pantallaPrincipal.visualitza();
    }

    public boolean registraUsuari(String nom, String contrasenya) {
        if (controladorUsuari.creaUsuari(nom,contrasenya)) {
            carregaMenuUsuari();
            return true;
        }
        else return false;

    }

    public boolean logInUsuari (String nom, String contrasenya) {
        if (controladorUsuari.carregaUsuari(nom, contrasenya)) {
            carregaMenuUsuari();
            return true;
        }
        else return false;

    }

    public void carregaMenuUsuari() {
        pantallaPrincipal.desactivar();
        if (menuUsuari == null) {
            menuUsuari = new MenuUsuari(this);
            menuUsuari.visualitza();
        } else menuUsuari.activar();
        String nomUsuari = controladorUsuari.getNomUsuari();
        menuUsuari.setNomUsuari(nomUsuari);
    }

    public void novaPartida() {
        menuUsuari.desactivar();
        if (configuracioPartida == null) {
            configuracioPartida = new ConfiguracioPartida(this);
            configuracioPartida.visualitza();
        } else configuracioPartida.activar();
    }

    public void comencaNovaPartida(int dificultat, boolean codemaker, String codi) {
        String nomUsuari = controladorUsuari.getNomUsuari();
        configuracioPartida.desactivar();
        configuracioPartida = null;
        String firstGuess = "";
        int midaTaulell = dificultat + 4;
        if (!codemaker) codi = ControladorPartida.generarCodiAleatori(midaTaulell);
        controladorPartida = new ControladorPartida(dificultat, codemaker, codi, nomUsuari);
        if (codemaker) firstGuess = controladorPartida.getFirstGuessString();
        pantallaPartida = new PantallaPartida(this,midaTaulell, codi, firstGuess,codemaker);
        pantallaPartida.visualitza();
    }

    public void carregaPartida(String id) {
        String nomUsuari = controladorUsuari.getNomUsuari();
        controladorPartida = new ControladorPartida(Integer.parseInt(id), nomUsuari);
        if (!controladorPartida.comprovarPartidaCarregada()) {
            menuC.error();
        } else {
            menuC.desactivar();
            menuC = null;
            controladorPartida = new ControladorPartida(Integer.parseInt(id), nomUsuari);

        }
    }

    public boolean desarPartida() {
        return controladorPartida.desarPartida();
    }

    public String nextGuessIA(String codi,int blanques, int negres) {
        controladorPartida.novaRespostaCodemakerVoid(codi, negres, blanques);
        return controladorPartida.nextGuessIAString();
    }

    public String generarResposta(String codi) {
        return controladorPartida.generaResposta(codi);
    }

    public void surtPartida() {
        pantallaPartida.desactivar();
        pantallaPartida = null;
        menuUsuari.activar();
    }

    public void surtConfigurarPartida () {
        configuracioPartida.desactivar();
        configuracioPartida = null;
        menuUsuari.activar();
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

    public ArrayList<String> getPartidesGuardades(){
        ArrayList<String> data = controladorUsuari.carregaInfoPartides();
        return data;
    }

    public void mostraTutorial(){
        menuUsuari.desactivar();
        if (pantallaTut == null) {
            pantallaTut = new PantallaTutorial(this);
            pantallaTut.visualitza();
        } else pantallaTut.activar();
    }

    public void surtTutorial(){
        pantallaTut.desactivar();
        menuUsuari.activar();
    }
}
