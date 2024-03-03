public class ListaCidade {
    private NoCidade inicio;

    public ListaCidade() {
        this.inicio = null;
    }

    public void inserirCidade(String cidade) {
        NoCidade novaCidade = new NoCidade(null, cidade);
        if (inicio == null) {
            inicio = novaCidade;
        } else {
            NoCidade aux = inicio;
            while (aux.getProximo() != null && !aux.getInfo().equals(cidade)) {
                aux = aux.getProximo();
            }
            if (!aux.getInfo().equals(cidade)) {
                aux.setProximo(novaCidade);
            } else {
                System.out.println("Cidade já inserida anteriormente");
            }
        }
    }

    public void ordenarCidades() {
        if (inicio != null) {
            NoCidade i, j;
            String aux;

            for (i = inicio; i != null; i = i.getProximo()) {
                for (j = i.getProximo(); j != null; j = j.getProximo()) {
                    if (i.getInfo().compareTo(j.getInfo()) > 0) {
                        aux = i.getInfo();
                        i.setInfo(j.getInfo());
                        j.setInfo(aux);
                    }
                }
            }
        }
    }

    public void exibirCidades() {
        NoCidade atual = inicio;
        while (atual != null) {
            System.out.println("   " + atual.getInfo());
            atual = atual.getProximo();
        }
    }

    public NoCidade buscarCidade(String Cidade){
        NoCidade atual = inicio ;
        while (atual != null && !atual.getInfo().equals(Cidade)){
            atual = atual.getProximo();
        }

        return atual;

    }

}
