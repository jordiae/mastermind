package com.mastermind.domini;

import com.mastermind.persistencia.DataController;

import java.util.ArrayList;

public class ControladorUsuari {
    private Usuari usuari;

    public ControladorUsuari {
        usuari = null;
    }

    public boolean creaUsuari(String nom, String contrasenya) {
        if (DataController.userExists(nom))
            return false;
        else {
            if (DataController.saveUser(nom,contrasenya)) {
                return true;
            }
            else
                return false;
        }
    }

    public boolean carregaUsuari(String nom, String contrasenya) {
        if (DataController.userExists(nom)) {
            return false;
        }
        else {
            Usuari u = DataController.getUser(nom);
            if (u == null) {
                return false;
            }
            else {
                if (u.getContrasenya() == contrasenya) {
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
        ArrayList<String> partides =  DataController.getPartidesByUser(usuari);
        return partides;

        //int ID, int difficulty, boolean codeMaker, boolean help, Time time, Taulell taulell
    }


}
