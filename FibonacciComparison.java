public class FibonacciComparison {

    public static void main(String[] args) {
        int n = 30; 

        long recursiveStartTime = System.nanoTime();
        int recursiveResult = fibonacciRecursive(n);
        long recursiveEndTime = System.nanoTime();
        double recursiveDuration = (recursiveEndTime - recursiveStartTime) / 1e6;
        System.out.println("Recursive Fibonacci Result: " + recursiveResult);
        System.out.println("Recursive Time: " + recursiveDuration + " ms");

        long iterativeStartTime = System.nanoTime();
        int iterativeResult = fibonacciIterative(n);
        long iterativeEndTime = System.nanoTime();
        double iterativeDuration = (iterativeEndTime - iterativeStartTime) / 1e6;
        System.out.println("Iterative Fibonacci Result: " + iterativeResult);
        System.out.println("Iterative Time: " + iterativeDuration + " ms");
    }

    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    public static int fibonacciIterative(int n) {
        int a = 0, b = 1, sum;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }
}
