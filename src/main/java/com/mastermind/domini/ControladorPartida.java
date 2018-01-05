package com.mastermind.domini;

// public Partida(int ID, int difficulty, boolean codeMaker, boolean help, Time time, Taulell taulell)

import com.mastermind.persistencia.DataController;

import javax.management.StringValueExp;
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
    //private boolean codeMakerTurn;
    private long initTime;
    private String user;

    public ControladorPartida(int difficulty, boolean codeMaker, String codiSolucio, String username) {
        ArrayList<Integer> pecesCodiSol = new ArrayList<>();
        for (int i = 0; i < codiSolucio.length(); i++) {
            pecesCodiSol.add(Integer.parseInt(String.valueOf(codiSolucio.charAt(i))));
        }
        Codi codiSol = new Codi(pecesCodiSol);
        //this(difficulty, codeMaker,codiSol,username);

        user = username;
        int id = (int) (new Date().getTime()/1000);
        boolean help = false;
        Time time = new Time(0);
        Taulell taulell = createTaulell(difficulty, codeMaker, codiSol);
        partida = new Partida(id, difficulty, codeMaker, help, time, taulell);
        int nColors = N_COLORS;
        int mida = sizeByDifficulty(difficulty);
        ia = new IA(nColors,mida);
        //codeMakerTurn = false;
        if (codeMaker) {
            iaFirstGuess = ia.firstGuess();
            //codeMakerTurn = true;
        }
        initTime = System.nanoTime();

    }

    // Constructora nova partida
    public ControladorPartida(int difficulty, boolean codeMaker, Codi codiSolucio, String username) {
        user = username;
        int id = (int) (new Date().getTime()/1000);
        boolean help = false;
        Time time = new Time(0);
        Taulell taulell = createTaulell(difficulty, codeMaker, codiSolucio);
        partida = new Partida(id, difficulty, codeMaker, help, time, taulell);
        int nColors = N_COLORS;
        int mida = sizeByDifficulty(difficulty);
        ia = new IA(nColors,mida);
        //codeMakerTurn = false;
        if (codeMaker) {
            iaFirstGuess = ia.firstGuess();
            //codeMakerTurn = true;
        }
        initTime = System.nanoTime();

    }



    public static String generarCodiAleatori(int size) {
        Random random = new Random();
        String pecesAEndevinar = "";
        for (int i = 0; i < size; i++) {
            pecesAEndevinar += String.valueOf(random.nextInt(6)+1);
        }
        return pecesAEndevinar;
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

            ArrayList<Tirada> tirades = partida.getTaulell().getTirades();
            for (Tirada tirada : tirades) {
                System.out.println(tirada.dataToString());
            }
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

    public String nextGuessIAString() {
        /*if (nova || partida.getTaulell().getTorn() == 0) {}
        else {
            return ia.nextGuess();
        }*/
        return ia.nextGuess().dataToString();
    }


    public String getFirstGuessString() {
        return iaFirstGuess.dataToString();
    }


    // Nota: mai no hem provat amb una mida diferent de 4
    private int sizeByDifficulty(int difficulty) {
        int mida = 4 + difficulty;
        return mida;
    }

    public Taulell novaTiradaUsuariCodebreaker(Codi codi) {
        Taulell taulell = partida.getTaulell();
        boolean tiradaValida = taulell.ferTirada(codi);
        if (tiradaValida) { //&& !codeMakerTurn) {
            partida.setTaulell(taulell);
            return taulell;
        }
        else {
            return null; // retorna null si ens hem passat de torns o no era el seu torn
        }
    }

    public Taulell novaRespostaCodemaker(Codi codiIA, Resposta resposta) {
        if (partida.getTaulell().getTorn() <= partida.getTaulell().getMaxTorn()) { // codeMakerTurn unic borrat


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


    public void novaRespostaCodemakerVoid(String codiIA, int nBlacks, int nWhites) {
       // if (partida.getTaulell().getTorn() < partida.getTaulell().getMaxTorn()) { // codeMakerTurn unic borrat

        ArrayList<Integer> pecesCodi = new ArrayList<>();
        for (int i = 0; i < codiIA.length(); i++) {
            pecesCodi.add(Integer.parseInt(String.valueOf(codiIA.charAt(i))));
        }
        Codi codi = new Codi(pecesCodi);
            ia.updateLastGuess(nBlacks, nWhites);
            Taulell taulell = partida.getTaulell();
            ArrayList<Tirada> tirades = taulell.getTirades();
            Resposta resposta = new Resposta(nBlacks,nWhites);
            Tirada tirada = new Tirada(codi, resposta);
            tirades.add(tirada);
            taulell.setTirades(tirades);
            taulell.setTorn(taulell.getTorn()+1);
            partida.setTaulell(taulell);
            //return taulell;
       // }
       // return null;
    }

    public String generaResposta(String codi) {
        ArrayList<Integer> pecesCodiAux = new ArrayList<>();
        for (int i = 0; i < codi.length(); i++) {
            pecesCodiAux.add(Integer.parseInt(String.valueOf(codi.charAt(i))));
        }
        Codi pecesCodi = new Codi(pecesCodiAux);
        Taulell taulell = this.novaTiradaUsuariCodebreaker(pecesCodi);
        /*if (taulell == null) {
            System.out.println("Nova tirada ha fallat");
            System.exit(0);
        }*/
        Resposta resposta = taulell.getTirades().get(taulell.getTirades().size()-1).getResposta();
        return String.valueOf(resposta.getnBlacks()) + String.valueOf(resposta.getnWhites());

    }


    private Taulell createTaulell(int difficulty, boolean codeMaker, Codi codiSolucio) {
        int mida = sizeByDifficulty(difficulty);
        ArrayList<Integer> peces = new ArrayList<>(mida);
        Random random = new Random();
        int nColors = N_COLORS;
        Codi codiSol;
        if (codeMaker) {
            codiSol = codiSolucio;
        } else {
            for (Integer peca : peces) {
                peces.add(peca, random.nextInt(nColors));
            }
            codiSol = new Codi(peces);
        }
        int torn = 0;
        ArrayList<Tirada> tirades = new ArrayList<>();
        int maxTorn = 10;
        Taulell taulell = new Taulell(torn, codiSolucio, tirades, maxTorn);
        return taulell;

    }

    public int getCurrentTurn() {
        return partida.getTaulell().getTorn();
    }
    public int getMaxTurn() {
        return partida.getTaulell().getMaxTorn();
    }

    public boolean desarPartida() {
        partida.setTime(new Time(this.getCurrentElapsedTime()));
        return DataController.savePartida(partida,user);
    }

    public ArrayList<String> informacioPartida() {
        ArrayList<String> tau = new ArrayList<>();
        ArrayList<Tirada> tirades = new ArrayList<>();
        for (int i = 0; i < tirades.size(); i++) {
            ArrayList<Integer> peces = new ArrayList<>();
            peces = tirades.get(i).getCodi().getPeces();
            String s = "";
            for (int j = 0; j < peces.size(); j++) {
                s += peces.get(j);
            }
            s += " ";
            int nBlacks = tirades.get(i).getResposta().getnBlacks();
            int nWhites = tirades.get(i).getResposta().getnWhites();
            s += String.valueOf(nBlacks) + String.valueOf(nWhites);
            tau.add(s);
        }
        return tau;
    }

    public int calcularPuntuacio() {
        partida.setTime(new Time(this.getCurrentElapsedTime()));
        return partida.getPuntuacio();
    }

}
