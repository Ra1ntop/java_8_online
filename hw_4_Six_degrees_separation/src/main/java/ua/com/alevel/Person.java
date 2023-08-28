package ua.com.alevel;

public class Person {
    private int id;
    private static boolean Hend = false;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public static boolean isHend() {
        return Hend;
    }

    public static void setHend(boolean hend) {
        Hend = hend;
    }

}