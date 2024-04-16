package lista;

import com.sun.source.tree.ReturnTree;

public class Lista {
    private No inicio;
    private No fim;

    public Lista() {
        this.inicio = null;
        this.fim = null;
    }

    public void inicializa() {
        inicio = fim = null;
    }

    public void inserirInicio(int info){
        No nova = new No(null,null,info);
        if(inicio == null){
            inicio = fim = nova;
        }
        else{
            inicio.setAnt(nova);
            inicio = nova;
        }
    }

    public void inserirFim(int info){
        No nova = new No(null,null,info);
        if(inicio == null){
            inicio = fim = nova;
        }
        else{
            fim.setProx(nova);
            nova.setAnt(fim);
            fim = nova;
        }
    }

    public No buscaExaustiva(int chave){
        No ppos = inicio;
        int i=0;
        while(ppos != null && chave > i ){
            ppos = ppos.getProx();
            i++;
        }
        return ppos ;
    }

    public void destruirLista() {
        inicio = fim = null;
    }

    public Lista duplicaLista() {
        Lista copia = new Lista(); // Criar uma nova lista de cópia localmente
        No atual = inicio;
        while (atual != null) {
            copia.inserirFim(atual.getInfo()); // Inserir na lista de cópia
            atual = atual.getProx();
        }
        return copia;
    }

    public int contaTam(){
        int i;
        No aux = inicio;
        for (i = 1; aux != fim && aux != null ; i++) {
            aux = aux.getProx();
        }
        return i;
    }
    private int contaTam(No in, No fm) {
        int i;
        No aux = in;
        for (i = 1; aux != fm && aux != null ; i++) {
            aux = aux.getProx();
        }
        return i;
    }

    public int buscaBinaria(int chave, int tam) {
        int comeco = 0, meio = (comeco + tam) / 2;
        No pos;
        pos = this.RetornaPos(meio);
        while (comeco < tam && pos.getInfo() != chave) {

            if (chave < pos.getInfo()) {
                tam = meio - 1;
            } else {
                comeco = meio + 1;
            }
            meio = (comeco + tam) / 2;
            pos = this.RetornaPos(meio);
        }
        if (chave > pos.getInfo())
            meio++;
        return meio;
    }

    public No RetornaPos(int tam){
        No pos = inicio;
        int i  = 0;
        //System.out.println(tam);
        while(tam > i && pos!=null){
            pos = pos.getProx();
            i ++;
        }
        return pos;
    }

    //Algoritimos visto em aula
    public void insercaoDireta(){
        int aux;
        No ppos, pi;

        for (pi = inicio.getProx() ; pi != null ;pi = pi.getProx()) {
            aux = pi.getInfo();
            ppos = pi;
            while(ppos != inicio && aux < ppos.getAnt().getInfo() ){

                ppos.setInfo(ppos.getAnt().getInfo());
                ppos = ppos.getAnt();
            }
            ppos.setInfo(aux);
        }
    }

    public void selecaoDireta(){
        int aux;
        No i = inicio,j=inicio,posMenor;

        while (i != null ) {
            posMenor = i;
            for(j = i.getProx() ; j != null ; j = j.getProx()){
                if(j.getInfo() < posMenor.getInfo())
                    posMenor = j;
            }
            aux = i.getInfo();
            i.setInfo(posMenor.getInfo());
            posMenor.setInfo(aux);
            i = i.getProx();
        }
    }

    public void insercaoBinaria() {
        No i = this.inicio, aux;
        int cont = 0;
        int pos, info;

        while (i != null) {
            pos = this.buscaBinaria(i.getInfo(), cont);
            aux = i;
            for (int j = cont; j > pos; j--) {
                info = aux.getAnt().getInfo();
                aux.getAnt().setInfo(aux.getInfo());
                aux.setInfo(info);
                aux = aux.getAnt();
            }
            i = i.getProx();
            cont++;
        }
    }

