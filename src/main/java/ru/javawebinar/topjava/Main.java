package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.MealRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.List;

/**
 * @see <a href="http://topjava.herokuapp.com">Demo application</a>
 * @see <a href="https://github.com/JavaOPs/topjava">Initial project</a>
 */
public class Main {

  static MealRepository mealRepository = new MealRepositoryImpl();
    public static void main(String[] args) {
        System.out.format("Hello TopJava Enterprise!");
        Collection<Meal> mealToList = mealRepository.getAll();
        mealToList.forEach(System.out :: println);
    }
}
