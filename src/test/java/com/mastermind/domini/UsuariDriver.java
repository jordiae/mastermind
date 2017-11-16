package com.mastermind.domini;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class UsuariDriver {
    private static Usuari usuari;
    private static boolean constructorExecuted;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        constructorExecuted = false;
        boolean runDriver = true;
        ArrayList<String> availableOptions = new ArrayList<String>();
        availableOptions.add("0 - Exit driver");
        availableOptions.add("1 - public Usuari(String nom, String contrasenya)");
        availableOptions.add("2 - public String getNom()");
        availableOptions.add("3 - public String getContrasenya()");
        availableOptions.add("4 - public void setNom(String nom)");
        availableOptions.add("5 - public void setContrasenya(String contrasenya)");
        availableOptions.add("6 - public int getNumPartidesGuardades()");
        availableOptions.add("7 - public ArrayList<Partida> getPartidesGuardades()");
        availableOptions.add("8 - public boolean afegirPartida(Partida partida)");
        availableOptions.add("9 - public String dataToString()");
        while (runDriver) {
            System.out.println("UsuariDriver");
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
                    runUsuari();
                    break;
                case 2:
                    runGetNom();
                    break;
                case 3:
                    runGetContrasenya();
                    break;
                case 4:
                    runSetNom();
                    break;
                case 5:
                    runSetContrasenya();
                    break;
                case 6:
                    runGetNumPartidesGuardades();
                    break;
                case 7:
                    runGetPartidesGuardades();
                    break;
                case 8:
                    runAfegirPartida();
                    break;
                case 9:
                    runDataToString();
                    break;
                default:
                    break;
            }
        }
    }

    private static void runUsuari() {
        System.out.println("Escriu el nom d'usuari i la contrasenya separats per un espai.");
        String nom = scanner.next();
        String contrasenya = scanner.next();
        usuari = new Usuari(nom,contrasenya);
        constructorExecuted = true;
        System.out.println("S'ha creat una nova instància d'usuari.");
    }

    private static void runGetNom() {
        System.out.println(usuari.getNom());
    }

    private static void runGetContrasenya() {
        System.out.println(usuari.getContrasenya());
    }

    private static void runSetNom() {
        System.out.println("Introdueix el nou nom d'usuari.");
        usuari.setNom(scanner.next());
    }

    private static void runSetContrasenya() {
        System.out.println("Introdueix el nou nom de contrasenya.");
        usuari.setContrasenya(scanner.next());
    }

    private static void runGetNumPartidesGuardades() {
        System.out.println(usuari.getNumPartidesGuardades());
    }

    private static void runGetPartidesGuardades() {
        System.out.println(usuari.getPartidesGuardades().toString());
    }

    private static void runAfegirPartida() {
        System.out.println("S'afegirà una instància de partida amb uns parametres qualsevols.");
        Random rand = new Random();
        int id = rand.nextInt((int) 1e6);
        int dificulty = rand.nextInt(3);
        Time time = new Time(rand.nextInt((int) 1e6));
        Partida partida = new Partida(id,dificulty,true,true,time,null);
        System.out.println(usuari.afegirPartida(partida));
    }

    private static void runDataToString() {
        System.out.println(usuari.dataToString());
    }

}
