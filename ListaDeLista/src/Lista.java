public class Lista {
    private NoEstado inicioEstado;

    public Lista() {
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
        if (estado != null) {
            NoCidade novaCidade = new NoCidade(null, cidade);
            if (estadoAtual.getListaCidades() == null) {
                estadoAtual.setListaCidades(novaCidade);
            } else {
                NoCidade aux = estadoAtual.getListaCidades();
                while (aux.getProximo() != null && !aux.getInfo().equals(cidade)) {
                    aux = aux.getProximo();
                }
                if (!aux.getInfo().equals(cidade)) {
                    aux.setProximo(novaCidade);
                } else {
                    System.out.println("Cidade já inserida anteriormente");
                }
            }
        } else {
            System.out.println("Estado não encontrado");
        }
    }

    public NoCidade buscarCidade(String cidade, String estado) {
        NoEstado estadoAtual = buscarEstado(estado);
        NoCidade atual = null;
        if (estadoAtual != null) {

            atual = estadoAtual.getListaCidades();
            while (atual != null && !(atual.getInfo().equals(cidade))) {
                atual = atual.getProximo();
            }

        }
        return atual;
    }


    public void exibirEstados() {
        NoEstado i = inicioEstado;
        while (i != null) {
            System.out.println(i.getInfo());
            i = i.getProximo();
        }
    }
    public void exibirCidades(NoCidade listaCidades) {
        for (NoCidade i = listaCidades;i != null;i=i.getProximo()) {
            System.out.println(i.getInfo());
        }
    }
    public void exibirTodasCidades() {
        for(NoEstado estado = inicioEstado; estado != null; estado = estado.getProximo())
        {
            exibirCidades(estado.getListaCidades());
        }
    }


    public int contaCidads(){
        int i =0;
        for(NoEstado estado = inicioEstado; estado != null; estado = estado.getProximo())
        {
            for (NoCidade cidade = estado.getListaCidades();cidade!= null;cidade=cidade.getProximo()) {
                i++;
            }
        }
        return i;
    }

    public int contaEstados(){
        int i =0;
        for(NoEstado estado = inicioEstado; estado != null; estado = estado.getProximo())
        {
                i++;
        }
        return i;
    }

    public void OrdenarEstado(){
        NoEstado i, J;
        NoCidade auxC;
        String aux = "";
        for (i = inicioEstado; i != null; i = i.getProximo()) {
            for (J = i.getProximo(); J != null; J = J.getProximo()) {
                if (i.getInfo().compareTo(J.getInfo()) > 0) {
                    aux = i.getInfo();
                    i.setInfo(J.getInfo());
                    J.setInfo(aux);
                    auxC = i.getListaCidades();
                    i.setListaCidades(J.getListaCidades());
                    J.setListaCidades(auxC);
                }
            }
        }
    }
    public void OrdenarCidade(NoEstado estado) {
        if (estado != null) {
            NoCidade i, j;
            String aux;

            for (i = estado.getListaCidades(); i != null; i = i.getProximo()) {
                for (j = i.getProximo(); j != null; j = j.getProximo()) {
                    if (i.getInfo().compareTo(j.getInfo()) > 0) {
                        aux = i.getInfo();
                        i.setInfo(j.getInfo());
                        j.setInfo(aux);
                    }
                }
            }
        } else {
            System.out.println("Estado nulo");
        }
    }
    public void OrdenarTodasCidades() {
        NoEstado atual = inicioEstado;

        while (atual != null) {
            OrdenarCidade(atual);
            atual = atual.getProximo();
        }
    }
}