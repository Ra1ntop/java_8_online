package ua.com.alevel.entity;
import java.util.ArrayList;
import java.util.List;



public class Group {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDefaultGroup() {
        this.id = -1; // Ознака групи за замовчуванням
        this.name = "default group";
    }
}
