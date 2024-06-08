package Bplus;


public class Aplicacao
{
    private static void inserirAte10000(BPlus b){
        for (int i = 0; i < 10001; i++)
            b.inserir(i);
    }

    private static void inserirAte100(BPlus b){
        for (int i = 0; i < 101; i++)
            b.inserir(i);
    }
    private static void excluiPares(BPlus b){
        for (int i = 0; i < 10; i+=2)
            b.excluir(i);
    }

    private static void excluiAte100(BPlus b){
        for (int i = 0; i < 101; i++)
            b.excluir(i);
    }

    private static void inserirAteTam(BPlus b,int tam){
        for (int i = 0; i < tam; i++)
            b.inserir(i);
    }
    private static void excluiAteTam(BPlus b,int tam){
        for (int i = 0; i < tam; i++)
            b.excluir(i);
    }

    private static void excluiInvertido(BPlus b){
        for (int i = 10; i >=0; i--)
            b.excluir(i);
    }

    public static void inserirArvore(BPlus b){
        b.inserir(9);
        b.inserir(10);
        b.inserir(13);
        b.inserir(14);
        b.inserir(11);
        b.inserir(19);
        b.inserir(20);
        b.inserir(16);
        b.inserir(0);
    }

    public static  void excluirArvore(BPlus b){
        b.excluir(10);
        b.excluir(16);
        b.excluir(19);
        b.excluir(20);
        b.excluir(14);
        b.excluir(9);
        b.excluir(11);
        b.excluir(13);
        b.excluir(0);
    }

    public static void main(String[] args)
    {
        BPlus b = new BPlus();

        System.out.println("###############[ inserir ]###############");
        //inserirAte100( b);
        inserirAteTam(b,37);
        //inserirAte10000(b);
        //inserirArvore(b);
        System.out.println("---------------[ exibir ]---------------");
        b.exibir();
        System.out.println("###############[ exibir inord ]###############");
        b.inOrd();

        System.out.println("###############[ excluir ]###############");
        //excluiAte10000(b);
        excluiAteTam(b,11);
        //b.excluir(0);
        //excluiAte100(b);
        //excluiPares(b);
        //excluirArvore(b);
        //excluiInvertido(b);
        System.out.println("---------------[ exibir ]---------------");
        b.exibir();
        System.out.println("###############[ exibir inord ]###############");
        b.inOrd();
    }


}