    public void bolha(){
        No i = inicio,fm = fim;
        int aux;
        boolean troca = true;

        while(contaTam(inicio,fm) > 1 && troca){
            troca = false;
            for(No j = inicio; j != fm ; j = j.getProx()){

                if(j.getInfo() > j.getProx().getInfo()){
                    troca = true;
                    aux = j.getInfo();
                    j.setInfo(j.getProx().getInfo());
                    j.getProx().setInfo(aux);
                }
            }
            fm = fm.getAnt();
            //TL --;
        }
    }

    public void shake(){
        No in = inicio, fn = fim;
        int aux;
        boolean troca = true;

        while (in != fn && troca){
            troca = false;
            for (No i = in; i != fn ; i = i.getProx()) {
                if(i.getInfo() > i.getProx().getInfo()){
                    troca = true;
                    aux = i.getInfo();
                    i.setInfo(i.getProx().getInfo());
                    i.getProx().setInfo(aux);
                }
            }
            fn = fn.getAnt();
            if(troca){
                troca = false;
                for (No i = fn; i != in ; i = i.getAnt()) {
                    if(i.getInfo()<i.getAnt().getInfo()) {
                        troca = true;
                        aux = i.getInfo();
                        i.setInfo(i.getAnt().getInfo());
                        i.getAnt().setInfo(aux);
                    }
                }
                in = in.getProx();
            }
        }
    }

    public void heap(){
        int tam = contaTam(inicio,fim),aux;
        No pai, fd, fe, maior;

        while(tam > 1){
            for( pai = RetornaPos(tam/2-1);  pai != null ; pai = pai.getAnt()){
                fe = RetornaPos( 2*(contaTam(inicio, pai)-1)+1);
                fd = RetornaPos( 2*(contaTam(inicio, pai)-1)+2);;
                maior = fe;
                if (contaTam(inicio, fd)<= tam && fd.getInfo() > maior.getInfo()) {
                    maior = fd;
                }
                if(maior.getInfo() > pai.getInfo()){
                    aux = pai.getInfo();
                    pai.setInfo(maior.getInfo());
                    maior.setInfo(aux);
                }
            }
            aux = inicio.getInfo();
            inicio.setInfo(RetornaPos(tam-1).getInfo());
            RetornaPos(tam-1).setInfo(aux);
            tam--;
        }
    }

    public void shell() {
        int tam = contaTam(inicio, fim), h = 1, aux, j, i;

        while (h < tam) {
            h = h * 3 + 1;
        }
        h = h / 3;
        while (h > 0) {
            for (i = h; i < tam; i++) {
                aux = RetornaPos(i).getInfo();
                for (j = i; j - h >= 0 && aux < RetornaPos(j - h).getInfo(); j = j - h) {
                    RetornaPos(j).setInfo(RetornaPos(j - h).getInfo());
                }
                RetornaPos(j).setInfo(aux);
            }
            h = h / 3;
        }
    }

    public void quickSort_SP() {
        int tam = contaTam(inicio,fim);
        quickSP(0, tam-1 );
    }

    private void quickSP(int ini, int fim) {
        int i = ini, j = fim, aux;
        No posI = RetornaPos(i);
        No posJ= RetornaPos(j);

        //System.out.println(posI.getInfo()+ " "+ posJ.getInfo());
        while(i < j) {
            while(i<j && posI.getInfo() <= posJ.getInfo()) {
                posI = posI.getProx();
                i++;
            }
            if(i < j) {
                aux = posI.getInfo();
                posI.setInfo(posJ.getInfo());
                posJ.setInfo(aux);
            }

            while (i < j && posI.getInfo() <= posJ.getInfo()) {
                posJ = posJ.getAnt();
                j--;
            }
            if(i < j) {
                aux = posI.getInfo();
                posI.setInfo(posJ.getInfo());
                posJ.setInfo(aux);
            }
        }
        if(ini < i-1) {
            quickSP(ini, i-1);
        }
        if(j+1 < fim) {
            quickSP(j+1, fim);
        }
    }

    public void quickSort_CP() {
        int tam = contaTam(inicio, fim);
        quickCP(0, tam-1);
    }

