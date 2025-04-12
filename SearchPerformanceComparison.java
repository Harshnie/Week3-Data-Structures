import java.util.Arrays;
import java.util.Random;

public class SearchPerformanceComparison {

    public static void main(String[] args) {
        int[] datasetSizes = {1000, 10000, 1000000};

        for (int size : datasetSizes) {
            int[] dataset = generateDataset(size);
            int target = dataset[new Random().nextInt(size)];

            System.out.println("Dataset Size: " + size);

            long linearStartTime = System.nanoTime();
            linearSearch(dataset, target);
            long linearEndTime = System.nanoTime();
            double linearDuration = (linearEndTime - linearStartTime) / 1e6;
            System.out.println("Linear Search Time: " + linearDuration + " ms");

            Arrays.sort(dataset);
            long binaryStartTime = System.nanoTime();
            binarySearch(dataset, target);
            long binaryEndTime = System.nanoTime();
            double binaryDuration = (binaryEndTime - binaryStartTime) / 1e6;
            System.out.println("Binary Search Time: " + binaryDuration + " ms");

            System.out.println();
        }
    }

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
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
