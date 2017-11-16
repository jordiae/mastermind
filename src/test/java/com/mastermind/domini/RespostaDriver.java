package com.mastermind.domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RespostaDriver {
    private static Resposta resposta;
    static boolean constructorExecuted;


    public static void main(String[] args) throws IOException {
        constructorExecuted = false;
        boolean runDriver = true;
        ArrayList<String> availableOptions = new ArrayList<String>();
        availableOptions.add("0 - Exit driver");
        availableOptions.add("1 - public Resposta()");
        availableOptions.add("2 - public void calcularResposta(Codi codi, Codi solucio)");
        availableOptions.add("3 - public int getnBlacks()");
        availableOptions.add("4 - public void setnBlacks(int nBlacks)");
        availableOptions.add("5 - public int getnWhites()");
        availableOptions.add("6 - public void setnWhites(int nWhites)");
        availableOptions.add("7 - public String dataToString()");
        while (runDriver) {
            System.out.println("RespostaDriver");
            System.out.println("Mètodes disponibles:");
            for (String s : availableOptions) {
                System.out.println(s);
            }
            int option;
            Scanner scanner = new Scanner(System.in);
            option = scanner.nextInt();
            System.out.println(option);
            switch (option) {
                case 0:
                    runDriver = false;
                    break;
                case 1:
                    runResposta();
                    break;
                case 2:
                    runCalcularResposta();
                    break;
                case 3:
                    runGetnBlacks();
                    break;
                case 4:
                    runSetnBlacks();
                    break;
                case 5:
                    runGetnWhites();
                    break;
                case 6:
                    runSetnWhites();
                    break;
                case 7:
                    runDataToString();
                    break;
                default:
                    break;
            }
        }


    }


    private static void runResposta() {
        resposta = new Resposta();
        constructorExecuted = true;
    }
    private static void runCalcularResposta() {
        System.out.println("Escriu el nombre d'elements del codi a intriduir, salt de línia i els elements separats amb un espai.");
        ArrayList<Integer> c = Utils.readNumsFromCommandLine();
        Codi codi = new Codi(c);
        System.out.println("Escriu el nombre d'elements del codi solució a intriduir, salt de línia i els elements separats amb un espai.");
        ArrayList<Integer> s = Utils.readNumsFromCommandLine();
        Codi codiSolucio = new Codi(s);
        resposta.calcularResposta(codi,codiSolucio);
    }

    private static void runGetnBlacks() {
        System.out.println(resposta.getnBlacks());
    }
    private static void runSetnBlacks() {
        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        resposta.setnBlacks(n);

    }
    private static void runSetnWhites() {
        int n;
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        resposta.setnWhites(n);
    }
    private static void runGetnWhites() {
        System.out.println(resposta.getnWhites());
    }
    private static void runDataToString() {
        System.out.println(resposta.dataToString());
    }



}
