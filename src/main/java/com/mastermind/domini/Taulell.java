package com.mastermind.domini;/* By Jordi Armengol. 6/11/17*/

import java.util.ArrayList;

public class Taulell {
    private int torn;
    private Codi codiSolucio;
    private ArrayList<Tirada> tirades;
    private int maxTorn; // afegit respecte UML

    public Taulell(int torn, Codi codiSolucio, ArrayList<Tirada> tirades, int maxTorn) {
        this.torn = torn;
        this.codiSolucio = codiSolucio;
        this.tirades = tirades;
        this.maxTorn = maxTorn;
    }

    public int getTorn() {
        return torn;
    }

    public void setTorn(int torn) {
        this.torn = torn;
    }

    public boolean ferTirada(Codi codi) {
        Resposta resposta = respondre(codi);
        if (torn < maxTorn) {
            Tirada tirada = new Tirada(codi, resposta);
            tirades.add(tirada);
            torn++;
            return true;
        }
        else
            return false;
    }
    private Resposta respondre(Codi codi) {
        Resposta resposta = new Resposta(codi,codiSolucio);
        resposta.calcularResposta();
        return resposta ;
    }

    public Codi getCodiSolucio() {
        return codiSolucio;
    }

    public void setCodiSolucio(Codi codiSolucio) {
        this.codiSolucio = codiSolucio;
    }

    public void setMaxTorn(int maxTorn) {
        this.maxTorn = maxTorn;
    }
    public int getMaxTorn(int maxTorn) {
        return maxTorn;
    }
}
