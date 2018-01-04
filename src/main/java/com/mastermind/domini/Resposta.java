package com.mastermind.domini;/* By Jordi Armengol. 6/11/17*/

import java.util.ArrayList;

public class Resposta {
    private int nBlacks;
    private int nWhites;

    public Resposta(int nBlacks, int nWhites) {
        this.nBlacks = nBlacks;
        this.nWhites = nWhites;
    }


    public Resposta() {
        nBlacks = 0;
        nWhites = 0;
    }

    // negre (color i posicio), blanc (color pero no posicio)
    public void calcularResposta(Codi codi, Codi solucio) {
        ArrayList<Integer> c = codi.getPeces();
        ArrayList<Integer> s = solucio.getPeces();
        ArrayList<Integer> black = new ArrayList<>();
        ArrayList<Integer> white = new ArrayList<>();
        nWhites = nBlacks = 0;
        for (int i = 0; i < s.size(); ++i) {
            if (c.get(i) == s.get(i)) {
                ++nBlacks;
                black.add(c.get(i));
            }
        }
        for (int i = 0; i < s.size(); ++i) {
            int color = s.get(i);
            for (int j = 0; j < c.size(); ++j) {
                if (j != i && c.get(j) == color) {
                    if (!black.contains(color) && ! white.contains(color)) {
                        ++nWhites;
                        white.add(color);
                    }
                }
            }
        }



    }

 
    public int getnBlacks() {
        return nBlacks;
    }

    public void setnBlacks(int nBlacks) {
        this.nBlacks = nBlacks;
    }

    public int getnWhites() {
        return nWhites;
    }

    public void setnWhites(int nWhites) {
        this.nWhites = nWhites;
    }

    public String dataToString() {
        return nBlacks + " " + nWhites;
    }
}