package com.mastermind.domini;

import com.mastermind.persistencia.DataController;

import java.awt.image.AreaAveragingScaleFilter;
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
            ++i;
            r = rec[i];
        }

    }

    public ArrayList<String> carregaInfoRecords() {
        ArrayList<String> a =  ranking.recordsToString();
        for (int i = a.size(); i < 10; ++i){
            a.add("no hi ha record");
        }
        return a;
    }
}
