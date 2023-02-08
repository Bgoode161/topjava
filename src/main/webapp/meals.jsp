<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2/7/2023
  Time: 9:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Meals</title>
    <style> table, th, td {
        border: 2px solid black;
        border-collapse: collapse;
        padding: 5px;
    } </style>
</head>
<body>
<a href="index.html" style="font-size: 2rem">Home</a>
<hr style="color: gray; border-width: 3px">
<h1>Meals</h1>

<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>

    <c:forEach var="meal" items="${meals}">
        <c:if test="${meal.excess == true}">
            <tr style="color: red">
                <td>${dtf.format(meal.dateTime)}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            </tr>
        </c:if>
        <c:if test="${meal.excess == false}">
            <tr style="color:green">
                <td> ${dtf.format(meal.dateTime)}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            </tr>
        </c:if>
    </c:forEach>


</table>
</body>
</html>
