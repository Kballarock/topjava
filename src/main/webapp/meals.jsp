<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="WEB-INF/tags/DateFormat.tld" prefix="f" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta charset="utf-8">
    <title>Meal</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/dataTables.bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div style="width: 800px;margin: 50px auto auto;">
    <h3>User meals</h3>
    <table id="userMeal" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <jsp:useBean id="mealList" scope="request" type="java.util.List"/>
            <c:forEach items="${mealList}" var="meal">
        <tr style="color: ${meal.excess == false ? '#28a745' : '#b21f2d'}">
            <td>${f:formatLocalDateTime(meal.dateTime, 'dd.MM.yyyy HH:mm')}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>