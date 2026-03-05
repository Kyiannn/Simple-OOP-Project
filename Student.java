public class Student extends Person {
    private String studentId;
    private double gpa;
    
    public Student(String name, int age, String studentId) {
        super(name, age);
        this.studentId = studentId;
        this.gpa = 0.0;
    }
    
    // Getters
    public String getStudentId() {
        return studentId;
    }
    
    public double getGpa() {
        return gpa;
    }
    
    // Setters
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    
    public void displayStudentInfo() {
        System.out.println("Student: " + getName() + " (ID: " + studentId + ")");
        System.out.println("Age: " + getAge() + ", GPA: " + gpa);
    }
}