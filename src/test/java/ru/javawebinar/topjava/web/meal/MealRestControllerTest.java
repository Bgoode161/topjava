package ru.javawebinar.topjava.web.meal;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static ru.javawebinar.topjava.web.meal.MealRestController.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.util.MealsUtil.getTos;

public class MealRestControllerTest extends AbstractControllerTest {

    @Autowired
    MealService mealService;

    static final String REST_URL = MEALS_REST_URL + "/";


    @Test
    void getTest() throws Exception {
        perform(get(REST_URL + MEAL1_ID))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_MATCHER.contentJson(meal1));
    }

    @Test
    void getAll() throws Exception {
        perform(get(REST_URL))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_TO_MATCHER.contentJson(getTos(meals, SecurityUtil.authUserCaloriesPerDay())));
    }

    @Test
    void createWithLocation() throws Exception {
        Meal newMeal = getNew();

        ResultActions resultActions = perform(post(REST_URL).
                contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMeal)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        Meal created = JsonUtil.readValue(resultActions.andReturn().getResponse().getContentAsString(), Meal.class);
        int newId = created.getId();
        newMeal.setId(newId);
        MEAL_MATCHER.assertMatch(created, newMeal);
        MEAL_MATCHER.assertMatch(mealService.get(newId, UserTestData.USER_ID), newMeal);
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + "delete/" + meal1.getId()))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        assertThrows(NotFoundException.class, () -> mealService.get(meal1.getId(), UserTestData.USER_ID));
    }

    @Test
    void update() throws Exception {
        Meal updated = MealTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + meal1.getId()).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
        MEAL_MATCHER.assertMatch(updated, mealService.get(meal1.getId(), UserTestData.USER_ID));
    }

    @Test
    void getBetween() throws Exception {
        perform(get(REST_URL + "filter?startDateTime=2020-01-30T07:00&endDateTime=2020-01-30T23:00:00"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MEAL_TO_MATCHER.contentJson(getTos(Arrays.asList(meal3, meal2, meal1), 2000)));

    }

}
