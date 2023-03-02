package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {

    public static final int MEAL1_ID = UserTestData.GUEST_ID + 1;

    public static final Meal MEAL1 = new Meal(MEAL1_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL2 = new Meal(MEAL1_ID + 1, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL3 = new Meal(MEAL1_ID + 2, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL4 = new Meal(MEAL1_ID + 3, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Meal MEAL5 = new Meal(MEAL1_ID + 4, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 500);
    public static final Meal MEAL6 = new Meal(MEAL1_ID + 5, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 1000);
    public static final Meal MEAL7 = new Meal(MEAL1_ID + 6, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 510);

    public static List<Meal> mealsUser = Arrays.asList(MEAL7, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);

    public static Meal getNewMeal() {
        return new Meal(null, LocalDateTime.now().truncatedTo(ChronoUnit.HOURS), "New Meal", 1000);
    }

    public static Meal getUpdatedMeal() {
        Meal updated = new Meal(MEAL1);
        updated.setDescription("Updated Description");
        updated.setDateTime(updated.getDateTime().plusHours(1));
        updated.setCalories(updated.getCalories() + 500);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }

    public static List<Meal> getFilteredByDateTime(List<Meal> meals, LocalDateTime startTime, LocalDateTime endTime) {
        return meals.stream()
                .filter(meal -> Util.isBetweenHalfOpen(meal.getDateTime(), startTime, endTime))
                .collect(Collectors.toList());
    }
}
