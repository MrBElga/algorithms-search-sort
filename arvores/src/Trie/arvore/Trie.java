package Trie.arvore;

import Trie.estruturas.Pilha;
import Trie.estruturas.Tipo;

import static java.lang.Character.toLowerCase;

public class Trie {
    public No raiz;

    public Trie() {
        raiz = new No();
        raiz.setLetras(' ', 0);
    }

    private int indice(char letra) {
        return toLowerCase(letra) - 'a';
    }

    public void inserir(String palavra) {
        char letra;
        int ind, i = 0;
        No atual = raiz;
        while (i < palavra.length()) {
            letra = palavra.charAt(i);
            ind = indice(letra);
            i++;
            if (atual.getVlig(ind) == null) {
                No novo = new No();
                novo.setLetras(letra, ind);
                atual.setVlig(ind, novo);
                atual = atual.getVlig(ind);
            } else {
                atual = atual.getVlig(ind);
            }
        }
        atual.setFlag(true);
    }


    public void imprimirTodas() {
        imprimirTodasAux(raiz, "");
    }

    private void imprimirTodasAux(No no, String palavraAtual) {
        if (no.isFlag()) {
            System.out.println(palavraAtual);
        }

        for (int i = 0; i < 26; i++) {
            if (no.getVlig(i) != null) {
                char letra = (char) ('a' + i);
                imprimirTodasAux(no.getVlig(i), palavraAtual + letra);
            }
        }
    }


}



