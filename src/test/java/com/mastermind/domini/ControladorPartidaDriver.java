package com.mastermind.domini;

import java.util.ArrayList;
import java.util.Scanner;

public class ControladorPartidaDriver {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Benvingut al driver de ControladorPartida");
        System.out.println("0 - Nova Partida");
        System.out.println("1 - Carregar Partida");
        boolean novaPartida;
        if (scan.nextInt() == 0) {
            novaPartida = true;
        }
        else {
            novaPartida = false;
        }
        if (novaPartida) {

            System.out.println("Difficulty (0, 1, 2):");
            int difficulty = scan.nextInt();
            boolean codeMaker;
            System.out.println("0 - codeMaker = false");
            System.out.println("1 - codemaker = true");
            codeMaker = scan.nextBoolean();
            System.out.println("Username = Carles");
            String username = "Carles";

            ArrayList<Integer> peces = new ArrayList<>();
            peces.add(0);
            peces.add(1);
            peces.add(2);
            peces.add(3);
            Codi codiSolucio = new Codi(peces); // codi solucio, random o inventat per l'usuari depenent del rol
            ControladorPartida controladorPartida = new ControladorPartida(difficulty,codeMaker,codiSolucio,username);
            if (codeMaker) {
                //controladorPartida.
            }
            else {
                //
            }

        }
        else {
            //ControladorPartida controladorPartida = ControladorPartida(int id, String username);
            System.out.println("Carregar partida encara no implementat al driver");
            System.exit(0);
        }
    }
}
/*
    public ControladorPartida(int difficulty, boolean codeMaker, Codi codiSolució, String username) {
    public ControladorPartida(int id, String username) {

    */
