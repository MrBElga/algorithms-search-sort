package Patricia;

import java.util.Objects;

public class No {
    public char[] letras;
    public String palavra;
    public int posLetra;
    public No[] vLig;

    public No(String palavra) {
        this.letras = new char[26];
        this.palavra = palavra;
        this.posLetra = 0;
        this.vLig = new No[26];
    }

    public char getLetras(int pos) {
        return letras[pos];
    }

    public void setLetras(int pos, char letra) {
        this.letras[pos] = letra;
    }

    public int getPosLetra() {
        return posLetra;
    }

    public void setPosLetra(int posLetra) {
        this.posLetra = posLetra;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public No getvLig(int pos) {
        return vLig[pos];
    }

    public void setvLig(int pos, No lig) {
        this.vLig[pos] = lig;
    }

    public No getProximaPalavra() {
        No proximaPalavra = null;
        if (Objects.equals(this.palavra, "")) {
            for (int i = 0; i < 26 && proximaPalavra == null; i++) {
                if (this.vLig[i] != null && !Objects.equals(this.vLig[i].getPalavra(), "")) {
                    proximaPalavra = this.vLig[i];
                }
            }
            return proximaPalavra;
        }
        return this;
    }

    public int filho() {
        for (int i = 0; i < 26; i++) {
            if (this.getvLig(i) != null) {
                return i; //se encontrar
            }
        }
        return -1; // Se nenhum filho for encontrado
    }


}

