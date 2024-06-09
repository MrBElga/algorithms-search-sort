package Trie.arvore;

public class No {
    private boolean flag;
    private No vlig[];
    private char letras[];

    public No() {
        this.vlig = new No[26];
        this.letras = new char[26];
        this.flag = false;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public No getVlig(int pos) {
        return vlig[pos];
    }

    public void setVlig(int pos, No lig) {
        this.vlig[pos] = lig;
    }

    public char getLetras(int pos) {
        return letras[pos];
    }

    public void setLetras(char letra, int pos) {
        this.letras[pos] = letra;
    }
}
