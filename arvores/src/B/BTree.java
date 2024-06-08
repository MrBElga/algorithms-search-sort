package B;

public class BTree {
    private final int M = 2;
    private No raiz;

    public BTree() {
        this.raiz = null;
    }

    public No getRaiz() {
        return raiz;
    }

    public No andarFolha(int info) {
        No folha = raiz;
        int pos;
        while (folha.getvLig(0) != null) {
            pos = folha.buscarPos(info);
            folha = folha.getvLig(pos);
        }
        return folha;
    }

    public No buscaPai(No folha, int info) {
        No pai = raiz;
        No no = raiz;
        int pos;
        while (no != folha) {
            pai = no;
            pos = no.buscarPos(info);
            no = no.getvLig(pos);
        }
        return pai;
    }

    private void split(No folha, No pai)
    {
        No cx1 = new No();
        No cx2 = new No();
        for(int i=0; i<No.M; i++)
        {
            cx1.setvInfo(i, folha.getvInfo(i));
            cx1.setvPos(i, folha.getvPos(i));
            cx1.setvLig(i, folha.getvLig(i));
        }
        cx1.setvLig(No.M, folha.getvLig(No.M));
        cx1.setTL(No.M);

        for(int i=No.M+1; i<2*No.M+1 ; i++)
        {
            cx2.setvInfo(i-(No.M+1), folha.getvInfo(i));
            cx2.setvPos(i-(No.M+1), folha.getvPos(i));
            cx2.setvLig(i-(No.M+1), folha.getvLig(i));
        }
        cx2.setvLig(No.M, folha.getvLig(2*No.M+1));
        cx2.setTL(No.M);

        if(pai==folha)
        {
            folha.setvInfo(0, folha.getvInfo(No.M));
            folha.setvPos(0, folha.getvPos(No.M));
            folha.setTL(1);
            folha.setvLig(0, cx1);
            folha.setvLig(1, cx2);
        }
        else
        {
            int pos = pai.buscarPos(folha.getvInfo(No.M));
            pai.remanejar(pos);
            pai.setvInfo(pos, folha.getvInfo(No.M));
            pai.setvPos(pos, folha.getvPos(No.M));
            pai.setTL(pai.getTL()+1);
            pai.setvLig(pos, cx1);
            pai.setvLig(pos+1, cx2);
            if(pai.getTL()>2*No.M)
            {
                folha=pai;
                pai=buscaPai(folha, folha.getvInfo(0));
                split(folha, pai);
            }
        }
    }



    public void inserir(int info, int posArq)
    {
        No folha, pai;
        int pos;
        if(raiz == null)
            raiz = new No(info,posArq);
        else
        {
            folha = andarFolha(info);
            pos = folha.buscarPos(info);
            folha.remanejar(pos);
            folha.setvInfo(pos, info);
            folha.setvPos(pos, posArq);
            folha.setTL(folha.getTL() + 1);
            if(folha.getTL() > 2*No.M)
            {
                pai = buscaPai(folha, info);
                split(folha, pai);
            }
        }
    }

    private No localizarNo(int info) {
        No aux = raiz;
        int pos;
        boolean flag = false;
        while (aux != null && !flag) {
            pos = aux.buscarPos(info);
            if (aux.getvInfo(pos) == info) {
                flag = true;
            } else {
                aux = aux.getvLig(pos);
            }
        }
        return aux;
    }

    private No localizarSubE(No no, int pos)
    {
        no = no.getvLig(pos);
        while(no.getvLig(0)!=null)
            no = no.getvLig(no.getTL());
        return no;
    }

    private No localizarSubD(No no, int pos)
    {
        no = no.getvLig(pos);
        while(no.getvLig(0)!=null)
            no = no.getvLig(0);
        return no;
    }



    public void excluir(int info) {
        int pos;
        No subE, subD, folha;
        No no = localizarNo(info);
        if (no != null) {
            pos = no.buscarPos(info);
            //se no nao eh folha
            if (no.getvLig(0) != null) {
                subE = localizarSubE(no, pos);
                subD = localizarSubD(no, pos+1);
                if (subE.getTL() > No.M || subD.getTL() == No.M) {
                    no.setvInfo(pos, subE.getvInfo(subE.getTL() - 1));
                    no.setvPos(pos, subE.getvInfo(subE.getTL() - 1));

                    folha = subE;
                    pos = subE.getTL() - 1;

                } else {
                    no.setvInfo(pos, subD.getvInfo(0));
                    no.setvPos(pos, subD.getvPos(0));
                    folha = subD;
                    pos = 0;
                }
            } else {
                folha = no;
            }

            // excluir folha
            folha.remanejarExclusao(pos);
            folha.setTL(folha.getTL() - 1);

            if (folha == raiz && folha.getTL() == 0) {
                raiz = null;
            } else if (folha != raiz && folha.getTL() < No.M) {
                redistribuirConcatenar(folha);
            }
        }
    }

