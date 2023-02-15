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
    }
    .normal {color: green}
    .exceeded {color: red}
    </style>
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
            <tr class = "${meal.excess ? 'exceeded' : 'normal'}">
                <td>${dtf.format(meal.dateTime)}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
    </c:forEach>
</table>
<hr>
<button><a href="meals?action=create">Create</a></button>
</body>
</html>
