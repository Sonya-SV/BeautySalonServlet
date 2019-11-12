<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registration form</title>
    <%--        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">--%>
    <%--    <link rel="stylesheet" href= "${pageContext.request.contextPath}/app/resources/css/index.css" />--%>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>\


    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" integrity="sha512-dTfge/zgoMYpP7QbHy4gWMEGsbsdZeCXz7irItjcC3sPUFtf0kuFbDz/ixG7ArTxmDjLXDmezHubeNikyKGVyQ==" crossorigin="anonymous">
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <script type='text/javascript' src='//code.jquery.com/jquery-1.8.3.js'></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker3.min.css">
    <script type='text/javascript' src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.min.js"></script>

    <style>
        <%@ include file="/lib/css/style.css" %>
    </style>
</head>
<body data-spy="scroll">
<div class="header">
    <div class="navbar-fixed-top">

            <div class="navbar navbar-default">
                <div class="container">
                <a class="navbar-brand" href="">Beauty</a>
                <ul class="nav navbar-nav">
                    <li><a href="${pageContext.request.contextPath}/beauty-salon/master/masterList">Masters</a></li>
                    <li><a href="${pageContext.request.contextPath}/beauty-salon/master/schedule">Schedule</a></li>
                    <li><a href="${pageContext.request.contextPath}/beauty-salon/master/categoryList">Categories</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                        <li><a><span class="glyphicon glyphicon-user"></span> ${sessionScope.user.email}</a></li>
                        <li><a href="${pageContext.request.contextPath}/beauty-salon/master/profile"> Profile</a></li>
                        <li><a href="${pageContext.request.contextPath}/beauty-salon/logout"><span
                                class="glyphicon glyphicon-log-out"></span> Logout </a></li>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>

</html>
