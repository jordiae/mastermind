package com.mastermind.domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TiradaDriver {
    private static Tirada tirada;
    public static void main(String[] args) throws IOException {
        boolean runDriver = true;
        ArrayList<String> availableOptions = new ArrayList<String>();
        availableOptions.add("0 - Exit driver");
        availableOptions.add("1 - public Tirada(Codi c)");
        availableOptions.add("2 - public Tirada(Codi c, Resposta r)");
        availableOptions.add("3 - public Codi getCodi()");
        availableOptions.add("4 - public void setCodi(Codi codi)");
        availableOptions.add("5 - public Resposta getResposta()");
        availableOptions.add("6 - public void setResposta(Resposta resposta)");
        availableOptions.add("7 - public String dataToString()");
        while (runDriver) {
            System.out.println("CodiDriver");
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
                    runTiradaCodi();
                    break;
                case 2:
                    runTiradaCodiResposta();
                    break;
                case 3:
                    runGetCodi();
                    break;
                case 4:
                    runSetCodi();
                    break;
                case 5:
                    runGetResposta();
                    break;
                case 6:
                    runSetResposta();
                    break;
                case 7:
                    runDataToString();
                    break;
                default:
                    break;
            }
        }


    }

    private static void runTiradaCodi() {
        System.out.println("Escriu el nombre d'elements del codi de tirada, salt de línia i els elements separats amb un espai.");
        ArrayList<Integer> c = Utils.readNumsFromCommandLine();
        Codi codi = new Codi(c);
        tirada = new Tirada(codi);
    }
    private static void runTiradaCodiResposta() {
        System.out.println("Escriu el nombre d'elements del codi de tirada, salt de línia i els elements separats amb un espai.");
        ArrayList<Integer> c = Utils.readNumsFromCommandLine();
        Codi codi = new Codi(c);
        System.out.println("Escriu nWhites de la resposta.");
        int nWhites;
        Scanner scanner = new Scanner(System.in);
        nWhites = scanner.nextInt();
        System.out.println("Escriu nBlacks de la resposta.");
        int nBlacks = scanner.nextInt();
        Resposta resposta = new Resposta();
        resposta.setnWhites(nWhites);
        resposta.setnBlacks(nBlacks);
        tirada = new Tirada(codi,resposta);
    }
    private static void runGetCodi() {
        System.out.println(tirada.getCodi().dataToString());
    }
    private static void runSetCodi() {
        System.out.println("Escriu el nombre d'elements del codi, salt de línia i els elements separats amb un espai.");
        ArrayList<Integer> c = Utils.readNumsFromCommandLine();
        Codi codi = new Codi(c);
        tirada.setCodi(codi);
    }
    private static void runGetResposta() {
        System.out.println(tirada.getResposta().dataToString());
    }
    private static void runSetResposta() {
        System.out.println("Escriu nWhites de la resposta.");
        int nWhites;
        Scanner scanner = new Scanner(System.in);
        nWhites = scanner.nextInt();
        System.out.println("Escriu nBlacks de la resposta.");
        int nBlacks = scanner.nextInt();
        Resposta resposta = new Resposta();
        resposta.setnWhites(nWhites);
        resposta.setnBlacks(nBlacks);
        tirada.setResposta(resposta);
    }

    private static void runDataToString() {
        String s = tirada.dataToString();
        System.out.println(s);
    }

}
