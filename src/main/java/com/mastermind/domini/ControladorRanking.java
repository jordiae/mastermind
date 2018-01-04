package com.mastermind.domini;

import com.mastermind.persistencia.DataController;

import java.util.ArrayList;

public class ControladorRanking {

    public Record[] carregaInfoRecords() {
        return DataController.getRecords();
    }
}
