package com.mastermind.domini;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class CodiDriver {
    private static Codi codi;
    static boolean constructorExecuted;

    public static void main(String[] args) throws IOException {
        constructorExecuted = false;
        boolean runDriver = true;
        ArrayList<String> availableOptions = new ArrayList<String>();
        availableOptions.add("0 - Exit driver");
        availableOptions.add("1 - public Codi(ArrayList<Integer> peces)");
        availableOptions.add("2 - public ArrayList<Integer> getPeces()");
        availableOptions.add("3 - public void setPeces(ArrayList<Integer> peces)");
        availableOptions.add("4 - public String dataToString()");
        availableOptions.add("5 - public void setFitnessScore(int s)");
        availableOptions.add("6 - public int getFitnessScore()");
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
                    runCodi();
                    break;
                case 2:
                    runGetPeces();
                    break;
                case 3:
                    runSetPeces();
                    break;
                case 4:
                    runDataToString();
                    break;
                case 5:
                    runSetFitnessScore();
                    break;
                case 6:
                    runGetFitnessScore();
                    break;
                default:
                    break;
            }
        }


    }
    private static void runCodi() {
        System.out.println("Escriu el nombre d'elements, salt de línia i els elements separats amb un espai.");
        ArrayList<Integer> p = readNumsFromCommandLine();
        codi = new Codi(p);
        constructorExecuted = true;

    }

    private static void runGetPeces() {
        String s = codi.getPeces().toString();
        System.out.println(s);

    }

    private static void runSetPeces() {
        System.out.println("Escriu el nombre d'elements, salt de línia i els elements separats amb un espai.");
        ArrayList<Integer> p = readNumsFromCommandLine();
        codi.setPeces(p);

    }

    private static void runDataToString() {
        String s = codi.dataToString();
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

    private static void runGetFitnessScore() {
        System.out.println(codi.getFitnessScore());
    }

    private static void runSetFitnessScore() throws IOException {
        int f;
        Scanner scanner = new Scanner(System.in);
        f = scanner.nextInt();
        codi.setFitnessScore(f);

    }


}