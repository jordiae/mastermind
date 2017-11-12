/* By Jordi Armengol. 6/11/17*/

package com.mastermind.domini;

import java.util.ArrayList;

public class Codi {
    private ArrayList<Integer> peces;
    private int fitnessScore;

    public ArrayList<Integer> getPeces() {
        return peces;
    }

    public void setPeces(ArrayList<Integer> peces) {
        this.peces = peces;
    }

    public Codi(ArrayList<Integer> peces) {
        this.fitnessScore = -1;

        this.peces = peces;
    }

    public String dataToString() {
        return peces.toString() + " " + fitnessScore;
    }

    public void setPeca(int pos, int col){peces.set(pos, col);}

    public void setFitnessScore(int s){
        this.fitnessScore = s;
    }

    public int getFitnessScore() {
        return fitnessScore;
    }
}
