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

    public ArrayList<String> carregaInfoRecords2() {
        ArrayList<String> a =  ranking.recordsToString();
        return a;
    }

    public boolean addRecord(String user, int score){
        Record r = new Record(user, score, false, new Time(1));
        boolean guardat = false;
        if (ranking.getRecordList().size() < 10){
            ranking.getRecordList().add(r);
            guardat = true;
        }
        else{
            for (int i = 0; i < ranking.getRecordList().size(); ++i){
                if (ranking.getRecordList().get(i).getScore() < r.getScore()) {
                    ranking.getRecordList().set(i, r);
                    guardat = true;
                    break;
                }
            }
        }

        if (guardat){
            ranking.ordenaRecords();
            ArrayList<String> re = carregaInfoRecords2();
            DataController.saveRecords(re);

        }
        return guardat;
    }
}
