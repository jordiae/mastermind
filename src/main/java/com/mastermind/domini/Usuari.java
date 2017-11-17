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
        for (int i = 0; i < numPartidesGuardades; ++i) {
            if (partidaList.get(i).getID() == partida.getID()) return false;
        }
        if (numPartidesGuardades == NUM_PARTIDES_MAX) {
            partidaList.remove(0);
            --numPartidesGuardades;
        }
        partidaList.add(partida);
        ++numPartidesGuardades;
        return true;
    }

    public String dataToString() {
        String partides = partidesToString().toString();
        return partides + " " + numPartidesGuardades + " " + nom + " " + contrasenya;
    }

    private ArrayList<String> partidesToString() {
        ArrayList<String> partides = new ArrayList<>();
        for (int i = 0; i < numPartidesGuardades; ++i) partides.add(partidaList.get(i).dataToString());
        return partides;
    }
}