    public int getPos(No pos) {
        No aux = inicio;
        int i = 0;
        while (aux != pos) {
            aux = aux.getProx();
            i++;
        }
        return i;
    }
    private void quickCP(int ini, int fim) {
        int i = ini, j = fim, pivo, aux;
        pivo = RetornaPos((i + j) / 2 ).getInfo();

        No elemI = RetornaPos(i);
        No elemJ = RetornaPos(j);
        while(i < j) {
            while(elemI.getInfo() < pivo){
                elemI = elemI.getProx();
                i++;
            }
            while(elemJ.getInfo() > pivo) {
                elemJ = elemJ.getAnt();
                j--;
            }
            if(i <= j) {
                aux = elemI.getInfo();
                elemI.setInfo(elemJ.getInfo());
                elemJ.setInfo(aux);

                elemI = elemI.getProx();
                i++;
                elemJ = elemJ.getAnt();
                j--;
            }
        }

        if(ini < j)
            quickCP(ini, j);
        if(i < fim)
            quickCP(i, fim);
    }

    public void merge(){
        Lista lista1= new Lista(), lista2=new Lista();
        int seq,k=0, aux,tam = contaTam(inicio,fim);
        No pi,pj;
        for ( seq = 1; seq < tam; seq=seq*2 ) {
            lista1.inicializa();
            lista2.inicializa();
            for (int i = 0; i <tam/2; i++) {
                lista1.inserirFim(RetornaPos(i).getInfo());
                lista2.inserirFim(RetornaPos(i+(tam/2)).getInfo());

            }
            aux=seq;
            k=0;
            pi=lista1.inicio;
            pj=lista2.inicio;
            inicializa();
            while (k<tam) {

                while (lista1.getPos(pi) < aux && lista2.getPos(pj) <aux) {
                    if (pi.getInfo() < pj.getInfo()) {
                        inserirFim(pi.getInfo());
                        pi = pi.getProx();
                        k++;
                    } else {
                        inserirFim(pj.getInfo());
                        pj = pj.getProx();
                        k++;
                    }
                }

                while (lista1.getPos(pi) < aux) {
                    inserirFim(pi.getInfo());
                    pi = pi.getProx();
                    k++;
                }

                while (lista2.getPos(pj) <aux) {
                    inserirFim(pj.getInfo());
                    pj =pj.getProx();
                    k++;
                }
                aux = aux+ seq;
            }
        }
    }

    //merge com o erro arrumado (segunda implementacao)
    public void merge2(){
        Lista listaaux = new Lista();
        merge_2(listaaux,inicio,fim);
    }

    private void merge_2(Lista listaaux, No esq, No dir){
        No meio;

        if(esq != dir){
            meio = RetornaPos(((contaTam(inicio,esq)-1) + (contaTam(inicio,dir)-1))/2);
            merge_2(listaaux, esq, meio);
            merge_2(listaaux, meio.getProx(), dir);
            fusao2(listaaux,esq,meio,meio.getProx(),dir);
        }
    }

    private void fusao2(Lista listaaux, No ini1, No fim1, No ini2, No fim2){
        listaaux.inicializa();
        No aux, i = ini1, j = ini2;

        while (i != fim1.getProx() && j != fim2.getProx()) {
            if (i.getInfo() < j.getInfo()) {
                listaaux.inserirFim(i.getInfo());
                i = i.getProx();
            } else {
                listaaux.inserirFim(j.getInfo());
                j = j.getProx();
            }
        }
        while (i != fim1.getProx()) {
            listaaux.inserirFim(i.getInfo());
            i = i.getProx();
        }
        while (j != fim2.getProx()) {
            listaaux.inserirFim(j.getInfo());
            j = j.getProx();
        }

        aux = listaaux.inicio;
        i = ini1;

        while (aux != null) {
            i.setInfo(aux.getInfo());
            aux = aux.getProx();
            i = i.getProx();
        }
    }

    private int buscaMaior(){
        int maior = 0;

        for (No i = inicio; i != null ; i = i.getProx()) {
            if(i.getInfo() > maior)
                maior = i.getInfo();
        }
        return maior;
    }

