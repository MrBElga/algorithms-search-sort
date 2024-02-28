public class Main {
    public static void main(String[] args) {
        Lista listaEstados = new Lista();
        NoEstado inserido;
        NoCidade inseridoC;

        listaEstados.inserirEstado("São Paulo");
        listaEstados.inserirEstado("Rio de Janeiro");
        listaEstados.inserirEstado("Minas Gerais");
        listaEstados.inserirEstado("Santa Catarina");
        listaEstados.inserirEstado("Paraná");

        listaEstados.inserirCidade("São Paulo", "São Paulo");
        listaEstados.inserirCidade("Assis", "São Paulo");
        listaEstados.inserirCidade("Marilia", "São Paulo");
        listaEstados.inserirCidade("Presidente Prudente", "São Paulo");
        listaEstados.inserirCidade("Rio de Janeiro", "Rio de Janeiro");
        listaEstados.inserirCidade("Belo Horizonte", "Minas Gerais");
        listaEstados.inserirCidade("Apucarana", "Paraná");
        listaEstados.inserirCidade("Arapongas", "Paraná");
        listaEstados.inserirCidade("Maringá", "Paraná");
        listaEstados.inserirCidade("Londrina", "Paraná");
        listaEstados.inserirCidade("Blumenau", "Santa Catarina");
        listaEstados.inserirCidade("Joinville", "Santa Catarina");

        System.out.println("\n----------------ESTADOS---------------\n");
        System.out.println("numero de Estados: "+listaEstados.contaEstados());
        listaEstados.exibirEstados();
        System.out.println("\n----------------CIDADES---------------\n");
        System.out.println("numero de cidades: "+listaEstados.contaCidads());
        listaEstados.exibirTodasCidades();
        System.out.println("\n----------------CIDADES SÃO PAULO---------------\n");
        NoEstado aux = listaEstados.buscarEstado("São Paulo");
        listaEstados.exibirCidades(aux.getListaCidades());
        System.out.println("\n----------------ORDENANDO ESTADOS---------------\n");
        listaEstados.OrdenarEstado();
        listaEstados.exibirEstados();

        System.out.println("\n----------------ORDENANDO CIDADES---------------\n");
        listaEstados.OrdenarTodasCidades();
        listaEstados.exibirTodasCidades();

        System.out.println("\n----------------BUSCAS---------------\n");
        inserido = listaEstados.buscarEstado("São Paulo");
        if(inserido!=null)
            System.out.println("estado encontrada");
        else
            System.out.println("estado não encotrada");

        inseridoC = listaEstados.buscarCidade("Presidente Prudente", "São Paulo");
        if(inseridoC!=null)
            System.out.println("cidade encontrada");
        else
            System.out.println("Cidade não encontrada");
    }
}
