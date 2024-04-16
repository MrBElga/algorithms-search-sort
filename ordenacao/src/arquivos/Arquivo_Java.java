package arquivos;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.*;
import java.util.Random;
import java.io.PrintWriter;

//... classe Arquivo (onde vai estar o m�todo para ordernar, etc) ....
public class Arquivo_Java {
    public final int  contReg = 1022;
    private Path path;
    private String nomearquivo;
    public RandomAccessFile arquivo;
    private int movimentos, comparacoes;
    private PrintWriter writer;



    public Arquivo_Java(String nomearquivo) {
        //System.out.println(nomearquivo);
        try {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
            arquivo.setLength(0);
        } catch (IOException e) {
            arquivo = null;
            this.nomearquivo = "";
        }
        iniciaComparacoes();
        iniciarMovimentos();
    }






    public void truncate(long pos) //desloca eof
    {
        try {
            arquivo.setLength(pos * Registro.length());
        } catch (IOException exc) {
        }
    }

    //semelhante ao feof() da linguagem C
    //verifica se o ponteiro esta no <EOF> do arquivo
    public boolean eof() {
        boolean retorno = false;
        try {
            if (arquivo.getFilePointer() == arquivo.length())
                retorno = true;
        } catch (IOException e) {
        }
        return (retorno);
    }
    public int filesize(){

        try {

            return (int)arquivo.length()/Registro.length();
        }catch (IOException e){
            return 0;
        }
    }

    private void fecharArquivo() {
        if (writer != null) {
            writer.close();
        }
    }
    //insere um Registro no final do arquivo, passado por par�metro
    public void inserirRegNoFinal(Registro reg) {
        seekArq(filesize());//ultimo byte
        reg.gravaNoArq(arquivo);
    }

    public void exibirArq() {
        int i;
        Registro aux = new Registro();
        seekArq(0);
        i = 0;
        while (!this.eof()) {

            aux.leDoArq(arquivo);
            aux.exibirReg();

            i++;
        }
    }

    public void exibirUmRegistro(int pos) {
        Registro aux = new Registro();
        seekArq(pos);
        System.out.println("Posicao " + pos);
        aux.leDoArq(arquivo);
        aux.exibirReg();
    }

    public void seekArq(int pos) {
        try {
            arquivo.seek(pos * Registro.length());
        } catch (IOException e) {
        }
    }

    public void leArq() {
        int codigo;

        codigo = Entrada.leInteger("Digite o c�digo");
        while (codigo != 0) {
            codigo = Entrada.leInteger("Digite a idade");
        }
    }


    public RandomAccessFile getFile() {
        return arquivo;
    }
    public void geraArquivoOrdenado() {
        int i;
        for (i = 0; i < contReg; i++) {
            Registro reg = new Registro(i);
            reg.gravaNoArq(arquivo);
        }
    }

    public void geraArquivoReverso() {
        int i;
        for (i = 0; i < contReg; i++) {
            Registro reg = new Registro(contReg - i - 1);
            reg.gravaNoArq(arquivo);
        }
    }

    public void geraArquivoRandomico() {
        Random rand = new Random();
        int i;
        for (i = 0; i < contReg; i++) {
            Registro reg1 = new Registro(rand.nextInt(contReg));
            reg1.gravaNoArq(arquivo);
        }
    }
    public void copiaArq(RandomAccessFile arquivoOrigem) {
        try {
            Registro reg = new Registro();
            int i = 0, tam = (int) arquivoOrigem.length() / Registro.length();
            this.arquivo = new RandomAccessFile("temp.dat", "rw");
            truncate(0);
            arquivoOrigem.seek(0);
            while (i < tam) {
                reg.leDoArq(arquivoOrigem);
                reg.gravaNoArq(arquivo);
                i++;
            }
        } catch (IOException e) {

        }

    }

    public int retQtdReg(){
        return contReg;
    }

    public void iniciaComparacoes(){
        this.comparacoes = 0;
    }
    public void iniciarMovimentos(){
        this.movimentos = 0;
    }

    public int getComparacoes(){
        return this.comparacoes;
    }

    public int getMovimentos(){
        return this.movimentos;
    }
    //.............................................................................
    /*

    insira aqui os m�todos de Ordena��o;

    */
    public void executa() {
        leArq();
        exibirArq();
    }

