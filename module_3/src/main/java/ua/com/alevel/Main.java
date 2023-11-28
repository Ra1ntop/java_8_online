package ua.com.alevel;

import ua.com.alevel.controller.MainController;
import ua.com.alevel.factory.JdbcFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            JdbcFactory.getInstance().initDB(Main.class);
            MainController mainController = new MainController();
            mainController.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
