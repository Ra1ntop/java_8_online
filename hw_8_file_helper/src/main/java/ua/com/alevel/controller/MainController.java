package ua.com.alevel.controller;

import ua.com.alevel.entity.OsNames;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainController {
    private Path pathNow;

    public Path getPathNow() {
        return pathNow;
    }

    public void setPathNow(Path pathNow) {
        this.pathNow = pathNow;
    }

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        welcomeMenu();
        setPathDefault();

        menu();
        String position = "";
        while ((position = bufferedReader.readLine())!=null){
            crud(position, bufferedReader);
            menu();
        }
    }

    public void menu() {
        System.out.println();
        System.out.println("If u wanna список всіх файлів та папок по зазначеній директорії press 1");
        System.out.println("If u wanna створення файла чи папки в зазначеній директорії press 2");
        System.out.println("If u wanna видалення файла чи папки в зазначеній директорії press 3");
        System.out.println("If u wanna переміщення файла чи папки із зазначеної директорії в зазначену директорію press 4");
        System.out.println("If u wanna пошук файла чи папки по зазначеній директорії press 4");
        System.out.println("If u wanna пошук тексту в усіх файлах та папках по зазначеній директорії press 5");
        System.out.println("If u wanna вихід з програми press 6");
        System.out.println();
    }

    private void crud(String position, BufferedReader bufferedReader) throws IOException {
        switch (position) {
            case "1" -> readDirectory();
            case "2" -> changeDirectory();
            case "3" -> System.out.println("position = " + position);
            case "4" -> System.out.println("position = " + position);
            case "5" -> System.out.println("position = " + position);
            case "6" -> System.exit(0);
        }
    }

    private void welcomeMenu() {
        String hello = "                                    Welcome to my app";
        FindOsController findOsController = new FindOsController();
        String osName = findOsController.getOsName();
        System.out.println(hello);
        System.out.println("                              You use " + osName + " operation system");
        String language = System.getProperty("user.language");
        System.out.println("Язык системы: " + language);
        //


    }

    private void setPathDefault() {
        if (System.getProperty("os.name").equals(OsNames.LinuxOs.getOsName())) {
            Path path = Paths.get("/home/" + System.getProperty("user.name"));
            System.out.println(path);
            System.out.println("file = " + path.toAbsolutePath());
            setPathNow(path);
        } else if (System.getProperty("os.name").equals(OsNames.MacOs.getOsName())) {
            Path path = Paths.get("/Users/" + System.getProperty("user.name") + "/");
            System.out.println("file = " + path.toAbsolutePath());
            setPathNow(path);
        }
//        } else if (System.getProperty("os.name").equals(OsNames.WindowsOs.getOsName())) {
//            Path path = Paths.get("/home/" + System.getProperty("user.name") + "/");
//            System.out.println("file = " + path.toAbsolutePath());
//            setPathNow(path);
//        }
    }

    private void readDirectory() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(getPathNow())) {
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void changeDirectory() {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Now u are in " + getPathNow());
        System.out.println("Если вы хотите зайти в папку ниже напишите 1");
        System.out.println("Если вы хотите зайти в папку выше напишите 2");
        String resultChoise;
        try {
            resultChoise = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (resultChoise.equals("1")) {
            readDirectory();
            System.out.println("Напишите название папки на которую хотите сменить");
            String choise;
            try {
                choise = bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String path = (getPathNow() + "/" + choise + "/");
            Path path1 = Paths.get(path);
            if (Files.exists(path1)){
                setPathNow(Path.of(getPathNow() + "/" + choise + "/"));
                System.out.println("choise: " + choise);
                System.out.println(getPathNow());
            }else {
                System.out.println("Такой папки нет");
                changeDirectory();
            }

        } else if (resultChoise.equals("2")) {
            Path currentPath = Paths.get(String.valueOf(getPathNow()));
            Path parentPath = currentPath.getParent();
            if (parentPath!=null){
                setPathNow(parentPath);
                System.out.println("Перешли в папку выше:");
                System.out.println(getPathNow());
            }else {
                System.out.println("Вы находитесь в корневой директории, нельзя подняться выше.");
            }
        }else {
            System.out.println("Вы ввели не то число");
            changeDirectory();
        }

    }

}
