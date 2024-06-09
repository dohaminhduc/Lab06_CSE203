import java.io.File;
import java.util.ArrayList;

public class StudentManager {
    ArrayList<Student> students;
    public StudentManager() {
        students = new ArrayList<>();
        if (FileUtils.fileExists("Student.DAT")){
            students.addAll(FileUtils.readStudentFromFile("Student.DAT"));
        }
    }
    public boolean addStudent(Student student) {
        for(Student s : students) {
            if (s.getStudentID().equals(student.getStudentID())) {
                return false;
            }
        }
        students.add(student);
        FileUtils.writeStudentsToFile("Student.DAT", students);
        return true;
    }
    public Student findStudentByID(String studentID) {
        for(Student s : students) {
            if (s.getStudentID().equals(studentID)) {
                return s;
            }
        }
        return null;
    }
    public ArrayList<Student> getStudents() {
        return students;
    }
    public void deleteStudent(String studentID) {
        students.remove(findStudentByID(studentID));
        FileUtils.writeStudentsToFile("Student.DAT", students);
    }
}
