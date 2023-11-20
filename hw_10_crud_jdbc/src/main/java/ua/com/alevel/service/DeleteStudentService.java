package ua.com.alevel.service;

import ua.com.alevel.dao.impl.StudentDaoImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteStudentService {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    StudentDaoImpl studentDaoImpl = new StudentDaoImpl();

    public void startDeleteStudent() {
        try {
            System.out.println("Enter student id: ");
            Long id = Long.valueOf(bufferedReader.readLine());

            deleteStudent(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteStudent(Long id) {
        studentDaoImpl.delete(id);
    }
}
