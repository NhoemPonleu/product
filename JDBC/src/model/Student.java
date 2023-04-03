package model;

public record Student(String name, Integer age, Integer id){};

//public class Student {
//    private final String student_name;
//    private final Integer student_age;
//    private final Integer student_id;
//    public Student(String student_name, Integer student_age, Integer studentId) {
//        this.student_name = student_name;
//        this.student_age = student_age;
//        student_id = studentId;
//    }
//    public Student(){};
//    public String getStudent_name() {
//        return student_name;
//    }
//    public Integer getStudent_age(){
//        return student_age;
//    }
//    public Integer getStudent_id() {
//        return student_id;
//    }
//    @Override
//    public String toString() {
//        return ">>> student{" +
//                "student_name='" + student_name +'\'' +
//                ", student_age='" + student_age + '\'' +
//                ", student_id='" + student_id + '\'' +
//                '}';
//    }
//}
