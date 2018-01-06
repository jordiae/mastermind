package com.mastermind.domini;

import java.sql.Time;

public class Record {

    private String name;
    private int score;
    private boolean codeMaker;
    private Time time;

    public Record(String name, int score, boolean codeMaker, Time time){
        this.name = name;
        this.score = score;
        this.codeMaker = codeMaker;
        this.time = time;
    }

    public Time getTime() {
        return time;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public boolean isCodeMaker() {
        return codeMaker;
    }

    public void setCodeMaker(boolean codeMaker) {
        this.codeMaker = codeMaker;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String dataToString() {
        // String s = this.name + " " + this.score + " "  + this.codeMaker + " " + this.time;
        String s = this.name + " " + this.score;
        return s;
    }
}
