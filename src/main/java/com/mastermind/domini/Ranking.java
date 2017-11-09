package com.mastermind.domini;

import java.util.ArrayList;

public class Ranking {

    private static final int NUM_RECORD_MAX = 15;

    private ArrayList<Record> recordList;
    private int puntuacioMinima;
    private int recordsGuardats;

    public Ranking() {
        recordList = new ArrayList<>(NUM_RECORD_MAX);
        recordsGuardats = 0;
    }

    public int getRecordsGuardats() {return recordsGuardats;}

    public ArrayList<Record> getRecordList() {return recordList;}

    public boolean afegirRecord(Record record){
        if (recordsGuardats == 0) {
            puntuacioMinima = record.getScore();
            recordList.add(record);
            ++recordsGuardats;
            return true;
        }
        if (puntuacioMinima > record.getScore()) {
            if (recordsGuardats == NUM_RECORD_MAX) return false;
            recordList.add(record);
            puntuacioMinima = record.getScore();
            ++recordsGuardats;
            return true;

        } else {
            if (recordsGuardats == NUM_RECORD_MAX) {
                recordList.remove(NUM_RECORD_MAX - 1);
                --recordsGuardats;
            }
            recordList.add(buscaPosicioRecord(record),record);
            puntuacioMinima = recordList.get(NUM_RECORD_MAX-1).getScore();
            ++recordsGuardats;
            return true;
        }
    }

    private int buscaPosicioRecord(Record record){
        for (int i = 0; i < recordsGuardats; ++i) {
            Record evalRecord = recordList.get(i);
            if (evalRecord.getScore() < record.getScore()) return i;
            if (evalRecord.getScore() == record.getScore()) {
                if (record.getName().compareTo(evalRecord.getName()) <= 0) return i;
            }
        }
        return recordsGuardats;
    }

}
