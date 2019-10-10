package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.impl.MealRepositoryCrudImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class MealServlet extends HttpServlet {
    private static final String LIST_MEAL = "meals.jsp";
    private static final String INSERT_OR_EDIT = "editmeal.jsp";

    private MealRepository repository = new MealRepositoryCrudImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward;
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            repository.removeMeal(Long.parseLong(req.getParameter("id")));
            forward = LIST_MEAL;
            List<MealTo> mealListWithExceeds = getMealExceeds();
            req.setAttribute("mealList", mealListWithExceeds);
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            Meal meal = repository.getMeal(Long.parseLong(req.getParameter("id")));
            req.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("mealList")) {
            forward = LIST_MEAL;
            List<MealTo> mealListWithExceeds = getMealExceeds();
            req.setAttribute("mealList", mealListWithExceeds);
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String mealId = req.getParameter("id");
        String mealDateTime = req.getParameter("date");
        LocalDateTime dateTime = LocalDateTime.parse(mealDateTime, DateTimeFormatter.ISO_DATE_TIME);
        String mealDescription = req.getParameter("description");

        int mealCalories = Integer.parseInt(req.getParameter("calories"));

        if (mealId == null || mealId.isEmpty()) {
            Meal meal = new Meal(dateTime, mealDescription, mealCalories);
            repository.addMeal(meal);
        } else {

            Meal meal = repository.getMeal(Long.parseLong(mealId));
            Meal updatedMeal = new Meal(dateTime, mealDescription, mealCalories);
            repository.removeMeal(meal.getId());
            repository.updateMeal(updatedMeal);
        }
        RequestDispatcher view = req.getRequestDispatcher(LIST_MEAL);
        List<MealTo> mealListWithExceeds = getMealExceeds();
        req.setAttribute("mealList", mealListWithExceeds);
        view.forward(req, resp);
    }

    private List<MealTo> getMealExceeds() {
        List<Meal> meals = repository.getAllMeals();
        List<MealTo> filteredMeal = MealsUtil
                .getFiltered(meals, LocalTime.MIN, LocalTime.MAX, 2000);
        filteredMeal.sort(Comparator.comparing(MealTo::getDateTime));
        return filteredMeal;
    }
}