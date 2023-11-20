package ua.com.alevel.service;

import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.dao.impl.StudentDaoImpl;
import ua.com.alevel.entity.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateStudentService {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    StudentDaoImpl studentDaoImpl = new StudentDaoImpl();

    public void startCreateStudent() {
        try {
            System.out.println("Enter First Name ");
            String fn = bufferedReader.readLine();
            System.out.println("Enter Last Name ");
            String ln = bufferedReader.readLine();
            System.out.println("Enter age");
            Integer age = Integer.valueOf(bufferedReader.readLine());
            createStudent(fn, ln, age);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createStudent(String fn, String ln, Integer age) {
        Student student = new Student();
        student.setFirstName(fn);
        student.setLastName(ln);
        student.setAge(age);
        studentDaoImpl.create(student);
    }
}
