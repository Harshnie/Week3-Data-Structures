import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

class WriteUserInputToFile {
    public static void main(String[] args) {
        String fileName = "user_input.txt";
        
        try (InputStreamReader isr = new InputStreamReader(System.in);
             BufferedReader br = new BufferedReader(isr);
             FileWriter fw = new FileWriter(fileName, true)) {
             
            String userInput;
            System.out.println("Enter text (type 'exit' to stop):");
            
            while (!(userInput = br.readLine()).equalsIgnoreCase("exit")) {
                fw.write(userInput + System.lineSeparator());
            }
            System.out.println("Input has been written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
