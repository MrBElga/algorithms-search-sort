public class Lista {
    private No inicio;
    private No fim;

    public Lista() {
        this.inicio = inicio;
        this.fim = fim;
    }
    public void inicializa(){
      inicio = null;
      fim = null;
    }
    public void inserirInicio(int info){
        No nova = new No(null,null,info);
        if(inicio == null){
            inicio = fim = nova;
        }
        else{
            inicio.setAnt(nova);
            inicio = nova;
        }
    }
    public void inserirFim(int info){
        No nova = new No(null,null,info);
        if(inicio == null){
            inicio = fim = nova;
        }
        else{
            fim.setProx(nova);
            nova.setAnt(fim);
            fim = nova;
        }
    }
    public void insercaoDireta(){
        int aux;
        No ppos, pi;
        for (pi = inicio.getProx() ; pi != null ;pi = pi.getProx()) {
            aux = pi.getInfo();
            ppos = pi;
            while(ppos != inicio && aux<ppos.getAnt().getInfo()){
                ppos.setInfo(ppos.getAnt().getInfo());
                ppos = ppos.getAnt();
            }
            ppos.setInfo(aux);
        }
    }
    public void exibir(){
        No i;
        if(inicio == null)
            System.out.println("[]");
        else{
            System.out.print("[");
            for(i = inicio ; i.getProx() != null ; i = i.getProx()){
                System.out.print(i.getInfo() + ",");
            }
            System.out.print(i.getInfo()+"]");
            //System.out.println("]");
        }
    }
    public No buscaExaustiva(int chave){
        No ppos = inicio;
        while(ppos != null && chave != ppos.getInfo() ){
            ppos = ppos.getProx();
        }
        return ppos;
    }
    public void remover(int info){
        No ppos = inicio.getProx();
        ppos = buscaExaustiva(info);
        if (ppos == inicio) {
            inicio = ppos.getProx();
        } else if (ppos == fim) {
            fim = ppos.getAnt();
        } else {
            if (ppos.getAnt() != null) {
                ppos.getAnt().setProx(ppos.getProx());
            }
            if (ppos.getProx() != null) {
                ppos.getProx().setAnt(ppos.getAnt());
            }
        }

        ppos.setAnt(null);
        ppos.setProx(null);
    }
}
