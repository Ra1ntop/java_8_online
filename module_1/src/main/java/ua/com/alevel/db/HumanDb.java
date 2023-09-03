package ua.com.alevel.db;

import ua.com.alevel.entity.Human;

public class HumanDb {
    private Human[] humans = new Human[1];
    private int lastDriverIndex = 0;
    public void add(Human human) {
        human.setId(String.valueOf(lastDriverIndex + 1));
        humans[lastDriverIndex] = human;
        lastDriverIndex++;
    }




    public void create(Human human){
        if (lastDriverIndex == humans.length - 1) {
            Human[] newStudents = new Human[humans.length * 2];
            System.arraycopy(humans, 0, newStudents, 0, humans.length);
            humans = newStudents;
            add(human);
        } else {
            add(human);
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
