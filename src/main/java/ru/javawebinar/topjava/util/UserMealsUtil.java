package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;


public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );

        System.out.println("HW0:  with 'forEach' result:");
        System.out.println(getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));

        System.out.println();

        System.out.println("HW0:  with 'Stream API' result:");
        System.out.println(getFilteredWithExceededStreamApi(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExceed> result = new ArrayList<>();

        if (mealList == null || mealList.isEmpty() || endTime.isBefore(startTime)) {
            result = Collections.emptyList();
        } else {
            Map<LocalDate, Integer> caloriesPerDaySum = new HashMap<>();

            for (UserMeal userMeal : mealList) {
                caloriesPerDaySum.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(), Integer::sum);
            }

            for (UserMeal userMeal : mealList) {
                if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                    result.add(new UserMealWithExceed(
                            userMeal.getDateTime(),
                            userMeal.getDescription(),
                            userMeal.getCalories(),
                            caloriesPerDaySum.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay));
                }
            }
        }
        return result;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededStreamApi(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        if (mealList == null || mealList.isEmpty() || endTime.isBefore(startTime)) {
            return Collections.emptyList();
        } else {
            Map<LocalDate, Integer> caloriesPerDaySum = mealList
                    .stream()
                    .collect(Collectors.groupingBy(date -> date.getDateTime().toLocalDate(),
                            Collectors.mapping(UserMeal::getCalories, Collectors.summingInt(Integer::intValue))));

            return mealList
                    .stream()
                    .filter(m -> TimeUtil.isBetween(m.getDateTime().toLocalTime(), startTime, endTime))
                    .map(m -> new UserMealWithExceed(m.getDateTime(), m.getDescription(),
                            m.getCalories(), caloriesPerDaySum.get(m.getDateTime().toLocalDate()) > caloriesPerDay))
                    .collect(Collectors.toList());
        }
    }
}