package com.mastermind.domini;

import com.mastermind.persistencia.DataController;

import java.util.ArrayList;
import java.util.Arrays;

public class ControladorUsuari {
    private Usuari usuari;

    public ControladorUsuari() {
        usuari = null;
    }

    public boolean creaUsuari(String nom, String contrasenya) {
        if (DataController.userExists(nom))
            return false;
        else {
            if (DataController.saveUser(nom,contrasenya)) {
                usuari = new Usuari(nom, contrasenya);
                return true;
            }
            else
                return false;
        }
    }

    public boolean carregaUsuari(String nom, String contrasenya) {
        if (!DataController.userExists(nom)) {
            return false;
        }
        else {
            Usuari u = DataController.getUser(nom);
            System.out.println(u.getNom());
            System.out.println(u.getContrasenya());
            if (u == null) {
                return false;
            }
            else {
                if (u.getContrasenya().equals(contrasenya)) {
                    usuari = u;
                    return true;
                }
                else {
                    return false;
                }

            }
        }
    }

    /*public boolean creaPartida(int difficulty, boolean codeMaker) {
        //int ID, int difficulty, boolean codeMaker, boolean help, Time time, Taulell taulell
    }*/


    public ArrayList<String> carregaInfoPartides() {
        Partida[] partidesArray = DataController.getPartidesUser(usuari.getNom());
        ArrayList<Partida> partides = new ArrayList<>(Arrays.asList(partidesArray));
        if (partides == null)
            return null;
        ArrayList<String> p = new ArrayList<>();
        for (Partida partida : partides) {
            p.add(partida.dataToString());
        }
        return p;

        //int ID, int difficulty, boolean codeMaker, boolean help, Time time, Taulell taulell
    }

    public Usuari getUsuari() {
        return usuari;
    }
}
