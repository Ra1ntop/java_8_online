package ua.com.alevel.db;

import ua.com.alevel.entity.Human;
import ua.com.alevel.controller.MainController;

import java.util.UUID;

public class HumanDb {
    private Human[] humans = new Human[10];
    public void create(Human human){
        for (int i = 0; i < humans.length; i++) {
            if (humans[i] == null) {
                humans[i] = human;
                human.setId(UUID.randomUUID().toString());
                break;
            }
        }
    }
    public Human[] findAll() {
        return humans;
    }
    public void delete(String id) {
        for (int i = 0; i < humans.length; i++) {
            if (humans[i] != null && humans[i].getId().equals(id)) {
                System.out.println(humans[i] + "was deleted");
                humans[i] = null;
                shiftArrayLeft(i);
                break;
            }
        }
    }

    private void shiftArrayLeft(int startIndex) {
        for (int i = startIndex; i < humans.length - 1; i++) {
            humans[i] = humans[i + 1];
        }
        // Clear the last element to remove the duplicate reference
        humans[humans.length - 1] = null;
    }

}
