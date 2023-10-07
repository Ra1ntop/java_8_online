package ua.com.alevel.controller;

import ua.com.alevel.FilePath;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class MainController {
    private final boolean checker = false;
    public void start() throws IOException {
        createFile();
        inputFileReader(FilePath.INPUT_FILE_NAME.getFilePath());
    }

    private void createFile() {
        if (!checker) {
            String filePath = FilePath.INPUT_FILE_NAME.getFilePath();
            File file = new File(filePath);
            System.out.println("file = " + file.getPath());
            System.out.println("file = " + file.getAbsolutePath());
            System.out.println("isFile before create = " + file.isFile());
            boolean result = false;
            try {

                result = file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (result) {
                System.out.println("File was created");
                defaultInputFileWriter();
            } else {
                System.out.println("file exist");
            }
            System.out.println("isFile after create = " + file.isFile());

        }else {
            String filePath = FilePath.OUTPUT_FILE_NAME.getFilePath();
            File file = new File(filePath);
            System.out.println("file = " + file.getPath());
            System.out.println("file = " + file.getAbsolutePath());
            System.out.println("isFile before create = " + file.isFile());
            boolean result = false;
            try {

                result = file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (result) {
                System.out.println("File was created");
            } else {
                System.out.println("file exist");
            }
            System.out.println("isFile after create = " + file.isFile());
        }
    }

    private void defaultInputFileWriter(){
        String file = FilePath.INPUT_FILE_NAME.getFilePath();
        try(FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write("4\n");
            fileWriter.write("gdansk\n");
            fileWriter.write("2\n");
            fileWriter.write("2 1\n");
            fileWriter.write("3 3\n");
            fileWriter.write("bydgoszcz\n");
            fileWriter.write("3\n");
            fileWriter.write("1 1\n");
            fileWriter.write("3 1\n");
            fileWriter.write("4 4\n");
            fileWriter.write("torun\n");
            fileWriter.write("3\n");
            fileWriter.write("1 3\n");
            fileWriter.write("2 1\n");
            fileWriter.write("4 1\n");
            fileWriter.write("warszawa\n");
            fileWriter.write("2\n");
            fileWriter.write("2 4\n");
            fileWriter.write("3 1\n");
            fileWriter.write("2\n");
            fileWriter.write("gdansk warszawa\n");
            fileWriter.write("bydgoszcz warszawa\n");


        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }
    private void inputFileReader(String file){
        ValidateController validateController = new ValidateController();
        ArrayList arrayList = new ArrayList();
        try(
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            String text = "";
            while (bufferedReader.ready()) {
                text = bufferedReader.readLine();
                arrayList.add(text);
            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
        validateController.startValidate(arrayList);
    }
}
