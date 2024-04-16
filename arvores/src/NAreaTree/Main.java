package NAreaTree;

public class Main {
    public static int N = 2;
    public static void main(String[] args) {
        Narea novo;
        novo = new Narea();

        novo.inserir(100);
        novo.inserir(90);
        novo.inserir(40);
        novo.inserir(99);
        novo.inserir(98);
        novo.inserir(95);
        novo.inOrd(novo.getRaiz());

    }
}