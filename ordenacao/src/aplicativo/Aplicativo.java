package aplicativo;

import java.util.Scanner;

public class Aplicativo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Arquivos arquivos = new Arquivos();
        Listas listas = new Listas();

        boolean sair = false;

        while (!sair) {
            System.out.println("\n######### MENU #########");
            System.out.println("[1] operações em lista");
            System.out.println("[2] operações em arquivo");
            System.out.println("[3] sair");
            System.out.print("Opcao: ");
            int escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    System.out.println("########################################################################\n\t\t\t\t\t\t\t\tEM LISTA\n" +
                                       "/----------------------------------------------------------------------/");
                    listas.executarListas();
                    System.out.println("########################################################################");
                    break;
                case 2:
                    System.out.println("########################################################################\n\t\t\t\t\t\t\t\tEM ARQUIVO\n" +
                                    "/----------------------------------------------------------------------/");
                    arquivos.executarArquivos();
                    System.out.println("########################################################################");
                    break;
                case 3:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }

        scanner.close();
    }
}
