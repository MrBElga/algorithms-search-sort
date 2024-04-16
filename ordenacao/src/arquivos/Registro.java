package arquivos;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Registro {

    public final int tf = 1022;
    private int codigo;
    private char lixo[] = new char[tf];

    public Registro() {
    }

    public Registro(int codigo) {
        this.codigo = codigo;
        for (int i = 0; i < tf; i++)
            lixo[i] = 'X';
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return new String(lixo);
    }

    public void gravaNoArq(RandomAccessFile arquivo) {
        try {
            arquivo.writeInt(codigo);
            for (int i = 0; i < tf; i++)
                arquivo.writeChar(lixo[i]);
        } catch (IOException e) {
        }
    }

    public void leDoArq(RandomAccessFile arquivo) {
        try {
            codigo = arquivo.readInt();
            for (int i = 0; i < tf; i++)
                lixo[i] = arquivo.readChar();

        } catch (IOException e) {
        }
    }

    public void exibirReg() {
        System.out.println("Código: " + codigo);
    }

    static int length() {
        return 2048;
    }
}
