package ua.com.alevel.db;

import ua.com.alevel.entity.Human;

public class HumanDb {
    private Human[] humans = new Human[1];
    private int lastDriverIndex = 0;
    public void add(Human human) {
        if (lastDriverIndex == humans.length) {
            // Увеличиваем размер массива, если необходимо
            Human[] newHumans = new Human[humans.length * 2];
            System.arraycopy(humans, 0, newHumans, 0, humans.length);
            humans = newHumans;
        }
        lastDriverIndex++;
        human.setId(String.valueOf(lastDriverIndex));
        humans[lastDriverIndex - 1] = human;
    }




    public void create(Human human){
        if (lastDriverIndex == humans.length - 1) {
            Human[] newStudents = new Human[humans.length * 2];
            System.arraycopy(humans, 0, newStudents, 0, humans.length);
            humans = newStudents;
        }
        human.setId(String.valueOf(lastDriverIndex + 1));
        human.setGroupId("-1"); // Устанавливаем groupId по умолчанию
        humans[lastDriverIndex] = human;
        lastDriverIndex++;// Устанавливаем groupId по умолчанию

    }
    public Human[] findAll() {
        int activeHumansCount = 0;
        for (Human human : humans) {
            if (human != null && !human.isDeleted()) {
                activeHumansCount++;
            }
        }

        Human[] activeHumans = new Human[activeHumansCount];
        int index = 0;
        for (Human human : humans) {
            if (human != null && !human.isDeleted()) {
                activeHumans[index++] = human;
            }
        }

        return activeHumans;
    }
    public void delete(String id) {
        for (int i = 0; i < humans.length; i++) {
            if (humans[i] != null && humans[i].getId().equals(id)) {
                System.out.println(humans[i] + " was marked as deleted");
                humans[i].setDeleted(true); // Помечаем студента как удаленного
                break;
            }
        }
    }

    public int getLastDriverIndex() {
        return lastDriverIndex;
    }

    public Object getHumans() {
        return humans;
    }

    public void setHumans(Object humans) {
        this.humans = (Human[]) humans;
    }
}
