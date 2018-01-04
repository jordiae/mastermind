package com.mastermind.domini;

import java.util.Scanner;

public class ControladorPartidaDriver {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
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

            ControladorPartida controladorPartida = new ControladorPartida(difficulty,codeMaker,codiSolucio,username);
        }
    }
}
/*
    public ControladorPartida(int difficulty, boolean codeMaker, Codi codiSolució, String username) {
    public ControladorPartida(int id, String username) {

    */