    public void insercaoDireta (){
        int tl = filesize();
        int pos;
        Registro aux = new Registro();
        Registro vet = new Registro();
        for (int i = 1; i < tl; i++) {
            pos = i;
            seekArq(i);
            aux.leDoArq(arquivo);
            seekArq(pos - 1);
            vet.leDoArq(arquivo);

            while (pos > 0 && aux.getCodigo() < vet.getCodigo()) {
                seekArq(pos);
                vet.gravaNoArq(arquivo);
                pos--;
                seekArq(pos - 1);
                vet.leDoArq(arquivo);
            }
            seekArq(pos);
            aux.gravaNoArq(arquivo);
        }

    }

    private int buscaBinaria(int elemento, int tam){
        int ini = 0, fim = tam - 1, meio = fim/2;
        Registro reg = new Registro();

        seekArq(meio);
        reg.leDoArq(arquivo);
        this.comparacoes++;
        while (meio != ini && elemento != reg.getCodigo()) {
            this.comparacoes++;
            if (elemento < reg.getCodigo())
                fim = meio;
            else
                ini = meio;

            meio = (ini + fim) / 2;
            seekArq(meio);
            reg.leDoArq(arquivo);
            this.comparacoes++;
        }
        this.comparacoes++;
        seekArq(tam - 1);
        reg.leDoArq(arquivo);
        if(elemento > reg.getCodigo())
            return tam;

        this.comparacoes++;
        seekArq(meio);
        reg.leDoArq(arquivo);
        if(elemento>reg.getCodigo())
            return meio + 1;
        return meio;
    }

    public void insercaoBinaria(){
        int fim, i, tam = filesize(), pos, j;
        //reg1 é o do meio e o 2 auxiliar
        Registro reg1 = new Registro(), reg2 = new Registro();
        for(i = 1 ; i < tam; i++){
            seekArq(i);
            reg2.leDoArq(arquivo);
            fim = i;
            pos = buscaBinaria(reg2.getCodigo(), fim);
            for(j = i; j > pos; j--){
                this.movimentos++;
                seekArq(j - 1);
                reg1.leDoArq(arquivo);
                reg1.gravaNoArq(arquivo);
            }
            if(pos != i){
                this.movimentos++;
                seekArq(pos);
                reg2.gravaNoArq(arquivo);
            }
        }
    }
    public void selecaoDireta() {
        int tl = filesize();
        int posMenor;
        Registro menor= new Registro(),vet = new Registro();
        for (int i = 0; i < tl -1; i++) {
            seekArq(i);
            menor.leDoArq(arquivo);
            posMenor = i;
            for (int j = i+1; j < tl; j++) {
                seekArq(j);
                vet.leDoArq(arquivo);
                if(menor.getCodigo()>vet.getCodigo()){
                    seekArq(j);
                    menor.leDoArq(arquivo);
                    posMenor = j;
                }
            }
            seekArq(i);
            vet.leDoArq(arquivo);
            seekArq(posMenor);
            vet.gravaNoArq(arquivo);
            seekArq(i);
            menor.gravaNoArq(arquivo);
        }

    }
    public void bolha(){
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        int tam = filesize();
        boolean troca = true;
        while (tam > 1 && troca){
            troca = false;
            for (int i = 0; i < tam - 1; i++) {
                seekArq(i);
                reg1.leDoArq(arquivo);
                reg2.leDoArq(arquivo);
                this.comparacoes++;
                if (reg1.getCodigo() > reg2.getCodigo()){
                    troca = true;
                    seekArq(i);
                    this.movimentos++;
                    reg2.gravaNoArq(arquivo);
                    this.movimentos++;
                    reg1.gravaNoArq(arquivo);
                }
            }
            tam--;
        }
    }

    public void shake(){
        int fn = filesize() - 1, in = 0, i;
        Registro reg1 = new Registro(), reg2 = new Registro();
        while (in < fn){
            for (i = in; i < fn; i++) {
                seekArq(i);
                reg1.leDoArq(arquivo);
                reg2.leDoArq(arquivo);
                this.comparacoes++;
                if (reg1.getCodigo() > reg2.getCodigo()) {
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    reg1.gravaNoArq(arquivo);
                    this.movimentos += 2;
                }
            }
            fn--;
            for (i = fn; i > in; i--) {
                seekArq(i - 1);
                reg1.leDoArq(arquivo);
                reg2.leDoArq(arquivo);
                this.comparacoes++;
                if (reg2.getCodigo() < reg1.getCodigo()) {
                    seekArq(i - 1);
                    reg2.gravaNoArq(arquivo);
                    reg1.gravaNoArq(arquivo);
                    this.movimentos += 2;
                }
            }
            in ++;
        }
    }

