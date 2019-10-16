<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/myStyle.css" rel="stylesheet">
</head>
<body>

<div align="center" style="margin-top: 20px">
    <a class="btn btn-info btn-sm" role="button" href="index.html">Home</a>
    <a class="btn btn-primary btn-sm" role="button" href="meals?action=create">Create</a>
</div>

<div style="width: 800px;margin: 50px auto auto;">
    <div class="jumbotron pt-4">
        <div class="container">
            <h3 class="text-left">Filters</h3>
            <div class="card border-dark">
                <div class="card-body pb-0">
                    <form id="filter" method="post" action="meals?action=filter">
                        <div class="row">
                            <div class="offset-md-0 col-4">
                                <label for="startDate">From date:</label>
                                <input class="form-control" type="date" name="startDate" id="startDate" value="${param.startDate}">
                            </div>
                            <div class="col-4">
                                <label for="endDate">To date:</label>
                                <input class="form-control" type="date" name="endDate" id="endDate" value="${param.endDate}">
                            </div>
                            <div class="offset-md-0 col-2">
                                <label for="startTime">Time from:</label>
                                <input class="form-control" type="time" name="startTime" id="startTime" value="${param.startTime}">
                            </div>
                            <div class="col-2">
                                <label for="endTime">Time to:</label>
                                <input class="form-control" type="time" name="endTime" id="endTime" value="${param.endTime}">
                            </div>
                        </div>
                        <div align="center" style="margin-top: 20px">
                               <button type="reset" class="btn btn-danger btn-sm" style=" margin-right: 20px">Clear</button>
                            <button type="submit" class="btn btn-success btn-sm" >Filter</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <h3 class="text-center">User meals</h3>
    <table id="userMeal" class="table table-striped table-bordered" cellspacing="0" width="100%">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th colspan="2" style="text-align: center"> Action</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <jsp:useBean id="meals" scope="request" type="java.util.List"/>
            <c:forEach items="${meals}" var="meal">
                <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealTo"/>
        <tr style="color: ${meal.excess ? 'red' : 'green'}">
            <td>
                    <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                    <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                    <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                    ${fn:formatDateTime(meal.dateTime)}
            </td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td style="text-align: center"><a class="btn btn-secondary btn-sm" role="button"
                                              href="meals?action=update&id=${meal.id}">Update</a></td>
            <td style="text-align: center"><a class="btn btn-danger btn-sm" role="button"
                                              href="meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>