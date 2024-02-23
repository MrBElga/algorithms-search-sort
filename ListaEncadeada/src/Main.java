//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Random;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Lista lista = new Lista();
        Random rand = new Random();

        for (int i = 0; i <= 10; i++) {
            lista.inserirFim(i);
        }
        lista.exibir();

        lista.remover(5);
        lista.exibir();
        lista.remover(10);
        lista.exibir();
        lista.remover(9);
        lista.exibir();
        lista.remover(0);
        lista.exibir();
        lista.remover(1);
        lista.exibir();
    }
}