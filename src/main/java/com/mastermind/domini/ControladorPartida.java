package com.mastermind.domini;

// public Partida(int ID, int difficulty, boolean codeMaker, boolean help, Time time, Taulell taulell)

import com.mastermind.persistencia.DataController;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class ControladorPartida {
    private Partida partida;
    private IA ia;
    private boolean partidaCarregadaCorrectament;
    private int N_COLORS = 6; // Nota: mai no hem provat amb una mida diferent de 6
    // Constructora nova partida
    public ControladorPartida(int difficulty, boolean codeMaker) {
        int id = (int) (new Date().getTime()/1000);
        boolean help = false;
        Time time = new Time(0);
        Taulell taulell = createTaulell(difficulty);
        partida = new Partida(id, difficulty, codeMaker, help, time, taulell);
        int nColors = N_COLORS;
        int mida = sizeByDifficulty(difficulty);
        ia = new IA(nColors,mida);
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
        int nColors = N_COLORS;
        int mida = sizeByDifficulty(partida.getDifficulty());
        ia = new IA(nColors,mida);

    }
    // Aquest metode s'ha de cridar justament despres de cridar la Constructora de arregar partida
    public boolean comprovarPartidaCarregada() {
        return partidaCarregadaCorrectament;
    }

    // Nota: mai no hem provat amb una mida diferent de 4
    private int sizeByDifficulty(int difficulty) {
        int mida = 4 + difficulty;
        return mida;
    }

    public Taulell novaTiradaCodebreaker(Codi codi) {
        Taulell taulell = partida.getTaulell();
        taulell.ferTirada(codi);
        partida.setTaulell(taulell);
        return taulell;
    }

    private Taulell createTaulell(int difficulty) {
        int mida = sizeByDifficulty(difficulty);
        ArrayList<Integer> peces = new ArrayList<>(mida);
        Random random = new Random();
        int nColors = N_COLORS;
        for (Integer peca : peces) {
            peces.add(peca,random.nextInt(nColors));
        }
        Codi codiSolucio = new Codi(peces);
        int torn = 0;
        ArrayList<Tirada> tirades = new ArrayList<>();
        int maxTorn = 10;
        Taulell taulell = new Taulell(torn, codiSolucio,tirades,maxTorn);
        return taulell;

    }

}