    public void shell(){
        int h=1, i,j,k, tam = filesize();
        Registro reg1 = new Registro(), reg2 = new Registro();
        while (h < tam) {
            h = h * 3 + 1;
        }

        for( h = h / 3; h > 0 ;   h = h / 3){
            for (i = 0; i < h ; i++) {
                for (j = 0; j + h < tam ; j=j+h) {
                    seekArq(j);
                    reg1.leDoArq(arquivo);
                    seekArq(j + h);
                    reg2.leDoArq(arquivo);
                    this.comparacoes++;
                    if (reg1.getCodigo() > reg2.getCodigo()) {
                        this.movimentos += 2;
                        seekArq(j);
                        reg2.gravaNoArq(arquivo);
                        seekArq(j + h);
                        reg1.gravaNoArq(arquivo);
                        if (j - h >= i) {
                            seekArq(j - h);
                            reg1.leDoArq(arquivo);
                            this.comparacoes++;
                            for (k = j; k - h >= i && reg2.getCodigo() < reg1.getCodigo();) {
                                this.movimentos++;
                                seekArq(k);
                                reg1.gravaNoArq(arquivo);
                                k -= h;
                                if (k - h >= i) {
                                    seekArq(k - h);
                                    reg1.leDoArq(arquivo);
                                }
                            }
                            this.movimentos++;
                            seekArq(k);
                            reg2.gravaNoArq(arquivo);
                        }
                    }
                }
            }
        }
    }

    public void troca(int i, int j) {
        Registro temp = new Registro();
        Registro rj = new Registro();
        seekArq(i);
        temp.leDoArq(arquivo);
        seekArq(j);
        rj.leDoArq(arquivo);
        seekArq(i);
        rj.gravaNoArq(arquivo);
        seekArq(j);
        temp.gravaNoArq(arquivo);
    }
    public void heap() {
        Registro vet1 = new Registro();
        Registro vet2 = new Registro();
        int fd, fe, maiorf, pai;
        int tam = filesize();

        while (tam> 1) {
            pai = tam / 2 - 1;
            while (pai >= 0) {
                fe = 2 * pai + 1;
                fd = fe + 1;
                maiorf = fe;

                seekArq(fe);
                vet1.leDoArq(arquivo);
                seekArq(fd);
                vet2.leDoArq(arquivo);

                this.comparacoes++;
                if (fd < tam && vet1.getCodigo() < vet2.getCodigo()) {
                    maiorf = fd;
                }

                seekArq(maiorf);
                vet1.leDoArq(arquivo);
                seekArq(pai);
                vet2.leDoArq(arquivo);

                this.comparacoes++;
                if (vet1.getCodigo() > vet2.getCodigo()) {
                    this.movimentos+= 2;
                    troca(pai, maiorf);
                }
                pai--;
            }
            this.movimentos+= 2;
            troca(0, tam - 1);
            tam--;
        }
    }


    private void quickSp(int ini, int fm){
        int i = ini, j = fm;
        Registro reg1 = new Registro(), reg2 = new Registro();

        while (i < j) {
            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(j);
            reg2.leDoArq(arquivo);

            while (i < j && reg1.getCodigo() <= reg2.getCodigo()) {
                this.comparacoes++;
                i++;
                seekArq(i);
                reg1.leDoArq(arquivo);
            }

            if (i < j) {
                this.movimentos += 2;
                seekArq(i);
                reg2.gravaNoArq(arquivo);
                seekArq(j);
                reg1.gravaNoArq(arquivo);
            }
            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(j);
            reg2.leDoArq(arquivo);

            while (j > i && reg2.getCodigo() >= reg1.getCodigo()) {
                this.comparacoes++;
                j--;
                seekArq(j);
                reg2.leDoArq(arquivo);
            }

            if (i < j) {
                this.movimentos += 2;
                seekArq(i);
                reg2.gravaNoArq(arquivo);
                seekArq(j);
                reg1.gravaNoArq(arquivo);
            }
        }

        if (ini < j - 1)
            quickSp(ini, j - 1);

        if (fm > i + 1)
            quickSp(j + 1, fm);
    }

    public void quick_sp(){
        quickSp(0, filesize() - 1);
    }

