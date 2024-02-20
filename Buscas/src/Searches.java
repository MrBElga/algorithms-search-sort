public class Searches {
    public  int TL = 0;
    public int [] vet = new int[10];
    public void insertElement(int element) {
        if (TL < vet.length) {
            vet[TL] = element;
            TL++;
            System.out.println("Element inserted successfully!");
        } else {
            System.out.println("The array is full. Cannot insert more elements.");
        }
    }
    public void printArray() {
        System.out.print("Array: [");
        for (int i = 0; i < TL; i++) {
            System.out.print(vet[i]);
            if (i < TL - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    public int exhaustiveSearch(int key){
        int pos = 0;
        while (pos < TL - 1 && key != vet[pos]){
            pos++;
        }
        if(pos < TL){
            return pos;
        }
        return -1;
    }
    public int sentinelSearch(int key){
        int pos = 0;
        vet[TL] = key; //sentinel
        while (key != vet[pos]){
            pos++;
        }
        if(pos < TL){
            return pos;
        }
        return -1;
    }
    public int sequentialIndexedSearch(int key){
        int pos = 0;
        while(pos <= TL && key > vet[pos]){
            pos++;
        }
        if(pos < TL && key == vet[pos]){
            return pos;
        }
        return -1;
    }
    public int searchBinary(int key) {
        int start = 0, finish = TL - 1, middle = finish/2;
        while (start < finish && key != vet[middle]) {
            if (key <  vet[middle])
                finish = middle - 1;
            else
                start = middle + 1;
            middle = (start + finish) / 2;
        }
        if (key ==  vet[middle]) {
            return middle;
        }
        return -1;
    }
}