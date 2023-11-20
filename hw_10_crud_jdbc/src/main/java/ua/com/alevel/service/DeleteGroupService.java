package ua.com.alevel.service;

import ua.com.alevel.dao.impl.GroupDaoImpl;
import ua.com.alevel.dao.impl.StudentDaoImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DeleteGroupService {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    GroupDaoImpl groupDao = new GroupDaoImpl();

    public void startDeleteGroup() {
        try {
            System.out.println("Enter group id: ");
            Long id = Long.valueOf(bufferedReader.readLine());

            deleteGroup(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteGroup(Long id) {
        groupDao.delete(id);
    }
}
