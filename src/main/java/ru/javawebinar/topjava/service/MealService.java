package ru.javawebinar.topjava.service;

import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;
import static ru.javawebinar.topjava.util.DateTimeUtil.getStartInclusive;
import static ru.javawebinar.topjava.util.DateTimeUtil.getEndExclusive;

@Service
public class MealService {

    private MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal get(int id, int userId) {
        return repository.get(id, userId);
    }

    public void delete(int id, int userId) {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public void update(Meal meal, int userId) {
        checkNotFoundWithId(repository.save(meal, userId), meal.getId());
    }

    public Meal create(Meal meal, int userId) {
      return repository.save(meal, userId);
    }
    public List<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    public List<Meal> getBetweenHalfOpen(LocalDate from, LocalDate to, int userId) {
      return repository.getBetweenHalfOpen(getStartInclusive(from), getEndExclusive(to), userId);
    }
}