    private void quickCp(int ini, int fm){
        int i = ini, j = fm, pivo;
        Registro reg = new Registro(), reg2 = new Registro();

        seekArq((i+j)/2);
        reg.leDoArq(arquivo);
        pivo = reg.getCodigo();
        while (i < j) {
            seekArq(i);
            reg.leDoArq(arquivo);
            this.comparacoes++;
            while (reg.getCodigo() < pivo) {
                i++;
                seekArq(i);
                reg.leDoArq(arquivo);
                this.comparacoes++;
            }

            seekArq(j);
            reg2.leDoArq(arquivo);
            this.comparacoes++;
            while (reg2.getCodigo() > pivo) {
                j--;
                seekArq(j);
                reg2.leDoArq(arquivo);
                this.comparacoes++;
            }

            if (i <= j) {
                if (i != j) {
                    this.movimentos += 2;
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    seekArq(j);
                    reg.gravaNoArq(arquivo);
                }

                i++;
                j--;
            }
        }
        if (ini < j)
            quickCp(ini, j);
        if (i < fm)
            quickCp(i, fm);
    }
    public void quick_cp(){
        quickCp(0, filesize() - 1);
    }

    //quick usando pilha
    private void quickPilha(){

    }

    public void quickpilha(){

    }



    private void particao(Arquivo_Java arq1, Arquivo_Java arq2, int tam) {
        arq1 = new Arquivo_Java("arq1.dat");
        arq2 = new Arquivo_Java("arq2.dat");
        Registro reg1 = new Registro(), reg2 = new Registro();
        int meio = tam/2;
        for(int i = 0; i<meio;i++){

            seekArq(i);
            reg1.leDoArq(arquivo);
            arq1.seekArq(i);
            reg1.gravaNoArq(arq1.getFile());

            seekArq(i+meio);
            reg2.leDoArq(arquivo);
            arq2.seekArq(i);
            reg2.gravaNoArq(arq2.getFile());
        }
    }

    private void fusao(Arquivo_Java arq1, Arquivo_Java arq2, int seq, int tam) {
        int i = 0, j = 0, k = 0, aSeq = seq;
        Registro reg1 = new Registro(),reg2 = new Registro();

        while (k < tam) {
            while (i < aSeq && j < aSeq) {
                this.comparacoes++;
                arq1.seekArq(i);
                reg1.leDoArq(arq1.getFile());

                arq2.seekArq(j);
                reg2.leDoArq(arq2.getFile());
                if (reg1.getCodigo() < reg2.getCodigo()){
                    this.movimentos++;
                    seekArq(k);
                    reg1.gravaNoArq(arquivo);
                    i++;
                    k++;
                }else{
                    this.movimentos++;
                    seekArq(k);
                    reg2.gravaNoArq(arquivo);
                    j++;
                    k++;
                }

            }

            while (i < aSeq) {
                this.movimentos++;

                arq1.seekArq(i);
                reg1.leDoArq(arq1.getFile());
                seekArq(k);
                reg1.gravaNoArq(arquivo);
                i++;
                k++;
            }
            while (j < aSeq) {
                this.movimentos++;

                arq2.seekArq(j);
                reg2.leDoArq(arq2.getFile());
                seekArq(k);
                reg2.gravaNoArq(arquivo);
                j++;
                k++;
            }

            aSeq += seq;

        }
    }

    public void merge1() {
        int tam = filesize(), seq = 1;
        Arquivo_Java arq1 = new Arquivo_Java("arq1.dat"), arq2 = new Arquivo_Java("arq2.dat");

        while (seq < tam) {
            particao(arq1, arq2, tam);
            fusao(arq1, arq2, seq, tam);

            seq *= 2;
        }

    }

    private void fusao2(Arquivo_Java arq, int ini1, int fim1, int ini2, int fim2) {
        Registro reg1 = new Registro(), reg2 = new Registro();
        int i = ini1, j = ini2, k = ini1;

        while (i <= fim1 && j <=fim2) {
            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(j);
            reg2.leDoArq(arquivo);

            this.comparacoes++;
            if (reg1.getCodigo() < reg2.getCodigo()) {
                arq.seekArq(k);
                reg1.gravaNoArq(arq.getFile());
                i++;
            } else {
                arq.seekArq(k);
                reg2.gravaNoArq(arq.getFile());
                j++;
            }
            k++;
        }

        while (i <=fim1) {
            seekArq(i);
            reg1.leDoArq(arquivo);
            arq.seekArq(k);
            reg1.gravaNoArq(arq.getFile());
            i++;
            k++;
        }
        while (j <= fim2) {
            seekArq(j);
            reg2.leDoArq(arquivo);
            arq.seekArq(k);
            reg2.gravaNoArq(arq.getFile());
            j++;
            k++;
        }

        seekArq(0);
        for (i = 0; i < k; i++) {
            this.movimentos++;
            arq.seekArq(i);
            reg1.leDoArq(arq.getFile());
            seekArq(i);
            reg1.gravaNoArq(arquivo);
        }

    }

