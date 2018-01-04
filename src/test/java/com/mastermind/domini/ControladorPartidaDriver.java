package com.mastermind.domini;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
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
            System.out.println("codeMaker = false");
            System.out.println("codemaker = true");
            codeMaker = scan.nextBoolean();
            System.out.println("Username = Carles");
            String username = "Carles";
            Codi codiSolucio;
            if (codeMaker) {
                ArrayList<Integer> peces = new ArrayList<>();
                peces.add(1);
                peces.add(2);
                peces.add(3);
                peces.add(4);
                codiSolucio = new Codi(peces); // codi solucio, random o inventat per l'usuari depenent del rol
                System.out.println("codiSolucio = 1234");
            }
            else {
                Random random = new Random();
                ArrayList<Integer> pecesAEndevinar = new ArrayList<>();
                pecesAEndevinar.add(random.nextInt(6)+1);
                pecesAEndevinar.add(random.nextInt(6)+1);
                pecesAEndevinar.add(random.nextInt(6)+1);
                pecesAEndevinar.add(random.nextInt(6)+1);
                Codi codiAEndevinar = new Codi(pecesAEndevinar);
                codiSolucio = codiAEndevinar;
            }
            ControladorPartida controladorPartida = new ControladorPartida(difficulty,codeMaker,codiSolucio,username);
            boolean guessed = false;
            if (codeMaker) { // Aquest cas aparentment funciona
                while (controladorPartida.getCurrentTurn() <= controladorPartida.getMaxTurn() && !guessed) { // segurament sobra "="
                    System.out.println("Torn: " + controladorPartida.getCurrentTurn());
                    Codi guessIA;
                    if (controladorPartida.getCurrentTurn() == 0) {
                        guessIA = controladorPartida.getFirstGuess();
                    }
                    else {
                        guessIA = controladorPartida.nextGuessIA();

                    }
                    System.out.println("Guess IA: " + guessIA.dataToString());
                    System.out.println("nBlacks i nWhites:");
                    int nBlacks = scan.nextInt();
                    int nWhites = scan.nextInt();
                    Resposta resposta = new Resposta();
                    resposta.setnWhites(nWhites);
                    resposta.setnBlacks(nBlacks);
                    controladorPartida.novaRespostaCodemaker(guessIA,resposta);
                    if (nBlacks == 4) {
                        guessed = true;
                        System.out.println("Guessed!");
                    }
                }

            }
            else { // Aquest cas aparentment funciona
                while (controladorPartida.getCurrentTurn() < controladorPartida.getMaxTurn() && !guessed) {
                    System.out.println("Torn: " + controladorPartida.getCurrentTurn());

                    ArrayList<Integer> pecesProposades = new ArrayList<>();

                    System.out.println("Proposta: 4 peces (entre 1 i 6)");

                    pecesProposades.add(scan.nextInt());
                    pecesProposades.add(scan.nextInt());
                    pecesProposades.add(scan.nextInt());
                    pecesProposades.add(scan.nextInt());

                    Codi codi = new Codi(pecesProposades);
                    Taulell taulell = controladorPartida.novaTiradaUsuariCodebreaker(codi);
                    if (taulell == null) {
                        System.out.println("Nova tirada ha fallat");
                        System.exit(0);
                    }
                    Resposta resposta = taulell.getTirades().get(taulell.getTirades().size()-1).getResposta();
                    System.out.println("Resposta: " + "nBlacks = " + resposta.getnBlacks());
                    System.out.println("Resposta: " + "nWhites = " + resposta.getnWhites());

                    if (resposta.getnBlacks() == 4) {
                        guessed = true;
                        System.out.println("Guessed!");
                        System.out.println("La solucio era: " + codiSolucio.dataToString());
                    }
                }

            }

        }







        else {
            //ControladorPartida controladorPartida = ControladorPartida(int id, String username);



        }





    }
}
/*
    public ControladorPartida(int difficulty, boolean codeMaker, Codi codiSolució, String username) {
    public ControladorPartida(int id, String username) {

    */
