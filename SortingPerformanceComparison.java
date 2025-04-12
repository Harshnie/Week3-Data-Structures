import java.util.Arrays;
import java.util.Random;

public class SortingPerformanceComparison {

    public static void main(String[] args) {
        int[] datasetSizes = {1000, 10000, 1000000};

        for (int size : datasetSizes) {
            int[] dataset = generateDataset(size);
            int[] datasetForMergeSort = Arrays.copyOf(dataset, dataset.length);
            int[] datasetForQuickSort = Arrays.copyOf(dataset, dataset.length);

            System.out.println("Dataset Size: " + size);

            long bubbleSortStartTime = System.nanoTime();
            bubbleSort(dataset);
            long bubbleSortEndTime = System.nanoTime();
            double bubbleSortDuration = (bubbleSortEndTime - bubbleSortStartTime) / 1e6;
            System.out.println("Bubble Sort Time: " + bubbleSortDuration + " ms");

            long mergeSortStartTime = System.nanoTime();
            mergeSort(datasetForMergeSort, 0, datasetForMergeSort.length - 1);
            long mergeSortEndTime = System.nanoTime();
            double mergeSortDuration = (mergeSortEndTime - mergeSortStartTime) / 1e6;
            System.out.println("Merge Sort Time: " + mergeSortDuration + " ms");

            long quickSortStartTime = System.nanoTime();
            quickSort(datasetForQuickSort, 0, datasetForQuickSort.length - 1);
            long quickSortEndTime = System.nanoTime();
            double quickSortDuration = (quickSortEndTime - quickSortStartTime) / 1e6;
            System.out.println("Quick Sort Time: " + quickSortDuration + " ms");

            System.out.println();
        }
    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) {
            arr[k++] = L[i++];
        }

        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static int[] generateDataset(int size) {
        int[] dataset = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            dataset[i] = random.nextInt();
        }
        return dataset;
    }
}
