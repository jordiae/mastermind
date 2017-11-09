/* By Jordi Armengol. 6/11/17*/

package com.mastermind.domini;

import java.util.ArrayList;

public class Codi {
    private ArrayList<Integer> peces;

    public ArrayList<Integer> getPeces() {
        return peces;
    }

    public void setPeces(ArrayList<Integer> peces) {
        this.peces = peces;
    }

    public Codi(ArrayList<Integer> peces) {

        this.peces = peces;
    }
}
