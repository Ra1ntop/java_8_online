package ua.com.alevel.controller;

import ua.com.alevel.entity.City;
import ua.com.alevel.entity.Neighbor;
import ua.com.alevel.service.MainService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    MainService mainService = new MainService();
    public void start(){
        mainService.startMainService();
        read();
    }

    private void read(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            String line;
            int n = 0;

            // Чтение количества городов
            if ((line = reader.readLine()) != null) {
                try {
                    n = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка: Неверный формат числа городов.");
                    return;
                }
            }

            List<City> cities = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                // Чтение имени города
                String cityName = reader.readLine();
                if (cityName == null || cityName.trim().isEmpty()) {
                    System.err.println("Ошибка: Имя города не может быть пустым.");
                    return;
                }
                City city = new City(cityName);

                // Чтение количества соседей
                int p = 0;
                if ((line = reader.readLine()) != null) {
                    try {
                        p = Integer.parseInt(line);
                    } catch (NumberFormatException e) {
                        System.err.println("Ошибка: Неверный формат числа соседей для города " + cityName);
                        return;
                    }
                }

                for (int j = 0; j < p; j++) {
                    // Чтение данных о соседях
                    if ((line = reader.readLine()) != null) {
                        String[] neighborData = line.split(" ");
                        if (neighborData.length != 2) {
                            System.err.println("Ошибка: Неверный формат данных о соседе для города " + cityName);
                            return;
                        }

                        try {
                            int neighborIndex = Integer.parseInt(neighborData[0]) - 1;
                            int cost = Integer.parseInt(neighborData[1]);
                            city.getNeighbors().add(new Neighbor(neighborIndex, cost));
                        } catch (NumberFormatException e) {
                            System.err.println("Ошибка: Неверный формат данных о соседе для города " + cityName);
                            return;
                        }
                    } else {
                        System.err.println("Ошибка: Недостаточно данных о соседях для города " + cityName);
                        return;
                    }
                }
                cities.add(city);
            }

            int r = 0;
            // Чтение количества запросов
            if ((line = reader.readLine()) != null) {
                try {
                    r = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка: Неверный формат числа запросов.");
                    return;
                }
            }

            for (int i = 0; i < r; i++) {
                if ((line = reader.readLine()) != null) {
                    String[] pathData = line.split(" ");
                    if (pathData.length != 2) {
                        System.err.println("Ошибка: Неверный формат данных для запроса #" + (i + 1));
                        continue;
                    }

                    String startCityName = pathData[0];
                    String endCityName = pathData[1];
                    int startIndex = -1;
                    int endIndex = -1;
                    for (int j = 0; j < n; j++) {
                        if (cities.get(j).getName().equals(startCityName)) {
                            startIndex = j;
                        }
                        if (cities.get(j).getName().equals(endCityName)) {
                            endIndex = j;
                        }
                    }
                    if (startIndex == -1 || endIndex == -1) {
                        System.err.println("Ошибка: Не удалось найти указанные города для запроса #" + (i + 1));
                        continue;
                    }

                    List<String> path = new ArrayList<>();
                    int minCost = findMinCost(cities, startIndex, endIndex, path, 0);
                    writer.write(minCost + "\n");
                } else {
                    System.err.println("Ошибка: Недостаточно данных для запроса #" + (i + 1));
                }
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private int findMinCost(List<City> cities, int currentCityIndex, int endCityIndex, List<String> path, int currentCost) {
        if (currentCityIndex == endCityIndex) {
            System.out.println("Path: " + String.join(" -> ", path));
            return currentCost;
        }

        int minCost = Integer.MAX_VALUE;
        for (Neighbor neighbor : cities.get(currentCityIndex).getNeighbors()) {
            if (!path.contains(cities.get(neighbor.getNeighborIndex()).getName())) {
                path.add(cities.get(neighbor.getNeighborIndex()).getName());
                int cost = findMinCost(cities, neighbor.getNeighborIndex(), endCityIndex, path, currentCost + neighbor.getCost());
                if (cost != Integer.MAX_VALUE) {
                    minCost = Math.min(minCost, cost);
                }
                path.remove(path.size() - 1);
            }
        }

        return minCost;
    }
}
