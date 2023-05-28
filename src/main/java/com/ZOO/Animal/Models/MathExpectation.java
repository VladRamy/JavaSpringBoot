package com.ZOO.Animal.Models;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MathExpectation {



    public HashMap<String , String > getPercentAge(AnimalList animalList){
        HashMap<String, String > percentAge = new HashMap<>();
        int count_animals = animalList.getZooList().size();
        for (int i = 0; i < count_animals; i++) {
            String dateR = animalList.getZooList().get(i).getDateR();
            String dateB = animalList.getZooList().get(i).getDateB();
            float age = getAge(dateR, dateB);

            String age_percent = percentAge.get(age);
            if(age_percent == null){
                float age_c = 1;
                for(int j = i + 1; j < count_animals; j++){
                    dateR = animalList.getZooList().get(j).getDateR();
                    dateB = animalList.getZooList().get(j).getDateB();
                    if (getAge(dateR, dateB) == age)
                        age_c++;
                }
                Float result = age_c / count_animals;
                percentAge.put("Возраст животного "+ age, "Мат ожидание "+result);
            }
        }
        return percentAge;
    }

    private float getAge(String dateR, String dateB){

        String[] array_dateR = dateR.split("\\.");
        String[] array_dateB = dateB.split("\\.");


        LocalDate startDate = LocalDate.of(Integer.parseInt(array_dateB[2]), Integer.parseInt(array_dateB[1]), Integer.parseInt(array_dateB[0]));
        LocalDate endDate = LocalDate.of(Integer.parseInt(array_dateR[2]), Integer.parseInt(array_dateR[1]), Integer.parseInt(array_dateR[0]));


        // Расчет разницы между двумя датами
        Period period = Period.between(startDate, endDate);


        // Получаем количество дней в разнице
        int totalYears = period.getYears();
        int totalMonths = period.getMonths();
        float total_months = (float) (totalMonths / 100.0);
        float totalAge = totalYears + total_months;

        return  totalAge;

    }

    public static void main(String[] args) throws FileNotFoundException {

    }
}


