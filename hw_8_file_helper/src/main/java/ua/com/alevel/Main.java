package ua.com.alevel;

import ua.com.alevel.controller.FindOsController;
import ua.com.alevel.controller.MainController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
//        FindOsController findOsController = new FindOsController();
//        findOsController.findOs();
        MainController mainController =  new MainController();
        try {
            mainController.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}