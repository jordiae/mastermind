package com.mastermind.domini;

import java.sql.Time;
import java.util.Random;

public class Partida {

    private int ID;
    private int difficulty;
    private boolean codeMaker;
    private boolean help;
    private Time time;
    private Taulell taulell;

    public Partida(int ID, int difficulty, boolean codeMaker, boolean help, Time time, Taulell taulell){
        this.ID = ID;
        this.difficulty = difficulty;
        this.codeMaker = codeMaker;
        this.help = help;
        this.time = time;
        this.taulell = taulell;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getID() {
        return ID;
    }

    public Taulell getTaulell() {
        return taulell;
    }

    public Time getTime() {
        return time;
    }

    public boolean isCodeMaker() {
        return codeMaker;
    }

    public boolean isHelp() {
        return help;
    }

    public void setCodeMaker(boolean codeMaker) {
        this.codeMaker = codeMaker;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setHelp(boolean help) {
        this.help = help;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTaulell(Taulell taulell) {
        this.taulell = taulell;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String dataToString() {
        return this.ID + " " + this.difficulty + " " + String.valueOf(this.codeMaker) + " "
               + String.valueOf(this.help) + " " + this.time.getTime() + " " + this.taulell.dataToString();
    }

    private boolean fesTirada(Codi codi){
        return this.taulell.ferTirada(codi);
    } // int -> boolean

    public int getPuntuacio(){
        Random r = new Random();
        return r.nextInt(1000); // time -> long -> int
    }

    public boolean getHelp() {
        return help;
    }
}
