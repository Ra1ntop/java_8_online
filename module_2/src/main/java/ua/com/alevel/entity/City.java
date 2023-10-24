package ua.com.alevel.entity;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private List<Neighbor> neighbors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Neighbor> getNeighbors() {
        return neighbors;
    }

    public City(String name) {
        setName(name);
        this.neighbors = new ArrayList<>();
    }
}
