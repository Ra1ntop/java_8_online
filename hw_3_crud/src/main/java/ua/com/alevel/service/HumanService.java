package ua.com.alevel.service;

import ua.com.alevel.db.HumanDb;
import ua.com.alevel.entity.Human;

public class HumanService {
    private HumanDb humanDb = new HumanDb();

    public void creqte(String firstName, String lastName, int age) {
        Human human = new Human();
        human.setFirstName(firstName);
        human.setLastName(lastName);
        human.setAge(age);
        humanDb.create(human);
    }
    public void delete(String id){
        humanDb.delete(id);
    }

    public Human[] findAll() {
        return humanDb.findAll();
    }
}
