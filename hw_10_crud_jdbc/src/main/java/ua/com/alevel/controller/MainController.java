package ua.com.alevel.controller;

import ua.com.alevel.service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainController {
    CreateStudentService createStudentService = new CreateStudentService();
    UpdateStudentService updateStudentService = new UpdateStudentService();
    FindAllStudents findAllStudents = new FindAllStudents();
    DeleteStudentService  deleteStudentService = new DeleteStudentService();
    CreateGroupService createGroupService = new CreateGroupService();
    AttachEmployeeToDepartmentService attachEmployeeToDepartmentService = new AttachEmployeeToDepartmentService();
    FindByDepartmentService findByDepartmentService = new FindByDepartmentService();
    DeleteGroupService deleteGroupService = new DeleteGroupService();

    public void start(){

        list();
    }
    private void list() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("--------------------------------------");
            System.out.println("1. Створити студента");
            System.out.println("2. UPDATE");
            System.out.println("3. FIND ALL");
            System.out.println("4. DELETE Student");
            System.out.println("5. Create Group");
            System.out.println("6. attach Employee To Department");
            System.out.println("7. Find By Department");
            System.out.println("8. DeleteGroup");
            System.out.println("9. exit");

            System.out.println("--------------------------------------");
            System.out.println("");



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
                    createStudentService.startCreateStudent();
                    break;
                case 2:
                    updateStudentService.startUpdateStudent();
                    break;
                case 3:
                    findAllStudents.startFind();
                    break;
                case 4:
                    deleteStudentService.startDeleteStudent();
                    break;
                case 5:
                    createGroupService.startCreateGroup();
                    break;
                case 6:
                    attachEmployeeToDepartmentService.startAttachEmployeeToDepartment();
                    break;
                case 7:
                    findByDepartmentService.startFindByDepartment();
                    break;
                case 8:
                    deleteGroupService.startDeleteGroup();
                    break;
                case 9:
                    System.out.println("Closing...");
                    System.exit(2);
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
    }
}
