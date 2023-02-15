<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2/14/2023
  Time: 2:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="meals">
    <input type="hidden" value="${meal.id}" name="id">
    <label for="dateTime">Date:</label>
    <input id="dateTime" type="datetime-local" value="${meal.dateTime}" name="dateTime">
    <br>
    <label for="description">Description:</label>
    <input id="description" type="text" value="${meal.description}" name="description">
    <br>
    <label for="calories">Calories:</label>
    <input id="calories" type="number" value="${meal.calories}" name="calories">
    <br>
    <button type="submit">Save</button>
    <br>
    <button onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>
