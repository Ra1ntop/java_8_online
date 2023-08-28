package ua.com.alevel;


import java.util.Random;

public class db {
    public static void createPerson(){
        Person[] arrPersons = new Person[8];
        for (int i = 0; i < 8; i++) {
            Person person = new Person();
            person.setId(i);
            arrPersons[i] = person;

        }
        System.out.println(handShake(arrPersons));

    }
    private static boolean checker = true;
    public static String handShake(Person[] arrPersons){
        Random random = new Random();
        while(checker){
            for (int i = 0; i < arrPersons.length; i++) {
                System.out.println("i= "+i);
                int randomNumber = random.nextInt(100) + 1;
                if (randomNumber<80) {
                    System.out.println(arrPersons[i].gethandShake());
                    arrPersons[i].sethandShake(true);
                    System.out.println("Случайное число: " + randomNumber);
                    System.out.println("ID персоны: " + arrPersons[i].getId() + " Рукопожатие было передена персоне с ид: " + (i+1) + " " + arrPersons[i].gethandShake());
                }else {

                    System.out.println("false Случайное число: " + randomNumber);
                    System.out.println("Ne peredali: " + arrPersons[i].gethandShake());
                    arrPersons[i].sethandShake(false);
                    i--;
                }

            }
            checker = false;

        }
        return "Done";
    }


}
