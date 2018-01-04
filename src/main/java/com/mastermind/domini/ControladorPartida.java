package com.mastermind.domini;

// public Partida(int ID, int difficulty, boolean codeMaker, boolean help, Time time, Taulell taulell)

import com.mastermind.persistencia.DataController;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class ControladorPartida {
    private Partida partida;
    private boolean partidaCarregadaCorrectament;
    // Constructora nova partida
    public ControladorPartida(int difficulty, boolean codeMaker) {
        int id = (int) (new Date().getTime()/1000);
        boolean help = false;
        Time time = new Time(0);
        Taulell taulell = createTaulell(difficulty);
        partida = new Partida(id, difficulty, codeMaker, help, time, taulell);
    }

    // Constructora carregar partida
    public ControladorPartida(int id, String username) {
        Partida p = DataController.getPartida(id,username);
        if (p != null) {
            partida = p;
            partidaCarregadaCorrectament = true;
        }
        else {
            partidaCarregadaCorrectament = false;
        }

    }
    // Aquest metode s'ha de cridar justament despres de cridar la Constructora de arregar partida
    public boolean comprovarPartidaCarregada() {
        return partidaCarregadaCorrectament;
    }

    private Taulell createTaulell(int difficulty) {
        int mida = 4 + difficulty;
        ArrayList<Integer> peces = new ArrayList<>(mida);
        Random random = new Random();
        for (Integer peca : peces) {
            peces.add(peca,random.nextInt(mida));
        }
        Codi codiSolucio = new Codi(peces);
        int torn = 0;
        ArrayList<Tirada> tirades = new ArrayList<>();
        int maxTorn = 10;
        Taulell taulell = new Taulell(torn, codiSolucio,tirades,maxTorn);
        return taulell;

    }
}
