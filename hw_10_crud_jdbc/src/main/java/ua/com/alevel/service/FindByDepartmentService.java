package ua.com.alevel.service;

import ua.com.alevel.dao.impl.StudentDaoImpl;
import ua.com.alevel.entity.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class FindByDepartmentService {
    private final StudentDaoImpl studentDao = new StudentDaoImpl();
    private final StudentDaoImpl departmentDao = new StudentDaoImpl();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public void startFindByDepartment() {
        try {
            System.out.println("Enter group id: ");
            Long id = Long.valueOf(bufferedReader.readLine());

            find(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void find(Long id) {
        Collection<Student> employees = studentDao.findByDepartment(id);
        for (Student student : employees) {
            System.out.println("employee = " + student);
        }
    }
}
