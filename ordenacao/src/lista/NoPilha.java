package lista;
public class NoPilha {
    private int info;
    private NoPilha prox;

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public NoPilha getProx() {
        return prox;
    }

    public void setProx(NoPilha prox) {
        this.prox = prox;
    }

    public NoPilha(int info, NoPilha prox) {
        this.info = info;
        this.prox = prox;
    }
}
