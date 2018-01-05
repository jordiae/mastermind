package com.mastermind.domini;

import java.io.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class IADriverAux {
    public static void main(String[] args){

        int mitja4 = 0;
        int mitja5 = 0;
        int mitja6 = 0;


        Scanner scan = new Scanner(System.in);
        Writer output;
        Random generator = new Random();

        File f = new File("codis4.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        f = new File("codis5.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        f = new File("codis6.txt");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }



        try{
            output = new BufferedWriter(new FileWriter("codis4.txt", true));  //clears file every time
            for (int i = 0; i < 100; ++i){
                String codi = "";
                for (int j = 0; j < 3; ++j) codi = codi +  (generator.nextInt(6) + 1) + " ";
                codi = codi  +  (generator.nextInt(6) + 1) + "\n";
                output.append(codi);
            }
            output.close();
        }
        catch(Throwable t){
            System.out.println("no s'ha pogut guardar el codi");
        }
        try{
            output = new BufferedWriter(new FileWriter("codis5.txt", true));  //clears file every time
            for (int i = 0; i < 100; ++i){
                String codi = "";
                for (int j = 0; j < 4; ++j) codi = codi +  (generator.nextInt(6) + 1) + " ";
                codi = codi  +  (generator.nextInt(6) + 1)+ "\n";
                output.append(codi);
            }
            output.close();
        }
        catch(Throwable t){
            System.out.println("no s'ha pogut guardar el codi");
        }
        try{
            output = new BufferedWriter(new FileWriter("codis6.txt", true));  //clears file every time
            for (int i = 0; i < 100; ++i){
                String codi = "";
                for (int j = 0; j < 5; ++j) codi = codi +  (generator.nextInt(6) + 1) + " ";
                codi = codi  +  (generator.nextInt(6) + 1)+ "\n";
                output.append(codi);
            }
            output.close();

        }
        catch(Throwable t){
            System.out.println("no s'ha pogut guardar el codi");
        }


        try (BufferedReader br = new BufferedReader(new FileReader("codis4.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {

                IA ia = new IA(6, 4);
                String[] codi = line.split(" ");
                ArrayList<Integer> c = new ArrayList<>();
                c.add(Integer.parseInt(codi[0]));
                c.add(Integer.parseInt(codi[1]));
                c.add(Integer.parseInt(codi[2]));
                c.add(Integer.parseInt(codi[3]));
                Codi sol = new Codi(c);
                Codi guess;
                Resposta r = new Resposta();
                boolean firstTry = true;
                int torns = 0;
                while(r.getnBlacks() != 4){
                    ++torns;
                    if (firstTry) {
                        firstTry = false;
                        guess = ia.firstGuess();
                    }
                    else guess = ia.nextGuess();
                    r.calcularResposta(guess, sol);
                    //System.out.println("guess:  " + guess.getPeces().toString());
                    //System.out.println("B(" + r.getnBlacks() + ") W(" + r.getnWhites() + ")");
                    //System.out.println();
                    ia.updateLastGuess(r.getnBlacks(), r.getnWhites());

                }
                mitja4 += torns;

            }
            System.out.println((float)mitja4/100);

        }
        catch(Throwable t){
            System.out.println("no s'ha pogut obrir el fitxer!!");

        }

        try (BufferedReader br = new BufferedReader(new FileReader("codis5.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {

                IA ia = new IA(6, 5);
                String[] codi = line.split(" ");
                ArrayList<Integer> c = new ArrayList<>();
                c.add(Integer.parseInt(codi[0]));
                c.add(Integer.parseInt(codi[1]));
                c.add(Integer.parseInt(codi[2]));
                c.add(Integer.parseInt(codi[3]));
                c.add(Integer.parseInt(codi[4]));
                Codi sol = new Codi(c);
                Codi guess;
                Resposta r = new Resposta();
                boolean firstTry = true;
                int torns = 0;
                while(r.getnBlacks() != 5){
                    ++torns;
                    if (firstTry) {
                        firstTry = false;
                        guess = ia.firstGuess();
                    }
                    else guess = ia.nextGuess();
                    r.calcularResposta(guess, sol);
                    //System.out.println("guess:  " + guess.getPeces().toString());
                    //System.out.println("B(" + r.getnBlacks() + ") W(" + r.getnWhites() + ")");
                    //System.out.println();
                    ia.updateLastGuess(r.getnBlacks(), r.getnWhites());

                }
                mitja5 += torns;

            }
            System.out.println((float)mitja5/100);

        }
        catch(Throwable t){
            System.out.println("no s'ha pogut obrir el fitxer!!");

        }

        try (BufferedReader br = new BufferedReader(new FileReader("codis6.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {

                IA ia = new IA(6, 6);
                String[] codi = line.split(" ");
                ArrayList<Integer> c = new ArrayList<>();
                c.add(Integer.parseInt(codi[0]));
                c.add(Integer.parseInt(codi[1]));
                c.add(Integer.parseInt(codi[2]));
                c.add(Integer.parseInt(codi[3]));
                c.add(Integer.parseInt(codi[4]));
                c.add(Integer.parseInt(codi[5]));
                Codi sol = new Codi(c);
                Codi guess;
                Resposta r = new Resposta();
                boolean firstTry = true;
                int torns = 0;
                while(r.getnBlacks() != 6){
                    ++torns;
                    if (firstTry) {
                        firstTry = false;
                        guess = ia.firstGuess();
                    }
                    else guess = ia.nextGuess();
                    r.calcularResposta(guess, sol);
                    //System.out.println("guess:  " + guess.getPeces().toString());
                    //System.out.println("B(" + r.getnBlacks() + ") W(" + r.getnWhites() + ")");
                    //System.out.println();
                    ia.updateLastGuess(r.getnBlacks(), r.getnWhites());

                }
                mitja6 += torns;

            }
            System.out.println((float)mitja6/100);

        }
        catch(Throwable t){
            System.out.println("no s'ha pogut obrir el fitxer!!");

        }

    }
}
