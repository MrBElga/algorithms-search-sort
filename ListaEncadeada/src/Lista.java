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
        No nova = new No(null,inicio,info);
        if(inicio == null){
            inicio = fim = nova;
        }
        else{
            inicio.setAnt(nova);
            inicio = nova;
        }
    }

    public void inserirFim(int info){
        No nova = new No(null,fim,info);
        if(inicio == null){
            inicio = fim = nova;
        }
        else{
            fim.setProx(nova);
            fim = nova;
        }
    }
    public void insercaoDireta(){
        int aux;
        No ppos, pi;
        for (pi = inicio.getProx() ; pi != null ;pi = pi.getProx()) {
            aux = pi.getInfo();
            ppos = pi;
            while(ppos != inicio && aux<ppos.getProx().getInfo()){
                ppos.setInfo(ppos.getAnt().getInfo());
                ppos = ppos.getAnt();
            }
            ppos.setInfo(aux);
        }
    }
    public void exibir(){
        if(inicio == null)
            System.out.println("[]");
        else{

            for(No i = inicio ; i != null ; i = i.getProx()){
                System.out.println(i.getInfo() + " ");
            }
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
        No ppos = inicio;
        ppos = buscaExaustiva(info);
        if(ppos == inicio){
            ppos.getProx().setAnt(null);
        }
        else if(ppos == fim){
            ppos.getAnt().setProx(null);
        }
        else{
            ppos.getAnt().setProx(ppos.getProx());
            ppos.getProx().setAnt(ppos.getAnt());
        }
    }
}