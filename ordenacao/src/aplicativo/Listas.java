package aplicativo;

import java.util.Random;
import lista.Lista;

public class Listas {

    public void executarListas(){
        Lista lista = new Lista();
        Lista copia;

        Random random = new Random();
        int randomNumber,i;

        for (i = 0; i < 32; i++) {
            randomNumber = random.nextInt(31);
            lista.inserirFim(randomNumber);
        }

        System.out.print("VETOR DESORDENADO: \t\t\t\t\t\t");
        lista.exibir();
        System.out.println("Len: "+ lista.contaTam());
        System.out.println("/----------------------------------------------------------------------/");


        copia = lista.duplicaLista();
        copia.insercaoDireta();
        System.out.print("Inserção direta: \t\t\t\t");
        copia.exibir();
        copia.destruirLista();

        copia = lista.duplicaLista();
        copia.selecaoDireta();
        System.out.print("Seleção direta:    \t\t\t\t");
        copia.exibir();
        copia.destruirLista();

        copia = lista.duplicaLista();
        copia.insercaoBinaria();
        System.out.print("Inserção Binaria: \t\t\t\t");
        copia.exibir();
        copia.destruirLista();

        copia = lista.duplicaLista();
        copia.bolha();
        System.out.print("Bolha:             \t\t\t\t");
        copia.exibir();
        copia.destruirLista();

        copia = lista.duplicaLista();
        copia.shake();
        System.out.print("Shake:            \t\t\t\t");
        copia.exibir();
        copia.destruirLista();

        copia = lista.duplicaLista();
        copia.shell();
        System.out.print("Shell:            \t\t\t\t");
        copia.exibir();
        copia.destruirLista();

        copia = lista.duplicaLista();
        copia.heap();
        System.out.print("Heap sort:       \t\t\t\t");
        copia.exibir();
        copia.destruirLista();

        copia = lista.duplicaLista();
        copia.quickSort_SP();
        System.out.print("Quick sem pivo: \t\t\t\t");
        copia.exibir();
        copia.destruirLista();

        copia = lista.duplicaLista();
        copia.quickSort_CP();
        System.out.print("Quick com pivo: \t\t\t\t");
        copia.exibir();
        copia.destruirLista();

        copia = lista.duplicaLista();
        copia.merge();
        System.out.print("Merge:        \t\t\t\t\t");
        copia.exibir();
        copia.destruirLista();

        copia = lista.duplicaLista();
        copia.merge2();
        System.out.print("Merge segunda implementação: \t");
        copia.exibir();
        copia.destruirLista();

        copia = lista.duplicaLista();
        copia.counting();
        System.out.print("Counting:    \t\t\t\t\t");
        copia.exibir();
        copia.destruirLista();

        copia = lista.duplicaLista();
        copia.bucket();
        System.out.print("Bucket: \t\t\t\t\t\t");
        copia.exibir();
        copia.destruirLista();

        copia = lista.duplicaLista();
        copia.radix();
        System.out.print("Radix: \t\t\t\t\t\t\t");
        copia.exibir();
        copia.destruirLista();

        copia = lista.duplicaLista();
        copia.comb();
        System.out.print("Comb: \t\t\t\t\t\t\t");
        copia.exibir();
        copia.destruirLista();

        copia = lista.duplicaLista();
        copia.gnome();
        System.out.print("Gnome: \t\t\t\t\t\t\t");
        copia.exibir();
        copia.destruirLista();

        copia = lista.duplicaLista();
        copia.tim();
        System.out.print("Tim: \t\t\t\t\t\t\t");
        copia.exibir();
        copia.destruirLista();
    }
}
