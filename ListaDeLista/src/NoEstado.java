public class NoEstado {
    private NoEstado proximo;
    private ListaCidade listaCidade;
    private String info;

    public NoEstado(NoEstado proximo, String info) {
        this.proximo = proximo;
        this.info = info;
        this.listaCidade = new ListaCidade();
    }

    public NoEstado getProximo() {
        return proximo;
    }

    public void setProximo(NoEstado proximo) {
        this.proximo = proximo;
    }

    public ListaCidade getListaCidades() {
        return listaCidade;
    }

    public void setListaCidades(ListaCidade listaCidade) {
        this.listaCidade = listaCidade;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
