package com.mastermind.domini;

import com.mastermind.persistencia.DataController;

import java.awt.image.AreaAveragingScaleFilter;
import java.sql.Time;
import java.util.ArrayList;

public class ControladorRanking {

    private Ranking ranking;

    public ControladorRanking(){
        ranking = new Ranking();
        Record[] rec = DataController.getRecords();
        int i = 0;
        Record r = rec[0];
        while (r != null && i < 10){
            ranking.afegirRecord(r);
            r = rec[i];
            ++i;
        }

    }

    public Ranking getRanking(){
        return ranking;
    }

    public ArrayList<String> carregaInfoRecords() {
        ArrayList<String> a =  ranking.recordsToString();
        for (int i = a.size(); i < 10; ++i){
            a.add("no hi ha record");
        }
        return a;
    }

    public ArrayList<String> carregaInfoRecords2() {
        ArrayList<String> a =  ranking.recordsToString();
        return a;
    }

    public void addRecord(String user, int score){
        Record r = new Record(user, score, false, new Time(1));
        ranking.afegirRecord(r);
    }
}
