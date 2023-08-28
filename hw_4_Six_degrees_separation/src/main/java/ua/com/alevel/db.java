package ua.com.alevel;

import com.sun.source.tree.BreakTree;

import java.util.Random;

public class db {
    public static void createPerson(){
        Person[] arrPersons = new Person[8];
        for (int i = 0; i < 8; i++) {
            Person person = new Person();
            person.setId(i);
            person.setHend(false);
            arrPersons[i] = person;
        }
        System.out.println(handShake(arrPersons));

    }
    private static boolean checker = true;
    public static String handShake(Person[] arrPersons){
        Random random = new Random();
        while(checker){
            for (int i = 0; i < arrPersons.length; i++) {
                int randomNumber = random.nextInt(100) + 1;
                if (randomNumber<80) {
                    Person person = arrPersons[i];
                    Person.setHend(true);
                    System.out.println("Случайное число: " + randomNumber);
                    System.out.println("ID персоны: " + person.getId() + " Руопожатие было передена персоне с ид: " + (i+1) );
                }else {
                    i--;
                    System.out.println("Случайное число: " + randomNumber);
                    System.out.println("Ne peredali: ");
                }

            }
            checker = false;

        }
        return "Done";
    }


}
