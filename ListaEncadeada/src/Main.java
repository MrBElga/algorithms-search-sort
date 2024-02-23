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

        for (int i = 0; i <=10; i++) {
            lista.remover(i);
            lista.exibir();
        }

    }
}