import java.util.Scanner;

class Book {
    String title;
    String author;
    String genre;
    int bookId;
    boolean isAvailable;
    Book next, prev;

    public Book(String title, String author, String genre, int bookId, boolean isAvailable) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.isAvailable = isAvailable;
        this.next = null;
        this.prev = null;
    }
}

class Library {
    private Book head = null;
    private Book tail = null;

    public void addAtBeginning(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
    }

    public void addAtEnd(String title, String author, String genre, int bookId, boolean isAvailable) {
        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        if (head == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
    }

    public void addAtPosition(int position, String title, String author, String genre, int bookId, boolean isAvailable) {
        if (position <= 0 || head == null) {
            addAtBeginning(title, author, genre, bookId, isAvailable);
            return;
        }

        Book newBook = new Book(title, author, genre, bookId, isAvailable);
        Book temp = head;
        int index = 0;
        while (index < position - 1 && temp.next != null) {
            temp = temp.next;
            index++;
        }

        newBook.next = temp.next;
        newBook.prev = temp;

        if (temp.next != null) {
            temp.next.prev = newBook;
        } else {
            tail = newBook;
        }

        temp.next = newBook;
    }

    public boolean removeByBookId(int bookId) {
        if (head == null) return false;

        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                if (temp == head && temp == tail) {
                    head = tail = null;
                } else if (temp == head) {
                    head = temp.next;
                    head.prev = null;
                } else if (temp == tail) {
                    tail = temp.prev;
                    tail.next = null;
                } else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                }
                return true;
            }
            temp = temp.next;
        }

        return false;
    }

    public void searchByTitle(String title) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                printBook(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No book found with title \"" + title + "\".");
    }

    public void searchByAuthor(String author) {
        Book temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author)) {
                printBook(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("No books found by author \"" + author + "\".");
    }

    public boolean updateAvailability(int bookId, boolean isAvailable) {
        Book temp = head;
        while (temp != null) {
            if (temp.bookId == bookId) {
                temp.isAvailable = isAvailable;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void displayForward() {
        Book temp = head;
        if (temp == null) {
            System.out.println("Library is empty.");
            return;
        }
        System.out.println("Books in Forward Order:");
        while (temp != null) {
            printBook(temp);
            temp = temp.next;
        }
    }

    public void displayReverse() {
        Book temp = tail;
        if (temp == null) {
            System.out.println("Library is empty.");
            return;
        }
        System.out.println("Books in Reverse Order:");
        while (temp != null) {
            printBook(temp);
            temp = temp.prev;
        }
    }

    public int countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    private void printBook(Book b) {
        System.out.println("Title: " + b.title + ", Author: " + b.author + ", Genre: " + b.genre + ", ID: " + b.bookId + ", Available: " + (b.isAvailable ? "Yes" : "No"));
    }
}

public class MainLibrary {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Library Management Menu ---");
            System.out.println("1. Add Book at Beginning");
            System.out.println("2. Add Book at End");
            System.out.println("3. Add Book at Position");
            System.out.println("4. Remove Book by ID");
            System.out.println("5. Search by Title");
            System.out.println("6. Search by Author");
            System.out.println("7. Update Availability");
            System.out.println("8. Display Books (Forward)");
            System.out.println("9. Display Books (Reverse)");
            System.out.println("10. Count Total Books");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            String title, author, genre;
            int id, pos;
            boolean available;

            switch (choice) {
                case 1:
                    System.out.print("Book Title: ");
                    title = sc.nextLine();
                    System.out.print("Author: ");
                    author = sc.nextLine();
                    System.out.print("Genre: ");
                    genre = sc.nextLine();
                    System.out.print("Book ID: ");
                    id = sc.nextInt();
                    System.out.print("Is Available (true/false): ");
                    available = sc.nextBoolean();
                    library.addAtBeginning(title, author, genre, id, available);
                    break;

                case 2:
                    System.out.print("Book Title: ");
                    title = sc.nextLine();
                    System.out.print("Author: ");
                    author = sc.nextLine();
                    System.out.print("Genre: ");
                    genre = sc.nextLine();
                    System.out.print("Book ID: ");
                    id = sc.nextInt();
                    System.out.print("Is Available (true/false): ");
                    available = sc.nextBoolean();
                    library.addAtEnd(title, author, genre, id, available);
                    break;

                case 3:
                    System.out.print("Position: ");
                    pos = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Book Title: ");
                    title = sc.nextLine();
                    System.out.print("Author: ");
                    author = sc.nextLine();
                    System.out.print("Genre: ");
                    genre = sc.nextLine();
                    System.out.print("Book ID: ");
                    id = sc.nextInt();
                    System.out.print("Is Available (true/false): ");
                    available = sc.nextBoolean();
                    library.addAtPosition(pos, title, author, genre, id, available);
                    break;

                case 4:
                    System.out.print("Enter Book ID to remove: ");
                    id = sc.nextInt();
                    boolean removed = library.removeByBookId(id);
                    System.out.println(removed ? "Book removed." : "Book not found.");
                    break;

                case 5:
                    System.out.print("Enter Book Title to search: ");
                    title = sc.nextLine();
                    library.searchByTitle(title);
                    break;

                case 6:
                    System.out.print("Enter Author Name to search: ");
                    author = sc.nextLine();
                    library.searchByAuthor(author);
                    break;

                case 7:
                    System.out.print("Enter Book ID: ");
                    id = sc.nextInt();
                    System.out.print("New Availability (true/false): ");
                    available = sc.nextBoolean();
                    boolean updated = library.updateAvailability(id, available);
                    System.out.println(updated ? "Availability updated." : "Book not found.");
                    break;

                case 8:
                    library.displayForward();
                    break;

                case 9:
                    library.displayReverse();
                    break;

                case 10:
                    System.out.println("Total Books: " + library.countBooks());
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);

        sc.close();
    }
}
