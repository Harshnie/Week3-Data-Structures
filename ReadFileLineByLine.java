import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

class ReadFileLineByLine {
    public static void main(String[] args) {
        String fileName = "example.txt";
        
        try (FileReader fr = new FileReader(fileName); BufferedReader br = new BufferedReader(fr)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
