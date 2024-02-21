public class Lista<T> {
    private  No<T> inicio;
    private No<T> ultimo;
    private int tamanho = 0;
    public void adiciona(T elemento){
        No<T> caixa = new No<T>(elemento);

        if(tamanho == 0){
            this.inicio=caixa;
        }
        else {
            this.ultimo.setProximo(caixa);
            this.ultimo.setAnterior(caixa);
        }
        this.ultimo=caixa;
        this.tamanho++;
    }
    public int getTamanho(){
        return this.tamanho;
    }

    public void limpar(){

        for (No<T> atual = this.inicio; atual != null;) {
            No<T> proximo = atual.getProximo();
            atual.setElemento(null);
            atual.setProximo(null);
            this.tamanho--;
            atual = proximo;
        }
        this.inicio = null;
        this.ultimo = null;
        this.tamanho = 0;
    }

    @Override
    public String toString() {
        if(this.tamanho == 0){
            return "[]";
        }
        else{
            StringBuilder builder = new StringBuilder("[");
            No<T> atual = this.inicio;
            for (int i = 0; i < this.tamanho - 1 ; i++) {
                builder.append(atual.getElemento()).append(",");
                atual = atual.getProximo();
            }
            builder.append(atual.getElemento()).append("]");

          /*builder.append(atual.getElemento()).append(",");
          while (atual.getProximo() != null){
              atual = atual.getProximo();
              builder.append(atual.getElemento()).append(",");
          }*/
            return builder.toString();
        }



    }
}
