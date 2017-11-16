package com.mastermind.domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CodiDriver {
    private static Codi codi;
    private static Scanner scanner;

    public static void main(String[] args) throws IOException {
        boolean runDriver = true;
        ArrayList<String> availableOptions = new ArrayList<String>();
        availableOptions.add("0 - Exit driver");
        availableOptions.add("1 - public Codi(ArrayList<Integer> peces)");
        availableOptions.add("2 - public ArrayList<Integer> getPeces()");
        availableOptions.add("3 - public void setPeces(ArrayList<Integer> peces)");
        availableOptions.add("4 - public void setPeca(int pos, int col)");
        availableOptions.add("5 - public boolean same(Codi c)");
        availableOptions.add("6 - public String dataToString()");
        while (runDriver) {
            System.out.println("CodiDriver");
            System.out.println("Mètodes disponibles:");
            for (String s : availableOptions) {
                System.out.println(s);
            }
            int option;
            scanner = new Scanner(System.in);
            option = scanner.nextInt();
            System.out.println(option);
            switch (option) {
                case 0:
                    runDriver = false;
                    break;
                case 1:
                    runCodi();
                    break;
                case 2:
                    runGetPeces();
                    break;
                case 3:
                    runSetPeces();
                    break;
                case 4:
                    runSetPeca();
                    break;
                case 5:
                    runSame();
                    break;
                case 6:
                    runDataToString();
                    break;
                default:
                    break;
            }
        }


    }
    private static void runCodi() {
        System.out.println("Escriu el nombre d'elements, salt de línia i els elements separats amb un espai.");
        ArrayList<Integer> p = Utils.readNumsFromCommandLine();
        codi = new Codi(p);
    }

    private static void runGetPeces() {
        String s = codi.getPeces().toString();
        System.out.println(s);
    }

    private static void runSetPeces() {
        System.out.println("Escriu el nombre d'elements, salt de línia i els elements separats amb un espai.");
        ArrayList<Integer> p = Utils.readNumsFromCommandLine();
        codi.setPeces(p);
    }

    private static void runSetPeca() {
        System.out.println("Escriu la posició i el color.");
        int pos = scanner.nextInt();
        int col = scanner.nextInt();
        codi.setPeca(pos,col);
    }

    private static void runSame() {
        System.out.println("Escriu el nombre d'elements, salt de línia i els elements separats amb un espai.");
        ArrayList<Integer> s = Utils.readNumsFromCommandLine();
        Codi same = new Codi(s);
        if (codi.same(same)) {
            System.out.println("TRUE");
        }
        else {
            System.out.println("FALSE");
        }
    }

    private static void runDataToString() {
        String s = codi.dataToString();
        System.out.println(s);
    }

}