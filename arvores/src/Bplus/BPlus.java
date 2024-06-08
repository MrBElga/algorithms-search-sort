package Bplus;

public class BPlus {
    private No raiz;
    //private int No.M = 5;

    public BPlus() {
        raiz = null;
    }

    public No getRaiz() {
        return this.raiz;
    }

    private No localizarNo(int info) {
        No aux = raiz;
        int pos;
        boolean flag = false;
        while (aux != null && !flag) {
            pos = aux.localizarpos(info);
            if (aux.getvInfo(pos) == info) {
                flag = true;
            } else {
                aux = aux.getvLig(pos);
            }
        }
        return aux;
    }

    private No localizarNoFolha(int info) {
        No aux = raiz;
        int pos;
        while (aux != null && aux.getvLig(0) != null) {
            pos = aux.localizarposFolha(info);
            aux = aux.getvLig(pos);
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
    public No localizarFolha(int info) {
        int i ;
        No aux = this.raiz;
        while (aux.getvLig(1) != null) {
            i = 0;
            while(i < aux.getTL() && info > aux.getvInfo(i)) i++;
            aux = aux.getvLig(i);
        }
        return aux;
    }

    public No localizarpai (No folha, int info) {
        No atual = raiz;
        No pai = atual;
        int pos;
        while(atual != folha && atual.getvLig(0) != null)
        {
            pai = atual;
            pos = atual.localizarposFolha(info);
            atual = atual.getvLig(pos);
        }
        return pai;
    }

    public void split(No folha, No pai) {
        No cx1 = new No();
        No cx2 = new No();
        int aux,meio , i, pos, info;

        if (folha.getvLig(0) == null) { // Caso seja um nó folha
            aux = (int) Math.ceil((double)(No.M - 1.0) / 2.0); // (4-1) / 2 = 3/2 = 1,5.ceil = 2 4/2 =2
            for (i = 0; i < aux; i++) {
                cx1.setvInfo(i, folha.getvInfo(i));
                cx1.setvPos(i, folha.getvPos(i));
                cx1.setTL(cx1.getTL() + 1);
            }
            meio = aux;
            for (i = aux; i < No.M; i++) {
                cx2.setvInfo(i - (aux), folha.getvInfo(i));
                cx2.setvPos(i - (aux), folha.getvPos(i));
                cx2.setTL(cx2.getTL() + 1);
            }
        } else {// Caso seja um nó não folha
            aux = (int) Math.ceil((double)(No.M / 2.0) - 1.0); //4/2 = 2   5/2 = 2,5 = 3
            for (i = 0; i < aux; i++) {
                cx1.setvInfo(i, folha.getvInfo(i));
                cx1.setvPos(i, folha.getvPos(i));
                cx1.setvLig(i, folha.getvLig(i));
                cx1.setTL(cx1.getTL() + 1);
            }
            cx1.setvLig(aux, folha.getvLig(aux));
            meio = aux++;
            for (i = aux; i < No.M; i++) {
                cx2.setvInfo(i - (aux), folha.getvInfo(i));
                cx2.setvPos(i - (aux), folha.getvPos(i));
                cx2.setvLig(i - (aux), folha.getvLig(i));
                cx2.setTL(cx2.getTL() + 1);
            }
            cx2.setvLig(i - aux, folha.getvLig(No.M));
        }

        if (folha == pai) {// Caso a folha seja a raiz
            folha.setvInfo(0, folha.getvInfo(meio));
            folha.setvPos(0, folha.getvPos(meio));
            folha.setvLig(0, cx1);
            folha.setvLig(1, cx2);
            folha.setTL(1);

            cx1.setProx(cx2);
            cx2.setant(cx1);
        } else {// Caso a folha não seja a raiz
            info = folha.getvInfo(meio);
            pos = pai.localizarpos(info);
            pai.remanejar(pos);
            pai.setTL(pai.getTL() + 1);
            pai.setvInfo(pos, folha.getvInfo(meio));
            pai.setvPos(pos, folha.getvPos(meio));
            pai.setvLig(pos, cx1);
            pai.setvLig(pos + 1, cx2);

            cx1.setant(folha.getant());
            if (folha.getant() != null) {
                folha.getant().setProx(cx1);
            }

            cx1.setProx(cx2);
            cx2.setant(cx1);
            cx2.setProx(folha.getProx());
            if (folha.getProx() != null) {
                folha.getProx().setant(cx2);
            }

            if (pai.getTL() > No.M - 1) { // Se overflow no pai, faz split recursivamente
                folha = pai;
                info = folha.getvInfo(meio);
                pai = this.localizarpai(folha, info);
                split(folha, pai);
            }
        }
    }

    public void inserir(int info) {
        No folha;
        No pai;
        int pos;
        if (this.raiz == null) {
            this.raiz = new No(info);
            raiz.setvPos(0,info);
        } else {
            folha = this.localizarFolha(info);
            pos = folha.localizarpos(info);
            folha.remanejar(pos);
            folha.setTL(folha.getTL() + 1);
            folha.setvInfo(pos, info);
            folha.setvPos(pos, info);
            if (folha.getTL() > No.M - 1) {
                pai = localizarpai(folha, info);
                split(folha, pai);
            }
        }
    }


    public void excluir(int info) {
        int pos, min = (int) Math.ceil(No.M / 2.0) - 1;
        No subE, subD, pai;
        No no = localizarNoFolha(info);

        if (no != null) {
            pos = no.localizarpos(info);
            // excluir folha
            no.remanejarExclusao(pos);
            no.setTL(no.getTL() - 1);

            // no é igual a raiz e tl é 0? se sim, árvore vazia
            if (no == raiz && no.getTL() == 0) {
                raiz = null;
            } else if (no != raiz && no.getTL() < min) {
                // passar o pai e o no para não ter que procurar novamente lá dentro
                redistribuirConcatenar(no,info);
            }
        }
    }


    private void redistribuirConcatenar(No no,int info) {
        int min = (int) Math.ceil(No.M / 2.0) - 1;
        No pai, irmaE = null, irmaD = null;
        No folha = no;
        //achar o pai e sua pos
        pai = localizarpai(folha, info);
        //achar a pos de pai
        int posPai = pai.localizarpos(folha.getvInfo(0));
        int pos = folha.localizarpos(folha.getvInfo(0));
        //as irmas sao nulas?
        if (posPai - 1 >= 0) {
            irmaE = pai.getvLig(posPai - 1);
        }
        if (posPai + 1 <= pai.getTL()) {
            irmaD = pai.getvLig(posPai + 1);
        }
        if(folha.getvLig(0) == null){//eh folha?

            if (irmaE != null && irmaE.getTL() > min) {//redistribuicao com irmao da esquerda
                folha.remanejar(0);
                folha.setvInfo(0, irmaE.getvInfo(irmaE.getTL() - 1));
                folha.setvPos(0, irmaE.getvPos(irmaE.getTL() - 1));
                folha.setTL(folha.getTL() + 1);

                irmaE.remanejarExclusao(irmaE.getTL()-1);
                irmaE.setTL(irmaE.getTL() - 1);

                pai.setvInfo(posPai-1,folha.getvInfo(0));
                pai.setvPos(posPai-1,folha.getvPos(0));
            } else if (irmaD != null && irmaD.getTL() > min) {//redistribuicao com a irma da direit

                folha.setvInfo(folha.getTL(),irmaD.getvInfo(0));
                folha.setvPos(folha.getTL(), irmaD.getvPos(0));
                folha.setTL(folha.getTL() + 1);

                irmaD.remanejarExclusao(0);
                irmaD.setTL(irmaD.getTL() - 1);

                pai.setvInfo(posPai,irmaD.getvInfo(0));
                pai.setvPos(posPai,irmaD.getvPos(0));
            }else{//concatenacao
                if (irmaE != null) {


                    for (int i = 0; i < folha.getTL(); i++) {
                        irmaE.setvInfo(irmaE.getTL(), folha.getvInfo(i));
                        irmaE.setvPos(irmaE.getTL(), folha.getvPos(i));
                        irmaE.setTL(irmaE.getTL() + 1);
                    }
                    irmaE.setProx(folha.getProx());
                    if (folha.getProx() != null) {
                        folha.getProx().setant(irmaE);
                    }
                    pai.remanejarExclusao(posPai-1);
                    pai.setvLig(posPai-1,irmaE);
                    pai.setTL(pai.getTL() - 1);


                }else if(irmaD != null){
                    irmaD.remanejar(0);
                    irmaD.setvInfo(0, pai.getvInfo(posPai));
                    irmaD.setvPos(0, pai.getvPos(posPai));


                    for (int i = 0; i < folha.getTL(); i++) {
                        irmaD.setvInfo(i,folha.getvInfo(i));
                        irmaD.setvPos(i, folha.getvPos(i));
                        irmaD.setTL(irmaD.getTL() + 1);
                    }
                    if (folha.getProx() != null) {
                        folha.getProx().setant(irmaD);
                    }
                    pai.remanejarExclusao(posPai);
                    pai.setTL(pai.getTL() - 1);

                }
            }
        }else{//no nao eh folha
            if (irmaE != null && irmaE.getTL() > min) {//redistribuicao com irmao da esquerda
                folha.remanejar(0);
                folha.setvInfo(0, pai.getvInfo(posPai));
                folha.setvPos(0, pai.getvPos(posPai));
                folha.setTL(folha.getTL() + 1);

                pai.setvInfo(posPai,irmaE.getvInfo(irmaE.getTL()-1));
                pai.setvPos(posPai,irmaE.getvPos(irmaE.getTL()-1));

                folha.setvLig(0,irmaE.getvLig(irmaE.getTL()));

                irmaE.setTL(irmaE.getTL()-1);

            } else if (irmaD != null && irmaD.getTL() > min) {//redistribuicao com a irma da direita
                folha.setvInfo(folha.getTL(),pai.getvInfo(posPai));
                folha.setvPos(folha.getTL(), pai.getvPos(posPai));
                folha.setTL(folha.getTL() + 1);

                pai.setvInfo(pos, irmaD.getvInfo(0));
                pai.setvPos(pos, irmaD.getvPos(0));
                folha.setvLig(folha.getTL()+1,irmaD.getvLig(0));
                irmaD.setTL(irmaD.getTL()-1);



            }else{//concatenacao
                if(irmaE != null){
                    folha.remanejar(0);
                    folha.setvInfo(0, pai.getvInfo(posPai-1));
                    folha.setvPos(0, pai.getvPos(posPai-1));
                    folha.setTL(folha.getTL() + 1);
                    for (int i =  folha.getTL()-1; i >= 0; i--) {
                        folha.remanejar(0);
                        folha.setvInfo(0, irmaE.getvInfo(i));
                        folha.setvPos(0, irmaE.getvPos(i));
                        folha.setvLig( 1, irmaE.getvLig(i + 1));
                        folha.setTL(folha.getTL() + 1);
                    }

                    folha.setvLig(0,irmaE.getvLig(0));
                    pai.remanejarExclusao(posPai-1);
                    pai.setTL(pai.getTL() - 1);

                    irmaE.setProx(folha.getProx());


                }else if(irmaD != null){
                    irmaD.remanejar(0);
                    irmaD.setvInfo(0, pai.getvInfo(posPai));
                    irmaD.setvPos(0, pai.getvPos(posPai));
                    irmaD.setTL(irmaD.getTL() + 1);

                    for (int i = 0; i < folha.getTL(); i++) {
                        irmaD.remanejar(0);
                        irmaD.setvInfo(i,folha.getvInfo(i));
                        irmaD.setvPos(i, folha.getvPos(i));
                        irmaD.setvLig(1, folha.getvLig(i + 1));
                        irmaD.setTL(irmaD.getTL() + 1);
                    }

                    irmaD.setvLig(0, folha.getvLig(0));
                    pai.remanejarExclusao(posPai);
                    pai.setTL(pai.getTL() - 1);
                }
            }
        }

        if (pai == raiz && pai.getTL() == 0) {
            if (irmaE != null)
                raiz = irmaE;
            else
                raiz = irmaD;
        } else if (pai != raiz && pai.getTL() < min) {
            redistribuirConcatenar(pai,info);
        }

    }


    public void exibir() {
        No aux = this.raiz;
        if(aux!=null) {
            while (aux.getvLig(0) != null)
                aux = aux.getvLig(0);
            while (aux != null) {
                for (int i = 0; i < aux.getTL(); i++)
                    System.out.println(aux.getvInfo(i) + " ");
                aux = aux.getProx();
            }
        }
        else{
            System.out.println("[arvore vazia]");
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
