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
import java.util.ArrayList;
import java.util.List;

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
        while ((position = bufferedReader.readLine()) != null) {
            crud(position, bufferedReader);
            menu();
        }
    }

    public void menu() {
        System.out.println();
        System.out.println("If u wanna readDirectory press 1");
        System.out.println("If u wanna changeDirectory press 2");
        System.out.println("If u createDirectory press 3");
        System.out.println("If u wanna deleteDirectory press 4");
        System.out.println("If u wanna пошук файла чи папки по зазначеній директорії press 4");
        System.out.println("If u wanna пошук тексту в усіх файлах та папках по зазначеній директорії press 5");
        System.out.println("If u wanna exit press 6");
        System.out.println();
    }

    private void crud(String position, BufferedReader bufferedReader) throws IOException {
        switch (position) {
            case "1" -> readDirectory();
            case "2" -> changeDirectory();
            case "3" -> createDirectory();
            case "4" -> deleteDirectory();
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
            if (Files.exists(path1)) {
                setPathNow(Path.of(getPathNow() + "/" + choise + "/"));
                System.out.println("choise: " + choise);
                System.out.println(getPathNow());
            } else {
                System.out.println("Такой папки нет");
                changeDirectory();
            }

        } else if (resultChoise.equals("2")) {
            Path currentPath = Paths.get(String.valueOf(getPathNow()));
            Path parentPath = currentPath.getParent();
            if (parentPath != null) {
                setPathNow(parentPath);
                System.out.println("Перешли в папку выше:");
                System.out.println(getPathNow());
            } else {
                System.out.println("Вы находитесь в корневой директории, нельзя подняться выше.");
            }
        } else {
            System.out.println("Вы ввели не то число");
            changeDirectory();
        }

    }

    private void createDirectory() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите название папки которую хотите создать");
        Path name = null;
        try {
            name = Path.of(bufferedReader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Path path;
        if (name != null) {
            path = Path.of(getPathNow() + "/" + name + "/");
            if (!Files.exists(path)) {
                try {
                    Files.createDirectory(path);
                    System.out.println("Папка с названием " + name + " была успешно создана");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Папка с таким именем существует" + name);
            }
            System.out.println("path = " + path);
        }


    }

    //    private void deleteDirectory(){
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        List list = new ArrayList<>();
//        try (DirectoryStream<Path> stream = Files.newDirectoryStream(getPathNow())) {
//            for (Path entry : stream) {
//                list.add(entry);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Введите название папки которую хотите удалить");
//        //
//        Path nameDir = null;
//        try {
//            nameDir = Path.of(bufferedReader.readLine());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Path pathDir;
//        if (nameDir != null) {
//            pathDir = Path.of(getPathNow() + "/" + nameDir + "/");
//            if (Files.exists(pathDir)) {
//                try {
//                    Files.delete(pathDir);
//                    System.out.println("Папка с названием " + pathDir + " была успешно удалена");
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            } else {
//                System.out.println("Папка с таким именем не существует" + pathDir);
//            }
//            System.out.println("path = " + pathDir);
//        }
//    }
    private void deleteDirectory() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите название папки которую хотите удалить");
        String dirName;
        try {
            dirName = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Path dirPath = getPathNow().resolve(dirName);

        if (Files.exists(dirPath) && Files.isDirectory(dirPath)) {
            System.out.println("Папка " + dirName + " не пуста. Видалити всі файли та папку? (yes/no)");
            String userChoice;
            try {
                userChoice = bufferedReader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (userChoice.equals("yes")) {
                deleteFilesAndDirectories(dirPath);
                System.out.println("Папка " + dirName + " та всі її вміст видалено.");
            } else {
                System.out.println("Видалення скасовано.");
            }
        } else {
            System.out.println("Папка з таким ім'ям не існує або це не папка.");
        }
    }

    private void deleteFilesAndDirectories(Path directoryPath) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(directoryPath)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    deleteFilesAndDirectories(entry);
                } else {
                    Files.delete(entry);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Files.delete(directoryPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
