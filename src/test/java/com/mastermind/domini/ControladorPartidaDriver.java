package com.mastermind.domini;

import com.mastermind.persistencia.DataController;

import javax.naming.ldap.Control;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
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
                    System.out.println(controladorPartida.getCurrentTurn());
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
            boolean codeMaker = false;
            if (codeMaker) {
                //ControladorPartida controladorPartida = ControladorPartida(int id, String username);
                String user = "Carles";

                //int id = (int) (new Date().getTime()/1000);
                int id = 1515109006;
                int difficulty = 0;

                boolean help = false;
                Time time = new Time(50);

                int torn = 3; // (no 2, ja s'han fet els anteriors)
                ArrayList<Integer> pecesSolucio = new ArrayList<>();
                pecesSolucio.add(2);
                pecesSolucio.add(3);
                pecesSolucio.add(5);
                pecesSolucio.add(6);
                Codi codiSolucio = new Codi(pecesSolucio);
                ArrayList<Integer> peces1 = new ArrayList<>();
                peces1.add(1);
                peces1.add(1);
                peces1.add(2);
                peces1.add(2);
                ArrayList<Integer> peces2 = new ArrayList<>();
                peces2.add(4);
                peces2.add(6);
                peces2.add(3);
                peces2.add(1);
                ArrayList<Integer> peces3 = new ArrayList<>();
                peces3.add(3);
                peces3.add(2);
                peces3.add(5);
                peces3.add(6);
                Codi codi1 = new Codi(peces1);
                Codi codi2 = new Codi(peces2);
                Codi codi3 = new Codi(peces3);
                Resposta resposta1 = new Resposta();
                resposta1.calcularResposta(codi1, codiSolucio);
                Resposta resposta2 = new Resposta();
                resposta2.calcularResposta(codi2, codiSolucio);
                Resposta resposta3 = new Resposta();
                resposta3.calcularResposta(codi3, codiSolucio);
                Tirada tirada1 = new Tirada(codi1, resposta1);
                Tirada tirada2 = new Tirada(codi2, resposta2);
                Tirada tirada3 = new Tirada(codi3, resposta3);
                ArrayList<Tirada> tirades = new ArrayList<>();
                tirades.add(tirada1);
                tirades.add(tirada2);
                tirades.add(tirada3);

                int maxTorn = 10;
                Taulell taulell = new Taulell(torn, codiSolucio, tirades, maxTorn);
                Partida partidaADesar = new Partida(id, difficulty, codeMaker, help, time, taulell);

                DataController.savePartida(partidaADesar, user);


                ControladorPartida controladorPartida = new ControladorPartida(id, user);

                boolean guessed = false;

                while (controladorPartida.getCurrentTurn() <= controladorPartida.getMaxTurn() && !guessed) { // segurament sobra "="
                    System.out.println("Torn: " + controladorPartida.getCurrentTurn());
                    Codi guessIA;
                    if (controladorPartida.getCurrentTurn() == 0) {
                        guessIA = controladorPartida.getFirstGuess();
                    } else {
                        System.out.println("hola");
                        guessIA = controladorPartida.nextGuessIA();

                    }
                    System.out.println("Guess IA: " + guessIA.dataToString());
                    System.out.println("nBlacks i nWhites:");
                    int nBlacks = scan.nextInt();
                    int nWhites = scan.nextInt();
                    Resposta resposta = new Resposta();
                    resposta.setnWhites(nWhites);
                    resposta.setnBlacks(nBlacks);
                    controladorPartida.novaRespostaCodemaker(guessIA, resposta);
                    if (nBlacks == 4) {
                        guessed = true;
                        System.out.println("Guessed!");
                    }
                }


            }
            else {
                //ControladorPartida controladorPartida = ControladorPartida(int id, String username);
                String user = "Carles";

                //int id = (int) (new Date().getTime()/1000);
                int id = 1515109006;
                int difficulty = 0;

                boolean help = false;
                Time time = new Time(50);

                int torn = 3; // (no 2, ja s'han fet els anteriors)
                ArrayList<Integer> pecesSolucio = new ArrayList<>();
                pecesSolucio.add(2);
                pecesSolucio.add(3);
                pecesSolucio.add(5);
                pecesSolucio.add(6);
                Codi codiSolucio = new Codi(pecesSolucio);
                ArrayList<Integer> peces1 = new ArrayList<>();
                peces1.add(1);
                peces1.add(1);
                peces1.add(2);
                peces1.add(2);
                ArrayList<Integer> peces2 = new ArrayList<>();
                peces2.add(4);
                peces2.add(6);
                peces2.add(3);
                peces2.add(1);
                ArrayList<Integer> peces3 = new ArrayList<>();
                peces3.add(3);
                peces3.add(2);
                peces3.add(5);
                peces3.add(6);
                Codi codi1 = new Codi(peces1);
                Codi codi2 = new Codi(peces2);
                Codi codi3 = new Codi(peces3);
                Resposta resposta1 = new Resposta();
                resposta1.calcularResposta(codi1, codiSolucio);
                Resposta resposta2 = new Resposta();
                resposta2.calcularResposta(codi2, codiSolucio);
                Resposta resposta3 = new Resposta();
                resposta3.calcularResposta(codi3, codiSolucio);
                Tirada tirada1 = new Tirada(codi1, resposta1);
                Tirada tirada2 = new Tirada(codi2, resposta2);
                Tirada tirada3 = new Tirada(codi3, resposta3);
                ArrayList<Tirada> tirades = new ArrayList<>();
                tirades.add(tirada1);
                tirades.add(tirada2);
                tirades.add(tirada3);

                int maxTorn = 10;
                Taulell taulell = new Taulell(torn, codiSolucio, tirades, maxTorn);
                Partida partidaADesar = new Partida(id, difficulty, codeMaker, help, time, taulell);

                DataController.savePartida(partidaADesar, user);


                ControladorPartida controladorPartida = new ControladorPartida(id, user);

                boolean guessed = false;

                while (controladorPartida.getCurrentTurn() <= controladorPartida.getMaxTurn() && !guessed) { // segurament sobra "="
                    System.out.println("Torn: " + controladorPartida.getCurrentTurn());
                    Codi guessIA;
                    if (controladorPartida.getCurrentTurn() == 0) {
                        guessIA = controladorPartida.getFirstGuess();
                    } else {
                        System.out.println("hola");
                        guessIA = controladorPartida.nextGuessIA();

                    }
                    System.out.println("Guess IA: " + guessIA.dataToString());
                    System.out.println("nBlacks i nWhites:");
                    int nBlacks = scan.nextInt();
                    int nWhites = scan.nextInt();
                    Resposta resposta = new Resposta();
                    resposta.setnWhites(nWhites);
                    resposta.setnBlacks(nBlacks);
                    controladorPartida.novaRespostaCodemaker(guessIA, resposta);
                    if (nBlacks == 4) {
                        guessed = true;
                        System.out.println("Guessed!");
                    }
                }


            }
        }





    }
}
/*
    public ControladorPartida(int difficulty, boolean codeMaker, Codi codiSolució, String username) {
    public ControladorPartida(int id, String username) {

    */
