package com.mastermind.domini;

// public Partida(int ID, int difficulty, boolean codeMaker, boolean help, Time time, Taulell taulell)

import com.mastermind.persistencia.DataController;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class ControladorPartida {
    private Partida partida;
    private IA ia;
    private boolean partidaCarregadaCorrectament;
    private int N_COLORS = 6; // Nota: mai no hem provat amb una mida diferent de 6
    private Codi iaFirstGuess;
    private boolean codeMakerTurn;
    private long initTime;
    private String user;

    // Constructora nova partida
    public ControladorPartida(int difficulty, boolean codeMaker, Codi codiSolució, String username) {
        user = username;
        int id = (int) (new Date().getTime()/1000);
        boolean help = false;
        Time time = new Time(0);
        Taulell taulell = createTaulell(difficulty, codeMaker, codiSolució);
        partida = new Partida(id, difficulty, codeMaker, help, time, taulell);
        int nColors = N_COLORS;
        int mida = sizeByDifficulty(difficulty);
        ia = new IA(nColors,mida);
        codeMakerTurn = false;
        if (codeMaker) {
            iaFirstGuess = ia.firstGuess();
            codeMakerTurn = true;
        }
        initTime = System.nanoTime();

    }




    // Constructora carregar partida
    public ControladorPartida(int id, String username) {
        Partida p = DataController.getPartida(id,username);
        if (p != null) {
            partida = p;
            partidaCarregadaCorrectament = true;
            int nColors = N_COLORS;
            int mida = sizeByDifficulty(partida.getDifficulty());
            ia = new IA(nColors,mida,partida.getTaulell().getTirades());
        }
        else {
            partidaCarregadaCorrectament = false;
        }

    }
    // Aquest metode s'ha de cridar justament despres de cridar la Constructora de arregar partida
    public boolean comprovarPartidaCarregada() {
        return partidaCarregadaCorrectament;
    }
    private long getCurrentElapsedTime() {
        return System.nanoTime() - initTime;
    }

    public boolean desaPartida() {
        partida.setTime(new Time(this.getCurrentElapsedTime()));
        if (DataController.savePartida(partida,user))
            return true;
        else {
            return false;
        }
    }

    public Codi nextGuessIA() {
        /*if (nova || partida.getTaulell().getTorn() == 0) {}
        else {
            return ia.nextGuess();
        }*/
        return ia.nextGuess();
    }

    public Codi getFirstGuess() {
        return iaFirstGuess;
    }

    // Nota: mai no hem provat amb una mida diferent de 4
    private int sizeByDifficulty(int difficulty) {
        int mida = 4 + difficulty;
        return mida;
    }

    public Taulell novaTiradaUsuariCodebreaker(Codi codi) {
        Taulell taulell = partida.getTaulell();
        boolean tiradaValida = taulell.ferTirada(codi);
        if (tiradaValida && !codeMakerTurn) {
            partida.setTaulell(taulell);
            return taulell;
        }
        else {
            return null; // retorna null si ens hem passat de torns o no era el seu torn
        }
    }

    public Taulell novaRespostaCodemaker(Codi codiIA, Resposta resposta) {
        if (codeMakerTurn && partida.getTaulell().getTorn() <= partida.getTaulell().getMaxTorn()) {


            ia.updateLastGuess(resposta.getnBlacks(), resposta.getnWhites());
            Taulell taulell = partida.getTaulell();
            ArrayList<Tirada> tirades = taulell.getTirades();
            Tirada tirada = new Tirada(codiIA, resposta);
            tirades.add(tirada);
            taulell.setTirades(tirades);
            taulell.setTorn(taulell.getTorn()+1);
            partida.setTaulell(taulell);
            return taulell;
        }
        return null;
    }



    private Taulell createTaulell(int difficulty, boolean codeMaker, Codi codiSolucio) {
        int mida = sizeByDifficulty(difficulty);
        ArrayList<Integer> peces = new ArrayList<>(mida);
        Random random = new Random();
        int nColors = N_COLORS;
        Codi codiSol;
        if (codeMaker) {
            codiSol = codiSolucio;
        }
        else {
            for (Integer peca : peces) {
                peces.add(peca, random.nextInt(nColors));
            }
            codiSol = new Codi(peces);
        }
        int torn = 0;
        ArrayList<Tirada> tirades = new ArrayList<>();
        int maxTorn = 10;
        Taulell taulell = new Taulell(torn, codiSolucio,tirades,maxTorn);
        return taulell;

    }

    public int getCurrentTurn() {
        return partida.getTaulell().getTorn();
    }
    public int getMaxTurn() {
        return partida.getTaulell().getMaxTorn();
    }

}
