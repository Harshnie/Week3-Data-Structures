import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class PerformanceComparison {

    public static void main(String[] args) {
        // Part 1: Compare StringBuilder and StringBuffer
        String input = "hello";
        int iterations = 1000000;

        long startTime = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append(input);
        }
        long endTime = System.nanoTime();
        long stringBuilderTime = endTime - startTime;

        startTime = System.nanoTime();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sbf.append(input);
        }
        endTime = System.nanoTime();
        long stringBufferTime = endTime - startTime;

        System.out.println("StringBuilder time: " + stringBuilderTime + " nanoseconds");
        System.out.println("StringBuffer time: " + stringBufferTime + " nanoseconds");

        // Part 2: Compare FileReader for word counting
        String fileName = "largefile.txt"; // Replace with the path to your large file
        try {
            long wordCountFileReader = countWordsWithFileReader(fileName);

            System.out.println("Word count using FileReader: " + wordCountFileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long countWordsWithFileReader(String fileName) throws IOException {
        long wordCount = 0;
        try (FileReader fr = new FileReader(fileName);
             BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                wordCount += line.split("\\s+").length;
            }
        }
        return wordCount;
    }
}
