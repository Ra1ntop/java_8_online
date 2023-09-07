package ua.com.alevel.controller;
import ua.com.alevel.db.GroupDb;

import ua.com.alevel.db.HumanDb;
import ua.com.alevel.entity.Human;
import ua.com.alevel.service.HumanService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainController {
    private HumanService humanService = new HumanService();
    private HumanDb humanDb = new HumanDb(); // Ваша база данных студентов
    private GroupDb groupDb = new GroupDb(10); // База данных групп
    private int currentGroupId = 1; // Идентификатор для новых групп
    public void start() throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        list();
        String p = "";
        while ((p = bufferedReader.readLine())!=null){

            Choose(p,bufferedReader);
            list();

        }
    }
    private void list(){
        System.out.println();
        System.out.println("If you want create student please enter 1");
        System.out.println("If you want find students please enter 2");
        System.out.println("If you want delete student please enter 3");
        System.out.println("If you want create group please enter 4");
        System.out.println("If you want delete group please enter 5");
        System.out.println("If you want to add student to group please enter 6");
        System.out.println("If you want to view students in a group please enter 7");
        System.out.println("If you want close app please enter 8");
    }
    private void Choose(String p, BufferedReader bufferedReader) throws IOException{
        switch (p){
            case "1" :
                System.out.println("CREATE");
                create(bufferedReader);
                break;
            case "2" :
                System.out.println("Find");
                findAll();
                break;
            case "3" : System.out.println("Del");
                delete(bufferedReader);
                break;
            case "4":
                System.out.println("Create Group");
                createGroup(bufferedReader);
                break;
            case "5":
                System.out.println("Delete Group");
                deleteGroup(bufferedReader);
                break;
            case "6":
                System.out.println("Add Student to Group");
                addStudentToGroup(bufferedReader);
                break;
            case "7":
                System.out.println("View Students in Group");
                viewStudentsInGroup(bufferedReader);
                break;
            case "8":
                System.out.println("Exiting the application.");
                System.exit(0);
                break;

        }
    }
    private void create(BufferedReader reader) throws IOException{
        System.out.println("First");
        String fn = reader.readLine();
        System.out.println("Last");
        String ln = reader.readLine();
        System.out.println("age");
        int age = Integer.parseInt(reader.readLine());
        humanService.creqte(fn, ln, age);
    }
    private void delete(BufferedReader reader) throws IOException {
        findAll();
        System.out.println("----------------------------------------------");
        System.out.println("Enter the ID of the person you want to delete:");
        String id = reader.readLine();
        humanService.deleqte(id);
        System.out.println("Person with ID " + id + " has been deleted.");

    }
    private void findAll(){
        Human[] humans = humanService.findAll();
        for (Human human : humans) {
            if (human != null && !human.isDeleted()) {
                System.out.println();
                System.out.println("----------------------------------------------");
                System.out.println("first name = " + human.getFirstName());
                System.out.println("last name = " + human.getLastName());
                System.out.println("groupId = " + human.getGroupId());
                System.out.println("age = " + human.getAge());
                System.out.println("id = " + human.getId());
                System.out.println("----------------------------------------------");
                System.out.println();
            } else {
                System.out.println("No one entity");
            }
        }
    }
    private void createGroup(BufferedReader reader) throws IOException {
        System.out.println("Group Name:");
        String groupName = reader.readLine();
        groupDb.createGroup(currentGroupId, groupName);
        System.out.println("Group created with ID: " + currentGroupId);
        currentGroupId++;
    }

    private void deleteGroup(BufferedReader reader) throws IOException {
        groupDb.printGroups();
        System.out.println("Enter the ID of the group you want to delete:");
        int groupId = Integer.parseInt(reader.readLine());
        groupDb.deleteGroup(groupId, humanDb);
        System.out.println("Group with ID " + groupId + " has been deleted.");
    }

    private void addStudentToGroup(BufferedReader reader) throws IOException {
        findAll();
        System.out.println("Enter the ID of the student you want to add to a group:");
        String studentId = reader.readLine();
        groupDb.printGroups();
        System.out.println("Enter the ID of the group where you want to add the student:");
        String groupId = reader.readLine();
        groupDb.addStudentToGroup(Integer.parseInt(studentId), Integer.parseInt(groupId), humanDb);
        System.out.println(" main controller " + Integer.parseInt(studentId)+ Integer.parseInt(groupId)+humanDb);
        System.out.println("Student with ID " + studentId + " has been added to group " + groupId);
    }

    private void viewStudentsInGroup(BufferedReader reader) throws IOException {
        groupDb.printGroups();
        System.out.println("Enter the ID of the group you want to view students in:");
        int groupId = Integer.parseInt(reader.readLine());
        groupDb.printStudentsInGroup(groupId, humanDb);
        Human[] students = humanDb.findAll();
        System.out.println("Students in HumanDb: " + students.length);
    }
}
