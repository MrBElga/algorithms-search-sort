public class Main {
    public static void main(String[] args){
        Lista<Integer> lista = new Lista<>();
        lista.adiciona(1);
        lista.adiciona(2);
        lista.adiciona(3);
        lista.adiciona(4);

        System.out.println("tamanho: " + lista.getTamanho());
        System.out.println(lista);

        int elementoBuscado = 3;
        No<Integer> noEncontrado = lista.busca(elementoBuscado);

        if (noEncontrado != null) {
            System.out.println("Elemento " + elementoBuscado + " encontrado na lista.");
        } else {
            System.out.println("Elemento " + elementoBuscado + " não encontrado na lista.");
        }

        lista.limpar();
        System.out.println("tamanho: " + lista.getTamanho());
        System.out.println(lista);
    }
}
