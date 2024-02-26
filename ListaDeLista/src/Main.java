
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Lista lista = new Lista();

        lista.inicializar();
        lista.inserirEstado("São paulo");
        lista.inserirEstado("teste");
        lista.inserirEstado(" ");
        lista.inserirEstado("São paulo");
        lista.inserirEstado("teste");
        lista.inserirEstado(" ");

        lista.exibirEstado();

    }
}