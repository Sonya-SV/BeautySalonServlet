<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registration form</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"
          integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ=="
          crossorigin="anonymous">
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script type='text/javascript' src='//code.jquery.com/jquery-1.8.3.js'></script>
    <style>
        <%@ include file="/lib/css/style.css" %>
    </style>
</head>
<body data-spy="scroll">
<div class="header">
    <div class="navbar-fixed-top">
        <div class="navbar navbar-default">
            <a class="navbar-brand" href="">Beauty</a>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/beauty-salon/master/masterlist"><fmt:message
                        key="masters"/></a></li>
                <li><a href="${pageContext.request.contextPath}/beauty-salon/master/schedule"><fmt:message
                        key="schedule"/></a></li>
                <li><a href="${pageContext.request.contextPath}/beauty-salon/master/categorylist"><fmt:message
                        key="categories"/></a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a><span class="glyphicon glyphicon-user"></span> ${sessionScope.user.email}</a></li>
                <li><a href="${pageContext.request.contextPath}/beauty-salon/master/profile"> <fmt:message
                        key="profile"/></a></li>
                <li><a href="${pageContext.request.contextPath}/beauty-salon/logout"><span
                        class="glyphicon glyphicon-log-out"></span> <fmt:message key="logout"/> </a></li>
                <li><a href="?lang=ua">
                    <img alt="Українська" height="42"
                         src="https://cdn3.iconfinder.com/data/icons/finalflags/256/Ukraine-Flag.png" width="42">
                </a></li>
                <li><a href="?lang=en">
                    <img alt="Англійська" height="42"
                         src="http://abali.ru/wp-content/uploads/2010/12/united-kingdom-flag.png" width="42">
                </a></li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
