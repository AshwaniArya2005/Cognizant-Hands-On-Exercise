public class MVCPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== MVC Pattern Implementation Demo ===\n");

        Student student = new Student("S101", "Ashwani Arya", "A");
        StudentView view = new StudentView();
        StudentController controller = new StudentController(student, view);

        System.out.println("--- Initial Student Record ---");
        controller.updateView();

        System.out.println("\n--- Updating Name and Grade ---");
        controller.setName("Rahul Sharma");
        controller.setGrade("A+");
        controller.updateView();

        System.out.println("\n--- Adding New Student ---");
        Student student2 = new Student("S102", "Priya Mehta", "B+");
        StudentController controller2 = new StudentController(student2, view);
        controller2.updateView();

        System.out.println("\n=============================================");
        System.out.println("SUCCESS: MVC Pattern working correctly!");
        System.out.println("=============================================");
    }
}
