package ua.com.alevel.service;

import ua.com.alevel.dao.impl.StudentDaoImpl;
import ua.com.alevel.data.PaginationData;
import ua.com.alevel.entity.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

public class FindAllStudents {
    static final int SIZE_OF_DATA_IN_PAGE = 10;
    static final String SORT = "id";
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    StudentDaoImpl studentDaoImpl = new StudentDaoImpl();

    public void startFind() {
        try {
            System.out.println("Enter page count: ");
            Integer pageNumber = Integer.valueOf(bufferedReader.readLine());
            System.out.println("If u wanna sort by arc press 1");
            System.out.println("If u wanna desc by desc press 2");
            Integer choseSortBy = Integer.valueOf(bufferedReader.readLine());
            String orderBy = "arc";
            if (choseSortBy == 1) {
                orderBy = "arc";
            }
            if (choseSortBy == 2) {
                orderBy = "desc";
            }
            findAllEmployee(pageNumber, orderBy);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void findAllEmployee(Integer pageNumber, String orderBy) {
        PaginationData data = new PaginationData();
        data.setPage(pageNumber);
        data.setSize(SIZE_OF_DATA_IN_PAGE);
        data.setSort(SORT);
        data.setOrderBy(orderBy);
        Collection<Student> collection = studentDaoImpl.findAll(data);
        for (Student student : collection) {
            System.out.println("employee = " + student);
        }
    }
}
