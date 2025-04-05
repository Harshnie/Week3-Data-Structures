import java.util.*;

class User {
    int userId;
    String name;
    int age;
    List<Integer> friends;
    User next;

    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friends = new ArrayList<>();
        this.next = null;
    }
}

class SocialNetwork {
    private User head = null;

    public void addUser(int userId, String name, int age) {
        User newUser = new User(userId, name, age);
        if (head == null) {
            head = newUser;
        } else {
            User temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newUser;
        }
    }

    public User findUserById(int userId) {
        User temp = head;
        while (temp != null) {
            if (temp.userId == userId) return temp;
            temp = temp.next;
        }
        return null;
    }

    public void addFriendConnection(int userId1, int userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);
        if (user1 == null || user2 == null || userId1 == userId2) return;

        if (!user1.friends.contains(userId2)) user1.friends.add(userId2);
        if (!user2.friends.contains(userId1)) user2.friends.add(userId1);
    }

    public void removeFriendConnection(int userId1, int userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);
        if (user1 == null || user2 == null) return;

        user1.friends.remove((Integer) userId2);
        user2.friends.remove((Integer) userId1);
    }

    public void displayFriends(int userId) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("Friends of " + user.name + " (ID: " + userId + "):");
        for (int friendId : user.friends) {
            User friend = findUserById(friendId);
            if (friend != null) {
                System.out.println("ID: " + friend.userId + ", Name: " + friend.name);
            }
        }
    }

    public void findMutualFriends(int userId1, int userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);
        if (user1 == null || user2 == null) return;

        System.out.println("Mutual Friends:");
        for (int id : user1.friends) {
            if (user2.friends.contains(id)) {
                User mutual = findUserById(id);
                if (mutual != null) {
                    System.out.println("ID: " + mutual.userId + ", Name: " + mutual.name);
                }
            }
        }
    }

    public void searchUserById(int userId) {
        User user = findUserById(userId);
        if (user != null) {
            System.out.println("User Found: ID: " + user.userId + ", Name: " + user.name + ", Age: " + user.age);
        } else {
            System.out.println("User not found.");
        }
    }

    public void searchUserByName(String name) {
        User temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                System.out.println("User Found: ID: " + temp.userId + ", Name: " + temp.name + ", Age: " + temp.age);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("User not found.");
    }

    public void countFriendsOfAllUsers() {
        User temp = head;
        while (temp != null) {
            System.out.println("User " + temp.name + " (ID: " + temp.userId + ") has " + temp.friends.size() + " friends.");
            temp = temp.next;
        }
    }

    public void displayAllUsers() {
        User temp = head;
        while (temp != null) {
            System.out.println("User ID: " + temp.userId + ", Name: " + temp.name + ", Age: " + temp.age);
            temp = temp.next;
        }
    }
}

public class MainSocialNetwork {
    public static void main(String[] args) {
        SocialNetwork network = new SocialNetwork();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Social Media Friend Connection System ---");
            System.out.println("1. Add User");
            System.out.println("2. Add Friend Connection");
            System.out.println("3. Remove Friend Connection");
            System.out.println("4. Display Friends");
            System.out.println("5. Find Mutual Friends");
            System.out.println("6. Search User by ID");
            System.out.println("7. Search User by Name");
            System.out.println("8. Count Friends of All Users");
            System.out.println("9. Display All Users");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            int id1, id2, age;
            String name;

            switch (choice) {
                case 1:
                    System.out.print("Enter User ID: ");
                    id1 = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    age = sc.nextInt();
                    network.addUser(id1, name, age);
                    break;
                case 2:
                    System.out.print("Enter Your User ID: ");
                    id1 = sc.nextInt();
                    System.out.print("Enter Friend's User ID: ");
                    id2 = sc.nextInt();
                    network.addFriendConnection(id1, id2);
                    break;
                case 3:
                    System.out.print("Enter Your User ID: ");
                    id1 = sc.nextInt();
                    System.out.print("Enter Friend's User ID to Remove: ");
                    id2 = sc.nextInt();
                    network.removeFriendConnection(id1, id2);
                    break;
                case 4:
                    System.out.print("Enter User ID to Display Friends: ");
                    id1 = sc.nextInt();
                    network.displayFriends(id1);
                    break;
                case 5:
                    System.out.print("Enter First User ID: ");
                    id1 = sc.nextInt();
                    System.out.print("Enter Second User ID: ");
                    id2 = sc.nextInt();
                    network.findMutualFriends(id1, id2);
                    break;
                case 6:
                    System.out.print("Enter User ID to Search: ");
                    id1 = sc.nextInt();
                    network.searchUserById(id1);
                    break;
                case 7:
                    System.out.print("Enter Name to Search: ");
                    name = sc.nextLine();
                    network.searchUserByName(name);
                    break;
                case 8:
                    network.countFriendsOfAllUsers();
                    break;
                case 9:
                    network.displayAllUsers();
                    break;
                case 0:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid option.");
            }

        } while (choice != 0);

        sc.close();
    }
}
