public class No {

    private No proximo;
    private String info;

    public No( No proximo, String cidade) {

        this.proximo = proximo;
        this.info = cidade;
    }

    public No getProximo() {
        return proximo;
    }

    public void setProximo(No proximo) {
        this.proximo = proximo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String cidade) {
        this.info = cidade;
    }

    @Override
    public String toString() {
        return "No{" +
                ", proximo=" + proximo +
                ", cidade=" + info +
                '}';
    }
}
