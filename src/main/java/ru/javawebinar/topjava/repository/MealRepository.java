package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealRepository {

    void addMeal(Meal meal);

    void updateMeal(Meal meal);

    void removeMeal(Long id);

    Meal getMeal(Long id);

    List<Meal> getAllMeals();
}