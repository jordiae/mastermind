package com.mastermind.domini;


import javafx.util.Pair;

import java.util.*;

public class IA {
    public static int MAX_POPULATION = 200;
    public static int MAX_GENERATION = 100;
    public static int MAX_ELEGIBLES = 60;
    public static Double PROB_CROSSOVER = 0.5;
    public static Double PROB_MUTATION = 0.03;
    public static Double PROB_PERMUTATION = 0.03;
    public static Double PROB_INVERSION = 0.02;

    private int colors; //nombre maxim de colors
    private int positions; //nombre de posicions dels codis
    private Random generator;
    private ArrayList<Tirada> prevGuess;

    public IA(int colors, int positions, ArrayList<Tirada> a){
        this.colors = colors;
        this.positions = positions;
        generator = new Random();
        prevGuess = a;

    }

    public IA(int colors, int positions) {
        this.colors = colors;
        this.positions = positions;
        generator = new Random();
        prevGuess = new ArrayList<>();
        ArrayList<Integer> aux = new ArrayList<>();
        for (int i = 0; i < positions / 2; ++i) {
            aux.add(i, 1);
        }
        for (int i = positions / 2; i < positions; ++i) {
            aux.add(i, 2);
        }
        Codi c = new Codi(aux);
        prevGuess.add(new Tirada(c));
    }

    public Codi firstGuess(){return prevGuess.get(0).getCodi();}

    public Codi nextGuess(){

        ArrayList<Codi> elegibles = new ArrayList<>();
        ArrayList<Codi> population = new ArrayList<>();

        while (population.size() < MAX_POPULATION){
            Codi aux = genRandCodi();
            if (!population.contains(aux)) population.add(aux);
        }  //inicialitzem poblacio aleatoria

        Comparator<Codi> comp = new Comparator<Codi>() {
            @Override
            public int compare(Codi o1, Codi o2) {
                return fitnessScore(o1, prevGuess) - fitnessScore(o2, prevGuess);
            }
        };

        int h = 1;
        int a = 1;
        int b = 1;
        while (h <= MAX_GENERATION*a && elegibles.size() <= MAX_ELEGIBLES*b) {
            ArrayList<Codi> sons = new ArrayList<>();

            for(int i = 0; i < MAX_POPULATION; ++i) {  //generem nova poblacio a partir de l'anterior
                Codi c;
                if (i == population.size() - 1) c = population.get(i);
                else c = crossover(population.get(i), population.get(i + 1));

                if (generator.nextFloat() <= PROB_MUTATION) c = mutation(c);   //apliquem mutacions segons probabilitat
                if (generator.nextFloat() <= PROB_PERMUTATION) c = permutation(c);
                if (generator.nextFloat() <= PROB_INVERSION) c = inversion(c);

                if (!sons.contains(c)) sons.add(c);
                else {
                    while (sons.contains(c)) c = genRandCodi();
                    sons.add(c);
                }
            }
            Collections.sort(population, comp);
            ArrayList<Codi> seleccio = new ArrayList<>();

            for(int i = 0; i < population.size(); ++i) if (elegible(population.get(i), prevGuess)) seleccio.add(population.get(i));

            if (seleccio.size() == 0){
                h++;
                continue;
            }

            for (int i = 0; i < seleccio.size(); ++i){
                Codi aux = seleccio.get(i);
                if (elegibles.contains(aux)){
                    elegibles.remove(aux);
                    Codi aux2 = genRandCodi();
                    while(elegibles.contains(aux2)) aux2 = genRandCodi();
                    elegibles.add(aux2);
                }
            }

            int j = 0;
            while (elegibles.size() < MAX_POPULATION && j < seleccio.size()){
                elegibles.add(seleccio.get(j));
                ++j;
            }
            population = seleccio;
            while (population.size() < MAX_POPULATION){
                Codi aux = genRandCodi();
                if (!population.contains(aux)) population.add(aux);
            }
            h++;
        }
        if (elegibles.size() == 0) {
            Codi aux = genRandCodi();
            while (!elegible(aux,prevGuess)) aux = genRandCodi();
            elegibles.add(aux);
        }
        Codi res = elegibles.get(generator.nextInt(elegibles.size()));
        while (guessed(res)) res = elegibles.get(generator.nextInt(elegibles.size()));
        Tirada guess = new Tirada(res);
        prevGuess.add(guess);
        return res;

    }

    private int fitnessScore(Codi c, ArrayList<Tirada> prevGuess){
        int sumB = 0;
        int sumW = 0;

        for (int i = 0; i < prevGuess.size(); i ++) {
            Resposta r = new Resposta();
            r.calcularResposta(c, prevGuess.get(i).getCodi());
            sumB = sumB + r.getnBlacks() - prevGuess.get(i).getResposta().getnBlacks();
            sumW = sumW + r.getnWhites() - prevGuess.get(i).getResposta().getnWhites();
        }
        return 2*sumB + sumW + 2*positions*(prevGuess.size() - 1);
    }

    private Codi genRandCodi(){
        ArrayList<Integer> codi = new ArrayList<>();
        for(int j = 0; j < positions; ++j) codi.add(generator.nextInt(colors) + 1);
        return new Codi(codi);
    }

    private Codi crossover(Codi c1, Codi c2){
        ArrayList<Integer> c3 = new ArrayList<>();
        for (int i = 0; i < positions; ++i){
            if (generator.nextFloat() <= PROB_CROSSOVER) c3.add(c1.getPeces().get(i));
            else c3.add(c2.getPeces().get(i));
        }

        return new Codi(c3);
    }

    private Codi mutation(Codi c){
        int pos = generator.nextInt(positions);
        int color = generator.nextInt(colors) + 1;
        c.setPeca(pos, color);
        return c;
    }

    private Codi permutation(Codi c){
        int pos1 = generator.nextInt(positions);
        int pos2 = generator.nextInt(positions);
        int col1 = c.getPeces().get(pos1);
        int col2 = c.getPeces().get(pos2);
        c.setPeca(pos1, col2);
        c.setPeca(pos2, col1);
        return c;
    }

    private Codi inversion(Codi c){
        int low = generator.nextInt(positions);
        int high = generator.nextInt(positions);
        if (high < low){
            int aux = low;
            low = high;
            high = aux;
        }
        if (high == low) return c;
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < low; ++i) result.add(c.getPeces().get(i));
        for (int i = high-1; i >= low; --i) result.add(c.getPeces().get(i));
        for (int i = high; i < positions; ++i) result.add(c.getPeces().get(i));
        return new Codi(result);
    }


    public void updateLastGuess(int nBlack, int nWhite){
        Resposta r = new Resposta();
        r.setnBlacks(nBlack);
        r.setnWhites(nWhite);
        prevGuess.get(prevGuess.size() - 1).setResposta(r);

    }

    public boolean guessed(Codi c){
        for (int i = 0; i < prevGuess.size(); ++i){
            if(prevGuess.get(i).getCodi().same(c)) return true;
        }
        return false;
    }

    private boolean elegible(Codi c, ArrayList<Tirada> prevGuess){
        for (int i = 0; i  < prevGuess.size(); ++i){
            Resposta r = new Resposta();
            r.calcularResposta(c, prevGuess.get(i).getCodi());
            if (r.getnBlacks() != prevGuess.get(i).getResposta().getnBlacks()) return false;
            if (r.getnWhites() != prevGuess.get(i).getResposta().getnWhites()) return false;
        }
        return true;
    }

}