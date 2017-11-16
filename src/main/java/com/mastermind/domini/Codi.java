/* By Jordi Armengol. 6/11/17*/

package com.mastermind.domini;

import java.util.ArrayList;

public class Codi {
    private ArrayList<Integer> peces;

    public Codi(ArrayList<Integer> peces) {
        this.peces = peces;
    }

    public ArrayList<Integer> getPeces() {
        return peces;
    }

    public void setPeces(ArrayList<Integer> peces) {
        this.peces = peces;
    }

    public void setPeca(int pos, int col){peces.set(pos, col);}

    public boolean same(Codi c){
        for (int i = 0; i < c.getPeces().size(); ++i)
            if (c.getPeces().get(i) != this.peces.get(i)) return false;
        return true;
    }

    public String dataToString() {
        return peces.toString();
    }
}