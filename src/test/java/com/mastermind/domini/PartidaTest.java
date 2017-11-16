package com.mastermind.domini;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.time.Instant;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PartidaTest {
    @org.junit.jupiter.api.Test
    void Partida() {
        ArrayList<Integer> s = new ArrayList<>();
        s.add(4); s.add(2); s.add(3); s.add(4);
        Codi solucio = new Codi(s);
        ArrayList<Integer> t = new ArrayList<>();
        t.add(1); t.add(1); t.add(5); t.add(3);
        Codi codiT = new Codi(t);
        Resposta resposta = new Resposta();
        resposta.setnBlacks(0);
        resposta.setnWhites(1);
        Tirada tirada = new Tirada(codiT,resposta);
        ArrayList<Tirada> tirades = new ArrayList<>();
        tirades.add(tirada);
        int maxTorn = 8;
        Taulell taulell = new Taulell(1,solucio, tirades, maxTorn);
        Partida partida = new Partida(195, 2, false, false,Time.valueOf("2:30:23"),taulell);
        Assert.assertEquals(195, partida.getID());
        Assert.assertEquals(2, partida.getDifficulty());
        Assert.assertEquals(false, partida.isCodeMaker());
        Assert.assertEquals(false, partida.isHelp());
        Assert.assertEquals(Time.valueOf("2:30:23"), partida.getTime());
        Assert.assertEquals(taulell, partida.getTaulell());

    }
    @org.junit.jupiter.api.Test
    void setHelp() {
        ArrayList<Integer> s = new ArrayList<>();
        s.add(1); s.add(2); s.add(3); s.add(4);
        Codi solucio = new Codi(s);
        ArrayList<Tirada> tirades = new ArrayList<>();
        int maxTorn = 5;
        Taulell taulell = new Taulell(0,solucio, tirades, maxTorn);
        Partida partida = new Partida(457, 3, false, false,Time.valueOf("10:30:23"),taulell);
        partida.setHelp(true);
        Assert.assertEquals(true,partida.isHelp());
        partida.setHelp(false);
        Assert.assertEquals(false,partida.isHelp());
    }

    @org.junit.jupiter.api.Test
    void setID() {
        ArrayList<Integer> s = new ArrayList<>();
        s.add(4); s.add(7); s.add(3); s.add(4); s.add(3);
        Codi solucio = new Codi(s);
        ArrayList<Tirada> tirades = new ArrayList<>();
        int maxTorn = 3;
        Taulell taulell = new Taulell(0,solucio, tirades, maxTorn);
        Partida partida = new Partida(382, 1, true, false,Time.valueOf("8:30:23"),taulell);
        partida.setID(666);
        Assert.assertEquals(666,partida.getID());
    }

    @org.junit.jupiter.api.Test
    void setTaulell() {
        ArrayList<Integer> s = new ArrayList<>();
        s.add(4); s.add(2); s.add(3); s.add(4);
        Codi solucio = new Codi(s);
        ArrayList<Integer> t = new ArrayList<>();
        t.add(1); t.add(1); t.add(5); t.add(3);
        Codi codiT = new Codi(t);
        Resposta resposta = new Resposta();
        resposta.setnBlacks(0);
        resposta.setnWhites(1);
        Tirada tirada = new Tirada(codiT,resposta);
        ArrayList<Tirada> tirades = new ArrayList<>();
        tirades.add(tirada);
        int maxTorn = 8;
        Taulell taulell = new Taulell(1,solucio, tirades, maxTorn);
        Partida partida = new Partida(195, 2, false, false,Time.valueOf("2:30:23"),taulell);
       
        Assert.assertEquals(taulell, partida.getTaulell());


        ArrayList<Integer> s2 = new ArrayList<>();
        s2.add(2); s2.add(8); s2.add(5); s2.add(4);
        Codi solucio2 = new Codi(s);
        ArrayList<Integer> t2 = new ArrayList<>();
        t2.add(8); t2.add(1); t2.add(8); t2.add(3);
        Codi codiT2 = new Codi(t2);
        ArrayList<Integer> t3 = new ArrayList<>();
        t3.add(5); t3.add(1); t3.add(2); t3.add(4);
        Codi codiT3 = new Codi(t3);
        Resposta resposta2 = new Resposta();
        Resposta resposta3 = new Resposta();
        resposta2.calcularResposta(solucio2,codiT2);
        resposta3.calcularResposta(solucio2,codiT3);

        Tirada tirada3 = new Tirada(codiT2,resposta2);
        Tirada tirada4 = new Tirada(codiT2,resposta3);
        ArrayList<Tirada> tirades2 = new ArrayList<>();
        tirades.add(tirada3); tirades.add(tirada4);
        int maxTorn2 = 10;
        Taulell taulell2 = new Taulell(1,solucio2, tirades2, maxTorn2);
        partida.setTaulell(taulell2);
        Assert.assertEquals(taulell2, partida.getTaulell());
    }

    @org.junit.jupiter.api.Test
    void setTime() {
        ArrayList<Integer> s = new ArrayList<>();
        s.add(4); s.add(9); s.add(3); s.add(4); s.add(3);
        Codi solucio = new Codi(s);
        ArrayList<Tirada> tirades = new ArrayList<>();
        int maxTorn = 10;
        Taulell taulell = new Taulell(0,solucio, tirades, maxTorn);
        Partida partida = new Partida(342, 1, true, false,Time.valueOf("6:30:25"),taulell);
        partida.setTime(Time.valueOf("3:40:15"));
        Assert.assertEquals(Time.valueOf("3:40:15"),partida.getTime());
    }

    @org.junit.jupiter.api.Test
    void dataToString() {
        Taulell taulell = new Taulell(1, new Codi(new ArrayList<Integer>()), new ArrayList<Tirada>(), 1);
        Partida partida = new Partida(195, 2, false, false,Time.valueOf("2:30:23"),taulell);
        Assert.assertEquals("195 2 false false 5423000 1 [] [] 1",partida.dataToString());
    }

    @org.junit.jupiter.api.Test
    void getDifficulty() {
        ArrayList<Integer> s = new ArrayList<>();
        s.add(4); s.add(9); s.add(3); s.add(2);
        Codi solucio = new Codi(s);
        ArrayList<Tirada> tirades = new ArrayList<>();
        int maxTorn = 5;
        Taulell taulell = new Taulell(0,solucio, tirades, maxTorn);
        Partida partida = new Partida(342, 2, false, false,Time.valueOf("3:30:25"),taulell);
        Assert.assertEquals(2,partida.getDifficulty());
    }

    @org.junit.jupiter.api.Test
    void getID() {
        ArrayList<Integer> s = new ArrayList<>();
        s.add(2); s.add(9); s.add(3); s.add(5);
        Codi solucio = new Codi(s);
        ArrayList<Tirada> tirades = new ArrayList<>();
        int maxTorn = 12;
        Taulell taulell = new Taulell(0,solucio, tirades, maxTorn);
        Partida partida = new Partida(787, 2, false, false,Time.valueOf("2:21:25"),taulell);
        Assert.assertEquals(787,partida.getID());
    }

    @org.junit.jupiter.api.Test
    void getTaulell() {
        ArrayList<Integer> s = new ArrayList<>();
        s.add(8); s.add(8); s.add(3); s.add(4);
        Codi solucio = new Codi(s);
        ArrayList<Integer> t = new ArrayList<>();
        t.add(8); t.add(1); t.add(8); t.add(3);
        Codi codiT = new Codi(t);
        Resposta resposta = new Resposta();
        resposta.calcularResposta(solucio,codiT);

        ArrayList<Integer> t2 = new ArrayList<>();
        t2.add(8); t2.add(1); t2.add(8); t2.add(3);
        Codi codiT2 = new Codi(t2);
        Resposta resposta2 = new Resposta();
        resposta2.calcularResposta(solucio,codiT2);

        Tirada tirada = new Tirada(codiT,resposta);
        Tirada tirada2 = new Tirada(codiT2,resposta2);
        ArrayList<Tirada> tirades = new ArrayList<>();
        tirades.add(tirada); tirades.add(tirada2);
        int maxTorn = 7;
        Taulell taulell = new Taulell(2,solucio, tirades, maxTorn);
        Partida partida = new Partida(334, 2, true, false,Time.valueOf("1:20:23"),taulell);
        Assert.assertEquals(taulell, partida.getTaulell());
    }

    @org.junit.jupiter.api.Test
    void getTime() {
        ArrayList<Integer> s = new ArrayList<>();
        s.add(2); s.add(8); s.add(3); s.add(5);
        Codi solucio = new Codi(s);
        ArrayList<Tirada> tirades = new ArrayList<>();
        int maxTorn = 10;
        Taulell taulell = new Taulell(0,solucio, tirades, maxTorn);
        Partida partida = new Partida(787, 2, false, false,Time.valueOf("21:21:25"),taulell);
        Assert.assertEquals(Time.valueOf("21:21:25"),partida.getTime());
    }

    @org.junit.jupiter.api.Test
    void isCodeMaker() {
        ArrayList<Integer> s = new ArrayList<>();
        s.add(4); s.add(8); s.add(3); s.add(9); s.add(3); s.add(1);
        Codi solucio = new Codi(s);
        ArrayList<Tirada> tirades = new ArrayList<>();
        int maxTorn = 15;
        Taulell taulell = new Taulell(0,solucio, tirades, maxTorn);
        Partida partida = new Partida(999, 2, true, false,Time.valueOf("4:56:10"),taulell);
        Assert.assertEquals(true,partida.isCodeMaker());
    }

    @org.junit.jupiter.api.Test
    void isHelp() {
        ArrayList<Integer> s = new ArrayList<>();
        s.add(4); s.add(9); s.add(3); s.add(9); s.add(3);
        Codi solucio = new Codi(s);
        ArrayList<Tirada> tirades = new ArrayList<>();
        int maxTorn = 6;
        Taulell taulell = new Taulell(0,solucio, tirades, maxTorn);
        Partida partida = new Partida(999, 2, false, false,Time.valueOf("6:30:25"),taulell);
        Assert.assertEquals(false,partida.isHelp());
    }

    @org.junit.jupiter.api.Test
    void setCodeMaker() {
        ArrayList<Integer> s = new ArrayList<>();
        s.add(4); s.add(5); s.add(3); s.add(9);
        Codi solucio = new Codi(s);
        ArrayList<Tirada> tirades = new ArrayList<>();
        int maxTorn = 12;
        Taulell taulell = new Taulell(0,solucio, tirades, maxTorn);
        Partida partida = new Partida(112, 3, false, false,Time.valueOf("0:13:29"),taulell);
        Assert.assertEquals(false,partida.isCodeMaker());
        partida.setCodeMaker(true);
        Assert.assertEquals(true,partida.isCodeMaker());
    }

    @org.junit.jupiter.api.Test
    void setDifficulty() {
        ArrayList<Integer> s = new ArrayList<>();
        s.add(2); s.add(5); s.add(1); s.add(9);
        Codi solucio = new Codi(s);
        ArrayList<Tirada> tirades = new ArrayList<>();
        int maxTorn = 8;
        Taulell taulell = new Taulell(0,solucio, tirades, maxTorn);
        Partida partida = new Partida(112, 3, false, false,Time.valueOf("5:13:29"),taulell);
        Assert.assertEquals(3,partida.getDifficulty());
        partida.setDifficulty(1);
        Assert.assertEquals(1,partida.getDifficulty());
    }

}