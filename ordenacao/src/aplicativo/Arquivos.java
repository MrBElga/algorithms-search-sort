package aplicativo;
import arquivos.Arquivo_Java;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Arquivos {
    public static final int tfArq = 1022;
    private Arquivo_Java ordenado, reverso, randomico, auxrev, auxrand,auxOrd;
    private int tini, tfim, movimentos, comparacoes;
    private FileWriter text;
    private PrintWriter writer;

    public void InsercaoDireta() {

        auxOrd.copiaArq(ordenado.getFile());
        auxOrd.iniciaComparacoes();
        auxOrd.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxOrd.insercaoDireta();
        tfim = (int) System.currentTimeMillis();
        movimentos = auxOrd.getMovimentos();
        comparacoes = auxOrd.getComparacoes();
       // System.out.println(movimentos+" "+comparacoes);
        escreveArq("|Insercao Direta     |", comparacoes, tfArq - 1, movimentos, 3 * (tfArq - 1),
                tfim - tini);
        imprimirArquivos(ordenado,auxOrd,"InsercaoDireta" ,"ordenado");

        auxrev.copiaArq(reverso.getFile());
        //auxrev.exibirArq();
        //auxrev.exibirArq();
        auxrev.iniciarMovimentos();
        auxrev.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrev.insercaoDireta();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrev.getComparacoes();
        movimentos = auxrev.getMovimentos();
        //System.out.println(movimentos+" "+comparacoes);
        //auxrev.exibirArq();
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) + tfArq - 4) / 4, movimentos, (Math.pow(tfArq,
                2) + 3 * tfArq - 4) / 2, tfim - tini);
        imprimirArquivos(reverso,auxrev,"InsercaoDireta" ,"reverso");

        auxrand.copiaArq(randomico.getFile());
        //auxrand.exibirArq();
        auxrand.iniciarMovimentos();
        auxrand.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrand.insercaoDireta();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrand.getComparacoes();
        movimentos = auxrand.getMovimentos();
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) + tfArq - 2) / 4, movimentos, (Math.pow(tfArq,
                2) + 9 * tfArq - 10) / 4, tfim - tini);
        writer.println("\n|===============================================================================================================================================|");
        imprimirArquivos(randomico,auxrand,"InsercaoDireta" ,"randomico");
    }

    public void InsercaoBinaria() {

        auxOrd.copiaArq(ordenado.getFile());
        auxOrd.iniciaComparacoes();
        auxOrd.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        ordenado.insercaoBinaria();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxOrd.getComparacoes();
        movimentos = auxOrd.getMovimentos();
        escreveArq("|Insercao Binaria    |", comparacoes, 0, movimentos, 3 * (tfArq - 1), tfim -
                tini);
        imprimirArquivos(ordenado,auxOrd,"InsecaoBinaria" ,"Oedenado");

        auxrev.copiaArq(reverso.getFile());
        auxrev.iniciarMovimentos();
        auxrev.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrev.insercaoBinaria();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrev.getComparacoes();
        movimentos = auxrev.getMovimentos();
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, (Math.pow(tfArq, 2) +
                3 * tfArq - 4) / 2, tfim - tini);

        imprimirArquivos(reverso,auxrev,"InsercaBinaria" ,"reversa");
        auxrand.copiaArq(randomico.getFile());
        auxrand.iniciarMovimentos();
        auxrand.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrand.insercaoBinaria();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrand.getComparacoes();
        movimentos = auxrand.getMovimentos();
        escreveArq("", comparacoes, tfArq * (Math.log(tfArq)), movimentos, (Math.pow(tfArq, 2) + 9 *
                tfArq - 10) / 2, tfim - tini);
        writer.println("\n|===============================================================================================================================================|");
        imprimirArquivos(randomico,auxrand,"InsercaoBinaria" ,"randomico");
    }

    public void SelecaoDireta() {

        auxOrd.copiaArq(ordenado.getFile());
        auxOrd.iniciarMovimentos();
        auxOrd.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxOrd.selecaoDireta();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxOrd.getComparacoes();
        movimentos = auxOrd.getMovimentos();
        escreveArq("|Selecao Direta\t     |", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos,
                3 * (tfArq - 1), tfim - tini);
        imprimirArquivos(ordenado,auxOrd,"SelecaoDireta" ,"Oedenado");

        auxrev.copiaArq(reverso.getFile());
        auxrev.iniciarMovimentos();
        auxrev.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrev.selecaoDireta();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrev.getComparacoes();
        movimentos = auxrev.getMovimentos();
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, Math.pow(tfArq, 2) / 4
                + (3 * (tfArq - 1)), tfim - tini);
        imprimirArquivos(reverso,auxrev,"SelecaoDireta" ,"reversa");

        auxrand.copiaArq(randomico.getFile());
        auxrand.iniciarMovimentos();
        auxrand.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrand.selecaoDireta();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrand.getComparacoes();
        movimentos = auxrand.getMovimentos();
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, tfArq *
                (Math.log((double) tfArq) + 0.577216f), tfim - tini);
        writer.println("\n|===============================================================================================================================================|");
        imprimirArquivos(randomico,auxrand,"SelecaoDireta" ,"randomico");
    }

    public void BubbleSort() {

        auxOrd.copiaArq(ordenado.getFile());
        auxOrd.iniciarMovimentos();
        auxOrd.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxOrd.bolha();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxOrd.getComparacoes();
        movimentos = auxOrd.getMovimentos();
        escreveArq("|Bolha               |", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, 3 *
                (tfArq - 1), tfim - tini);
        imprimirArquivos(ordenado,auxOrd,"bolha" ,"Oedenado");

        auxrev.copiaArq(reverso.getFile());
        auxrev.iniciarMovimentos();
        auxrev.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrev.bolha();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrev.getComparacoes();
        movimentos = auxrev.getMovimentos();
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, Math.pow(tfArq, 2) / 4
                + (3 * (tfArq - 1)), tfim - tini);
        imprimirArquivos(reverso,auxrev,"bolha" ,"reversa");

        auxrand.copiaArq(randomico.getFile());
        auxrand.iniciarMovimentos();
        auxrand.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrand.bolha();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrand.getComparacoes();
        movimentos = auxrand.getMovimentos();
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, tfArq *
                (Math.log((double) tfArq) + 0.577216f), tfim - tini);
        writer.println("\n|===============================================================================================================================================|");
        imprimirArquivos(randomico,auxrand,"bolha" ,"randomico");
    }

    public void ShakeSort() {

        ordenado.iniciarMovimentos();
        ordenado.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        ordenado.shake();
        tfim = (int) System.currentTimeMillis();
        comparacoes = ordenado.getComparacoes();
        movimentos = ordenado.getMovimentos();
        escreveArq("|Shake               |", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, 0,
                tfim - tini);
        imprimirArquivos(ordenado,auxOrd,"shake" ,"Oedenado");

        auxrev.copiaArq(reverso.getFile());
        auxrev.iniciarMovimentos();
        auxrev.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrev.shake();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrev.getComparacoes();
        movimentos = auxrev.getMovimentos();
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, 3 * (Math.pow(tfArq,
                2) - tfArq) / 4, tfim - tini);
        imprimirArquivos(reverso,auxrev,"shake" ,"reversa");

        auxrand.copiaArq(randomico.getFile());
        auxrand.iniciarMovimentos();
        auxrand.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrand.shake();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrand.getComparacoes();
        movimentos = auxrand.getMovimentos();
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, 3 * (Math.pow(tfArq,
                2) - tfArq) / 2, tfim - tini);
        writer.println("\n|===============================================================================================================================================|");
        imprimirArquivos(randomico,auxrand,"shake" ,"randomico");
    }

    public void ShellSort() {

        auxOrd.copiaArq(ordenado.getFile());
        auxOrd.iniciarMovimentos();
        auxOrd.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxOrd.shake();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxOrd.getComparacoes();
        movimentos = auxOrd.getMovimentos();
        escreveArq("|Shell               |", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, 3 *
                (tfArq - 1), tfim - tini);
        imprimirArquivos(ordenado,auxOrd,"shell" ,"Oedenado");

        auxrev.copiaArq(reverso.getFile());
        auxrev.iniciarMovimentos();
        auxrev.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrev.shell();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrev.getComparacoes();
        movimentos = auxrev.getMovimentos();
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, Math.pow(tfArq, 2) /
                (4 + 3 * (tfArq - 1)), tfim - tini);
        imprimirArquivos(reverso,auxrev,"shell" ,"reversa");

        auxrand.copiaArq(randomico.getFile());
        auxrand.iniciarMovimentos();
        auxrand.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrand.shell();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrand.getComparacoes();
        movimentos = auxrand.getMovimentos();
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, tfArq *
                (Math.log((double) tfArq) + 0.577216f), tfim - tini);
        writer.println("\n|===============================================================================================================================================|");
        imprimirArquivos(randomico,auxrand,"shell" ,"randomico");
    }

    public void HeapSort() {
        auxOrd.copiaArq(ordenado.getFile());
        auxOrd.iniciarMovimentos();
        auxOrd.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxOrd.heap();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxOrd.getComparacoes();
        movimentos = auxOrd.getMovimentos();
        escreveArq("|Heap                |", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, 3 *
                (tfArq - 1), tfim - tini);
        imprimirArquivos(ordenado,auxOrd,"heap" ,"Oedenado");

        auxrev.copiaArq(reverso.getFile());
        auxrev.iniciarMovimentos();
        auxrev.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrev.heap();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrev.getComparacoes();
        movimentos = auxrev.getMovimentos();
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, Math.pow(tfArq, 2) /
                (4 + 3 * (tfArq - 1)), tfim - tini);
        imprimirArquivos(reverso,auxrev,"heap" ,"reversa");

        auxrand.copiaArq(randomico.getFile());
        auxrand.iniciarMovimentos();
        auxrand.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrand.heap();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrand.getComparacoes();
        movimentos = auxrand.getMovimentos();
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, tfArq *
                (Math.log((double) tfArq) + 0.577216f), tfim - tini);
        writer.println("\n|===============================================================================================================================================|");
        imprimirArquivos(randomico,auxrand,"heap" ,"randomico");
    }

    public void QuickSortSP() {
        auxOrd.copiaArq(ordenado.getFile());
        auxOrd.iniciarMovimentos();
        auxOrd.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxOrd.quick_sp();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxOrd.getComparacoes();
        movimentos = auxOrd.getMovimentos();
        escreveArq("|Quick Sort SP\t     |", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos,
                3 * (tfArq - 1), tfim - tini);
        imprimirArquivos(ordenado,auxOrd,"quick_sp" ,"Oedenado");

        auxrev.copiaArq(reverso.getFile());
        auxrev.iniciarMovimentos();
        auxrev.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrev.quick_sp();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrev.getComparacoes();
        movimentos = auxrev.getMovimentos();
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, Math.pow(tfArq, 2) /
                (4 + 3 * (tfArq - 1)), tfim - tini);
        imprimirArquivos(reverso,auxrev,"quick_sp" ,"reverso");

        auxrand.copiaArq(randomico.getFile());
        auxrand.iniciarMovimentos();
        auxrand.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrand.quick_sp();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrand.getComparacoes();
        movimentos = auxrand.getMovimentos();
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, tfArq *
                (Math.log((double) tfArq) + 0.577216f), tfim - tini);
        writer.println("\n|===============================================================================================================================================|");
        imprimirArquivos(randomico,auxrand,"quick_sp" ,"randomico");
    }

    public void QuickSortCP() {
        auxOrd.copiaArq(ordenado.getFile());
        auxOrd.iniciarMovimentos();
        auxOrd.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxOrd.quick_cp();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxOrd.getComparacoes();
        movimentos = auxOrd.getMovimentos();
        escreveArq("|Quick Sort CP\t     |", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos,
                3 * (tfArq - 1), tfim - tini);
        imprimirArquivos(ordenado,auxOrd,"quickcp" ,"ordenado");

        auxrev.copiaArq(reverso.getFile());
        auxrev.iniciarMovimentos();
        auxrev.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrev.quick_cp();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrev.getComparacoes();
        movimentos = auxrev.getMovimentos();
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, Math.pow(tfArq, 2) /
                (4 + 3 * (tfArq - 1)), tfim - tini);
        imprimirArquivos(reverso,auxrev,"quickcp" ,"reverso");

        auxrand.copiaArq(randomico.getFile());
        auxrand.iniciarMovimentos();
        auxrand.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrand.quick_cp();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrand.getComparacoes();
        movimentos = auxrand.getMovimentos();
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, tfArq *
                (Math.log((double) tfArq) + 0.577216f), tfim - tini);
        writer.println("\n|===============================================================================================================================================|");
        imprimirArquivos(randomico,auxrand,"quickcp" ,"randomico");
    }


    public void merge1() {

        auxOrd.copiaArq(ordenado.getFile());
        auxOrd.iniciaComparacoes();
        auxOrd.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxOrd.merge1();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxOrd.getComparacoes();
        movimentos = auxOrd.getMovimentos();
        escreveArq("|Merge 1\t     |", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, 3 *
                (tfArq - 1), tfim - tini);
        imprimirArquivos(ordenado,auxOrd,"merge1" ,"ordenado");

        auxrev.copiaArq(reverso.getFile());
        auxrev.iniciaComparacoes();
        auxrev.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxrev.merge1();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrev.getComparacoes();
        movimentos = auxrev.getMovimentos();
        /*System.out.println("reverso original:");
        reverso.exibirArq();
        System.out.println("reverso:");
        auxrev.exibirArq();*/
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, Math.pow(tfArq, 2) /
                (4 + 3 * (tfArq - 1)), tfim - tini);
        imprimirArquivos(reverso,auxrev,"merge1" ,"reversp");

        auxrand.copiaArq(randomico.getFile());
        auxrand.iniciaComparacoes();
        auxrand.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxrand.merge1();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrand.getComparacoes();
        movimentos = auxrand.getMovimentos();
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, tfArq *
                (Math.log((double) tfArq) + 0.577216f), tfim - tini);
        writer.println("\n|===============================================================================================================================================|");/*

        System.out.println("randomico original:");
        randomico.exibirArq();
        System.out.println("randomico:");
        auxrand.exibirArq();*/
        imprimirArquivos(randomico,auxrand,"merge1" ,"randomico");
    }

    public void merge2() {

        auxOrd.copiaArq(ordenado.getFile());
        auxOrd.iniciaComparacoes();
        auxOrd.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxOrd.merge2();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxOrd.getComparacoes();
        movimentos = auxOrd.getMovimentos();
        escreveArq("|Merge 2\t     |", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, 3 *
                (tfArq - 1), tfim - tini);
       imprimirArquivos(ordenado,auxrand,"merge2" ,"ordenado");

        auxrev.copiaArq(reverso.getFile());
        auxrev.iniciaComparacoes();
        auxrev.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxrev.merge2();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrev.getComparacoes();
        movimentos = auxrev.getMovimentos();
        imprimirArquivos (reverso,auxrev,"merge2" ,"ordenado");

        escreveArq("", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, Math.pow(tfArq, 2) /
                (4 + 3 * (tfArq - 1)), tfim - tini);

        auxrand.copiaArq(randomico.getFile());
        auxrand.iniciaComparacoes();
        auxrand.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxrand.merge2();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrand.getComparacoes();
        movimentos = auxrand.getMovimentos();
        escreveArq("", comparacoes, (Math.pow(tfArq, 2) - tfArq) / 2, movimentos, tfArq *
                (Math.log((double) tfArq) + 0.577216f), tfim - tini);
        writer.println("\n|===============================================================================================================================================|");
       imprimirArquivos(randomico,auxrand,"merge2" ,"randomico");
    }

    private void CountingSort() {

        auxOrd.copiaArq(ordenado.getFile());
        auxOrd.iniciaComparacoes();
        auxOrd.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxOrd.counting();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxOrd.getComparacoes();
        movimentos = auxOrd.getMovimentos();
        escreveArq("|Counting\t     |", comparacoes, -1, movimentos, -1, tfim - tini);
        imprimirArquivos(ordenado,auxrand,"counting" ,"ordenado");

        auxrev.copiaArq(reverso.getFile());
        auxrev.iniciaComparacoes();
        auxrev.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxrev.counting();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrev.getComparacoes();
        movimentos = auxrev.getMovimentos();
        escreveArq("", comparacoes, -1, movimentos, -1, tfim - tini);
        imprimirArquivos(reverso,auxrev,"counting" ,"reverso");

        auxrand.copiaArq(randomico.getFile());
        auxrand.iniciaComparacoes();
        auxrand.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxrand.counting();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrand.getComparacoes();
        movimentos = auxrand.getMovimentos();
        escreveArq("", comparacoes, -1, movimentos, -1, tfim - tini);
        imprimirArquivos(randomico,auxrev,"counting" ,"randomico");
        writer.println("\n|===============================================================================================================================================|");
    }

    private void RadixSort() {

        auxOrd.copiaArq(ordenado.getFile());
        auxOrd.iniciaComparacoes();
        auxOrd.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxOrd.radix();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxOrd.getComparacoes();
        movimentos = auxOrd.getMovimentos();
        escreveArq("|Radix               |", comparacoes, -1, movimentos, -1, tfim - tini);
        imprimirArquivos(ordenado,auxOrd,"radix" ,"ordenado");

        auxrev.copiaArq(reverso.getFile());
        auxrev.iniciaComparacoes();
        auxrev.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxrev.radix();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrev.getComparacoes();
        movimentos = auxrev.getMovimentos();
        escreveArq("", comparacoes, -1, movimentos, -1, tfim - tini);
        imprimirArquivos(reverso,auxrev,"radix" ,"reverso");

        auxrand.copiaArq(randomico.getFile());
        auxrand.iniciaComparacoes();
        auxrand.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxrand.radix();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrand.getComparacoes();
        movimentos = auxrand.getMovimentos();
        escreveArq("", comparacoes, -1, movimentos, -1, tfim - tini);
        imprimirArquivos(randomico,auxrand,"radix" ,"randomico");
        writer.println("\n|===============================================================================================================================================|");
    }

    private void CombSort() {

        auxOrd.copiaArq(ordenado.getFile());
        auxOrd.iniciaComparacoes();
        auxOrd.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxOrd.comb();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxOrd.getComparacoes();
        movimentos = auxOrd.getMovimentos();
        escreveArq("|Comb                |", comparacoes, -1, movimentos, -1, tfim - tini);
        imprimirArquivos(ordenado,auxOrd,"comb" ,"ordenado");

        auxrev.copiaArq(reverso.getFile());
        auxrev.iniciaComparacoes();
        auxrev.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxrev.comb();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrev.getComparacoes();
        movimentos = auxrev.getMovimentos();
        escreveArq("", comparacoes, -1, movimentos, -1, tfim - tini);
        imprimirArquivos(reverso,auxrev,"comb" ,"reverso");

        auxrand.copiaArq(randomico.getFile());
        auxrand.iniciaComparacoes();
        auxrand.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxrand.comb();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrand.getComparacoes();
        movimentos = auxrand.getMovimentos();
        escreveArq("", comparacoes, -1, movimentos, -1, tfim - tini);
        imprimirArquivos(randomico,auxrev,"comb" ,"randomico");

        writer.println("\n|===============================================================================================================================================|");
    }

    private void GnomeSort() {

        auxOrd.copiaArq(ordenado.getFile());
        auxOrd.iniciaComparacoes();
        auxOrd.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxOrd.gnome();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxOrd.getComparacoes();
        movimentos = auxOrd.getMovimentos();
        escreveArq("|Gnome               |", comparacoes, -1, movimentos, -1, tfim - tini);
        imprimirArquivos(ordenado,auxOrd,"gnome" ,"ordenado");

        auxrev.copiaArq(reverso.getFile());
        auxrev.iniciaComparacoes();
        auxrev.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxrev.gnome();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrev.getComparacoes();
        movimentos = auxrev.getMovimentos();
        escreveArq("", comparacoes, -1, movimentos, -1, tfim - tini);
        imprimirArquivos(reverso,auxrev,"gnome" ,"reverso");

        auxrand.copiaArq(randomico.getFile());
        auxrand.iniciaComparacoes();
        auxrand.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxrand.gnome();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrand.getComparacoes();
        movimentos = auxrand.getMovimentos();
        escreveArq("", comparacoes, -1, movimentos, -1, tfim - tini);
        imprimirArquivos(randomico,auxrand,"gnome" ,"randomico");
        writer.println("\n|===============================================================================================================================================|");
    }

    private void TimSort() {

        auxOrd.copiaArq(ordenado.getFile());
        auxOrd.iniciaComparacoes();
        auxOrd.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxOrd.tim();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxOrd.getComparacoes();
        movimentos = auxOrd.getMovimentos();
        escreveArq("|Tim                 |", comparacoes, -1, movimentos, -1, tfim - tini);
        imprimirArquivos(ordenado,auxOrd,"tim" ,"ordenado");

        auxrev.copiaArq(reverso.getFile());
        auxrev.iniciarMovimentos();
        auxrev.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrev.tim();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrev.getComparacoes();
        movimentos = auxrev.getMovimentos();
        escreveArq("", comparacoes, -1, movimentos, -1, tfim - tini);
        imprimirArquivos(reverso,auxrev,"tim" ,"reverso");

        auxrand.copiaArq(randomico.getFile());
        auxrand.iniciaComparacoes();
        auxrand.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxrand.tim();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrand.getComparacoes();
        movimentos = auxrand.getMovimentos();
        escreveArq("", comparacoes, -1, movimentos, -1, tfim - tini);
        imprimirArquivos(randomico,auxrand,"tim" ,"randomico");
        writer.println("\n|===============================================================================================================================================|");

    }

    private void bucketSort() {

        auxOrd.copiaArq(ordenado.getFile());
        auxOrd.iniciaComparacoes();
        auxOrd.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxOrd.bucket();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxOrd.getComparacoes();
        movimentos = auxOrd.getMovimentos();
        escreveArq("|Bucket              |", comparacoes, -1, movimentos, -1, tfim - tini);
        imprimirArquivos(ordenado,auxOrd,"Bucket" ,"ordenado");

        auxrev.copiaArq(reverso.getFile());
        auxrev.iniciarMovimentos();
        auxrev.iniciaComparacoes();
        tini = (int) System.currentTimeMillis();
        auxrev.bucket();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrev.getComparacoes();
        movimentos = auxrev.getMovimentos();
        escreveArq("", comparacoes, -1, movimentos, -1, tfim - tini);
        imprimirArquivos(reverso,auxrev,"Bucket" ,"reverso");

        auxrand.copiaArq(randomico.getFile());
        auxrand.iniciaComparacoes();
        auxrand.iniciarMovimentos();
        tini = (int) System.currentTimeMillis();
        auxrand.bucket();
        tfim = (int) System.currentTimeMillis();
        comparacoes = auxrand.getComparacoes();
        movimentos = auxrand.getMovimentos();
        escreveArq("", comparacoes, -1, movimentos, -1, tfim - tini);
        imprimirArquivos(randomico,auxrand,"Bucket" ,"randomico");
        writer.println("\n|===============================================================================================================================================|");

    }

    public void imprimirArquivos(Arquivo_Java arquivoOriginal, Arquivo_Java arquivoResultado,String nome, String tipo) {
        System.out.println("Original "+nome +" "+tipo+":");
        arquivoOriginal.exibirArq();
        System.out.println("Resultado:");
        arquivoResultado.exibirArq();
    }

    public void iniciaArq() {
        this.ordenado = new Arquivo_Java("Ordenado.dat");
        this.reverso = new Arquivo_Java("Reverso.dat");
        this.randomico = new Arquivo_Java("Randomico.dat");
        this.auxrand = new Arquivo_Java("auxRand.dat");
        this.auxrev = new Arquivo_Java("auxRev.dat");
        this.auxOrd = new Arquivo_Java("auxOrd.dat");

        try {
            this.text = new FileWriter("tabela.txt");
            this.writer = new PrintWriter(text);
        } catch (Exception e) {
            System.out.println("Erro ao criar arquivo TXT" + e.getMessage());
            System.exit(-1);
        }
    }

    private void gerarCabecalho() {
        writer.println("|===============================================================================================================================================|");
        writer.printf("|MÉTODOS DE ORDENAÇÃO|ARQUIVO ORDENADO\t\t\t\t|ARQUIVO EM ORDEM REVERSA\t\t "
                + "|ARQUIVO RANDÔMICO                     |\n");
        writer.printf(
                "|\t\t     |Comp. 1\t|Comp. 2|Mov. 1\t|Mov. 2\t|Tempo\t|Comp. 1|Comp. 2|Mov. 1\t|Mov. 2\t|Tempo\t|"
                        + "Comp. 1|Comp. 2|Mov. 1\t|Mov. 2\t|Tempo\t|\n");
        writer.println("|===============================================================================================================================================|");

    }

    private void fecharArquivo() {
        if (writer != null) {
            writer.close();
        }
    }
    private void escreveArq(String nomeMetodo, int cp, double ce, int mp, double me, double tempo) {
        writer.printf(nomeMetodo + " " + cp + "\t| " + (int) ce + "\t| " + mp +
                "\t| " + (int) me + "\t| " + (int) tempo + "\t|");
    }
    public void executarArquivos() {
        //Arquivo_Java a = new Arquivo_Java("arquivo.dat");
       // Arquivo_Java a2 = new Arquivo_Java("backup.dat");
        iniciaArq();
        gerarCabecalho();


        ordenado.geraArquivoOrdenado();
        reverso.geraArquivoReverso();
        randomico.geraArquivoRandomico();

       // randomico.exibirArq();

        // Execute as operações
        //a.executa();

        System.out.println("executando insercao direta...");
        InsercaoDireta();
        System.out.println("===========================================");
        System.out.println("executando insercao binaria...");
        InsercaoBinaria();
        System.out.println("===========================================");
        System.out.println("executando selecao direta...");
        SelecaoDireta();
        System.out.println("===========================================");
        System.out.println("executando bolha...");
        BubbleSort();
        System.out.println("===========================================");
        System.out.println("executando shake...");
        ShakeSort();
        System.out.println("===========================================");
        System.out.println("executando shell...");
        ShellSort();
        System.out.println("===========================================");
        System.out.println("executando heap...");
        HeapSort();
        System.out.println("===========================================");
        System.out.println("executando quick sem pivô...");
        QuickSortSP();
        System.out.println("===========================================");
        System.out.println("executando quick com pivô...");
        QuickSortCP();
        System.out.println("===========================================");
        System.out.println("executando merge1...");
        merge1();
        System.out.println("===========================================");
        System.out.println("executando merge2...");
        merge2();
        System.out.println("===========================================");
        System.out.println("executando couting...");
        CountingSort();
        System.out.println("===========================================");
        System.out.println("executando bucket...");
        bucketSort();
        System.out.println("===========================================");
        System.out.println("executando radix...");
        RadixSort();
        System.out.println("===========================================");
        System.out.println("executando comb...");
        CombSort();
        System.out.println("===========================================");
        System.out.println("executando gnome...");
        GnomeSort();
        System.out.println("===========================================");
        System.out.println("executando tim...");
        TimSort();
        System.out.println("===========================================");
        fecharArquivo();
    }
}
