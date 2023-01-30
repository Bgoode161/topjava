package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(13, 0), LocalTime.of(23, 0), 2000);
        mealsTo.forEach(System.out::println);


//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }


    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
        ArrayList<UserMealWithExcess> userMealsWithExcess = new ArrayList<>();
        Map<LocalDate, Integer> caloriesPerDate = new HashMap<>();
        for (UserMeal userMeal : meals) {
                if (!caloriesPerDate.containsKey(userMeal.getDateTime().toLocalDate())) {
                    caloriesPerDate.put(userMeal.getDateTime().toLocalDate(), userMeal.getCalories());
                }
                else {
                    Integer caloriesPrev = caloriesPerDate.get(userMeal.getDateTime().toLocalDate());
                    Integer caloriesSum = userMeal.getCalories() + caloriesPrev;
                    caloriesPerDate.put(userMeal.getDateTime().toLocalDate(), caloriesSum);
                }
            }

        for (UserMeal userMeal : meals) {
              LocalDate key = userMeal.getDateTime().toLocalDate();
              if (caloriesPerDate.containsKey(key) && caloriesPerDate.get(key) > caloriesPerDay
                      && TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                  userMealsWithExcess.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(),
                              userMeal.getCalories(), true));
              }
        }

        return userMealsWithExcess;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        return null;
    }
}
