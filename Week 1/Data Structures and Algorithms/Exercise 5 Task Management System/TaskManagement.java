class Task {
    private int taskId;
    private String taskName;
    private String status;
    Task next;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
        this.next = null;
    }

    public int getTaskId() { return taskId; }
    public String getTaskName() { return taskName; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return String.format("Task { ID: %3d, Name: %-25s, Status: %s }",
                taskId, taskName, status);
    }
}

class TaskLinkedList {
    private Task head;
    private int size;

    public TaskLinkedList() {
        head = null;
        size = 0;
    }

    public void addFirst(Task task) {
        task.next = head;
        head = task;
        size++;
        System.out.println("Added (head): " + task);
    }

    public void addLast(Task task) {
        if (head == null) {
            head = task;
        } else {
            Task curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = task;
        }
        size++;
        System.out.println("Added (tail): " + task);
    }

    public Task searchById(int id) {
        Task curr = head;
        while (curr != null) {
            if (curr.getTaskId() == id) return curr;
            curr = curr.next;
        }
        return null;
    }

    public void traverse() {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }
        Task curr = head;
        int index = 0;
        while (curr != null) {
            System.out.println("  [" + index++ + "] " + curr);
            curr = curr.next;
        }
    }

    public boolean delete(int id) {
        if (head == null) {
            System.out.println("List is empty.");
            return false;
        }
        if (head.getTaskId() == id) {
            System.out.println("Deleted: " + head);
            head = head.next;
            size--;
            return true;
        }
        Task prev = head;
        Task curr = head.next;
        while (curr != null) {
            if (curr.getTaskId() == id) {
                prev.next = curr.next;
                curr.next = null;
                size--;
                System.out.println("Deleted: " + curr);
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        System.out.println("Task ID " + id + " not found.");
        return false;
    }

    public int size() { return size; }
}

public class TaskManagement {
    public static void main(String[] args) {
        System.out.println("=== Task Management System ===");

        TaskLinkedList list = new TaskLinkedList();
        list.addLast(new Task(1, "Design Database Schema", "In Progress"));
        list.addLast(new Task(2, "Implement REST APIs",    "Pending"));
        list.addLast(new Task(3, "Write Unit Tests",       "Pending"));
        list.addFirst(new Task(4, "Setup CI/CD Pipeline",  "Completed"));

        System.out.println("\nAll Tasks:");
        list.traverse();

        System.out.println("\nSearch ID=3: " + list.searchById(3));
        System.out.println("Search ID=99: " + (list.searchById(99) == null ? "Not found." : list.searchById(99)));

        System.out.println();
        list.delete(2);
        list.delete(99);

        System.out.println("\nAfter deletion (" + list.size() + " tasks):");
        list.traverse();

        System.out.println("\nComplexity: AddHead=O(1), AddTail/Search/Delete=O(n).");
        System.out.println("Linked lists allow dynamic sizing and O(1) head insertion.");
    }
}
