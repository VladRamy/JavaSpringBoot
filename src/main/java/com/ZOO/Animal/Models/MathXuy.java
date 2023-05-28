package com.ZOO.Animal.Models;

import java.time.LocalDate;
import java.time.Period;

public class MathXuy {
    public class ProbabilityDistributionCurve {



        public static void main(String[] args) {
            // Задаем начальную и конечную даты
            int year = 0;
            int month = 0;
            int dayOfMonth = 0;


            LocalDate startDate = LocalDate.of(2023, 1, 1);
            LocalDate endDate = LocalDate.of(2023, 12, 31);

            // Расчет разницы между двумя датами
            Period period = Period.between(startDate, endDate);

            // Получаем количество дней в разнице
            int totalDays = period.getDays();

            // Генерация значений и их вывод
            for (int day = 0; day <= totalDays; day++) {
                double probability = calculateProbability(day, totalDays);
                System.out.println("Day = " + day + ", Probability = " + probability);
            }
        }

        // Метод для расчета вероятности для каждой точки
        private static double calculateProbability(int day, int totalDays) {
            // Ваша логика расчета вероятности
            // Здесь можно использовать различные модели и функции распределения вероятностей
            // в зависимости от требуемых результатов и данных

            // В этом примере просто используем равномерное распределение
            return (double) day / totalDays;
        }
    }
}
