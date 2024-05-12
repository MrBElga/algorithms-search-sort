package BTreep;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Bt {
    public static void main(String[] args) throws InterruptedException {
        BTree arvore = new BTree();
        for (int i = 0; i < 10; i++) {
            arvore.inserir(i, i);
        }
        System.out.println("inseriu");
        System.out.println("ARVORE:");
        arvore.printArvore();
        System.out.println("INORD:");
        arvore.inOrd();

        for (int i = 0; i < 5; i++) {
            Random rand = new Random();
            int valorAleatorio = rand.nextInt(10);
            arvore.excluir(valorAleatorio);
            System.out.println("Excluiu o valor: " + valorAleatorio);
            System.out.println("ARVORE:");
            arvore.printArvore();
            System.out.println("INORD:");
            arvore.inOrd();
            sleep(500);
        }

    }
}