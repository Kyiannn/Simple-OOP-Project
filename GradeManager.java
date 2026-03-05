import java.util.ArrayList;
import java.util.Scanner;

public class GradeManager {
    private ArrayList<Student> students;
    private ArrayList<Course> courses;
    private Scanner scanner;
    
    public GradeManager() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    // Getters
    public ArrayList<Student> getStudents() {
        return students;
    }
    
    public ArrayList<Course> getCourses() {
        return courses;
    }
    
    // Methods
    public void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter age: ");
        int age = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        
        Student newStudent = new Student(name, age, id);
        students.add(newStudent);
        System.out.println("Student added successfully!");
    }
    
    public void addCourse() {
        System.out.print("Enter course code: ");
        String code = scanner.nextLine();
        
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter units: ");
        int credits = Integer.parseInt(scanner.nextLine());
        
        Course newCourse = new Course(code, name, credits);
        courses.add(newCourse);
        System.out.println("Course added successfully!");
    }
    
    public void enrollStudent() {
        if (students.isEmpty()) {
            System.out.println("No students available! Please add a student first.");
            return;
        }
        
        if (courses.isEmpty()) {
            System.out.println("No courses available! Please add a course first.");
            return;
        }
        
        // Show students
        System.out.println("\n--- Students ---");
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            System.out.println(i + ". " + s.getName() + " (ID: " + s.getStudentId() + ")");
        }
        System.out.print("Select student number: ");
        int sIndex = Integer.parseInt(scanner.nextLine());
        
        // Show courses
        System.out.println("\n--- Courses ---");
        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            System.out.println(i + ". " + c.getCourseCode() + " - " + c.getCourseName());
        }
        System.out.print("Select course number: ");
        int cIndex = Integer.parseInt(scanner.nextLine());
        
        // Enroll
        Student selectedStudent = students.get(sIndex);
        Course selectedCourse = courses.get(cIndex);
        selectedCourse.enrollStudent(selectedStudent);
    }
    
    public void assignGrade() {
        if (courses.isEmpty()) {
            System.out.println("No courses available!");
            return;
        }
        
        // NOTE TO SELF: DISPLAY COURSES
        System.out.println("\n--- Courses ---");
        for (int i = 0; i < courses.size(); i++) {
            Course c = courses.get(i);
            System.out.println(i + ". " + c.getCourseCode() + " - " + c.getCourseName());
        }
        System.out.print("Select course number: ");
        int cIndex = Integer.parseInt(scanner.nextLine());
        
        Course selectedCourse = courses.get(cIndex);
        
        if (selectedCourse.getEnrolledStudents().isEmpty()) {
            System.out.println("No students enrolled in this course!");
            return;
        }
        
        // NOTE TO SELF: enroll lists of students
        System.out.println("\n--- Enrolled Students ---");
        ArrayList<Student> enrolled = selectedCourse.getEnrolledStudents();
        for (int i = 0; i < enrolled.size(); i++) {
            System.out.println((i+1) + ". " + enrolled.get(i).getName());
        }
        
        System.out.print("Enter student number to grade: ");
        int sIndex = Integer.parseInt(scanner.nextLine()) - 1;
        
        System.out.print("Enter grade (0-100): ");
        double grade = Double.parseDouble(scanner.nextLine());
        
        selectedCourse.assignGrade(sIndex, grade);
        calculateAllGPAs();
    }
    
    public void calculateAllGPAs() {
        // NOTE TO SELF: resets the gpa's to zero.
        for (Student s : students) {
            s.setGpa(0.0);
        }
        
        // NOTE TO SELF: Mao ni ang code para calculate ang mga gpa base sa student's course.
        for (Student s : students) {
            double totalPoints = 0;
            int totalCourses = 0;
            
            for (Course c : courses) {
                ArrayList<Student> enrolled = c.getEnrolledStudents();
                ArrayList<Double> grades = c.getGrades();
                
                for (int i = 0; i < enrolled.size(); i++) {
                    if (enrolled.get(i).getStudentId().equals(s.getStudentId())) {
                        double grade = grades.get(i);
                        if (grade > 0) {
                            totalPoints += grade;
                            totalCourses++;
                        }
                        break;
                    }
                }
            }
            
            if (totalCourses > 0) {
                double gpa = totalPoints / totalCourses;
                s.setGpa(gpa);
            }
        }
    }
    
    public void displayAll() {
        System.out.println("\n=== STUDENTS ===");
        if (students.isEmpty()) {
            System.out.println("No students in system");
        } else {
            for (Student s : students) {
                s.displayStudentInfo();
            }
        }
        
        System.out.println("\n=== COURSES ===");
        if (courses.isEmpty()) {
            System.out.println("No courses in system");
        } else {
            for (Course c : courses) {
                c.displayCourseInfo();
            }
        }
    }
    
    public void showMenu() {
        while (true) {
            System.out.println("\n=== STUDENT GRADE SYSTEM ===");
            System.out.println("1. Add Student");
            System.out.println("2. Add Course");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. Assign Grade");
            System.out.println("5. Display All Information");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            
            String choice = scanner.nextLine();
            
            if (choice.equals("1")) {
                addStudent();
            } else if (choice.equals("2")) {
                addCourse();
            } else if (choice.equals("3")) {
                enrollStudent();
            } else if (choice.equals("4")) {
                assignGrade();
            } else if (choice.equals("5")) {
                displayAll();
            } else if (choice.equals("0")) {
                System.out.println("Later alligator!");
                break;
            } else {
                System.out.println("Jarvis tell them they are wrong.");
            }
        }
    }
}