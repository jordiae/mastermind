package com.mastermind.domini;

import java.util.Date;

public class Record {

    private String name;
    private int score;
    private boolean codeMaker;
    private Date data;

    public Record(String name, int score, boolean codeMaker, Date data){
        this.name = name;
        this.score = score;
        this.codeMaker = codeMaker;
        this.data = data;
    }

    public Date getData() {
        return data;
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

    public void setData(Date data) {
        this.data = data;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String dataToString() {
        String s = this.name + " " + this.score + " " + " " + this.codeMaker + " " + this.data;
        return s;
    }
}
