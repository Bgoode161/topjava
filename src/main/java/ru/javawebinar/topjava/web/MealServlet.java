package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.MealsDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MealServlet extends HttpServlet {

  private final static MealsDao mealsDao = new MealsDao();
  private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("meals", MealsUtil.filteredByStreams(mealsDao.getMeals(), LocalTime.of(0, 0),
                  LocalTime.of(23, 59), mealsDao.getCALORIES_PER_DAY()));
        req.setAttribute("dtf", dateTimeFormatter);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}
