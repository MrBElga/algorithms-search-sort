public class Lista {
    private String info;
    private No inicio;

    public Lista() {
        this.inicio = null;
    }
    public void inicializar(){
        inicio = null;
    }
    public void inserirEstado(String info){
        No nova = new No(null, info);
        if(inicio == null){
            inicio = nova;
        }
        else{
          No aux = inicio;
            while (aux.getProximo() != null && aux.getInfo() != info){
                aux = aux.getProximo();
            }
            if(aux.getInfo() != info){
                aux.setProximo(nova);

            }
            else{
                System.out.println("Estado já inserido anteriormente");
            }

        }
    }
    public No buscarEstado(String info){
        No ppos = inicio;
        while(ppos != null && info != ppos.getInfo() ){
            ppos.getProximo();
        }
        return ppos;
    }
    public void inserirCidade(String info, String Estado){
        No ppos = buscarEstado(Estado);
        if(ppos != null) {
            No nova = new No(null, info);
            if (inicio == null) {
                inicio = nova;
            } else {
                No aux = inicio;
                while (aux.getProximo() != null && aux.getInfo() != info) {
                    aux = aux.getProximo();
                }
                if (aux.getInfo() != info) {
                    aux.setProximo(nova);

                } else {
                    System.out.println("Estado já inserido anteriormente");
                }
            }
        }
        else{
            System.out.println("Cidade não encontada");
        }
    }



    public boolean buscarCidade(String info){
        return false;
    }
    public void remover(){

    }

    public void exibirEstado(){
        No i;
        if(inicio == null)
            System.out.println("[]");
        else{
            System.out.print("[");
            for(i = inicio ; i.getProximo() != null ; i = i.getProximo()){
                System.out.print(i.getInfo()+", ");
            }
            System.out.print(i.getInfo() + "]");
        }
    }

    public void exibirCidade(){

    }


}
