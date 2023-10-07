package ua.com.alevel.controller;

import java.util.ArrayList;

public class ValidateController {
    public void startValidate(ArrayList arrayList){
        for (Object object:arrayList) {
            System.out.println(object);
        }
        System.out.println(checkIfCorrectNumbersOfCities(arrayList.get(0)));
    }
    private boolean checkIfCorrectNumbersOfCities(Object o){

        int nubersOfCities = -1;
        try {
            nubersOfCities = Integer.parseInt(String.valueOf(o));
            if ((nubersOfCities<10000)&&(nubersOfCities>5)){
                System.out.println(o);
                return true;
            }else {

            }
        }catch (NumberFormatException e){
            System.err.println("Warning! В строчке 1 введено не число или оно введено не коректно: 1. " + o + " ");
        }
        return false;



    }
}
