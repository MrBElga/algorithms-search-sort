package estruturas;

import Patricia.No;

public class Fila {
    Tipo inicio;

    public Fila() {
        this.inicio = null;
    }


    public void insere(No valor, int nivel, String palavra) {
        Tipo aux;
        aux = inicio;
        if (inicio == null) {
            inicio = new Tipo(valor, null, nivel, palavra);
        } else {
            while (aux.getProx() != null) {
                aux = aux.getProx();
            }
            aux.setProx(new Tipo(valor, null, nivel, palavra));
        }
    }

    public No remove() {
        No aux;
        if (this.inicio == null) {
            aux = null;
        } else {
            aux = this.inicio.getValor();
            this.inicio = this.inicio.getProx();

        }
        return aux;
    }

    public int retornaNivel() {
        if (this.inicio != null) {
            return this.inicio.getNivel();
        } else {
            return -1;
        }
    }


    public boolean isEmpty() {
        if (this.inicio == null) {
            return true;
        }
        return false;
    }


}
