public class ordencao {
    public int []vet = new int[10];
    public int TL = 0;

    public int searchBinary(int key,int i) {
        int start = 0, finish = TL - 1, middle = finish/2;
        while (start < finish && key != vet[middle]) {
            if (key <  vet[middle])
                finish = middle - 1;
            else
                start = middle + 1;
            middle = (start + finish) / 2;
        }
        if (key > vet[middle]) {
            return middle + 1;
        }
        return middle;
    }
    public void OrdInsertDir(){
     int aux , pos, i;
     for(i = 1; i < TL ; i++){
         pos = i;
         aux = vet[i];
         while (pos > 0 && vet[pos] > aux){
             vet[pos] = vet[pos-1];
             pos--;
         }
         vet[pos] = aux;
     }
    }
    public void OrdInsertBin(){
        int pos, aux,i;
        for (i = 0; i < TL; i++) {
            aux = vet[i];
            pos = searchBinary(aux, i);
            for (int j = 0; j > pos; j++) {
                vet[pos] = vet[pos -1];
            }
            vet[pos] = aux;
        }
    }
}
