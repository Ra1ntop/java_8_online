package ua.com.alevel.service;

import ua.com.alevel.dao.impl.GroupDaoImpl;
import ua.com.alevel.dao.impl.StudentDaoImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AttachEmployeeToDepartmentService {

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    StudentDaoImpl studentDao = new StudentDaoImpl();
    GroupDaoImpl groupDao = new GroupDaoImpl();

    public void startAttachEmployeeToDepartment() {
        try {
            System.out.println("Enter student id: ");
            Long studentId = Long.valueOf(bufferedReader.readLine());
            if (studentDao.isExit(studentId)) {
                System.out.println("Enter group id: ");
                Long groupId = Long.valueOf(bufferedReader.readLine());
                if (groupDao.isExit(groupId)) {
                    attachEmployeeToDepartment(studentId, groupId);
                    System.out.println("Was added");
                } else {
                    System.out.println("We can't find group with id = " + groupId);

                }

            } else {
                System.out.println("We can't find student with id = " + studentId);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void attachEmployeeToDepartment(Long studentId, Long groupId) {
        groupDao.addStudentsToGroup(groupId, studentId);
    }
}
