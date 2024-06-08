package Patricia.Patricia;

import estruturas.Fila;

import java.util.Objects;

import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

public class
Patricia {
    public No raiz;

    public Patricia() {
        this.raiz = null;
    }

    private int indiceDaLetra(char letra) {
        return toLowerCase(letra) - 'a';
    }

    private No buscarLetraDif(String palavra, No aux, No prox) {
        for (int i = 0; i < 26; i++) {
            for (int j = 0; palavra.length() - 1 > j; j++) {
                if (palavra.charAt(j) == prox.getLetras(i)) {
                    aux = prox.getvLig(i);
                }
            }
        }
        return aux;
    }


    public void insere(String palavra) {
        palavra = palavra.toUpperCase(); // Para evitar erros com diferenciação de maiúsculas e minúsculas
        No atual, prox, nova, aux; // 'atual' e 'prox' para andar de 2 em 2
        char letraAt = palavra.charAt(0), letraProx; // Letra do atual e prox
        int indice = indiceDaLetra(letraAt), posLetra = 1, pos = 0, posDif;
        // 'indice' é o índice do vetor de acordo com a letra; 'posLetra' é a posição da letra de 1 em diante

        if (raiz == null) { // Caso a árvore esteja vazia
            raiz = new No("");
            raiz.setvLig(indice, new No(palavra));
            raiz.setLetras(indice, letraAt);
            raiz.setPosLetra(posLetra);
        } else { // Se a árvore não estiver vazia
            atual = raiz;
            prox = atual.getvLig(indiceDaLetra(palavra.charAt(atual.getPosLetra() - 1)));
            if (prox == null) { // Insere diretamente com uma caixa nova
                nova = new No(palavra);
                atual.setLetras(indiceDaLetra(palavra.charAt(atual.getPosLetra() - 1)), palavra.charAt(atual.getPosLetra() - 1));
                atual.setvLig(indiceDaLetra(palavra.charAt(atual.getPosLetra() - 1)), nova);
            } else { // Já tem uma letra naquela posição, então procurar seu prefixo
                int diferente = 1;
                aux = acharPalavra(prox);
                aux = buscarLetraDif(palavra, aux, prox);

                if (aux.getPalavra() != "")
                    while (aux.getPalavra().charAt(diferente) == palavra.charAt(diferente) && diferente < palavra.length() - 1) {
                        diferente++;
                    }

                if (prox.getPosLetra() - 1 == diferente) { // caso ache uma pos que já esteja ocupada
                    nova = new No(palavra);
                    prox.setvLig(indiceDaLetra(palavra.charAt(diferente)), nova);
                    prox.setLetras(indiceDaLetra(palavra.charAt(diferente)), palavra.charAt(diferente));
                } else {
                    if (diferente == palavra.length() - 1 && aux.getPalavra().length() - 1 == diferente) {
                        // Se forem iguais até o final da palavra, com exceção do último caractere
                        aux = new No(palavra);
                        nova = new No("");
                        nova.setPosLetra(diferente + 1);
                        nova.setLetras(indiceDaLetra(palavra.charAt(diferente)), palavra.charAt(diferente));
                        nova.setLetras(indiceDaLetra(prox.getPalavra().charAt(diferente)), prox.getPalavra().charAt(diferente));
                        nova.setvLig(indiceDaLetra(palavra.charAt(diferente)), aux);
                        nova.setvLig(indiceDaLetra(prox.getPalavra().charAt(diferente)), prox);
                        atual.setvLig(indiceDaLetra(palavra.charAt(atual.getPosLetra() - 1)), nova);
                        System.out.println(palavra.length());
                    } else if (diferente < aux.getPalavra().length() - 1 && Objects.equals(prox.getPalavra(), "") && palavra.length() != aux.getPalavra().length()) {
                        // Caso o prefixo esteja contido em uma palavra existente na árvore
                        nova = new No(palavra);
                        nova.setPosLetra(diferente + 1);
                        nova.setLetras(indiceDaLetra(palavra.charAt(diferente)), palavra.charAt(diferente));
                        nova.setvLig(indiceDaLetra(aux.getPalavra().charAt(diferente)), atual.getvLig(indiceDaLetra(palavra.charAt(atual.getPosLetra() - 1))));
                        atual.setvLig(indiceDaLetra(palavra.charAt(atual.getPosLetra() - 1)), nova);
                    } else if (!Objects.equals(prox.getPalavra(), "")) {
                        // Caso geral de inserção dividindo um nó existente
                        nova = new No("");
                        nova.setPosLetra(diferente + 1);
                        nova.setLetras(indiceDaLetra(prox.getPalavra().charAt(diferente)), prox.getPalavra().charAt(diferente));

                        No nova2 = new No(palavra);
                        nova.setLetras(indiceDaLetra(palavra.charAt(diferente)), palavra.charAt(diferente));
                        nova.setvLig(indiceDaLetra(prox.getPalavra().charAt(diferente)), prox);
                        nova.setvLig(indiceDaLetra(nova2.getPalavra().charAt(diferente)), nova2);
                        atual.setvLig(indiceDaLetra(palavra.charAt(atual.getPosLetra() - 1)), nova);
                    } else {
                        nova = new No("");
                        nova.setPosLetra(diferente + 1);
                        nova.setLetras(indiceDaLetra(aux.getPalavra().charAt(diferente)), aux.getPalavra().charAt(diferente));

                        No nova2 = new No(palavra);
                        nova.setLetras(indiceDaLetra(palavra.charAt(diferente)), palavra.charAt(diferente));
                        nova.setvLig(indiceDaLetra(aux.getPalavra().charAt(diferente)), aux);
                        nova.setvLig(indiceDaLetra(nova2.getPalavra().charAt(diferente)), nova2);
                        prox.setvLig(indiceDaLetra(palavra.charAt(prox.getPosLetra() - 1)), nova);
                    }
                }
            }
        }
    }

    private No acharPalavra(No no) {
        No atual = no;
        No prox = null;
        prox = atual.getProximaPalavra();
        while (prox == null && atual.getPosLetra() > 0) {
            atual = atual.getvLig(atual.filho());
            prox = atual.getProximaPalavra();
        }
        return prox;
    }

    public void imprimirNivelANivel() {
        No aux = this.raiz;
        Fila f = new Fila();
        int nivel = 0;
        f.insere(aux, nivel, "");

        while (!f.isEmpty()) {
            nivel = f.retornaNivel();
            nivel++;
            aux = f.remove();
            for (int i = 0; i < 26; i++) {
                if (aux.getvLig(i) != null) {
                    if (!Objects.equals(aux.getvLig(i).getPalavra(), ""))//if para evitar imprimir os vazios tambem
                        System.out.println("Palavra : " + aux.getvLig(i).getPalavra() + " nivel : " + nivel);
                    f.insere(aux.getvLig(i), nivel, "");
                }

            }
        }
    }

    public void imprimirTodas() {
        if (raiz == null) {
            System.out.println("Não existem palavras na árvore.");
        } else {
            Fila f = new Fila();
            f.insere(raiz, 0, "");

            while (!f.isEmpty()) {
                No aux = f.remove();
                if (!Objects.equals(aux.getPalavra(), "")) {
                    System.out.println(aux.getPalavra());
                }

                for (int i = 0; i < 26; i++) {
                    if (aux.getvLig(i) != null) {
                        String palavraFilho = aux.getPalavra() + aux.getvLig(i).getPalavra();
                        f.insere(aux.getvLig(i), 0, palavraFilho);
                    }
                }
            }
        }
    }

    private No buscarLetra(No atual, char letra) {
        boolean achou = false;
        for (int i = 0; i < 26 && !achou; i++) {
            if (atual.getLetras(i) == letra) {
                atual = atual.getvLig(i);
                achou = true;
            }
        }
        return achou ? atual : null;
    }

    public void buscar(String palavra) {
        if (raiz == null) {
            System.out.println("A árvore está vazia.");
        } else {
            palavra = palavra.toUpperCase();
            No atual = raiz;
            int i = 0;
            char letra = 0;
            while (atual != null && i < palavra.length() && !Objects.equals(atual.getPalavra(), palavra)) {
                if (atual.getPosLetra() - 1 >= 0)
                    letra = palavra.charAt(atual.posLetra - 1);

                atual = buscarLetra(atual, letra);
                if (atual != null) {
                    i++;
                }
            }
            if (atual != null) {
                if (palavra.equals(atual.getPalavra())) {
                    System.out.println("Palavra encontrada: " + atual.getPalavra());
                } else {
                    System.out.println("Palavra não encontrada.");
                }
            } else {
                System.out.println("Palavra não encontrada.");
            }
        }
    }
}
