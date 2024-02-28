public class NoEstado {
    private NoEstado proximo;
    private NoCidade listaCidades;
    private String info;

    public NoEstado(NoEstado proximo, String info) {
        this.proximo = proximo;
        this.info = info;
        this.listaCidades = null;
    }

    public NoEstado getProximo() {
        return proximo;
    }

    public void setProximo(NoEstado proximo) {
        this.proximo = proximo;
    }

    public NoCidade getListaCidades() {
        return listaCidades;
    }

    public void setListaCidades(NoCidade listaCidades) {
        this.listaCidades = listaCidades;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
