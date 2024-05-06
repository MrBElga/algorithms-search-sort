package BTreep;

public class BTree {
    private final int M = 2;
    private No raiz;

    public BTree(){
        this.raiz = null;
    }

    public No getRaiz(){
        return raiz;
    }

    public No andarFolha(int info){
        No folha = raiz;
        int pos;
        while(folha.getvLig(0)!=null){
            pos = folha.buscarPos(info);
            folha = folha.getvLig(pos);
        }
        return folha;
    }
    public No buscaPai(No folha,int info){
        No pai = raiz;
        No no = raiz;
        int pos;
        while(no!=folha){
            pai = no;
            pos = no.buscarPos(info);
            no = no.getvLig(pos);
        }
        return pai;
    }

    public void split(No folha,No pai){
        No Cx1 = new No(), Cx2 = new No();

        for (int i = 0; i < M ; i++) {
            Cx1.setvInfo(i,folha.getvInfo(i));
            Cx1.setvPos(i,folha.getvPos(i));
            Cx1.setvLig(i,folha.getvLig(i));
        }
        Cx1.setvLig(M,folha.getvLig(M));
        Cx1.setTL(M);

        for (int i = M+1; i < 2*M+1 ; i++) {
            Cx2.setvInfo(i-(M+1),folha.getvInfo(i));
            Cx2.setvPos(i-(M+1),folha.getvPos(i));
            Cx2.setvLig(i-(M+1),folha.getvLig(i));
        }
        Cx2.setvLig(M,folha.getvLig(2*M+1));
        Cx2.setTL(M);

        if(folha == pai){
            folha.setvInfo(0,folha.getvInfo(M));
            folha.setvPos(0,folha.getvPos(M));
            folha.setTL(1);
            folha.setvLig(0,Cx1);
            folha.setvLig(1,Cx2);
        }
        else{
            int pos = pai.buscarPos(folha.getvInfo(M));
            pai.setvInfo(pos,folha.getvInfo(M));
            pai.remanejar(pos);
            pai.setvPos(pos,folha.getvPos(M));
            pai.setvLig(pos,Cx1);
            pai.setvLig(pos+1,Cx2);
            pai.setTL(pai.getTL()+1);
            if(pai.getTL() > 2*M){
                folha = pai;
                pai = buscaPai(folha, folha.getvInfo(pos));
                split(folha,pai);
            }
        }
    }

    public void inserir(int info, int pos1){
        No folha,pai;
        int pos2;

        if(raiz == null){
            raiz = new No(info,pos1);
        }
        else{
            //vai até a folha
            folha = andarFolha(info);
            pos2 = folha.buscarPos(info);
            folha.remanejar(pos2);
            folha.setvInfo(pos2, info);
            folha.setvPos(pos2,pos1);
            folha.setTL(folha.getTL()+1);
            if(folha.getTL() > 2*M){
                pai = buscaPai(folha,info);
                split(folha,pai);
            }
        }
    }

    private No localizarNo(int info){
        return null;
    }

    private No localizaSubE(No no,int pos){
        no = no.getvLig(pos);
        while (no.getvLig(0) != null)
            no = no.getvLig(no.getTL());
        return  no;
    }

    private No localizaSubD(No no,int pos){
        no = no.getvLig(pos);
        while (no.getvLig(0) != null)
            no = no.getvLig(0);
        return  no;
    }


