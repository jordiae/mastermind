package com.mastermind.domini;

import java.util.ArrayList;
import java.util.Scanner;

public class IADriverAux {
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);

        while(true){
            IA ia = new IA(6, 4);
            System.out.println("introdueix codi: ");
            ArrayList<Integer> codi = new ArrayList<>();
            for (int i = 0; i < 4; ++i){
                int x = scan.nextInt();
                codi.add(x);
            }
            boolean firstTry = true;
            Codi sol = new Codi(codi);
            Codi guess;
            Resposta r = new Resposta();
            while(r.getnBlacks() != 4){
                if (firstTry) {
                    firstTry = false;
                    guess = ia.firstGuess();
                }
                else guess = ia.nextGuess();
                r.calcularResposta(guess, sol);
                System.out.println("guess:  " + guess.getPeces().toString());
                System.out.println("B(" + r.getnBlacks() + ") W(" + r.getnWhites() + ")");
                System.out.println();
                ia.updateLastGuess(r.getnBlacks(), r.getnWhites());

            }
        }
    }
}
