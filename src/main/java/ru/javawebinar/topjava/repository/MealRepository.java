package ru.javawebinar.topjava.repository;

import org.springframework.lang.NonNull;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository {
    // null if updated meal do not belong to userId
    Meal save(Meal meal, int userId);

    // false if meal do not belong to userId
    boolean delete(int id, int userId);

    // null if meal do not belong to userId
    Meal get(int id, int userId);

    // ORDERED dateTime desc
    List<Meal> getAll(int userId);

    // ORDERED dateTime desc
    List<Meal> getBetweenInclusive(@NonNull LocalDateTime startDate, @NonNull LocalDateTime endDate, int userId);

    default Meal getWithUser(int id, int userId) {
        throw new UnsupportedOperationException();
    }
}
