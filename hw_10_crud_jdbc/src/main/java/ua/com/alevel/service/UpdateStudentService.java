package ua.com.alevel.service;

import ua.com.alevel.dao.impl.StudentDaoImpl;
import ua.com.alevel.entity.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UpdateStudentService {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    StudentDaoImpl studentDaoImpl = new StudentDaoImpl();

    public void startUpdateStudent() {
        try {
            System.out.println("");
            System.out.println("Enter First Name ");
            String fn = bufferedReader.readLine();
            System.out.println("Enter Last Name ");
            String ln = bufferedReader.readLine();
            System.out.println("Enter age");
            Integer age = Integer.valueOf(bufferedReader.readLine());
            System.out.println("Enter id: ");
            Long id = Long.valueOf(bufferedReader.readLine());
            updateStudent(fn, ln, age, id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateStudent(String fn, String ln, Integer age, Long id) {
        Student student = new Student();
        student.setFirstName(fn);
        student.setLastName(ln);
        student.setAge(age);
        student.setId(id);
        studentDaoImpl.update(student);
    }
}
