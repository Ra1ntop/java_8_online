package ua.com.alevel.service;

import ua.com.alevel.dao.impl.GroupDaoImpl;
import ua.com.alevel.dao.impl.StudentDaoImpl;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CreateGroupService {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    GroupDaoImpl groupDao = new GroupDaoImpl();
    public void startCreateGroup() {
        try {
            System.out.println("Enter Group Name ");
            String groupName = bufferedReader.readLine();

            createStudent(groupName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void createStudent(String groupName){
        Group group = new Group();
        group.setName(groupName);
        groupDao.create(group);
    }
}
