package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter line for 1st task");
        String nums = bufferedReader.readLine();
//        String s = "1w4tt!7";
        System.out.println(Service.findNum(nums));

        System.out.println("Please enter line for 2nd task");
        String letters = bufferedReader.readLine();

        Service.findLet(letters);
        System.out.println();

        System.out.println("Please enter number of lessons for 3d task");
        String lesson = bufferedReader.readLine();

//        String lesson = "3";
        Service.findTime(Integer.valueOf(lesson));
    }
}
