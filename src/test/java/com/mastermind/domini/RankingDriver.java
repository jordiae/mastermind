package com.mastermind.domini;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class RankingDriver {
    private static Ranking ranking;
    private static boolean constructorExecuted;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        constructorExecuted = false;
        boolean runDriver = true;
        ArrayList<String> availableOptions = new ArrayList<String>();
        availableOptions.add("0 - Exit driver");
        availableOptions.add("1 - public Ranking()");
        availableOptions.add("2 - public int getRecordsGuardats()");
        availableOptions.add("3 - public ArrayList<Record> getRecordList()");
        availableOptions.add("4 - public boolean afegirRecord(Record record)");
        availableOptions.add("5 - public String dataToString()");
        while (runDriver) {
            System.out.println("RankingDriver");
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
                    runRanking();
                    break;
                case 2:
                    runGetRecordsGuardats();
                    break;
                case 3:
                    runGetRecordList();
                    break;
                case 4:
                    runAfegirRecord();
                    break;
                case 5:
                    runDataToString();
                    break;
                default:
                    break;
            }
        }
    }

    private static void runRanking() {
        ranking = new Ranking();
        constructorExecuted = true;
        System.out.println("S'ha creat una nova instància de la classe Ranking.");
    }

    private static void runGetRecordsGuardats() {
        System.out.println(ranking.getRecordsGuardats());
    }

    private static void runGetRecordList() {
        String s = ranking.getRecordList().toString();
        System.out.println(s);
    }

    private static void runAfegirRecord() {
        System.out.println("Escriu un nom i una puntuació, separats per espais.\n" +
                "La resta d'informació serà seleccionada arbitràriament.");
        String nom = scanner.next();
        int puntuacio = scanner.nextInt();
        Random random = new Random();
        Date date = new Date(random.nextInt(),random.nextInt(),random.nextInt());
        Record record = new Record(nom,puntuacio,true, date);
        boolean b = ranking.afegirRecord(record);
        System.out.println(b);
    }

    private static void runDataToString() {
        String s = ranking.dataToString();
        System.out.println(s);
    }

}
