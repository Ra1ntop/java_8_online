package ua.com.alevel.util;

import ua.com.alevel.entity.Group;

import java.util.Collection;
import java.util.Random;

public class GroupDbUtil {

    private final static Random random = new Random();

    public static <E extends Group> long generateId(Collection<E> entities) {
        long id = random.nextInt(1_000_000);
        if (entities.stream().anyMatch(e -> Long.valueOf(e.getId()).equals(id))) {
            generateId(entities);
        }
        return id;
    }
}
