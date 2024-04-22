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