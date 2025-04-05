import java.util.Scanner;

class MovieNode {
    String title;
    String director;
    int year;
    double rating;
    MovieNode prev;
    MovieNode next;

    public MovieNode(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.prev = null;
        this.next = null;
    }
}

class MovieManagementSystem {
    private MovieNode head;
    private MovieNode tail;

    public void addMovieBeginning(String title, String director, int year, double rating) {
        MovieNode newNode = new MovieNode(title, director, year, rating);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    public void addMovieEnd(String title, String director, int year, double rating) {
        MovieNode newNode = new MovieNode(title, director, year, rating);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    public void addMovieAtPosition(int position, String title, String director, int year, double rating) {
        if (position <= 0) {
            addMovieBeginning(title, director, year, rating);
            return;
        }
        MovieNode newNode = new MovieNode(title, director, year, rating);
        MovieNode current = head;
        int index = 0;
        while (current != null && index < position) {
            current = current.next;
            index++;
        }
        if (current == null) {
            addMovieEnd(title, director, year, rating);
        } else {
            MovieNode prevNode = current.prev;
            newNode.next = current;
            newNode.prev = prevNode;
            if (prevNode != null) {
                prevNode.next = newNode;
            } else {
                head = newNode;
            }
            current.prev = newNode;
        }
    }

    public boolean removeMovieByTitle(String title) {
        MovieNode current = head;
        while (current != null) {
            if (current.title.equals(title)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void searchByDirector(String director) {
        MovieNode current = head;
        boolean found = false;
        while (current != null) {
            if (current.director.equalsIgnoreCase(director)) {
                System.out.println(current.title + ", " + current.director + ", " + current.year + ", " + current.rating);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No movies found by this director.");
        }
    }

    public void searchByRating(double rating) {
        MovieNode current = head;
        boolean found = false;
        while (current != null) {
            if (current.rating == rating) {
                System.out.println(current.title + ", " + current.director + ", " + current.year + ", " + current.rating);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No movies found with this rating.");
        }
    }

    public void displayForward() {
        MovieNode current = head;
        if (current == null) {
            System.out.println("No movies to display.");
            return;
        }
        while (current != null) {
            System.out.println(current.title + ", " + current.director + ", " + current.year + ", " + current.rating);
            current = current.next;
        }
    }

    public void displayReverse() {
        MovieNode current = tail;
        if (current == null) {
            System.out.println("No movies to display.");
            return;
        }
        while (current != null) {
            System.out.println(current.title + ", " + current.director + ", " + current.year + ", " + current.rating);
            current = current.prev;
        }
    }

    public boolean updateRating(String title, double newRating) {
        MovieNode current = head;
        while (current != null) {
            if (current.title.equals(title)) {
                current.rating = newRating;
                return true;
            }
            current = current.next;
        }
        return false;
    }
}

public class MovieManagerApp {
    public static void main(String[] args) {
        MovieManagementSystem system = new MovieManagementSystem();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMovie Management System");
            System.out.println("1. Add Movie at Beginning");
            System.out.println("2. Add Movie at End");
            System.out.println("3. Add Movie at Specific Position");
            System.out.println("4. Remove Movie by Title");
            System.out.println("5. Search Movie by Director");
            System.out.println("6. Search Movie by Rating");
            System.out.println("7. Display Movies Forward");
            System.out.println("8. Display Movies Reverse");
            System.out.println("9. Update Movie Rating");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            String title, director;
            int year, position;
            double rating;

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    title = sc.nextLine();
                    System.out.print("Enter director: ");
                    director = sc.nextLine();
                    System.out.print("Enter year: ");
                    year = sc.nextInt();
                    System.out.print("Enter rating: ");
                    rating = sc.nextDouble();
                    system.addMovieBeginning(title, director, year, rating);
                    break;
                case 2:
                    System.out.print("Enter title: ");
                    title = sc.nextLine();
                    System.out.print("Enter director: ");
                    director = sc.nextLine();
                    System.out.print("Enter year: ");
                    year = sc.nextInt();
                    System.out.print("Enter rating: ");
                    rating = sc.nextDouble();
                    system.addMovieEnd(title, director, year, rating);
                    break;
                case 3:
                    System.out.print("Enter position: ");
                    position = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter title: ");
                    title = sc.nextLine();
                    System.out.print("Enter director: ");
                    director = sc.nextLine();
                    System.out.print("Enter year: ");
                    year = sc.nextInt();
                    System.out.print("Enter rating: ");
                    rating = sc.nextDouble();
                    system.addMovieAtPosition(position, title, director, year, rating);
                    break;
                case 4:
                    System.out.print("Enter title to remove: ");
                    title = sc.nextLine();
                    boolean removed = system.removeMovieByTitle(title);
                    if (removed) System.out.println("Movie removed.");
                    else System.out.println("Movie not found.");
                    break;
                case 5:
                    System.out.print("Enter director to search: ");
                    director = sc.nextLine();
                    system.searchByDirector(director);
                    break;
                case 6:
                    System.out.print("Enter rating to search: ");
                    rating = sc.nextDouble();
                    system.searchByRating(rating);
                    break;
                case 7:
                    system.displayForward();
                    break;
                case 8:
                    system.displayReverse();
                    break;
                case 9:
                    System.out.print("Enter title to update rating: ");
                    title = sc.nextLine();
                    System.out.print("Enter new rating: ");
                    rating = sc.nextDouble();
                    boolean updated = system.updateRating(title, rating);
                    if (updated) System.out.println("Rating updated.");
                    else System.out.println("Movie not found.");
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 0);

        sc.close();
    }
}
