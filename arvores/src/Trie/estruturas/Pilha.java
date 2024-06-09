package Trie.estruturas;

import Trie.arvore.No;

public class Pilha {
    private Tipo topo;

    public Pilha() {
        this.topo = null;
    }

    public void push(No valor, int nivel, char palavra) {
        Tipo novo = new Tipo(valor, topo, nivel, palavra);
        topo = novo;
    }

    public No remove() {
        No aux;
        if (this.topo == null) {
            aux = null;
        } else {
            aux = this.topo.getValor();
            this.topo = this.topo.getProx();

        }
        return aux;
    }


    public boolean isEmpty() {
        return topo == null;
    }
}
