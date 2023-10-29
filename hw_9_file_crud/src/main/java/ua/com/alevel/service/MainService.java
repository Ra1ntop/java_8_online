package ua.com.alevel.service;

import ua.com.alevel.dao.impl.GroupCrudDaoImpl;
import ua.com.alevel.dao.impl.StudentCrudDaoImpl;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainService {

    private final StudentCrudDaoImpl studentCrudDao = new StudentCrudDaoImpl();
    private final GroupCrudDaoImpl groupCrudDao = new GroupCrudDaoImpl();
    private static int StudentCounter = 1;
    private static final int DEFAULT_GROUP_ID = -1;

    public void start() {
        createStudentsFile();
        createGroupsFile();
        list();
    }

    private void createStudentsFile() {
        File file = new File("students.csv");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createGroupsFile() {
        File file = new File("groups.csv");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void list() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("1. Створити студента");
            System.out.println("2. Видалити студента");
            System.out.println("3. Створити групу");
            System.out.println("4. Видалити групу");
            System.out.println("5. Додати студента до групи");
            System.out.println("6. Вивести всіх студентів");
            System.out.println("7. Вивести всі групи");
            System.out.println("8. Вивести студентів у групу");
            System.out.println("9. Вийти");

            int choice = 0;
            try {
                choice = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println("Введено неправильний формат даних. Будь ласка, введіть номер меню.");
                continue;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            switch (choice) {
                case 1:
                    createStudent(reader);
                    break;
                case 2:
                    deleteStudent(reader);
                    break;
                case 3:
                    createGroup(reader);
                    break;
                case 4:
                    deleteGroup(reader);
                    break;
                case 5:
                    addStudentToGroup(reader);
                    break;
                case 6:
                    listAllStudents();
                    break;
                case 7:
                    listAllGroups();
                    break;
                case 8:
                    listStudentsInGroup(reader);
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }


    private void createStudent(BufferedReader reader) {
        try {
            System.out.print("Введіть ім'я студента: ");
            String fn = reader.readLine();
            System.out.print("Введіть фамілію студента: ");
            String ln = reader.readLine();
            System.out.print("Введіть рік студента: ");
            int age = Integer.valueOf(reader.readLine());
            Student student = new Student();
            student.setName(fn);
            student.setLast_name(ln);
            student.setAge(age);
            student.setGroupId(DEFAULT_GROUP_ID);
            studentCrudDao.create(student);
            StudentCounter++;
            System.out.println("Студент створений.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteStudent(BufferedReader reader) {
        try {
            listAllStudents();
            System.out.print("Введіть ID студента, якого потрібно видалити: ");
            String StudentIdStr = null;
            StudentIdStr = reader.readLine();
            if (isNumeric(StudentIdStr)) {
                long studentId = Long.parseLong(StudentIdStr);
                studentCrudDao.delete(studentId);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createGroup(BufferedReader reader) {
        try {
            System.out.print("Введіть назву групи: ");
            String nameGroup = reader.readLine();
            Group group = new Group();
            group.setName(nameGroup);
            groupCrudDao.create(group);
            System.out.println("Студент створений.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteGroup(BufferedReader reader) {

        try {
            listAllGroups();
            System.out.print("Введіть ID групи, яку потрібно видалити: ");
            String groupIdStr = null;
            groupIdStr = reader.readLine();
            Collection<Group> groupArrayList = new ArrayList<>();
            Collection<Student> studentCollection = new ArrayList<>();
            if (isNumeric(groupIdStr)) {
                long groupId = Long.parseLong(groupIdStr);
                boolean groupExists = false;
                groupArrayList = groupCrudDao.findAll();
                studentCollection = studentCrudDao.findAll();
                groupCrudDao.delete(groupId);
                for (Student student : studentCollection) {
                    if (student.getGroupId() == groupId) {
                        student.setGroupId(-1);
                        studentCrudDao.update(student.getId(), student);
                    }
                }
            }
            System.out.println("Група видалена.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void addStudentToGroup(BufferedReader reader) {
        try {
            listAllStudents();
            System.out.print("Введіть ID студента: ");
            String StudentIdStr = reader.readLine();
            if (isNumeric(StudentIdStr)) {
                long StudentId = Long.parseLong(StudentIdStr);
                listAllGroups();
                System.out.print("Введіть ID групи: ");
                String groupIdStr = reader.readLine();

                if (isNumeric(groupIdStr)) {
                    long groupId = Long.parseLong(groupIdStr);

                    boolean StudentExists = false;
                    boolean groupExists = false;

                    if (studentCrudDao.existsById(StudentId)) {
                        StudentExists = true;
                        if (groupCrudDao.existsById(groupId)) {
                            groupExists = true;
                            Student student = studentCrudDao.findById(StudentId);
                            student.setGroupId(groupId);
                            studentCrudDao.update(StudentId, student);

                        }
                    }
                    if (!StudentExists) {
                        System.out.println("Student does not exist");
                    }
                    if (!groupExists) {
                        System.out.println("Group does not exist");
                    }

                } else {
                    System.out.println("Невірний формат ID групи. Введіть числове значення.");
                }
            } else {
                System.out.println("Невірний формат ID студента. Введіть числове значення.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void listAllStudents() {
        if (StudentCounter != 0) {
            System.out.println("Список студентів:");
            Collection<Student> studentsArrayList = new ArrayList<>();
            Collection<Group> groupCollection = new ArrayList<>();
            studentsArrayList = studentCrudDao.findAll();
            groupCollection = groupCrudDao.findAll();
            Collection<Group> finalGroupCollection = groupCollection;
            studentsArrayList.forEach(student -> {
                System.out.println("-------------------------------------------");
                System.out.println("id = " + student.getId() + " ");
                System.out.println("first name = " + student.getName() + " ");
                System.out.println("last name = " + student.getLast_name() + " ");
                System.out.println("age = " + student.getAge() + " ");
                System.out.println("group id = " + student.getGroupId() + " ");
                if (student.getGroupId() == -1) {
                    System.out.println("group name: " + "default group" + " ");
                } else {
                    finalGroupCollection.forEach(group -> {
                        if (group.getId() == student.getGroupId()) {
                            System.out.println("group name: " + group.getName() + " ");
                        }
                    });
                }
                System.out.println("-------------------------------------------");


            });
        } else {
            System.out.println("Студентов не создано");
        }
    }

    private void listAllGroups() {
        System.out.println("Список груп:");
        Collection<Group> groupArrayList = new ArrayList<>();
        groupArrayList = groupCrudDao.findAll();
        groupArrayList.forEach(group -> {
            System.out.println("-------------------------------------------");

            System.out.println("id = " + group.getId() + " ");
            System.out.println("first name = " + group.getName() + " ");
            System.out.println("-------------------------------------------");

        });
    }

    private void listStudentsInGroup(BufferedReader reader) {
        try {
            listAllGroups();
            System.out.print("Введіть ID групи для виводу студентів: ");
            String groupIdStr = null;
            AtomicBoolean groupExists = new AtomicBoolean(false);
            groupIdStr = reader.readLine();
            if (isNumeric(groupIdStr)) {
                int groupId = Integer.parseInt(groupIdStr);
                Collection<Student> studentsArrayList = new ArrayList<>();
                studentsArrayList = studentCrudDao.findAll();
                System.out.println("Cписок студентів у цій группі");
                studentsArrayList.forEach(student -> {

                    if (groupId == student.getGroupId()) {
                        groupExists.set(true);
                        System.out.println("-------------------------------------------");
                        System.out.println("id = " + student.getId() + " ");
                        System.out.println("first name = " + student.getName() + " ");
                        System.out.println("last name = " + student.getLast_name() + " ");
                        System.out.println("age = " + student.getAge() + " ");
                        System.out.println("group id = " + student.getGroupId() + " ");
                        System.out.println("-------------------------------------------");
                    }
                });
                if (!groupExists.get()) {
                    System.out.println("Група з таким ID не існує.");
                }
            } else {
                System.out.println("Невірний формат ID групи. Введіть числове значення.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return !str.isEmpty();
    }
}
