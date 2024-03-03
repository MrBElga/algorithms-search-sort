public class NoCidade {
    private NoCidade proximo;
    private String info;

    public NoCidade(NoCidade proximo, String info) {
        this.proximo = proximo;
        this.info = info;
    }

    public NoCidade() {
    }

    public NoCidade getProximo() {
        return proximo;
    }

    public void setProximo(NoCidade proximo) {
        this.proximo = proximo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}