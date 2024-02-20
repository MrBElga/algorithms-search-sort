
public class Main {
    public static void main(String args[]) {
        Searches searches = new Searches();

        searches.insertElement(5);
        searches.insertElement(10);
        searches.insertElement(3);
        searches.insertElement(8);

        searches.printArray();

        int keyToSearch = 3;
        int resultExhaustive = searches.exhaustiveSearch(keyToSearch);
        int resultSentinel = searches.sentinelSearch(keyToSearch);
        int resultSequential = searches.sequentialIndexedSearch(keyToSearch);
        int resultBinary = searches.searchBinary(keyToSearch);

        System.out.println(resultExhaustive + " "+ resultSentinel +" " +" "+resultSequential + " "+ resultBinary );

        System.out.println("Exhaustive Search: " + (resultExhaustive != -1 ? "Element found at index " + resultExhaustive : "Element not found"));
        System.out.println("Sentinel Search: " + (resultSentinel != -1 ? "Element found at index " + resultSentinel : "Element not found"));
        System.out.println("Sequential Search: " + (resultSequential != -1 ? "Element found at index " + resultSequential : "Element not found"));
        System.out.println("Binary Search: " + (resultBinary != -1 ? "Element found at index " + resultBinary : "Element not found"));

    }
}