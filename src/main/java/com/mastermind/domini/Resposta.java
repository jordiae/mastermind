package com.mastermind.domini;/* By Jordi Armengol. 6/11/17*/

import java.util.ArrayList;

public class Resposta {
    private int nBlacks = 0;
    private int nWhites = 0;


    public Resposta() {
    }

    // 1: negre (color i posicio), 0: blanc (color pero no posicio)
    public void calcularResposta(Codi codi, Codi solucio) {
        ArrayList<Integer> c = codi.getPeces();
        ArrayList<Integer> s = solucio.getPeces();
        int i = 0;
        while (i < c.size()){


            if (c.get(i).equals(s.get(i))) {
                nBlacks++;
                s.remove(i);
            }

            else {
                boolean whiteFound = false;
                int j = 0;
                while (!whiteFound && j < s.size()) {
                    if (c.get(i).equals(s.get(j))) {
                        nWhites++;
                        s.remove(j);
                        whiteFound = true;
                    }
                    else {
                        j++;
                    }

                }
            }
            i++;
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
}