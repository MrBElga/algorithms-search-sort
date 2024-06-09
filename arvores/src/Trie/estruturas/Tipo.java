package Trie.estruturas;

import Trie.arvore.No;

public class Tipo {
    private No valor;
    private Tipo prox;
    private int nivel;
    private char palavra;

    public Tipo(No valor, Tipo prox, int nivel, char palavra) {
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

    public char getPalavra() {
        return palavra;
    }

    public void setPalavra(char palavra) {
        this.palavra = palavra;
    }
}
