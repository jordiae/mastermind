package com.mastermind.domini;/* By Jordi Armengol. 6/11/17*/

import java.util.ArrayList;

public class Resposta {
    private ArrayList<Integer> peces;
    private Codi codi;
    private Codi solucio;
    private int BLACK = 1;
    private int WHITE = 0;

    public Resposta(ArrayList<Integer> peces) {
        this.peces = peces;
    }

    public Resposta(Codi codi, Codi solucio) {
        this.peces = peces;
    }

    public ArrayList<Integer> getPeces() {
        return peces;
    }

    public void setPeces(ArrayList<Integer> peces) {
        this.peces = peces;
    }

    public Codi getCodi() {
        return codi;
    }

    public void setCodi(Codi codi) {
        this.codi = codi;
    }

    // 1: negre (color i posicio), 0: blanc (color pero no posicio)
    public void calcularResposta() {
        ArrayList<Integer> r = new ArrayList<Integer>();
        ArrayList<Integer> c = codi.getPeces();
        ArrayList<Integer> s = solucio.getPeces();
        boolean blackFound = false;
        int i = 0;
        while (!blackFound && i < c.size()){


            if (c.get(i).equals(s.get(i))) {
                r.add(BLACK);
                blackFound = true;
            }

            else {
                boolean whiteFound = false;
                int j = 0;
                while (!whiteFound && j < s.size()) {
                    if (c.get(i).equals(s.get(j))) {
                        r.add(WHITE);
                        whiteFound = true;
                    }
                    else {
                        j++;
                    }

                }
                i++;
            }
        }
        this.peces = r;
    }

    public Codi getSolucio() {
        return solucio;
    }

    public void setSolucio(Codi solucio) {
        this.solucio = solucio;
    }
}