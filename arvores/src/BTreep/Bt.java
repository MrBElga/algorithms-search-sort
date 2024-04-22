package BTreep;

public class Bt {
    public static void main(String[] args) {
        BTree arvore = new BTree();
        for(int i = 0;i<10;i++){
            arvore.inserir(i, i);
        }
        System.out.println("inseriu");
        arvore.inOrd();
        System.out.println("fez em ordem");
    }
}