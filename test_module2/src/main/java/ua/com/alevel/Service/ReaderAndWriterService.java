package ua.com.alevel.Service;

import ua.com.alevel.Entity.BaseEntity;
import ua.com.alevel.Entity.City;

import java.io.*;
import java.util.*;

public class ReaderAndWriterService {
    private final String INPUT_FILE_NAME;

    public List<String> startReadAndCreate() {
        File file = new File(INPUT_FILE_NAME);
        createFile(file);
        List<String> inputStringList = new ArrayList<>(inputFileReader(file));
        System.out.println("startReadAndCreate inputStringList " + inputStringList.size());
        readInputFile();
        return inputStringList;
    }

    private void createFile(File file) {

        System.out.println("file = " + file.getPath());
        System.out.println("file = " + file.getAbsolutePath());
        System.out.println("file exist = " + file.exists());
        System.out.println("isFile before create = " + file.isFile());

        boolean result = false;
        try {

            result = file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (result) {
            System.out.println("File was created");
            defaultInputFileWriter(file);

        } else {
            System.out.println("file exist");
        }
        System.out.println("isFile after create = " + file.isFile());
    }

    private void defaultInputFileWriter(File file) {
        try (FileWriter fileWriter = new FileWriter(file, false)) {
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
            fileWriter.write("bydgoszcz warszawa");
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    private List<String> inputFileReader(File file) {
        List<String> inputStringList = new ArrayList<>();
        try (
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            String text = "";
            while (bufferedReader.ready()) {
                text = bufferedReader.readLine();
                inputStringList.add(text);

            }
        } catch (IOException e) {
            System.out.println("e = " + e.getMessage());
        }
        System.out.println("inputStringList.size() = " + inputStringList.size());
        return inputStringList;

    }
    Map<String, City> cities = new TreeMap<>();
    Map<String, String> fromCityToEndCity = new TreeMap<>();
    private void readInputFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE_NAME));
            int numbersOfCity = Integer.parseInt(reader.readLine());

            for (int i = 0; i < numbersOfCity; i++) {
                String cityName = reader.readLine();
                City city = new City(cityName);
                city.setId(i+1);
                int numbersOfNeighbors = Integer.parseInt(reader.readLine());
                for (int j = 0; j < numbersOfNeighbors; j++) {
                    String[] neighborData = reader.readLine().split(" ");
                    int neighborName = Integer.parseInt(neighborData[0]);
                    int cost = Integer.parseInt(neighborData[1]);
                    city.setWaysAndCostMap(neighborName,cost);
                }
                cities.put(cityName, city);
            }
            int r = Integer.parseInt(reader.readLine());


            for (int i = 0; i < r; i++) {
                String[] path = reader.readLine().split(" ");
                String startCity = path[0];
                String endCity = path[1];
                fromCityToEndCity.put(startCity,endCity);
            }
            printAllData();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void printAllData() {
        System.out.println("Cities:");
        for (Map.Entry<String, City> item : cities.entrySet()) {
            System.out.println("k: "+ item.getKey());
            System.out.println("v: " + item.getValue().getNameCity() + " " + item.getValue().getId() + " " + item.getValue().getWaysAndCostMap());
        }

        System.out.println("From City to End City:");
        for (Map.Entry<String, String> item : fromCityToEndCity.entrySet()) {
            System.out.printf("Key: %s  Value: %s \n", item.getKey(), item.getValue());
        }
    }




    public ReaderAndWriterService(String INPUT_FILE_NAME) {
        this.INPUT_FILE_NAME = INPUT_FILE_NAME;
    }
}
