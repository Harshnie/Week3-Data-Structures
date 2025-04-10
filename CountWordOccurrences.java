import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

class CountWordOccurrences {
    public static void main(String[] args) {
        String fileName = "example.txt";
        String targetWord = "word"; 
        int count = 0;

        try (FileReader fr = new FileReader(fileName); BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (word.equalsIgnoreCase(targetWord)) {
                        count++;
                    }
                }
            }
            System.out.println("The word '" + targetWord + "' appears " + count + " times.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
