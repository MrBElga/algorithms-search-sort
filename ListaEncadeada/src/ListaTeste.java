
public class ListaTeste {
    public static void main(String[] args){
        Lista<Integer> lista = new Lista<>();
        lista.adiciona(1);
        lista.adiciona(2);
        lista.adiciona(3);
        lista.adiciona(4);

        System.out.println("tamanho: " + lista.getTamanho());
        System.out.println(lista);

        lista.limpar();
        System.out.println("tamanho: " + lista.getTamanho());
        System.out.println(lista);
    }
}
