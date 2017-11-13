package com.mastermind.domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaulellDriver {
    private static Taulell taulell;
    static boolean constructorExecuted;

    public static void main(String[] args) throws IOException {
        constructorExecuted = false;
        boolean runDriver = true;
        ArrayList<String> availableOptions = new ArrayList<String>();
        availableOptions.add("0 - Exit driver");
        availableOptions.add("1 - public Taulell(int torn, Codi codiSolucio, ArrayList<Tirada> tirades, int maxTorn)");
        availableOptions.add("2 - public int getTorn()");
        availableOptions.add("3 - public void setTorn(int torn)");
        availableOptions.add("4 - public boolean ferTirada(Codi codi)");
        availableOptions.add("5 - public Codi getCodiSolucio()");
        availableOptions.add("6 - public void setCodiSolucio(Codi codiSolucio)");
        availableOptions.add("7 - public void setMaxTorn(int maxTorn)");
        availableOptions.add("8 - public int getMaxTorn()");
        availableOptions.add("9 - public String dataToString()");

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
                    runTaulell();
                    break;
                case 2:
                    runGetTorn();
                    break;
                case 3:
                    runSetTorn();
                    break;
                case 4:
                    runFerTirada();
                    break;
                case 5:
                    runGetCodiSolucio();
                    break;
                case 6:
                    runSetCodiSolucio();
                    break;
                case 7:
                    runSetMaxTorn();
                    break;
                case 8:
                    runGetMaxTorn();
                    break;
                case 9:
                    runDataToString();
                    break;
                default:
                    break;
            }
        }


    }
    private static void runTaulell() {
        System.out.println("Escriu el torn actual i salt de línia");
        int t;
        Scanner scanner = new Scanner(System.in);
        t = scanner.nextInt();
        System.out.println("Escriu el nombre d'elements del codi solució, salt de línia i els elements separats amb un espai.");
        ArrayList<Integer> c = readNumsFromCommandLine();
        Codi codi = new Codi(c);

        System.out.println("Escriu el nombre de tirades, salt de línia, i les tirades");
        System.out.println("Per cada tirada, el nombre d'elements del codi, salt de linia, i els elements de codi separats per un espai. Després nWhites i nBlacks separats per un salt de línia");
        int nTirades = scanner.nextInt();
        ArrayList<Tirada> tirades = new ArrayList<Tirada>();
        for (int i = 0; i < nTirades; i++) {
            ArrayList<Integer> cTirada = readNumsFromCommandLine();
            Codi codiTirada = new Codi(cTirada);
            int nWhites = scanner.nextInt();
            int nBlacks = scanner.nextInt();
            Resposta resposta = new Resposta();
            resposta.setnWhites(nWhites);
            resposta.setnBlacks(nBlacks);
            Tirada tirada = new Tirada(codiTirada,resposta);
            tirades.add(tirada);

        }
        System.out.println("Escriu el màxim de torns i salt de línia");
        int m;
        m = scanner.nextInt();
        taulell = new Taulell(t,codi,tirades,m);
        constructorExecuted = true;
    }
    private static void runGetTorn() {
        System.out.println(taulell.getTorn());
    }
    private static void runSetTorn() {
        int t;
        Scanner scanner = new Scanner(System.in);
        t = scanner.nextInt();
        taulell.setTorn(t);
    }
    private static void runFerTirada() {
        System.out.println("Escriu el nombre d'elements del codi de la nova tirada, salt de línia i els elements separats amb un espai.");
        ArrayList<Integer> c = readNumsFromCommandLine();
        Codi codi = new Codi(c);
        taulell.ferTirada(codi);
    }
    private static void runGetCodiSolucio() {
        System.out.println(taulell.getCodiSolucio().dataToString());
    }
    private static void runSetCodiSolucio() {
        System.out.println("Escriu el nombre d'elements del codi solució, salt de línia i els elements separats amb un espai.");
        ArrayList<Integer> c = readNumsFromCommandLine();
        Codi codi = new Codi(c);
        taulell.setCodiSolucio(codi);
    }
    private static void runSetMaxTorn() {
        int t;
        Scanner scanner = new Scanner(System.in);
        t = scanner.nextInt();
        taulell.setMaxTorn(t);
    }
    private static void runGetMaxTorn() {
        System.out.println(taulell.getMaxTorn());
    }

    private static void runDataToString() {
        String s = taulell.dataToString();
        System.out.println(s);
    }


    private static ArrayList<Integer> readNumsFromCommandLine() {

        Scanner s = new Scanner(System.in);

        int count = s.nextInt();
        s.nextLine(); // throw away the newline.

        ArrayList<Integer> numbers = new ArrayList<Integer>(count);
        Scanner numScanner = new Scanner(s.nextLine());
        for (int i = 0; i < count; i++) {
            if (numScanner.hasNextInt()) {
                numbers.add (numScanner.nextInt());
            } else {
                System.out.println("Error: no suficients nombres");
                break;
            }
        }

        return numbers;
    }

}
