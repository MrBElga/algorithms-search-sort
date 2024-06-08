package BTreep;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Bt {
    public static void main(String[] args) throws InterruptedException {
        BTree arvore = new BTree();
        for (int i = 0; i < 10; i++) {
            arvore.inserir(i, i);
            System.out.println("Inseriu o valor: " + i);
            System.out.println("Arvore:");
            arvore.printBTree(arvore);
            sleep(500);
        }
        System.out.println("inseriu");
        System.out.println("INORD:");
        arvore.inOrd();
        System.out.println("Arvore:");
        arvore.printBTree(arvore);
        for (int i = 0; i < 10; i++) {
            arvore.excluir(i);
            System.out.println("Excluiu o valor: " + i);
            System.out.println("INORD:");
            arvore.inOrd();
            System.out.println("Arvore:");
            arvore.printBTree(arvore);
            sleep(500);
        }

    }
}
