package ua.com.alevel.controller;

import ua.com.alevel.entity.Human;
import ua.com.alevel.service.HumanService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainController {
    private HumanService humanService = new HumanService();
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
        System.out.println("If you want del app please enter 3");
        System.out.println("If you want close app please enter 4");
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
            case "4" :
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
        for (int i = 0; i < humans.length; i++) {
            Human human = humans[i];
            if (human != null) {
                System.out.println();
                System.out.println("----------------------------------------------");
                System.out.println("first name = " + human.getFirstName());
                System.out.println("last name = " + human.getLastName());
                System.out.println("age = " + human.getAge());
                System.out.println("id = " + human.getId());
                System.out.println("----------------------------------------------");
                System.out.println();
            }
        }
    }
}
