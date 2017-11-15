package com.mastermind.domini;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class IADriver {
    private static IA ia;
    static boolean constructorExecuted;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        constructorExecuted = false;
        boolean runDriver = true;
        ArrayList<String> availableOptions = new ArrayList<String>();
        availableOptions.add("0 - Exit driver");
        availableOptions.add("1 - public IA(int colors, int positions)");
        availableOptions.add("2 - public Codi nextGuess()");
        availableOptions.add("3 - public void updateLastGuess(int nBlack, int nWhite)");
        while (runDriver) {
            System.out.println("CodiDriver");
            System.out.println("Mètodes disponibles:");
            for (String s : availableOptions) {
                System.out.println(s);
            }
            int option;
            option = scanner.nextInt();
            System.out.println(option);
            switch (option) {
                case 0:
                    runDriver = false;
                    break;
                case 1:
                    runIA();
                    break;
                case 2:
                    runNextGuess();
                    break;
                case 3:
                    runUpdateLastGuess();
                default:
                    break;
            }
        }

    }

    private static void runIA() {
        System.out.println("Escriu el nombre de colors i de posicions amb els quals jugarà la IA.");
        int colors = scanner.nextInt();
        int posicions = scanner.nextInt();
        ia = new IA(colors,posicions);
        constructorExecuted = true;
        System.out.println("S'ha creat una nova instància de IA.");
        System.out.println(ia.firstGuess().dataToString());
    }

    private static void runNextGuess() {
        System.out.println(ia.nextGuess().dataToString());
    }

    private  static void  runUpdateLastGuess() {
        System.out.println("Digues el nombre de peces blanques i negres que vols col·locar respectivament");
        int nWhite = scanner.nextInt();
        int nBlack = scanner.nextInt();
        ia.updateLastGuess(nBlack,nWhite);
    }
}
