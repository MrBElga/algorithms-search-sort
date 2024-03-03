import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        ListaEstado listaEstado = new ListaEstado();
        NoCidade l = new NoCidade();
        listaEstado.inserirEstado("São Paulo");
        listaEstado.inserirEstado("Rio de Janeiro");
        listaEstado.inserirEstado("Minas Gerais");
        listaEstado.inserirCidade("Campinas", "São Paulo");
        listaEstado.inserirCidade("Santos", "São Paulo");
        listaEstado.inserirCidade("Niterói", "Rio de Janeiro");
        listaEstado.inserirCidade("Belo Horizonte", "Minas Gerais");
        listaEstado.inserirCidade("Uberlândia", "Minas Gerais");

        System.out.println("Estados e suas cidades (antes da ordenação):");
        listaEstado.exibirTodasCidades();
        System.out.println();

        listaEstado.ordenarEstados();
        listaEstado.OrdenarTodasCidades();
        System.out.println("Estados e suas cidades (após a ordenação):");
        listaEstado.exibirTodasCidades();

        System.out.println("buscando cidades");
        l = listaEstado.buscarCidade("São Paulo","Campinas");
        if (l!=null){
            System.out.println("cidade encontrada");
        }
        else{
            System.out.println("cidade não encontrada");
        }
    }
}
