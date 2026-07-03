public class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    public String getId() { return model.getId(); }
    public void setId(String id) { model.setId(id); }

    public String getName() { return model.getName(); }
    public void setName(String name) { model.setName(name); }

    public String getGrade() { return model.getGrade(); }
    public void setGrade(String grade) { model.setGrade(grade); }

    public void updateView() {
        view.displayStudentDetails(model.getId(), model.getName(), model.getGrade());
    }
}
