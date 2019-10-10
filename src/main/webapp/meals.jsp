<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="WEB-INF/tags/DateFormat.tld" prefix="f" %>
<jsp:useBean id="mealList" scope="request" type="java.util.List<ru.javawebinar.topjava.model.MealTo>"/>


<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <meta charset="utf-8">
    <title>Meal</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/dataTables.bootstrap.min.css" rel="stylesheet">

</head>
<body>

<div align="center" style="margin-top: 20px">
    <a class="btn btn-info btn-sm" role="button"
       href="index.html">Home</a>
    <a class="btn btn-primary btn-sm" role="button"
       href="meals?action=insert">Create</a>
</div>

<div style="width: 800px;margin: 50px auto auto;">
    <h3>User meals</h3>
    <table id="userMeal" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>Id</th>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th colspan="2" style="text-align: center"> Action</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <c:forEach items="${mealList}" var="meal">
        <tr style="color: ${meal.excess == false ? '#28a745' : '#b21f2d'}">
            <td>${meal.id}</td>
            <td>${f:formatLocalDateTime(meal.dateTime, 'dd.MM.yyyy HH:mm')}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td style="text-align: center"><a class="btn btn-secondary btn-sm" role="button"
                                              href="meals?action=edit&id=${meal.id}">Update</a></td>
            <td style="text-align: center"><a class="btn btn-danger btn-sm" role="button"
                                              href="meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>