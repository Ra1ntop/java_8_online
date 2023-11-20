package ua.com.alevel;



import ua.com.alevel.controller.MainController;
import ua.com.alevel.factory.JdbcFactory;

import java.io.IOException;

public class Hw10Main {
    public static void main(String[] args) throws IOException {
        JdbcFactory.getInstance().initDB(Hw10Main.class);
        MainController mainController = new MainController();
        mainController.start();
    }
}