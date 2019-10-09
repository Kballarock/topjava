package ru.javawebinar.topjava.repository.impl;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MealRepositoryCrudImpl implements MealRepository {

    private static Map<Long, Meal> map = new ConcurrentHashMap<>();

    static {
        List<Meal> mealList = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        for (Meal meal : mealList) {
            map.put(meal.getId(), meal);
        }
    }

    @Override
    public void addMeal(Meal meal) {
        map.put(meal.getId(), meal);
    }

    @Override
    public void updateMeal(Meal meal) {
        map.put(meal.getId(), meal);
    }

    @Override
    public void removeMeal(Meal meal) {
        map.remove(meal.getId());
    }

    @Override
    public void removeMeal(long id) {
        map.remove(id);
    }

    @Override
    public Meal getMeal(long id) {
        return map.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(map.values());
    }
}