package com.mastermind.domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class IADriver {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        System.out.println("IADriver");
        System.out.println("Aquest driver consisteix en un mini-joc interactiu amb la IA," +
                " jugant com a CodeMaker. \n" + "Escolleix un codi inicial amb 4 colors de l'1 al 6.\n" +
                "Nota: 4 posicions i 6 colors es correspon a la dificultat fàcil del joc.");

        ArrayList<Integer> codiInicial = new ArrayList<>();
        int tirades = 0;
        for (int i = 0; i < 4; ++i) codiInicial.add(scanner.nextInt());
        IA ia = new IA(6,4);
        System.out.println("Codi proposat de la IA:");
        Codi respostaIA = ia.firstGuess();
        System.out.println(ia.firstGuess().dataToString());
        ++tirades;
        while (!respostaIA.equals(codiInicial)) {
            System.out.println("Indica el nombre de peces negres i blanques respectivament.");
            int pecesNegres = scanner.nextInt();
            int pecesBlanques = scanner.nextInt();
            Resposta resposta = new Resposta();
            resposta.calcularResposta(respostaIA, new Codi(codiInicial));
            if (resposta.getnBlacks() != pecesNegres || resposta.getnWhites() != pecesBlanques) {
                System.out.println("Has escrit malament el nombre de peces negres o blanques.");
                System.out.println("El codi correcte era:");
                System.out.println(String.valueOf(resposta.getnBlacks()) + " peces negres.");
                System.out.println(String.valueOf(resposta.getnWhites()) + " peces blanques.");
                pecesNegres = resposta.getnBlacks();
                pecesBlanques = resposta.getnWhites();
            } else System.out.println("Has escrit la resposta correctament!");
            ia.updateLastGuess(pecesNegres,pecesBlanques);
            System.out.println("Codi proposat de la IA:");
            respostaIA = ia.nextGuess();
            System.out.println(ia.nextGuess().dataToString());
            ++tirades;
        }
        System.out.println("Sembla ser que la IA ha esbrinat el teu codi!\n" +
                "El joc ha acabat en " + String.valueOf(tirades) + " tirades.");
    }

}
