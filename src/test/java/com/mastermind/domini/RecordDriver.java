package com.mastermind.domini;

import java.io.IOException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.DateFormat;

public class RecordDriver {
    private static Record record = null;
    private static boolean created;
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ParseException {
        created = false;
        boolean run = true;


        ArrayList<String> availableOptions = new ArrayList<String>();
        availableOptions.add("0 - Exit driver");
        availableOptions.add("1 -  public Record(String name, int score, boolean codeMaker, Date data)");
        availableOptions.add("2 -  public Date getData()");
        availableOptions.add("3 - public int getScore()");
        availableOptions.add("4 - public String getName()");
        availableOptions.add("5 - public boolean isCodeMaker()");
        availableOptions.add("6 -  public void setCodeMaker(boolean codeMaker))");
        availableOptions.add("7 - public void setData(Date data)");
        availableOptions.add("8 - public void setName(String name)");
        availableOptions.add("9 - public void setScore(int score)");
        availableOptions.add("10 -  public String dataToString()");
        String error = "no s'ha creat un Record!";

        while (run) {
            System.out.println("CodiDriver");
            System.out.println("Mètodes disponibles:");
            for (String s : availableOptions) {
                System.out.println(s);
            }
            int option = scan.nextInt();
            switch(option) {
                case 0:
                    run = false;
                    break;
                case 1:
                    runRecord();
                    break;
                case 2:
                    if (record == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runRecord();
                    }
                    runGetData();
                    break;
                case 3:
                    if (record == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runRecord();
                    }
                    runGetScore();
                    break;
                case 4:
                    if (record == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runRecord();
                    }
                    runGetName();
                    break;
                case 5:
                    if (record == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runRecord();
                    }
                    runIsCodeMaker();
                    break;
                case 6:
                    if (record == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runRecord();
                    }
                    runSetCodeMaker();
                    break;
                case 7:
                    if (record == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runRecord();
                    }
                    runSetData();
                    break;
                case 8:
                    if (record == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runRecord();
                    }
                    runSetName();
                    break;
                case 9:
                    if (record == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runRecord();
                    }
                    runSetScore();
                    break;
                case 10:
                    if (record == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runRecord();
                    }
                    runDataToString();
                    break;
            }
        }
    }

    private static void runRecord() throws ParseException {
        System.out.println("introdueix el nom(String): ");
        String nom = scan.nextLine();
        System.out.println("introdueix la puntuacio(int): ");
        int score = scan.nextInt();
        System.out.println("introdueix si el rol era codemaker(bool): ");
        boolean codemaker = scan.nextBoolean();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        //record = new Record(nom, score, codemaker, date);
        System.out.println("record creat! ja pots provar les funcions!");
    }

    private static void runGetData() throws ParseException {
        System.out.println("Data del record: "+record.getTime().toString());
    }

    private static void runGetScore() {
        System.out.println("Puntuació del record: "+ (record.getScore()));
    }

    private static void runGetName() {
        System.out.println("Nom del jugador del record: "+ (record.getName()));
    }

    private static void runIsCodeMaker() {
        if (record.isCodeMaker()) System.out.println("El jugador té el rol de Codemaker");
        else System.out.println("El jugador té el rol de Codebreaker");
    }

    private static void runSetCodeMaker() {
        System.out.println("Selecciona el rol del jugador:");
        System.out.println("    0. CodeBreaker");
        System.out.println("    1. CodeMaker");
        Boolean codemaker = scan.nextBoolean();
        record.setCodeMaker(codemaker);
        runIsCodeMaker();
    }

    private static void runSetData() throws ParseException {
        System.out.println("Introdueix la data(dd-M-yyyy hh:mm:ss):");
        String data = scan.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date date = sdf.parse(data);
        //record.setData(date);
        runGetData();
    }

    private static void runSetName() {
        System.out.println("Introdueix el nom del jugador:");
        String name = scan.nextLine();
        record.setName(name);
        runGetName();
    }

    private static void runSetScore() {
        System.out.println("Introdueix la puntuació:");
        Integer score = scan.nextInt();
        record.setScore(score);
        runGetScore();
    }

    private static void runDataToString() {
        System.out.println("Data parsejada de tipus date a String: "+record.dataToString());
    }
}
