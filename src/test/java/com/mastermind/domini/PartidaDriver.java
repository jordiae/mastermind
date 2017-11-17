package com.mastermind.domini;

import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class PartidaDriver {

    private static Partida partida = null;
    private static boolean created;
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ParseException {
        created = false;
        boolean run = true;


        ArrayList<String> availableOptions = new ArrayList<String>();
        availableOptions.add("0 -  Exit driver");
        availableOptions.add("1 -  public Partida(int ID, int difficulty, boolean codeMaker, boolean help, Time time, Taulell taulell)");
        availableOptions.add("2 -  public int getDifficulty()");
        availableOptions.add("3 -  public int getID()");
        availableOptions.add("4 -  public Time getTime()");
        availableOptions.add("5 -  public boolean isCodeMaker()");
        availableOptions.add("6 -  public boolean isHelp()");
        availableOptions.add("7 -  public void setCodeMaker(boolean codeMaker)");
        availableOptions.add("8 -  public void setDifficulty(int difficulty)");
        availableOptions.add("9 -  public void setHelp(boolean help))");
        availableOptions.add("10 - public void setID(int ID)");
        availableOptions.add("11 - public void setTime(Time time)");
        availableOptions.add("12 - public String dataToString()");
        availableOptions.add("13 - private boolean fesTirada(Codi codi)");
        availableOptions.add("14 - private int getPuntuacio()");

        while (run) {
            System.out.println("PartidaDriver");
            System.out.println("Mètodes disponibles:");
            for (String s : availableOptions) {
                System.out.println(s);
            }
            int option = scan.nextInt();
            switch (option) {
                case 0:
                    run = false;
                    break;
                case 1:
                    runPartida();
                    break;
                case 2:
                    if (partida == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runPartida();
                    }
                    runGetDifficulty();
                    break;
                case 3:
                    if (partida == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runPartida();
                    }
                    runGetID();
                    break;
                case 4:
                    if (partida == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runPartida();
                    }
                    runGetTime();
                    break;
                case 5:
                    if (partida == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runPartida();
                    }
                    runIsCodeMaker();
                    break;
                case 6:
                    if (partida == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runPartida();
                    }
                    runIsHelp();
                    break;
                case 7:
                    if (partida == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runPartida();
                    }
                    runSetCodemaker();
                    break;
                case 8:
                    if (partida == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runPartida();
                    }
                    runSetDifficulty();
                    break;
                case 9:
                    if (partida == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runPartida();
                    }
                    runSetHelp();
                    break;
                case 10:
                    if (partida == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runPartida();
                    }
                    runSetID();
                    break;
                case 11:
                    if (partida == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runPartida();
                    }
                    runSetTime();
                    break;
                case 12:
                    if (partida == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runPartida();
                    }
                    runDataToString();
                    break;
                case 13:
                    if (partida == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runPartida();
                    }
                    runFesTirada();
                    break;
                case 14:
                    if (partida == null) {
                        System.out.println("Cal crear una instància de récord abans de prosseguir");
                        runPartida();
                    }
                    runGetPuntuacio();
                    break;
            }
        }
    }

    private static void runPartida(){
        System.out.println("introdueix ID(int): ");
        int id = scan.nextInt();
        scan.nextLine();
        System.out.println("introdueix dificultat(int): ");
        int dif = scan.nextInt();
        scan.nextLine();
        System.out.println("introdueix codemaker(bool): ");
        Boolean b = scan.nextBoolean();
        scan.nextLine();
        System.out.println("introdueix help(bool): ");
        Boolean b2 = scan.nextBoolean();
        scan.nextLine();
        System.out.println("Introdueix temps acumulat: hores, minuts i segons.");
        int h  = scan.nextInt();
        int m  = scan.nextInt();
        int s  = scan.nextInt();
        scan.nextLine();
        Time timeE = Time.valueOf(h + ":" + m + ":" + s);
        Taulell t = new Taulell(1, new Codi(new ArrayList<Integer>()), new ArrayList<Tirada>(), 1);
        partida = new Partida(id, dif, b, b2, timeE, t);
    }

    private static void runGetDifficulty(){
        System.out.println("La dificultat es: " + partida.getDifficulty());

    }

    private static void runGetID(){
        System.out.println("ID de la partida: " + partida.getID());
    }

    private static void runGetTime(){
        Date d = new Date();
        System.out.println("Time: " + d.getTime());
    }

    private static void runIsCodeMaker(){
        if (partida.isCodeMaker()) System.out.println("Es codemaker");
        else System.out.println("Es codebreaker");
    }

    private static void runIsHelp(){
        if (partida.isHelp()) System.out.println("Ha utilitzat ajuda");
        else System.out.println("No ha utilitzat ajuda");
    }

    private static void runSetCodemaker(){
        System.out.println("Introdueix si el jugador es codemaker(bool): ");
        partida.setCodeMaker(scan.nextBoolean());
        scan.nextLine();
        if (partida.isCodeMaker()) System.out.println("Es codemaker");
        else System.out.println("Es codebreaker");
    }

    private static void runSetDifficulty(){
        System.out.println("Introdueix la dificultat(int): ");
        partida.setDifficulty(scan.nextInt());
        scan.nextLine();
        System.out.println("La dificultat es: " + partida.getDifficulty());
    }

    private static void runSetHelp(){
        System.out.println("Digues si el jugador ha utilitzat ajuda(bool): ");
        partida.setHelp(scan.nextBoolean());
        scan.nextLine();
        if (partida.isHelp()) System.out.println("Ha utilitzat ajuda");
        else System.out.println("No ha utilitzat ajuda");
    }

    private static void runSetID(){
        System.out.println("Introdueix ID(int): ");
        partida.setID(scan.nextInt());
        System.out.println("ID de la partida: " + partida.getID());
    }

    private static void runSetTime(){
        System.out.println("Introdueix temps acumulat: hores, minuts i segons.");
        int h  = scan.nextInt();
        int m  = scan.nextInt();
        int s  = scan.nextInt();
        Time.valueOf(h + ":" + m + ":" + s);
        System.out.println("Temps de la partida: " + partida.getTime());
    }

    private static void  runDataToString(){
        System.out.println("Dades: " + partida.dataToString());
    }

    private static void runFesTirada(){
        System.out.println("Introdueix un codi(INT INT INT INT): ");
        ArrayList<Integer> a = new ArrayList<>();
        for(int i = 0; i < 4; i++) a.add(scan.nextInt());
        partida.getTaulell().ferTirada(new Codi(a));
        System.out.println("dades taulell: " + partida.getTaulell().dataToString());
    }

    private static void runGetPuntuacio(){
        System.out.println("Puntuacio: " + partida.getDifficulty()*partida.getTaulell().getTorn());
    }
}
