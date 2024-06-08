package NArea;

public class No {
    private int[] vInfo;
    private No[]vLig;
    private int TL;

    public No() {
    }

    public No(int info) {
        this.vInfo = new int [Main.N-1];
        this.vLig = new No[Main.N];
        this.vInfo[0] = info;
        this.TL = 1;
    }

    public int getvInfo(int p) {
        return vInfo[p];
    }

    public void setvInfo(int p, int info) {
        this.vInfo[p] = info;
    }

    public No getvLig(int p) {
        return vLig[p];
    }

    public void setvLig(int p, No lig) {
        this.vLig[p] = lig;
    }

    public int getTL() {
        return TL;
    }

    public void setTL(int TL) {
        this.TL = TL;
    }

    public int buscarPos(int info){
        int pos = 0;
        while(pos < TL && info > vInfo[pos])
            pos++;
        return pos;
    }

    public void remanejar(int pos){
        for(int i = TL;i > pos;i--){
            vInfo[i] = vInfo[i-1];
        }
    }
}
