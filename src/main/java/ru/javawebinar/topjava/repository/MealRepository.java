package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

public interface MealRepository {

    void addMeal(Meal meal);

    void updateMeal(Meal meal);

    void removeMeal(Meal meal);

    void removeMeal(long id);

    Meal getMeal(long id);
}