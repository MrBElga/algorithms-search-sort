package Bplus;

public class No{
    public static final int M = 5 ;//4 ;
    //private int M = 4;
    private int[] vInfo;
    private int[] vPos;
    private No[] vLig;
    private int TL;
    private No ant;
    private No prox;

    public No() {
        this.vInfo = new int[M];
        this.vPos = new int[M];
        this.vLig = new No[M + 1];
        this.ant = this.prox = null;
        this.TL = 0;
    }

    public No(int vInfo) {
        this.vInfo = new int[M];
        this.vPos = new int[M];
        this.vLig = new No[M + 1];
        this.ant = this.prox = null;
        this.TL = 0;
        this.vInfo[this.TL++] = vInfo;
    }

    public int getvInfo(int pos) {
        return this.vInfo[pos];
    }

    public void setvInfo(int pos, int vInfo) {
        this.vInfo[pos] = vInfo;
    }

    public int getvPos(int pos) {
        return this.vPos[pos];
    }

    public void setvPos(int pos, int vPos) {
        this.vPos[pos] = vPos;
    }

    public No getvLig(int pos) {
        return vLig[pos];
    }

    public void setvLig(int pos, No vLig) {
        this.vLig[pos] = vLig;
    }

    public int getTL() {
        return TL;
    }

    public void setTL(int TL) {
        this.TL = TL;
    }

    public No getant() {
        return ant;
    }

    public void setant(No ant) {
        this.ant = ant;
    }

    public No getProx() {
        return prox;
    }

    public void setProx(No prox) {
        this.prox = prox;
    }

    public void remanejar(int pos) {
        vLig[TL+1] = vLig[TL];
        for(int j=TL; j>pos; j--)
        {
            vInfo[j] = vInfo[j-1];
            vPos[j] = vPos[j-1];
            vLig[j] = vLig[j-1];
        }
    }

    public void remanejarExclusao(int pos) {
        for (int i = pos; i < TL; i++) {
            vInfo[i] = vInfo[i + 1];
            vPos[i] = vPos[i + 1];
            vLig[i] = vLig[i + 1];
        }
        vLig[TL - 1] = vLig[TL];
    }

    public int localizarpos(int Info) {
        int pos = 0;
        while (pos < TL && vInfo[pos] < Info)
            pos++;
        return pos;
    }


    public int localizarposFolha(int Info) {
        int pos = 0;
        while (pos < TL && vInfo[pos] <= Info)
            pos++;
        return pos;
    }

}

