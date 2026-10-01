import java.util.ArrayList;

public class StudentService {
    private ArrayList<Student> students;

    public StudentService() {
        students = FileHandler.loadStudents();
    }

    // Next id = biggest existing id + 1
    private int generateId() {
        int max = 0;
        for (Student s : students) {
            if (s.getId() > max) {
                max = s.getId();
            }
        }
        return max + 1;
    }

    public void addStudent(String name, int age, String course) {
        Student student = new Student(generateId(), name, age, course);
        students.add(student);
        FileHandler.saveStudents(students);
        System.out.println("Student added with ID " + student.getId());
    }

    public void viewAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null; // not found
    }

    public boolean updateStudent(int id, String name, int age, String course) {
        Student s = findById(id);
        if (s == null) {
            return false;
        }
        s.setName(name);
        s.setAge(age);
        s.setCourse(course);
        FileHandler.saveStudents(students);
        return true;
    }

    public boolean deleteStudent(int id) {
        Student s = findById(id);
        if (s == null) {
            return false;
        }
        students.remove(s);
        FileHandler.saveStudents(students);
        return true;
    }
}