    private void merge_2(Arquivo_Java arq, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            merge_2(arq, esq, meio);
            merge_2(arq, meio + 1, dir);
            fusao2(arq, esq, meio, meio + 1, dir);
           //exibirArq();
        }
    }
    public void merge2(){
        Arquivo_Java arq = new Arquivo_Java("arq1.dat");
        int tam = filesize();
        merge_2(arq,0,tam-1);

    }

    public void counting(){
        int range = contReg, tam = filesize(), i;
        Registro reg = new Registro();
        Registro [] auxB = new Registro[tam];

        int [] cont = new int[range];

        for (i = 0; i < tam; i++) {
            seekArq(i);
            reg.leDoArq(arquivo);
            cont[reg.getCodigo()]++;
        }

        for (i = 0; i < range - 1; i++)
            cont[i + 1] += cont[i];

        for (i = tam - 1; i >= 0; i--) {
            seekArq(i);
            reg.leDoArq(arquivo);
            auxB[--cont[reg.getCodigo()]] = reg;
            reg = new Registro();
        }
        seekArq(0);
        for (i = 0; i < tam; i++) {
            this.movimentos++;
            auxB[i].gravaNoArq(arquivo);
        }
    }

    private void ordBucket(Registro[] baldes, int n) {
        // Implementação básica do algoritmo de ordenação dentro de cada balde
        // Você pode substituir por um algoritmo de ordenação mais eficiente, se desejar
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n - i; j++) {
                if (baldes[j - 1].getCodigo() > baldes[j].getCodigo()) {
                    Registro temp = baldes[j - 1];
                    baldes[j - 1] = baldes[j];
                    baldes[j] = temp;
                }
            }
        }
    }



    public void bucket() {
        Registro reg1 = new Registro();
        seekArq(0);
        reg1.leDoArq(arquivo);
        int maior = reg1.getCodigo(), i, index;
        int[] pos = new int[filesize()];
        for (i = 1; i < filesize(); i++) {
            seekArq(i);
            reg1.leDoArq(arquivo);
            this.comparacoes++;
            maior = Math.max(maior, reg1.getCodigo());
        }
        Arquivo_Java[] baldes = new Arquivo_Java[filesize()];
        for (i = 0; i < filesize(); i++) {
            baldes[i] = new Arquivo_Java("balde" + i + ".dat");
        }
        for (i = 0; i < filesize(); i++) {
            seekArq(i);
            reg1.leDoArq(arquivo);
            index = (reg1.getCodigo() * filesize()) / (maior + 1);
            baldes[index].seekArq(pos[index]++);
            reg1.gravaNoArq(baldes[index].arquivo);
            this.movimentos++;
        }
        for (i = 0; i < filesize(); i++) {
            if (pos[i] != 0) {
                baldes[i].insercaoDireta();
            }
        }
        i = 0;
        int j = 0;
        while (i < filesize()) {
            baldes[j].seekArq(0);
            while (j < filesize() && !baldes[j].eof()) {
                reg1.leDoArq(baldes[j].arquivo);
                seekArq(i++);
                reg1.gravaNoArq(arquivo);
                this.movimentos++;
            }
            j++;
        }
        for (i = 0; i < filesize(); i++) {
            try {
                baldes[i].arquivo.close();
            } catch (Exception e) {}
            new File("balde" + i + ".dat").delete();
        }
    }

    public void radix() {
        int i, j, max = contReg, tam = filesize();
        Registro reg, vet_aux[] = new Registro[tam];
        int count[];

        seekArq(0);
        for (i = 1; i < max; i *= 10) {
            count = new int[10];

            seekArq(0);
            for (j = 0; j < tam; j++) {
                reg = new Registro();
                reg.leDoArq(arquivo);
                count[(reg.getCodigo() / i) % 10]++;
            }

            for (j = 0; j < 9; j++)
                count[j + 1] += count[j];

            for (j = tam - 1; j >= 0; j--) {
                reg = new Registro();
                seekArq(j);
                reg.leDoArq(arquivo);
                vet_aux[--count[(reg.getCodigo() / i) % 10]] = reg;
            }

            seekArq(0);
            for (j = 0; j < tam; j++) {
                this.movimentos++;
                vet_aux[j].gravaNoArq(arquivo);
            }
        }
    }

    public void comb() {
        int i = 0, tam = filesize(), fator = (int) (tam / 1.3);
        Registro reg1 = new Registro(), reg2 = new Registro();

        while (fator > 0 && i != tam - 1) {
            i = 0;
            while (i + fator < tam) {
                seekArq(i);
                reg1.leDoArq(arquivo);
                seekArq(fator + i);
                reg2.leDoArq(arquivo);

                this.comparacoes++;
                if (reg1.getCodigo() > reg2.getCodigo()) {
                    this.movimentos += 2;
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    seekArq(fator + i);
                    reg1.gravaNoArq(arquivo);
                }
                i++;
            }
            fator = (int) (fator / 1.3);
        }
    }

    public void gnome() {
        int i = 0, tam = filesize(), j;
        Registro reg1 = new Registro(), reg2 = new Registro();

        for (i = 0; i < tam - 1; i++) {
            seekArq(i);
            reg1.leDoArq(arquivo);
            reg2.leDoArq(arquivo);

            this.comparacoes++;
            if (reg1.getCodigo() > reg2.getCodigo()) {
                j = i;

                this.comparacoes++;
                while (j >= 0 && reg2.getCodigo() < reg1.getCodigo()) {
                    this.movimentos += 2;
                    seekArq(j);
                    reg2.gravaNoArq(arquivo);
                    reg1.gravaNoArq(arquivo);

                    j--;
                    if (j >= 0) {
                        seekArq(j);
                        reg1.leDoArq(arquivo);
                        reg2.leDoArq(arquivo);
                        this.comparacoes++;
                    }
                }
            }
        }
    }

    public void insercao_Tim(int inicio, int fim) {
        Registro auxReg = new Registro();
        Registro reg = new Registro();
        Registro regPos = new Registro();
        int TL, i = inicio + 1;
        if (fim == -1)
            TL = filesize();
        else
            TL = fim;

        while (i < TL) {
            seekArq(i);
            auxReg.leDoArq(arquivo);
            int pos = buscaBinaria(auxReg.getCodigo(), i);

            for (int j = i; j > pos; j--) {
                seekArq(j - 1);
                reg.leDoArq(arquivo);
                regPos.leDoArq(arquivo);
                seekArq(j - 1);
                regPos.gravaNoArq(arquivo);
                reg.gravaNoArq(arquivo);
            }
            i++;
        }
    }

    private void fusaoTim(Arquivo_Java aux, int ini1, int fim1, int ini2, int fim2) {
        int k = 0;
        int i = ini1;
        int j = ini2;
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        aux.seekArq(0);

        while (i <= fim1 && j <= fim2) {
            seekArq(i);
            reg1.leDoArq(arquivo);
            seekArq(j);
            reg2.leDoArq(arquivo);

            this.comparacoes++;
            if (reg1.getCodigo() < reg2.getCodigo()) {
                seekArq(k++);
                reg1.gravaNoArq(aux.getFile());
                i++;
            } else {
                seekArq(k++);
                reg2.gravaNoArq(aux.getFile());
                j++;
            }
        }

        while (i <= fim1) {
            seekArq(i++);
            reg1.leDoArq(arquivo);

            seekArq(k++);
            reg1.gravaNoArq(aux.getFile());
        }

        while (j <= fim2) {
            seekArq(j++);
            reg2.leDoArq(arquivo);

            seekArq(k++);
            reg2.gravaNoArq(aux.getFile());
        }

        aux.seekArq(0);
        for (i = 0; i < k; i++) {
            seekArq(i + ini1);
            this.movimentos++;
            reg1.leDoArq(aux.getFile());
            reg1.gravaNoArq(arquivo);
        }
        aux.truncate(0);
    }

    public void tim() {
        int tam = filesize();
        int rodaTF = contReg;
        int dir, meio;
        Arquivo_Java aux = new Arquivo_Java("auxMerge.dat");

        aux.truncate(tam);

        for (int i = 0; i < tam; i += rodaTF) {
            if (i + rodaTF < tam)
                insercao_Tim(i, i + rodaTF);
            else
                insercao_Tim(i, tam);
        }

        for (int i = rodaTF; i < tam; tam *= 2)
            for (int esq = 0; esq < tam; esq += 2 * tam) {
                if (esq + 2 * tam < tam)
                    dir = esq + 2 * tam - 1;
                else
                    dir = tam - 1;
                meio = (esq + dir) / 2;

                fusaoTim(aux, esq, meio, meio + 1, dir);
            }
    }
}