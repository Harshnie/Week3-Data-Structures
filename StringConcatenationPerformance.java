public class StringConcatenationPerformance {

    public static void main(String[] args) {
        int[] operationsCount = {1000, 10000, 1000000};

        for (int n : operationsCount) {
            System.out.println("Operations Count: " + n);

            long stringStartTime = System.nanoTime();
            concatenateWithString(n);
            long stringEndTime = System.nanoTime();
            double stringDuration = (stringEndTime - stringStartTime) / 1e6;
            System.out.println("String Concatenation Time: " + stringDuration + " ms");

            long stringBuilderStartTime = System.nanoTime();
            concatenateWithStringBuilder(n);
            long stringBuilderEndTime = System.nanoTime();
            double stringBuilderDuration = (stringBuilderEndTime - stringBuilderStartTime) / 1e6;
            System.out.println("StringBuilder Concatenation Time: " + stringBuilderDuration + " ms");

            long stringBufferStartTime = System.nanoTime();
            concatenateWithStringBuffer(n);
            long stringBufferEndTime = System.nanoTime();
            double stringBufferDuration = (stringBufferEndTime - stringBufferStartTime) / 1e6;
            System.out.println("StringBuffer Concatenation Time: " + stringBufferDuration + " ms");

            System.out.println();
        }
    }

    public static void concatenateWithString(int n) {
        String result = "";
        for (int i = 0; i < n; i++) {
            result += "hello";
        }
    }

    public static void concatenateWithStringBuilder(int n) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append("hello");
        }
    }

    public static void concatenateWithStringBuffer(int n) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < n; i++) {
            result.append("hello");
        }
    }
}
