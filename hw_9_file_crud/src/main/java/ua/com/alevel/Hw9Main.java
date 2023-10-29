package ua.com.alevel;

import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.Student;
import ua.com.alevel.service.MainService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Hw9Main {


    public static void main(String[] args) throws IOException {
        MainService mainService = new MainService();
        mainService.start();
    }


}