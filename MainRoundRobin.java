import java.util.*;

class Process {
    int processId;
    int burstTime;
    int priority;
    int remainingTime;
    int waitingTime;
    int turnaroundTime;
    Process next;

    public Process(int processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.next = null;
    }
}

class RoundRobinScheduler {
    private Process head = null;
    private Process tail = null;

    public void addProcess(int id, int burstTime, int priority) {
        Process newProcess = new Process(id, burstTime, priority);
        if (head == null) {
            head = tail = newProcess;
            newProcess.next = head;
        } else {
            tail.next = newProcess;
            newProcess.next = head;
            tail = newProcess;
        }
    }

    public void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in the queue.");
            return;
        }
        Process temp = head;
        do {
            System.out.println("Process ID: " + temp.processId + ", Burst Time: " + temp.burstTime + ", Priority: " + temp.priority + ", Remaining Time: " + temp.remainingTime);
            temp = temp.next;
        } while (temp != head);
    }

    public void simulate(int timeQuantum) {
        if (head == null) {
            System.out.println("No processes to schedule.");
            return;
        }

        int time = 0;
        Queue<Process> finishedProcesses = new LinkedList<>();
        Process current = head;

        while (head != null) {
            if (current.remainingTime > 0) {
                int execTime = Math.min(current.remainingTime, timeQuantum);
                current.remainingTime -= execTime;
                time += execTime;

                if (current.remainingTime == 0) {
                    current.turnaroundTime = time;
                    current.waitingTime = time - current.burstTime;
                    finishedProcesses.add(current);
                    removeProcessById(current.processId);
                    if (head == null) break;
                    current = current.next;
                } else {
                    current = current.next;
                }
            } else {
                current = current.next;
            }

            System.out.println("\nProcesses after time " + time + ":");
            displayProcesses();
        }

        int totalWaiting = 0, totalTurnaround = 0, count = finishedProcesses.size();

        System.out.println("\nFinal Process Times:");
        for (Process p : finishedProcesses) {
            System.out.println("Process ID: " + p.processId + " | Waiting Time: " + p.waitingTime + " | Turnaround Time: " + p.turnaroundTime);
            totalWaiting += p.waitingTime;
            totalTurnaround += p.turnaroundTime;
        }

        double avgWaiting = (double) totalWaiting / count;
        double avgTurnaround = (double) totalTurnaround / count;
        System.out.println("\nAverage Waiting Time: " + avgWaiting);
        System.out.println("Average Turnaround Time: " + avgTurnaround);
    }

    public void removeProcessById(int processId) {
        if (head == null) return;

        Process curr = head, prev = tail;

        do {
            if (curr.processId == processId) {
                if (curr == head && curr == tail) {
                    head = tail = null;
                } else {
                    prev.next = curr.next;
                    if (curr == head) head = curr.next;
                    if (curr == tail) tail = prev;
                }
                return;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != head);
    }
}

public class MainRoundRobin {
    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler();
        Scanner sc = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n--- Round Robin Scheduler ---");
            System.out.println("1. Add Process");
            System.out.println("2. Display Processes");
            System.out.println("3. Simulate Round Robin Scheduling");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            int id, burst, prio, quantum;

            switch (choice) {
                case 1:
                    System.out.print("Enter Process ID: ");
                    id = sc.nextInt();
                    System.out.print("Enter Burst Time: ");
                    burst = sc.nextInt();
                    System.out.print("Enter Priority: ");
                    prio = sc.nextInt();
                    scheduler.addProcess(id, burst, prio);
                    break;

                case 2:
                    scheduler.displayProcesses();
                    break;

                case 3:
                    System.out.print("Enter Time Quantum: ");
                    quantum = sc.nextInt();
                    scheduler.simulate(quantum);
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
