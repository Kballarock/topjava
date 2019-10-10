<%--suppress ELValidationInJSP --%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title>Add/Edit</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/edit-form.css" rel="stylesheet">
</head>
<body>

<div class="edit-clean" style="min-height: 928px; ">

    <form method="post" action="meals" style="max-width: 453px;">
        <h3 style="text-align: center">${not empty meal.id ? "Update meal": "Add new meal"}</h3>

        <c:if test="${not empty meal.id}">
            <div class="form-group">
                <label for="id">Id</label>
                <input id="id" readonly class="form-control" required name="id" value="${meal.id}">
            </div>
        </c:if>

        <div class="form-group">
            <label for="date">Date and time</label>
            <input id="date" class="form-control" type="datetime-local" required name="date" value="${meal.dateTime}">
        </div>

        <div class="form-group">
            <label for="desc">Description</label>
            <input id="desc" class="form-control" type="text" required name="description" value="${meal.description}">
        </div>

        <div class="form-group">
            <label for="calories">Calories</label>
            <input id="calories" class="form-control" type="number" required name="calories" value="${meal.calories}">
        </div>

        <div class="form-group">
            <button class="btn btn-success btn-block" type="submit" value="Submit">Success</button>
        </div>

        <div align="center">
            <a href="meals?action=mealList" style="font-size: 18px;color: rgb(37,137,237); margin-right: 20px"> <<
                Back</a>
            <a href="index.html" style="font-size: 18px;color: rgb(37,137,237);">Home >></a>
        </div>
    </form>
</div>
</body>
</html>