    public void excluir(int info){
        No no = localizarNo(info), subE, subD, folha;
        int pos;
        if(no!=null){
            pos = no.buscarPos(info);
            //se no não eh folha
            if(no.getvLig(0) != null){
                subE = localizaSubE(no,pos);
                subD = localizaSubD(no,pos+1);
                if(subE.getTL() > No.M || subD.getTL() == No.M){
                    no.setvInfo(pos, subE.getvInfo(subE.getTL()-1));
                    no.setvPos(pos, subE.getvInfo(subE.getTL()-1));
                    folha = subE;
                    pos = subE.getTL()-1;
                }
                else{
                    no.setvInfo(pos, subD.getvInfo(0));
                    no.setvPos(pos, subD.getvInfo(0));
                    folha = subD;
                    pos = 0;
                }
            }
            else{
                folha = no;
            }
            //exclusão na folha
            folha.remanejarExclusao(pos);
            folha.setTL(no.getTL()-1);

            if(folha == raiz && folha.getTL() ==0){
                raiz = null;
            }
            else if(folha!= raiz && folha.getTL() < No.M){
                redistribuirConcatenar(folha);
            }

        }
    }

    private void redistribuirConcatenar(No folha) {
        No pai = buscaPai(folha, folha.getvInfo(0));
        int posPai = pai.buscarPos(folha.getvInfo(0));
        No irmaE = null,irmaD=null;
        if(posPai - 1 >= 0){
            irmaE = pai.getvLig(posPai-1);
        }
        if(posPai+1 <= pai.getTL()){
            irmaD = pai.getvLig(posPai+1);
        }

        if(irmaE != null && irmaE.getTL() > No.M){
          folha.remanejar(0);
          folha.setvInfo(0,pai.getvInfo(posPai-1));
          folha.setvPos(0,pai.getvPos(posPai-1));
          folha.setTL(folha.getTL()+1);
          pai.setvInfo(posPai-1,irmaE.getvInfo(irmaE.getTL()-1));
          pai.setvPos(posPai-1,irmaE.getvPos(irmaE.getTL()-1));
          folha.setvLig(0,irmaE.getvLig(irmaE.getTL()));
          irmaE.setTL(irmaE.getTL()-1);


        } else if (irmaD != null && irmaD.getTL() > No.M) {//redistribuicao com a irma da direita
            folha.setvInfo(folha.getTL(), pai.getvInfo(posPai));
            folha.setvPos(folha.getTL(), pai.getvPos(posPai));
            folha.setTL(folha.getTL()+1);
            pai.setvInfo(posPai, irmaD.getvInfo(0));
            pai.setvPos(posPai, irmaD.getvPos(0));
            folha.setvLig(folha.getTL(), irmaD.getvLig(0));
            irmaD.remanejarExclusao(0);
            irmaD.setTL(irmaD.getTL()-1);
        } else{//concatenacao
            if(irmaE != null){
                irmaE.setvInfo(irmaE.getTL(), pai.getvInfo(posPai - 1));
                irmaE.setvPos(irmaE.getTL(), pai.getvPos(posPai-1));
                irmaE.setTL(irmaE.getTL()+1);
                pai.remanejarExclusao(posPai-1);
                pai.setTL(pai.getTL()-1);
                pai.setvLig(posPai-1, irmaE);
                for (int i = 0; i < folha.getTL() ; i++) {
                    irmaE.setvInfo(irmaE.getTL(), folha.getvInfo(i));
                    irmaE.setvPos(irmaE.getTL(), folha.getvPos(i));
                    irmaE.setvLig(irmaE.getTL(), folha.getvLig(i));
                    irmaE.setTL(irmaE.getTL()+1);
                }
                irmaE.setvLig(irmaE.getTL(),folha.getvLig(folha.getTL()));
            }
            else{

            }
            folha = pai;
            if(pai == raiz && pai.getTL() == 0){
                if(irmaE != null)
                    raiz = irmaE;
                else
                    raiz = irmaD;
            }
            else if(folha.getTL()<M)
                    redistribuirConcatenar(folha);

        }
    }

    public void inOrd(){
        inOrd(raiz);
    }
    private void inOrd(No raiz){
        if(raiz != null){
            for(int i = 0;i<raiz.getTL();i++){
                inOrd(raiz.getvLig(i));
                System.out.println(raiz.getvInfo(i));
            }
            inOrd(raiz.getvLig(raiz.getTL()));
        }
    }
}
