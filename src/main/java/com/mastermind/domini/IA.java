package com.mastermind.domini;




import java.util.*;

public class IA {
    public static int MAX_POPULATION = 150;
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
                ArrayList<Codi> codis = new ArrayList<>();
                if (i == population.size() - 1){
                    if (generator.nextFloat() >= PROB_CROSSOVER) codis = oneSplitCrossover(population.get(i), population.get(i));
                    else codis = twoSplitCrossover(population.get(i), population.get(i));
                }
                else {
                    if (generator.nextFloat() >= PROB_CROSSOVER) codis = oneSplitCrossover(population.get(i), population.get(i + 1));
                    else codis = twoSplitCrossover(population.get(i), population.get(i + 1));
                }

                if (generator.nextFloat() <= PROB_MUTATION){
                    codis.set(0, mutation(codis.get(0)));
                    codis.set(1, mutation(codis.get(1)));
                }    //apliquem mutacions segons probabilitat
                if (generator.nextFloat() <= PROB_PERMUTATION){
                    codis.set(0, permutation(codis.get(0))) ;
                    codis.set(1, permutation(codis.get(1))) ;
                }
                if (generator.nextFloat() <= PROB_INVERSION){
                    codis.set(0,inversion(codis.get(0))) ;
                    codis.set(1, inversion(codis.get(1))) ;
                }

                if (!sons.contains(codis.get(0))) sons.add(codis.get(0));
                else {
                    while (sons.contains(codis.get(0))) codis.set(0, genRandCodi());
                    sons.add(codis.get(0));
                }

                if (!sons.contains(codis.get(1))) sons.add(codis.get(1));
                else {
                    while (sons.contains(codis.get(1))) codis.set(1, genRandCodi());
                    sons.add(codis.get(0));
                }
            }
            population = sons;
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
        return sumB + sumW;
    }

    private Codi genRandCodi(){
        ArrayList<Integer> codi = new ArrayList<>();
        for(int j = 0; j < positions; ++j) codi.add(generator.nextInt(colors) + 1);
        return new Codi(codi);
    }

    private ArrayList<Codi> oneSplitCrossover(Codi son1, Codi son2){
        int pos = generator.nextInt(positions);
        ArrayList<Integer> aux1 = new ArrayList<>(positions);
        ArrayList<Integer> aux2 = new ArrayList<>(positions);
        for (int i =0; i < pos; ++i){
            aux1.add(son1.getPeces().get(i));
            aux2.add(son2.getPeces().get(i));
        }
        for (int i = pos; i < positions; ++i){
            aux1.add(son1.getPeces().get(i));
            aux2.add(son2.getPeces().get(i));
        }

        ArrayList<Codi> res = new ArrayList<>();
        res.add(new Codi(aux1));
        res.add(new Codi(aux2));
        return res;
    }

    private ArrayList<Codi> twoSplitCrossover(Codi son1, Codi son2){
        ArrayList<Integer> aux1 = new ArrayList<>();
        ArrayList<Integer> aux2 = new ArrayList<>();
        int min = generator.nextInt(positions);
        int max = generator.nextInt(positions);
        if (min > max){
            int aux = min;
            max = min;
            min = max;
        }
        for (int i = 0; i < min; ++i){
            aux1.add(son1.getPeces().get(i));
            aux2.add(son2.getPeces().get(i));
        }
        for (int i = min; i < max; ++i){
            aux1.add(son1.getPeces().get(i));
            aux2.add(son2.getPeces().get(i));
        }
        for (int i = max; i < positions; ++i){
            aux1.add(son1.getPeces().get(i));
            aux2.add(son2.getPeces().get(i));
        }
        ArrayList<Codi> res = new ArrayList<>();
        res.add(new Codi(aux1));
        res.add(new Codi(aux2));
        return res;
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