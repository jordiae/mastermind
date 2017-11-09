package com.mastermind.domini;/* By Jordi Armengol. 6/11/17*/

public class Tirada {
    private Codi codi;
    private Resposta resposta;
    public Tirada(Codi c, Resposta r) {
        this.codi = c;
        this.resposta = r;
    }

    public Codi getCodi() {
        return codi;
    }

    public void setCodi(Codi codi) {
        this.codi = codi;
    }

    public Resposta getResposta() {
        return resposta;
    }

    public void setResposta(Resposta resposta) {
        this.resposta = resposta;
    }
}
