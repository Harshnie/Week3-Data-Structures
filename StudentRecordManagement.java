import java.util.Scanner;

public class StudentRecordManagement {
    Student head = null;

    public void addAtBeginning(int roll, String name, int age, String grade) {
        Student newStudent = new Student(roll, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }
    public void addAtEnd(int roll, String name, int age, String grade) {
        Student newStudent = new Student(roll, name, age, grade);
        if (head == null) {
            head = newStudent;
            return;
        }
        Student temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newStudent;
    }

    public void addAtPosition(int pos, int roll, String name, int age, String grade) {
        if (pos <= 1) {
            addAtBeginning(roll, name, age, grade);
            return;
        }

        Student newStudent = new Student(roll, name, age, grade);
        Student temp = head;

        for (int i = 1; i < pos - 1 && temp != null; i++) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Position out of range. Adding at end.");
            addAtEnd(roll, name, age, grade);
            return;
        }

        newStudent.next = temp.next;
        temp.next = newStudent;
    }

    public void deleteByRoll(int roll) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        if (head.rollNumber == roll) {
            head = head.next;
            System.out.println("Record deleted.");
            return;
        }

        Student temp = head;
        while (temp.next != null && temp.next.rollNumber != roll) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Student with Roll Number " + roll + " not found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("Record deleted.");
        }
    }


    public void searchByRoll(int roll) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNumber == roll) {
                System.out.println("Student Found:");
                System.out.println("Roll: " + temp.rollNumber + ", Name: " + temp.name +
                        ", Age: " + temp.age + ", Grade: " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll Number " + roll + " not found.");
    }

    public void updateGrade(int roll, String newGrade) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNumber == roll) {
                temp.grade = newGrade;
                System.out.println("Grade updated successfully.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Student with Roll Number " + roll + " not found.");
    }


    public void displayRecords() {
        if (head == null) {
            System.out.println("No student records available.");
            return;
        }

        Student temp = head;
        System.out.println("Student Records:");
        while (temp != null) {
            System.out.println("Roll: " + temp.rollNumber + ", Name: " + temp.name +
                    ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }


    public static void main(String[] args) {
        StudentRecordManagement srm = new StudentRecordManagement();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Record Menu:");
            System.out.println("1. Add at Beginning");
            System.out.println("2. Add at End");
            System.out.println("3. Add at Position");
            System.out.println("4. Delete by Roll Number");
            System.out.println("5. Search by Roll Number");
            System.out.println("6. Update Grade");
            System.out.println("7. Display All Records");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            int roll, age, pos;
            String name, grade;

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll, Name, Age, Grade: ");
                    roll = sc.nextInt();
                    name = sc.next();
                    age = sc.nextInt();
                    grade = sc.next();
                    srm.addAtBeginning(roll, name, age, grade);
                    break;
                case 2:
                    System.out.print("Enter Roll, Name, Age, Grade: ");
                    roll = sc.nextInt();
                    name = sc.next();
                    age = sc.nextInt();
                    grade = sc.next();
                    srm.addAtEnd(roll, name, age, grade);
                    break;
                case 3:
                    System.out.print("Enter Position, Roll, Name, Age, Grade: ");
                    pos = sc.nextInt();
                    roll = sc.nextInt();
                    name = sc.next();
                    age = sc.nextInt();
                    grade = sc.next();
                    srm.addAtPosition(pos, roll, name, age, grade);
                    break;
                case 4:
                    System.out.print("Enter Roll Number to delete: ");
                    roll = sc.nextInt();
                    srm.deleteByRoll(roll);
                    break;
                case 5:
                    System.out.print("Enter Roll Number to search: ");
                    roll = sc.nextInt();
                    srm.searchByRoll(roll);
                    break;
                case 6:
                    System.out.print("Enter Roll Number and new Grade: ");
                    roll = sc.nextInt();
                    grade = sc.next();
                    srm.updateGrade(roll, grade);
                    break;
                case 7:
                    srm.displayRecords();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
