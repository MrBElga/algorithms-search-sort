package lista;


public class Pilha {
    private NoPilha topo;

    public NoPilha getTopo() {
        return topo;
    }

    public void setTopo(NoPilha topo) {
        this.topo = topo;
    }

    public Pilha() {
        this.topo = null;
    }

    public void push(int info) {
        NoPilha novo = new NoPilha(info, this.topo);
        this.topo = novo;
    }

    public int pop() {
        int aux;
        if (this.topo == null) {
            aux = -1;
        } else {
            aux = this.topo.getInfo();
            this.topo = this.topo.getProx();

        }
        return aux;
    }

    public boolean isEmpty() {
        if (this.topo == null) {
            return true;
        }
        return false;
    }
}