    private void redistribuirConcatenar(No folha) {
        No pai = buscaPai(folha, folha.getvInfo(0));
        int posPai = pai.buscarPos(folha.getvInfo(0));
        No irmaE = null, irmaD = null;
        if (posPai - 1 >= 0) {
            irmaE = pai.getvLig(posPai - 1);
        }
        if (posPai + 1 <= pai.getTL()) {
            irmaD = pai.getvLig(posPai + 1);
        }

        if (irmaE != null && irmaE.getTL() > No.M) {
            folha.remanejar(0);
            folha.setvInfo(0, pai.getvInfo(posPai - 1));
            folha.setvPos(0, pai.getvPos(posPai - 1));
            folha.setvLig(0, irmaE.getvLig(irmaE.getTL()));
            folha.setTL(folha.getTL() + 1);

            pai.setvInfo(posPai - 1, irmaE.getvInfo(irmaE.getTL() - 1));
            pai.setvPos(posPai - 1, irmaE.getvPos(irmaE.getTL() - 1));

            irmaE.setTL(irmaE.getTL() - 1);

        } else if (irmaD != null && irmaD.getTL() > No.M) {//redistribuicao com a irma da direita
            folha.setvInfo(folha.getTL(), pai.getvInfo(posPai));
            folha.setvPos(folha.getTL(), pai.getvPos(posPai));
            folha.setvLig(folha.getTL() + 1, irmaD.getvLig(0));
            folha.setTL(folha.getTL() + 1);

            pai.setvInfo(posPai, irmaD.getvInfo(0));
            pai.setvPos(posPai, irmaD.getvPos(0));

            irmaD.remanejarExclusao(0);
            irmaD.setTL(irmaD.getTL() - 1);
        } else {//concatenacao
            if (irmaE != null) {
                irmaE.setvInfo(irmaE.getTL(), pai.getvInfo(posPai - 1));
                irmaE.setvPos(irmaE.getTL(), pai.getvPos(posPai - 1));
                irmaE.setTL(irmaE.getTL() + 1);
                irmaE.setvLig(irmaE.getTL(), folha.getvLig(0));

                for (int i = 0; i < folha.getTL(); i++) {
                    irmaE.setvInfo(irmaE.getTL(), folha.getvInfo(i));
                    irmaE.setvPos(irmaE.getTL(), folha.getvPos(i));
                    irmaE.setvLig(irmaE.getTL() + 1, folha.getvLig(i + 1));
                    irmaE.setTL(irmaE.getTL() + 1);
                }
                pai.remanejarExclusao(posPai - 1);
                pai.setTL(pai.getTL() - 1);
                pai.setvLig(posPai - 1, irmaE);
            } else {
                folha.setvInfo(folha.getTL(), pai.getvInfo(posPai));
                folha.setvPos(folha.getTL(), pai.getvPos(posPai));
                folha.setTL(folha.getTL() + 1);
                folha.setvLig(folha.getTL(), irmaD.getvLig(0));
                for (int i = 0; i < irmaD.getTL(); i++) {
                    folha.setvInfo(folha.getTL(), irmaD.getvInfo(i));
                    folha.setvPos(folha.getTL(), irmaD.getvPos(i));
                    folha.setvLig(folha.getTL() + 1, irmaD.getvLig(i + 1));
                    folha.setTL(folha.getTL() + 1);
                }

                pai.remanejarExclusao(posPai);
                pai.setTL(pai.getTL() - 1);
                pai.setvLig(posPai, folha);
            }


            if (pai == raiz && pai.getTL() == 0) {
                if (irmaE != null) raiz = irmaE;
                else raiz = irmaD;
            } else if (pai != raiz && pai.getTL() < M) {
                redistribuirConcatenar(pai);
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

    public  void printBTree(BTree tree) {
        printBTree(tree.getRaiz(), "", true);
    }

    private  void printBTree(No node, String prefix, boolean isTail) {
        if (node != null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + nodeToString(node));
            for (int i = 0; i < node.getTL() + 1; i++) {
                printBTree(node.getvLig(i), prefix + (isTail ? "    " : "│   "), i == node.getTL());
            }
        }
    }

    private  String nodeToString(No node) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < node.getTL(); i++) {
            builder.append(node.getvInfo(i));
            if (i < node.getTL() - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }


}
