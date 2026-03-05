import java.util.ArrayList;

public class Course {
    private String courseCode;
    private String courseName;
    private int credits;
    private ArrayList<Student> enrolledStudents;
    private ArrayList<Double> grades;
    
    public Course(String courseCode, String courseName, int credits) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.enrolledStudents = new ArrayList<>();
        this.grades = new ArrayList<>();
    }
    
    // Getters
    public String getCourseCode() {
        return courseCode;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public int getCredits() {
        return credits;
    }
    
    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }
    
    public ArrayList<Double> getGrades() {
        return grades;
    }
    
    // Setters
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public void setCredits(int credits) {
        this.credits = credits;
    }
    
    // Methods
    public void enrollStudent(Student s) {
        enrolledStudents.add(s);
        grades.add(0.0); // Initial grade
        System.out.println(s.getName() + " enrolled in " + courseName);
    }
    
    public void assignGrade(int studentIndex, double grade) {
        if (studentIndex >= 0 && studentIndex < grades.size()) {
            grades.set(studentIndex, grade);
            System.out.println("Grade " + grade + " assigned to " + 
                             enrolledStudents.get(studentIndex).getName());
        } else {
            System.out.println("Invalid student index!");
        }
    }
    
    public void displayCourseInfo() {
        System.out.println("\nCourse: " + courseCode + " - " + courseName);
        System.out.println("Credits: " + credits);
        System.out.println("Enrolled Students: " + enrolledStudents.size());
        
        for (int i = 0; i < enrolledStudents.size(); i++) {
            System.out.println("  " + (i+1) + ". " + enrolledStudents.get(i).getName() + 
                             " - Grade: " + grades.get(i));
        }
    }
}