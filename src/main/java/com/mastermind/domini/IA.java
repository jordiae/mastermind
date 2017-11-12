package com.mastermind.domini;


import java.util.*;

public class IA {
    public static int MAX_POPULATION = 150;
    public static int MAX_GENERATION = 100;
    public static int MAX_ELEGIBLES = 60;
    public static Double PROB_CROSSOVER = 0.5;
    public static Double PROB_MUTATION = 0.3;
    public static Double PROB_PERMUTATION = 0.3;
    public static Double PROB_INVERSION = 0.2;

    private int colors; //nombre maxim de colors
    private int positions; //nombre de posicions dels codis
    private Random generator;
    private ArrayList<Tirada> prevGuess;


    public IA(int colors, int positions){
        this.colors = colors;
        this.positions = positions;
        generator = new Random();
        prevGuess = new ArrayList<>();
        ArrayList<Integer> aux = new ArrayList<>();
        aux.add( 1); aux.add( 1); aux.add( 2); aux.add( 2);
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
                return o1.getFitnessScore() - o2.getFitnessScore();
            }
        };



        int h = 1;
        while (h <= MAX_GENERATION && elegibles.size() <= MAX_ELEGIBLES){
            ArrayList<Codi> sons = new ArrayList<>();
            Collections.sort(population, comp);     //ordenem poblacio segons qualitat
            for(int i = 0; i < MAX_POPULATION; ++i){  //generem nova poblacio a partir de l'anterior
                Codi c;
                if (i == population.size() - 1)  c = population.get(i);
                else  c = crossover(population.get(i), population.get(i + 1));

                if (generator.nextFloat() <= PROB_MUTATION) c = mutation(c);   //apliquem mutacions segons probabilitat
                if (generator.nextFloat() <= PROB_PERMUTATION) c = permutation(c);

                if (!sons.contains(c)){
                    int fitness = fitness_score(c, prevGuess);
                    c.setFitnessScore(fitness);
                    sons.add(c);
                    if (fitness == 0 && !elegibles.contains(c)) elegibles.add(c);
                }
            }
            h++;
            population = sons;
        }

        Codi res = elegibles.get(generator.nextInt(elegibles.size()));
        while (prevGuess.contains(res)) res = elegibles.get(generator.nextInt(elegibles.size()));
        Tirada guess = new Tirada(res);
        prevGuess.add(guess);
        return res;

    }

    private int fitness_score(Codi c, ArrayList<Tirada> prevGuess){
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
        int pos = generator.nextInt(positions) + 1;
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


    public void updateLastGuess(int nBlack, int nWhite){
        Resposta r = new Resposta();
        r.setnBlacks(nBlack);
        r.setnWhites(nWhite);
        prevGuess.get(prevGuess.size() - 1).setResposta(r);
    }

}