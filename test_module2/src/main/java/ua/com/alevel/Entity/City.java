package ua.com.alevel.Entity;

import java.util.Map;
import java.util.TreeMap;

public class City extends BaseEntity{
    private String nameCity;
    private Map<Integer, Integer> waysAndCostMap = new TreeMap<>();

    public Map<Integer, Integer> getWaysAndCostMap() {
        return waysAndCostMap;
    }

    public void setWaysAndCostMap(Integer key, Integer value) {
        waysAndCostMap.put(key,value);
    }

    public String getNameCity() {
        return nameCity;
    }

    public City(String nameCity) {
        this.nameCity = nameCity;
    }

}