    //Algoritimos para pesquisar
    //cormen 175
    public void counting() {
        //notei que na implementação do cormen ao buscar o maior pode ser que de eror
        //caso eu não tenha por exemplo o valor 32 no array e meu array tenha 32 pos
        //torquei pelo TF o valor de K assim garanto que tenho o array de 32
        int tam = contaTam(inicio,fim), K=tam;
        int[] vetCount = new int[K],vetAux = new int[K];
        No aux = inicio;

        for (int i = 0;i < tam;i++) {
            vetCount[aux.getInfo()]++;
            aux = aux.getProx();
        }
        for(int i = 1;i < vetCount.length;i++)
            vetCount[i] = vetCount[i] +vetCount[i-1] ;
        for (aux = fim;aux != null; aux = aux.getAnt())
            vetAux[--vetCount[aux.getInfo()]] = aux.getInfo();
        aux = inicio;
        for (int i = 0; i < tam;i++) {
            aux.setInfo(vetAux[i]);
            aux = aux.getProx();
        }
    }

    //cormen 180
    //ordenar em cada balde
    private void ordBucket(Lista lista) {
        if (lista.inicio != null) {
            No atual = lista.inicio.getProx();
            while (atual != null) {
                int chave = atual.getInfo();
                No anterior = atual.getAnt();

                while (anterior != null && anterior.getInfo() > chave) {
                    anterior.getProx().setInfo(anterior.getInfo());
                    anterior = anterior.getAnt();
                }
                if (anterior == null) {
                    lista.inicio.setInfo(chave);
                } else {
                    anterior.getProx().setInfo(chave);
                }
                atual = atual.getProx();
            }
        }
    }

    public void bucket(){
        int tam = contaTam(inicio,fim), maior = tam, divisor = (int) ((maior + 1) * 100) / 10;
        Lista[] baldes = new Lista[10];

        //iniciar cada lista (baldes)
        for (int i = 0; i < 10; i++) {
            baldes[i] = new Lista();
        }

        for (int i = 0; i < tam; i++) {
            int j = RetornaPos(i).getInfo() / divisor;
            baldes[j].inserirFim(RetornaPos(i).getInfo());
        }

        for (int i = 0; i < 10; i++) {
            ordBucket(baldes[i]);
        }

        int pos = 0;
        for (int i = 0; i < 10; i++) {
            No atual = baldes[i].inicio;
            while (atual != null) {
                RetornaPos(pos++).setInfo(atual.getInfo());
                atual = atual.getProx();
            }
        }
    }

    //cormen 178
    private void countingRadix(int div ) {
        int tam = contaTam(inicio,fim),K = tam;
        int[] vetAux = new int[K], vetCount = new int[K];

        No aux = inicio;

        for (int i = 0;i < tam;i++) {
            vetCount[(aux.getInfo()/div)%10]++;
            aux = aux.getProx();
        }
        for(int i = 1;i < vetCount.length;i++)
            vetCount[i] = vetCount[i] +vetCount[i-1] ;
        for (aux = fim;aux != null; aux = aux.getAnt()){
            vetAux[vetCount[(aux.getInfo() / div) % 10] - 1] = aux.getInfo();
            vetCount[(aux.getInfo() / div )% 10] -= 1;
        }
        aux = inicio;
        for (int i = 0; i < tam; i++) {
            aux.setInfo(vetAux[i]);
            aux = aux.getProx();
        }
    }

    public void radix(){
        No pos = inicio;
        int maior = pos.getInfo();

        for (int i = 0; i < contaTam(inicio,fim)-1 ; i++) {
            if(pos.getInfo() > maior)
                    maior=pos.getInfo();
            pos=pos.getProx();
        }
        for (int i = 1; maior/i > 0 ; i*=10) {
            countingRadix(i);
        }
    }

    private int proxFator( int fator){
        fator = (fator*10)/13;
        if(fator < 1)
           return 1;
        return fator;
    }

