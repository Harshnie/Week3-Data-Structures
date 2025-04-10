public class LinearSearchFirstNegative {

    public static void main(String[] args) {
        int[] arr = {3, 5, -1, 8, -4, 6}; 
        int index = findFirstNegative(arr);
        
        if (index != -1) {
            System.out.println("First negative number found at index: " + index);
        } else {
            System.out.println("No negative number found.");
        }
    }

    public static int findFirstNegative(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                return i; 
            }
        }
        return -1; 
    }
}
