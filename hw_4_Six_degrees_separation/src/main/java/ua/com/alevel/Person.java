package ua.com.alevel;

public class Person {
    private int id;
    private static boolean handShake = false;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean gethandShake() {
        return handShake;
    }

    public static void sethandShake(boolean hand) {
        handShake = hand;
    }
}
