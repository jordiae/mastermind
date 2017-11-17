package com.mastermind.domini;

import java.util.ArrayList;

public class Ranking {

    private ArrayList<Record> recordList;
    private int puntuacioMinima;
    private int recordsGuardats;

    public Ranking() {
        recordList = new ArrayList<>();
        recordsGuardats = 0;
    }

    public String dataToString() {
        String s = recordsToString() + " " + recordsGuardats;
        return s;
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
            if (recordsGuardats == Constants.NUM_RECORD_MAX) return false;
            recordList.add(record);
            puntuacioMinima = record.getScore();
            ++recordsGuardats;
            return true;

        } else {
            if (recordsGuardats == Constants.NUM_RECORD_MAX) {
                recordList.remove(recordsGuardats - 1);
                --recordsGuardats;
            }
            recordList.add(buscaPosicioRecord(record),record);
            puntuacioMinima = recordList.get(recordsGuardats-1).getScore();
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

    private ArrayList<String> recordsToString() {
        ArrayList<String> records = new ArrayList<>();
        for (int i = 0; i < recordsGuardats; ++i) records.add(recordList.get(i).dataToString());
        return records;
    }

}
