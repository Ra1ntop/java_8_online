package ua.com.alevel.controller;

import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.GroupCrudService;
import ua.com.alevel.service.StudentCrudService;
import ua.com.alevel.service.impl.GroupCrudServiceImpl;
import ua.com.alevel.service.impl.StudentCrudServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Set;

public class MainController {

    final StudentCrudService studentCrudService = new StudentCrudServiceImpl();
    final GroupCrudService groupCrudService = new GroupCrudServiceImpl();

    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        menu();
        String position;
        while (true) {
            try {
                if (!((position = reader.readLine()) != null)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                crud(position, reader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            menu();
        }
    }

    private void menu() {
        System.out.println("---------------------------------------------");
        System.out.println("1. Створити студента");
        System.out.println("2. UPDATE");
        System.out.println("3. DELETE Student");
        System.out.println("4. findByIDStudent");
        System.out.println("5. findAllStudents");
        System.out.println("6. Create Group");
        System.out.println("7. deleteGroup");
        System.out.println("8. findByIdGroup");
        System.out.println("9. findAllGroups");
        System.out.println("10. addStudentToGroup");
        System.out.println("11. deleteStudentFromGroup");
        System.out.println("12. findAllStudentsInGroup");
        System.out.println("13. findAllGroupsForStudent");
        System.out.println("0. Exit");
        System.out.println("---------------------------------------------");
    }


    private void crud(String position, BufferedReader reader) throws IOException {

        switch (position) {
            case "1":
                createStudent(reader);
                break;
            case "2":
                updateStudent(reader);
                break;
            case "3":
                deleteStudent(reader);
                break;
            case "4":
                findByIDStudent(reader);
                break;
            case "5":
                findAllStudents();
                break;
            case "6":
                createGroup(reader);
                break;
            case "7":
                deleteGroup(reader);
                break;
            case "8":
                findByIdGroup(reader);
                break;
            case "9":
                findAllGroups();
                break;
            case "10":
                addStudentToGroup(reader);
                break;
            case "11":
                deleteStudentFromGroup(reader);
                break;
            case "12":
                findAllStudentsInGroup(reader);
                break;
            case "13":
                findAllGroupsForStudent(reader);
                break;
            case "0":
                System.exit(0);

        }
    }


    private void createStudent(BufferedReader reader) throws IOException {
        System.out.println("Enter first name");
        String fn = reader.readLine();
        System.out.println("Enter last name");
        String ln = reader.readLine();
        int age;
        System.out.println("Enter age");
        try {
            age = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Entered value in not a number.Try again");
            return;
        }
        Student student = new Student();
        student.setFirstName(fn);
        student.setLastName(ln);
        student.setAge(age);
        studentCrudService.create(student);
    }

    private void updateStudent(BufferedReader reader) throws IOException {
        System.out.println("enter id");
        Long id = Long.valueOf(reader.readLine());
        System.out.println("Enter first name");
        String fn = reader.readLine();
        System.out.println("Enter last name");
        String ln = reader.readLine();
        int age;
        System.out.println("Enter age");
        try {
            age = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println("Entered value in not a number.Try again");
            return;
        }
        Student student = new Student();
        student.setFirstName(fn);
        student.setLastName(ln);
        student.setAge(age);
        student.setId(id);
        studentCrudService.update(student);
    }

    private void deleteStudent(BufferedReader reader) throws IOException {
        System.out.println("Enter student id");
        Long id = Long.valueOf(reader.readLine());
        studentCrudService.delete(id);
    }

    private void findByIDStudent(BufferedReader reader) throws IOException {
        System.out.println("enter id");
        Long id = Long.valueOf(reader.readLine());
        Student student = studentCrudService.findById(id);
        System.out.println("----");
        System.out.println("Id = " + student.getId());
        System.out.println("First name = " + student.getFirstName());
        System.out.println("Last name = " + student.getLastName());
        System.out.println("Age = " + student.getAge());
        System.out.println("----");
    }

    private void findAllStudents() {
        Collection<Student> collection = studentCrudService.findAll();
        for (Student student : collection) {
            System.out.println("student = " + student);
        }
    }

    private void createGroup(BufferedReader reader) throws IOException {
        System.out.println("Enter group name");
        String gn = reader.readLine();
        Group group = new Group();
        group.setGroupName(gn);
        groupCrudService.create(group);
    }

    private void deleteGroup(BufferedReader reader) throws IOException {
        System.out.println("Enter group id");
        Long id = Long.valueOf(reader.readLine());
        groupCrudService.delete(id);
    }

    private void findByIdGroup(BufferedReader reader) throws IOException {
        System.out.println("enter id");
        Long id = Long.valueOf(reader.readLine());
        Group group = groupCrudService.findById(id);
        System.out.println("----");
        System.out.println("Id = " + group.getId());
        System.out.println("Group name = " + group.getGroupName());
        System.out.println("----");
    }

    private void findAllGroups() {
        Collection<Group> collection = groupCrudService.findAll();
        for (Group group : collection) {
            System.out.println("group = " + group);
        }
    }

    public void addStudentToGroup(BufferedReader reader) throws IOException {
        System.out.println("Enter student id");
        long stId = Integer.parseInt(reader.readLine());
        System.out.println("Enter group id");
        long grId = Integer.parseInt(reader.readLine());
        groupCrudService.addStudentToGroup(grId, stId);
    }

    public void deleteStudentFromGroup(BufferedReader reader) throws IOException {
        System.out.println("Enter student id");
        long stId = Integer.parseInt(reader.readLine());
        System.out.println("Enter group id");
        long grId = Integer.parseInt(reader.readLine());
        groupCrudService.deleteStudentFromGroup(grId, stId);
    }

    private void findAllStudentsInGroup(BufferedReader reader) throws IOException {
        System.out.println("Enter group id");
        long grId = Integer.parseInt(reader.readLine());
        Set<Student> students = groupCrudService.findById(grId).getStudents();
        students.forEach(s -> System.out.println(s.toString()));
    }

    private void findAllGroupsForStudent(BufferedReader reader) throws IOException {
        System.out.println("Enter student id");
        long stId = Integer.parseInt(reader.readLine());
        Set<Group> groups = studentCrudService.findById(stId).getGroups();
        groups.forEach(g -> System.out.println(g.toString()));
    }
}