    public void comb(){
        //o fator em alguns lugares também é chamado de gap (lacuna)
        int tam = contaTam(inicio,fim), fator = tam, i = 0, aux;
        //flag para a troca do loop
        boolean flag = true;

        while (fator != 1 || flag){
            fator = proxFator(fator);
            flag = false;
            for (i = 0; i < tam - fator ; i++) {
                if(RetornaPos(i).getInfo() > RetornaPos(i + fator).getInfo()){
                    aux = RetornaPos(i).getInfo();
                    RetornaPos(i).setInfo(RetornaPos(i + fator).getInfo());
                    RetornaPos(i + fator).setInfo(aux);
                    flag = true;
                }
            }
        }
    }

    public void gnome(){
        int aux = 0;

        for (No i = inicio ; i != fim ; i = i.getProx()) {
            if (i.getInfo() > i.getProx().getInfo()) {
                for (No j = i.getProx(); j != inicio && j.getInfo() < j.getAnt().getInfo(); j = j.getAnt() ) {
                    aux = j.getInfo();
                    j.setInfo(j.getAnt().getInfo());
                    j.getAnt().setInfo(aux);
                }
            }
        }
    }

    private int limite(int i, int tam, int n) {
        int limiteSup = 0;
        if (i + tam - 1 < n - 1) {
            limiteSup = i + tam - 1;
        } else {
            limiteSup = n - 1;
        }
        return limiteSup;
    }

    private void insertTim(int esq, int dir) {
        int i = 0, aux = 0, j = 0;

        for (i = esq + 1 ;i <= dir; i++) {
            aux = RetornaPos(i).getInfo();
            for (j = i - 1; j >= esq && RetornaPos(j).getInfo() > aux; j--)
                RetornaPos(j + 1).setInfo(RetornaPos(j).getInfo());
            RetornaPos(j + 1).setInfo(aux);
        }
    }

    private void mergeTim(int esq, int meio, int dir) {
        int tam = meio - esq + 1, auxtam = dir - meio, i = 0, j = 0, k = 0;
        int[] vetC = new int[tam], vetB = new int[auxtam];

        for (i =0; i < tam; i++)
            vetC[i] = RetornaPos(esq + i).getInfo();
        for (i = 0;i < auxtam;i++)
            vetB[i] = RetornaPos(meio + 1 + i).getInfo();
        for ( i = j = 0,k = esq;i < tam && j < auxtam;) {
            if (vetC[i] < vetB[j])
                RetornaPos(k++).setInfo(vetC[i++]);
            else
                RetornaPos(k++).setInfo(vetB[j++]);
        }
        while (i < tam)
            RetornaPos(k++).setInfo(vetC[i++]);
        while (j < auxtam)
            RetornaPos(k++).setInfo(vetB[j++]);
    }

    public void tim() {
        int tam = contaTam(inicio,fim), meio, i = 0;
        int esq, dir;

        for (i=0;i < tam; i+=tam)
            insertTim(i,limite((i + tam - 1), (tam - 1),tam));
        for (i =1; i < tam;   i = 2 * i) {
            for (esq = 0; esq < tam;esq += 2 * i) {
                meio = esq + i - 1;
                dir = limite((esq + 2 * i - 1), (tam - 1),tam);
                mergeTim(esq, meio, dir);
            }
        }
    }

    /*
    public void remover(int info){
        No ppos = inicio.getProx();
        ppos = RetornaPos(info);
        if (ppos == inicio) {
            inicio = ppos.getProx();
        } else if (ppos == fim) {
            fim = ppos.getAnt();
        } else {
            if (ppos.getAnt() != null) {
                ppos.getAnt().setProx(ppos.getProx());
            }
            if (ppos.getProx() != null) {
                ppos.getProx().setAnt(ppos.getAnt());
            }
        }

        ppos.setAnt(null);
        ppos.setProx(null);
    }
   */
    public void exibir() {
        No i;
        if (inicio == null) {
            System.out.println("[]");
        } else {
            System.out.print("[");
            for (i = inicio; i != null; i = i.getProx()) {
                System.out.print(i.getInfo());
                if (i.getProx() != null) {
                    System.out.print(",");
                }
            }
            System.out.println("]");
        }
    }
}
