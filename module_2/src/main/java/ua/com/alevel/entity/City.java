package ua.com.alevel.entity;

import java.util.HashMap;
import java.util.Map;

public class City extends BaseEntity {
    Map<Integer,String> map = new HashMap<>();
    public void setValueMap(){

    }
    public Map getMap(){
        return map;
    }
}
