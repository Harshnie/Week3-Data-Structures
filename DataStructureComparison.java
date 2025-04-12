import java.util.*;

public class DataStructureComparison {

    public static void main(String[] args) {
        int n = 1000000; 

        
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = i;
        }

        long arrayStartTime = System.nanoTime();
        int arraySearchResult = linearSearch(array, n / 2); 
        long arrayEndTime = System.nanoTime();
        double arrayDuration = (arrayEndTime - arrayStartTime) / 1e6;
        System.out.println("Array Search Result: " + arraySearchResult);
        System.out.println("Array Search Time: " + arrayDuration + " ms");

        // HashSet Search
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            hashSet.add(i);
        }

        long hashSetStartTime = System.nanoTime();
        boolean hashSetSearchResult = hashSet.contains(n / 2); 
        long hashSetEndTime = System.nanoTime();
        double hashSetDuration = (hashSetEndTime - hashSetStartTime) / 1e6;
        System.out.println("HashSet Search Result: " + hashSetSearchResult);
        System.out.println("HashSet Search Time: " + hashSetDuration + " ms");

        // TreeSet Search
        Set<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            treeSet.add(i);
        }

        long treeSetStartTime = System.nanoTime();
        boolean treeSetSearchResult = treeSet.contains(n / 2); 
        long treeSetEndTime = System.nanoTime();
        double treeSetDuration = (treeSetEndTime - treeSetStartTime) / 1e6;
        System.out.println("TreeSet Search Result: " + treeSetSearchResult);
        System.out.println("TreeSet Search Time: " + treeSetDuration + " ms");
    }

    public static int linearSearch(Integer[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1; 
    }
}
