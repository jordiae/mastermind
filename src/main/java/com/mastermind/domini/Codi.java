/* By Jordi Armengol. 6/11/17*/

package com.mastermind.domini;

import java.util.ArrayList;

public class Codi {
    private ArrayList<Integer> peces;
    private int fitness_score;

    public ArrayList<Integer> getPeces() {
        return peces;
    }

    public void setPeces(ArrayList<Integer> peces) {
        this.peces = peces;
    }

    public Codi(ArrayList<Integer> peces) {
        this.fitness_score = -1;

        this.peces = peces;
    }

    public String dataToString() {
        return peces.toString();
    }

    public void setPeca(int pos, int col){peces.set(pos, col);}

    public void setFitness_score(int s){
        this.fitness_score = s;
    }

    public int getFitness_score() {
        return fitness_score;
    }
}
