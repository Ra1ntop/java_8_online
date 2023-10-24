package ua.com.alevel.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class MainService {
    File file = new File("input.txt");


    public void startMainService(){
        if (checkIfFileExist()){
            System.out.println("file exist");
        }else {
            System.out.println("doesn't exist");
            createDefaultFile();
        }
    }
    private boolean checkIfFileExist(){
        boolean checker = false;
        if (file.exists()&file.isFile()){
            checker = true;
        }


        return checker;
    }
    private void createDefaultFile(){
        boolean result = false;
        try {

            result = file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (result) {
            System.out.println("File was created");
            defaultInputFileWriter();
        }

    }

    private void defaultInputFileWriter() {
        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write("6\n");
            fileWriter.write("gdansk\n");
            fileWriter.write("3\n");
            fileWriter.write("2 1\n");
            fileWriter.write("3 3\n");
            fileWriter.write("5 4\n");
            fileWriter.write("bydgoszcz\n");
            fileWriter.write("4\n");
            fileWriter.write("1 1\n");
            fileWriter.write("3 1\n");
            fileWriter.write("4 4\n");
            fileWriter.write("5 2\n");
            fileWriter.write("torun\n");
            fileWriter.write("3\n");
            fileWriter.write("1 3\n");
            fileWriter.write("2 1\n");
            fileWriter.write("4 1\n");
            fileWriter.write("warszawa\n");
            fileWriter.write("3\n");
            fileWriter.write("2 4\n");
            fileWriter.write("3 1\n");
            fileWriter.write("6 2\n");
            fileWriter.write("kiev\n");
            fileWriter.write("2\n");
            fileWriter.write("2 2\n");
            fileWriter.write("1 3\n");
            fileWriter.write("krakow\n");
            fileWriter.write("1\n");
            fileWriter.write("4 2\n");
            fileWriter.write("3\n");
            fileWriter.write("torun kiev\n");
            fileWriter.write("krakow gdansk\n");
            fileWriter.write("bydgoszcz warszawa");
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }
}
