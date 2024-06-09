package NArea;

public class Narea {
    private No raiz;

    public Narea() {

    }

    public No getRaiz() {
        return raiz;
    }

    public void inserir(int info) {
        No aux;
        int pos;
        boolean flag = false;
        if (raiz == null) {
            raiz = new No(info);
        } else {
            //atribuindo aux para andar pelo aux
            aux = raiz;
            //enquanto não inseriu(flag serve para ver se ocorreu a inserção)
            while (!flag) {
                //buscar a posicao
                pos = aux.buscarPos(info);
                //se o Tl é menor q N (N-1 pois o vetor é 0 e 1 para 3 areas)
                if (aux.getTL() < No.N - 1) {
                    aux.remanejar(pos);
                    aux.setvInfo(pos, info);
                    aux.setTL(aux.getTL() + 1);
                    flag = true;
                } else {
                    if (aux.getvLig(pos) == null) {
                        aux.setvLig(pos, new No(info));
                        flag = true;
                    } else {
                        aux = aux.getvLig(pos);
                    }
                }
            }
        }
    }

    public void inOrd() {
        inOrd(raiz);
    }

    private void inOrd(No raiz) {
        if (raiz != null) {
            for (int i = 0; i < raiz.getTL(); i++) {
                inOrd(raiz.getvLig(i));
                System.out.println(raiz.getvInfo(i));
            }
            inOrd(raiz.getvLig(raiz.getTL()));
        }
    }
}
