import java.util.Scanner;

class Task {
    int taskId;
    String taskName;
    int priority;
    String dueDate;
    Task next;

    public Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class TaskScheduler {
    private Task head = null;
    private Task tail = null;
    private Task current = null;

    public void addAtBeginning(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            tail.next = head;
            current = head;
        } else {
            newTask.next = head;
            head = newTask;
            tail.next = head;
        }
    }

    public void addAtEnd(int taskId, String taskName, int priority, String dueDate) {
        Task newTask = new Task(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = tail = newTask;
            tail.next = head;
            current = head;
        } else {
            tail.next = newTask;
            tail = newTask;
            tail.next = head;
        }
    }

    public void addAtPosition(int position, int taskId, String taskName, int priority, String dueDate) {
        if (position <= 0 || head == null) {
            addAtBeginning(taskId, taskName, priority, dueDate);
            return;
        }

        Task newTask = new Task(taskId, taskName, priority, dueDate);
        Task temp = head;
        int index = 0;
        while (index < position - 1 && temp.next != head) {
            temp = temp.next;
            index++;
        }

        newTask.next = temp.next;
        temp.next = newTask;

        if (temp == tail) {
            tail = newTask;
        }
    }

    public boolean removeByTaskId(int taskId) {
        if (head == null) return false;

        Task temp = head;
        Task prev = tail;

        do {
            if (temp.taskId == taskId) {
                if (temp == head && temp == tail) {
                    head = tail = current = null;
                } else {
                    prev.next = temp.next;
                    if (temp == head) head = head.next;
                    if (temp == tail) tail = prev;
                    if (temp == current) current = current.next;
                    tail.next = head;
                }
                return true;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);

        return false;
    }

    public void viewCurrentTask() {
        if (current == null) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Current Task:");
            System.out.println("ID: " + current.taskId);
            System.out.println("Name: " + current.taskName);
            System.out.println("Priority: " + current.priority);
            System.out.println("Due Date: " + current.dueDate);
            current = current.next;
        }
    }

    public void displayAllTasks() {
        if (head == null) {
            System.out.println("No tasks in the list.");
            return;
        }

        Task temp = head;
        System.out.println("All Tasks:");
        do {
            System.out.println("ID: " + temp.taskId + ", Name: " + temp.taskName + ", Priority: " + temp.priority + ", Due: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks to search.");
            return;
        }

        Task temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                System.out.println("ID: " + temp.taskId + ", Name: " + temp.taskName + ", Due: " + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("No task found with priority " + priority);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Task Scheduler Menu ---");
            System.out.println("1. Add Task at Beginning");
            System.out.println("2. Add Task at End");
            System.out.println("3. Add Task at Position");
            System.out.println("4. Remove Task by ID");
            System.out.println("5. View Current Task & Move to Next");
            System.out.println("6. Display All Tasks");
            System.out.println("7. Search by Priority");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            int id, priority, position;
            String name, due;

            switch (choice) {
                case 1:
                    System.out.print("Enter Task ID: ");
                    id = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Task Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Priority: ");
                    priority = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Due Date: ");
                    due = sc.nextLine();
                    scheduler.addAtBeginning(id, name, priority, due);
                    break;

                case 2:
                    System.out.print("Enter Task ID: ");
                    id = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Task Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Priority: ");
                    priority = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Due Date: ");
                    due = sc.nextLine();
                    scheduler.addAtEnd(id, name, priority, due);
                    break;

                case 3:
                    System.out.print("Enter Position: ");
                    position = sc.nextInt();
                    System.out.print("Enter Task ID: ");
                    id = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Task Name: ");
                    name = sc.nextLine();
                    System.out.print("Enter Priority: ");
                    priority = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Due Date: ");
                    due = sc.nextLine();
                    scheduler.addAtPosition(position, id, name, priority, due);
                    break;

                case 4:
                    System.out.print("Enter Task ID to remove: ");
                    id = sc.nextInt();
                    boolean removed = scheduler.removeByTaskId(id);
                    if (removed) System.out.println("Task removed.");
                    else System.out.println("Task not found.");
                    break;

                case 5:
                    scheduler.viewCurrentTask();
                    break;

                case 6:
                    scheduler.displayAllTasks();
                    break;

                case 7:
                    System.out.print("Enter Priority to search: ");
                    priority = sc.nextInt();
                    scheduler.searchByPriority(priority);
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);

        sc.close();
    }
}
