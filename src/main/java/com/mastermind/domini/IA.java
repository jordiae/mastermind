package com.mastermind.domini;

import java.util.ArrayList;

public class IA {
    public Codi fesGuess(Partida partida) {
        ArrayList<Integer> peces = new ArrayList<Integer>();
        peces.add(2);
        Codi codi = new Codi(peces);
        return codi;
    }
}
