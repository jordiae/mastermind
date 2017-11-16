package com.mastermind.domini;

import java.util.ArrayList;

public class Usuari {

    private static final int NUM_PARTIDES_MAX = 15;

    private ArrayList<Partida> partidaList;
    private int numPartidesGuardades;
    private String nom;
    private String contrasenya;

    public Usuari(String nom, String contrasenya) {
        this.nom = nom;
        this.contrasenya = contrasenya;
        partidaList = new ArrayList<>();
        numPartidesGuardades = 0;
    }

    public String getNom() {return nom;}

    public String getContrasenya() {return contrasenya;}

    public void setNom(String nom) {this.nom = nom;}

    public void setContrasenya(String contrasenya) {this.contrasenya = contrasenya;}

    public int getNumPartidesGuardades() {return numPartidesGuardades;}

    public ArrayList<Partida> getPartidesGuardades() {return partidaList;}

    public boolean afegirPartida(Partida partida){
        if (numPartidesGuardades == NUM_PARTIDES_MAX) {
            partidaList.remove(0);
            --numPartidesGuardades;
        }
        partidaList.add(partida);
        ++numPartidesGuardades;
        return true;
    }

    public String dataToString() {
        return partidaList.toString() + " " + numPartidesGuardades + " " + nom + " " + contrasenya;
    }
}
