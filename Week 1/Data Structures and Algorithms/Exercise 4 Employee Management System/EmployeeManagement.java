class Employee {
    private int employeeId;
    private String name;
    private String position;
    private double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public int getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getPosition() { return position; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return String.format("Employee { ID: %3d, Name: %-18s, Position: %-20s, Salary: $%8.2f }",
                employeeId, name, position, salary);
    }
}

class EmployeeArray {
    private Employee[] employees;
    private int size;

    public EmployeeArray(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }

    public boolean add(Employee employee) {
        if (size == employees.length) {
            System.out.println("Array is full. Cannot add: " + employee.getName());
            return false;
        }
        employees[size++] = employee;
        System.out.println("Added: " + employee);
        return true;
    }

    public Employee searchById(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == id) {
                return employees[i];
            }
        }
        return null;
    }

    public void traverse() {
        if (size == 0) {
            System.out.println("No employees on record.");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.println("  [" + i + "] " + employees[i]);
        }
    }

    public boolean delete(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == id) {
                Employee removed = employees[i];
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--size] = null;
                System.out.println("Deleted: " + removed);
                return true;
            }
        }
        System.out.println("Employee ID " + id + " not found.");
        return false;
    }

    public int size() { return size; }
}

public class EmployeeManagement {
    public static void main(String[] args) {
        System.out.println("=== Employee Management System ===");

        EmployeeArray emp = new EmployeeArray(10);
        emp.add(new Employee(101, "Ashwani Arya",  "Software Engineer",   85000));
        emp.add(new Employee(102, "Priya Mehta",   "Product Manager",    110000));
        emp.add(new Employee(103, "Rahul Sharma",  "QA Engineer",         72000));
        emp.add(new Employee(104, "Anita Singh",   "DevOps Engineer",     95000));

        System.out.println("\nAll Employees:");
        emp.traverse();

        System.out.println("\nSearch ID=103: " + emp.searchById(103));
        System.out.println("Search ID=999: " + (emp.searchById(999) == null ? "Not found." : emp.searchById(999)));

        System.out.println();
        emp.delete(102);
        emp.delete(999);

        System.out.println("\nAfter deletion (" + emp.size() + " records):");
        emp.traverse();

        System.out.println("\nComplexity: Add=O(1), Search/Delete/Traverse=O(n).");
        System.out.println("Arrays suit fixed-size data needing O(1) index access.");
    }
}
