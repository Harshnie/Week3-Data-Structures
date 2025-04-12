import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;

public class FileReadingEfficiency {

    public static void main(String[] args) {
        String fileName = "largeFile.txt";

        long fileReaderStartTime = System.nanoTime();
        readWithFileReader(fileName);
        long fileReaderEndTime = System.nanoTime();
        double fileReaderDuration = (fileReaderEndTime - fileReaderStartTime) / 1e6;
        System.out.println("FileReader Time: " + fileReaderDuration + " ms");

        long inputStreamReaderStartTime = System.nanoTime();
        readWithInputStreamReader(fileName);
        long inputStreamReaderEndTime = System.nanoTime();
        double inputStreamReaderDuration = (inputStreamReaderEndTime - inputStreamReaderStartTime) / 1e6;
        System.out.println("InputStreamReader Time: " + inputStreamReaderDuration + " ms");
    }

    public static void readWithFileReader(String fileName) {
        try (FileReader fr = new FileReader(fileName);
             BufferedReader br = new BufferedReader(fr)) {
            while (br.readLine() != null) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readWithInputStreamReader(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {
            while (br.readLine() != null) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
