package ru.javawebinar.topjava.web;


import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(UserServlet.class);
    private static final int CALORIES_PER_DAY = 2000;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("Getting users meal..");
        MealRepository mealRepository = new MealRepository();
        List<MealTo> listOfMeal = getUsersMeal(mealRepository.getMeals());

        req.setAttribute("mealList", listOfMeal);
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }

    private static List<MealTo> getUsersMeal(List<Meal> mealList) {

        List<MealTo> result = new ArrayList<>();

        if (mealList == null || mealList.isEmpty()) {
            result = Collections.emptyList();
        } else {
            Map<LocalDate, Integer> caloriesPerDaySum = new HashMap<>();

            for (Meal userMeal : mealList) {
                caloriesPerDaySum.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(), Integer::sum);
            }

            for (Meal userMeal : mealList) {

                result.add(new MealTo(
                        userMeal.getDateTime(),
                        userMeal.getDescription(),
                        userMeal.getCalories(),
                        caloriesPerDaySum.get(userMeal.getDateTime().toLocalDate()) > MealServlet.CALORIES_PER_DAY));
            }
        }
        return result;
    }
}