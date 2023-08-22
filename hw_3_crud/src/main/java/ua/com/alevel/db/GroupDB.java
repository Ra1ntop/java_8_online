package ua.com.alevel.db;

import ua.com.alevel.entity.Human;
import ua.com.alevel.controller.MainController;

public class GroupDB {
    private Human[] humans = new Human[10];
    public void create(Human human){
        for (int i = 0; i < humans.length; i++) {
            if (humans[i] == null) {
                humans[i] = human;
                break;
            }
        }
    }
    public Human[] findAll() {
        return humans;
    }

}
