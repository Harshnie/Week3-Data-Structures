import java.util.Scanner;

class Item {
    String itemName;
    int itemId;
    int quantity;
    double price;
    Item next;

    public Item(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class Inventory {
    private Item head = null;

    public void addAtBeginning(String itemName, int itemId, int quantity, double price) {
        Item newItem = new Item(itemName, itemId, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    public void addAtEnd(String itemName, int itemId, int quantity, double price) {
        Item newItem = new Item(itemName, itemId, quantity, price);
        if (head == null) {
            head = newItem;
            return;
        }
        Item temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newItem;
    }

    public void addAtPosition(int position, String itemName, int itemId, int quantity, double price) {
        if (position <= 0 || head == null) {
            addAtBeginning(itemName, itemId, quantity, price);
            return;
        }
        Item newItem = new Item(itemName, itemId, quantity, price);
        Item temp = head;
        int index = 0;
        while (index < position - 1 && temp.next != null) {
            temp = temp.next;
            index++;
        }
        newItem.next = temp.next;
        temp.next = newItem;
    }

    public boolean removeByItemId(int itemId) {
        if (head == null) return false;
        if (head.itemId == itemId) {
            head = head.next;
            return true;
        }
        Item current = head;
        while (current.next != null) {
            if (current.next.itemId == itemId) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean updateQuantity(int itemId, int newQuantity) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) {
                temp.quantity = newQuantity;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void searchByItemId(int itemId) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) {
                System.out.println("Found: " + temp.itemName + ", ID: " + temp.itemId + ", Qty: " + temp.quantity + ", Price: " + temp.price);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    public void searchByItemName(String name) {
        Item temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.itemName.equalsIgnoreCase(name)) {
                System.out.println("Found: " + temp.itemName + ", ID: " + temp.itemId + ", Qty: " + temp.quantity + ", Price: " + temp.price);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("Item not found.");
    }

    public void displayTotalValue() {
        Item temp = head;
        double total = 0;
        while (temp != null) {
            total += temp.quantity * temp.price;
            temp = temp.next;
        }
        System.out.println("Total Inventory Value: $" + total);
    }

    public void displayAllItems() {
        Item temp = head;
        if (temp == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("Inventory Items:");
        while (temp != null) {
            System.out.println("Name: " + temp.itemName + ", ID: " + temp.itemId + ", Qty: " + temp.quantity + ", Price: " + temp.price);
            temp = temp.next;
        }
    }

    public void sortByItemName(boolean ascending) {
        head = mergeSort(head, "name", ascending);
    }

    public void sortByPrice(boolean ascending) {
        head = mergeSort(head, "price", ascending);
    }

    private Item mergeSort(Item head, String sortBy, boolean ascending) {
        if (head == null || head.next == null) return head;

        Item middle = getMiddle(head);
        Item nextOfMiddle = middle.next;
        middle.next = null;

        Item left = mergeSort(head, sortBy, ascending);
        Item right = mergeSort(nextOfMiddle, sortBy, ascending);

        return sortedMerge(left, right, sortBy, ascending);
    }

    private Item sortedMerge(Item a, Item b, String sortBy, boolean ascending) {
        Item result;
        if (a == null) return b;
        if (b == null) return a;

        boolean condition;
        if (sortBy.equals("name")) {
            condition = ascending ? a.itemName.compareToIgnoreCase(b.itemName) <= 0 : a.itemName.compareToIgnoreCase(b.itemName) > 0;
        } else {
            condition = ascending ? a.price <= b.price : a.price > b.price;
        }

        if (condition) {
            result = a;
            result.next = sortedMerge(a.next, b, sortBy, ascending);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next, sortBy, ascending);
        }

        return result;
    }

    private Item getMiddle(Item head) {
        if (head == null) return head;
        Item slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

public class MainInventory {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Inventory Management Menu ---");
            System.out.println("1. Add Item at Beginning");
            System.out.println("2. Add Item at End");
            System.out.println("3. Add Item at Position");
            System.out.println("4. Remove Item by ID");
            System.out.println("5. Update Quantity by ID");
            System.out.println("6. Search by ID");
            System.out.println("7. Search by Name");
            System.out.println("8. Display All Items");
            System.out.println("9. Display Total Inventory Value");
            System.out.println("10. Sort by Name");
            System.out.println("11. Sort by Price");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            String name;
            int id, qty, pos;
            double price;
            boolean asc;

            switch (choice) {
                case 1:
                    System.out.print("Item Name: ");
                    name = sc.nextLine();
                    System.out.print("Item ID: ");
                    id = sc.nextInt();
                    System.out.print("Quantity: ");
                    qty = sc.nextInt();
                    System.out.print("Price: ");
                    price = sc.nextDouble();
                    inventory.addAtBeginning(name, id, qty, price);
                    break;
                case 2:
                    System.out.print("Item Name: ");
                    name = sc.nextLine();
                    System.out.print("Item ID: ");
                    id = sc.nextInt();
                    System.out.print("Quantity: ");
                    qty = sc.nextInt();
                    System.out.print("Price: ");
                    price = sc.nextDouble();
                    inventory.addAtEnd(name, id, qty, price);
                    break;
                case 3:
                    System.out.print("Position: ");
                    pos = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Item Name: ");
                    name = sc.nextLine();
                    System.out.print("Item ID: ");
                    id = sc.nextInt();
                    System.out.print("Quantity: ");
                    qty = sc.nextInt();
                    System.out.print("Price: ");
                    price = sc.nextDouble();
                    inventory.addAtPosition(pos, name, id, qty, price);
                    break;
                case 4:
                    System.out.print("Enter ID to remove: ");
                    id = sc.nextInt();
                    boolean removed = inventory.removeByItemId(id);
                    System.out.println(removed ? "Item removed." : "Item not found.");
                    break;
                case 5:
                    System.out.print("Enter ID: ");
                    id = sc.nextInt();
                    System.out.print("New Quantity: ");
                    qty = sc.nextInt();
                    boolean updated = inventory.updateQuantity(id, qty);
                    System.out.println(updated ? "Quantity updated." : "Item not found.");
                    break;
                case 6:
                    System.out.print("Enter ID to search: ");
                    id = sc.nextInt();
                    inventory.searchByItemId(id);
                    break;
                case 7:
                    System.out.print("Enter Name to search: ");
                    name = sc.nextLine();
                    inventory.searchByItemName(name);
                    break;
                case 8:
                    inventory.displayAllItems();
                    break;
                case 9:
                    inventory.displayTotalValue();
                    break;
                case 10:
                    System.out.print("Sort Ascending (true/false): ");
                    asc = sc.nextBoolean();
                    inventory.sortByItemName(asc);
                    System.out.println("Sorted by Name.");
                    break;
                case 11:
                    System.out.print("Sort Ascending (true/false): ");
                    asc = sc.nextBoolean();
                    inventory.sortByPrice(asc);
                    System.out.println("Sorted by Price.");
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
