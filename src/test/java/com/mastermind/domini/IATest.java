package com.mastermind.domini;

import java.util.ArrayList;
import java.util.Scanner;

public class IATest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /*ArrayList<Integer> codiInit = new ArrayList<>();
        int tirades = 0;
        for (int i = 0; i < 4; ++i) codiInit.add(scanner.nextInt());
        Codi codiInicial = new Codi(codiInit);
        IA ia = new IA(6,4);
        System.out.println("Codi proposat de la IA:");
        Codi respostaIA = ia.firstGuess();
        System.out.println(ia.firstGuess().dataToString());
        ++tirades;*/

        ArrayList<Integer> pecesSolucio = new ArrayList<>();
        pecesSolucio.add(2);
        pecesSolucio.add(3);
        pecesSolucio.add(5);
        pecesSolucio.add(6);
        Codi codiSolucio = new Codi(pecesSolucio);
        ArrayList<Integer> peces1 = new ArrayList<>();
        peces1.add(1);
        peces1.add(1);
        peces1.add(2);
        peces1.add(2);
        ArrayList<Integer> peces2 = new ArrayList<>();
        peces2.add(4);
        peces2.add(6);
        peces2.add(3);
        peces2.add(1);
        ArrayList<Integer> peces3 = new ArrayList<>();
        peces3.add(3);
        peces3.add(2);
        peces3.add(5);
        peces3.add(6);
        Codi codi1 = new Codi(peces1);
        Codi codi2 = new Codi(peces2);
        Codi codi3 = new Codi(peces3);
        Resposta resposta1 = new Resposta();
        resposta1.calcularResposta(codi1,codiSolucio);
        Resposta resposta2 = new Resposta();
        resposta2.calcularResposta(codi2,codiSolucio);
        Resposta resposta3 = new Resposta();
        resposta3.calcularResposta(codi3,codiSolucio);
        Tirada tirada1 = new Tirada(codi1,resposta1);
        Tirada tirada2 = new Tirada(codi2,resposta2);
        Tirada tirada3 = new Tirada(codi3,resposta3);
        ArrayList<Tirada> tirades = new ArrayList<>();
        tirades.add(tirada1);
        tirades.add(tirada2);
        tirades.add(tirada3);
        IA ia = new IA(6,4,tirades);

        Codi codiSol = new Codi(pecesSolucio);
        Codi codiProposatIA;
        boolean guessed = false;
        while (!guessed) {
            System.out.println("Codi proposat de la IA:");
            codiProposatIA = ia.nextGuess();
            System.out.println(codiProposatIA.dataToString());
            System.out.println("Indica el nombre de peces negres i blanques respectivament.");
            int pecesNegres = scanner.nextInt();
            int pecesBlanques = scanner.nextInt();
            Resposta resposta = new Resposta();
            resposta.calcularResposta(codiProposatIA, codiSol);
            if (resposta.getnBlacks() != pecesNegres || resposta.getnWhites() != pecesBlanques) {
                System.out.println("Has escrit malament el nombre de peces negres o blanques.");
                System.out.println("El codi correcte era:");
                System.out.println(String.valueOf(resposta.getnBlacks()) + " peces negres.");
                System.out.println(String.valueOf(resposta.getnWhites()) + " peces blanques.");
                pecesNegres = resposta.getnBlacks();
                pecesBlanques = resposta.getnWhites();
            } else System.out.println("Has escrit la resposta correctament!");
            ia.updateLastGuess(pecesNegres,pecesBlanques);
            if (pecesNegres == 4) {
                System.out.println("hola");
                guessed = true;
            }
        }
        System.out.println("Sembla ser que la IA ha esbrinat el teu codi!\n" +
                "El joc ha acabat en " + String.valueOf(tirades) + " tirades.");
    }
}
