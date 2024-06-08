package estruturas;

import Patricia.Patricia.No;

public class Tipo {
    No valor;
    Tipo prox;
    int nivel;
    String palavra;

    public Tipo(No valor, Tipo prox, int nivel, String palavra) {
        this.valor = valor;
        this.prox = prox;
        this.nivel = nivel;
        this.palavra = palavra;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public No getValor() {
        return valor;
    }

    public Tipo getProx() {
        return prox;
    }

    public void setProx(Tipo prox) {
        this.prox = prox;
    }
}
