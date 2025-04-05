import java.util.Scanner;

class TextState {
    String content;
    TextState prev;
    TextState next;

    public TextState(String content) {
        this.content = content;
        this.prev = null;
        this.next = null;
    }
}

class TextEditor {
    private TextState head;
    private TextState current;
    private int size = 0;
    private final int MAX_HISTORY = 10;

    public void type(String newText) {
        TextState newState = new TextState(newText);

        if (head == null) {
            head = newState;
            current = head;
            size = 1;
        } else {
            current.next = newState;
            newState.prev = current;
            current = newState;

            // Remove states beyond current for fresh branch after undo
            TextState temp = current.next;
            while (temp != null) {
                TextState toDelete = temp;
                temp = temp.next;
                toDelete.prev = toDelete.next = null;
                size--;
            }
            current.next = null;
            size++;
        }

        if (size > MAX_HISTORY) {
            head = head.next;
            head.prev = null;
            size--;
        }
    }

    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
        } else {
            System.out.println("No more undo steps available.");
        }
    }

    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
        } else {
            System.out.println("No more redo steps available.");
        }
    }

    public void displayCurrentState() {
        if (current != null) {
            System.out.println("Current Text: \"" + current.content + "\"");
        } else {
            System.out.println("Editor is empty.");
        }
    }
}

public class TextEditorHistoryManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextEditor editor = new TextEditor();
        int choice;

        do {
            System.out.println("\n--- Text Editor ---");
            System.out.println("1. Type New Text");
            System.out.println("2. Undo");
            System.out.println("3. Redo");
            System.out.println("4. Display Current Text");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter new text: ");
                    String text = scanner.nextLine();
                    editor.type(text);
                    break;
                case 2:
                    editor.undo();
                    break;
                case 3:
                    editor.redo();
                    break;
                case 4:
                    editor.displayCurrentState();
                    break;
                case 0:
                    System.out.println("Exiting editor...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
