public class ListaEstado {
    private NoEstado inicioEstado;


    public ListaEstado() {
        this.inicioEstado = null;
    }

    public void inserirEstado(String estado) {
        NoEstado novoEstado = new NoEstado(null, estado);
        if (inicioEstado == null) {
            inicioEstado = novoEstado;
        } else {
            NoEstado aux = inicioEstado;
            while (aux.getProximo() != null && !aux.getInfo().equals(estado)) {
                aux = aux.getProximo();
            }
            if (!aux.getInfo().equals(estado)) {
                aux.setProximo(novoEstado);
            } else {
                System.out.println("Estado já inserido anteriormente");
            }
        }
    }
    public NoEstado buscarEstado(String estado) {
        NoEstado aux = inicioEstado;
        while (aux != null && !aux.getInfo().equals(estado)) {
            aux = aux.getProximo();
        }
        return aux;
    }

    public void inserirCidade(String cidade, String estado) {
        NoEstado estadoAtual = buscarEstado(estado);
        if (estadoAtual != null) {
            estadoAtual.getListaCidades().inserirCidade(cidade);
        } else {
            System.out.println("Estado não encontrado");
        }
    }

    public void exibirEstados() {
        NoEstado i = inicioEstado;
        while (i != null) {
            System.out.println(i.getInfo());
            i = i.getProximo();
        }
    }

    public void ordenarEstados() {
        if (inicioEstado != null) {
            NoEstado i, j;
            String aux;
            ListaCidade auxListaCidades;

            for (i = inicioEstado; i != null; i = i.getProximo()) {
                for (j = i.getProximo(); j != null; j = j.getProximo()) {
                    if (i.getInfo().compareTo(j.getInfo()) > 0) {
                        // Troca informações dos estados
                        aux = i.getInfo();
                        i.setInfo(j.getInfo());
                        j.setInfo(aux);

                        // Troca listas de cidades associadas aos estados
                        auxListaCidades = i.getListaCidades();
                        i.setListaCidades(j.getListaCidades());
                        j.setListaCidades(auxListaCidades);
                    }
                }
            }
        }
    }

    public int contaEstados(){
        int i =0;
        for(NoEstado estado = inicioEstado; estado != null; estado = estado.getProximo())
        {
                i++;
        }
        return i;
    }
    public void OrdenarTodasCidades() {
        NoEstado atual = inicioEstado;

        while (atual != null) {
            atual.getListaCidades().ordenarCidades();
            atual = atual.getProximo();
        }
    }
    public NoCidade buscarCidade(String estado, String cidade) {
        NoCidade atual = null;
        NoEstado estadoat = buscarEstado(estado);
        if (estadoat != null) {
            atual = estadoat.getListaCidades().buscarCidade(cidade);
        }

        return atual;
    }
    public void exibirTodasCidades() {
        NoEstado estadoAtual = inicioEstado;

        while (estadoAtual != null) {
            System.out.println("Estado: " + estadoAtual.getInfo());
            estadoAtual.getListaCidades().exibirCidades();
            System.out.println();  // Adicione uma quebra de linha entre os estados
            estadoAtual = estadoAtual.getProximo();
        }

    }
}