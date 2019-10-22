<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registration form</title>
    <%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">--%>
    <%--    <link rel="stylesheet" href= "${pageContext.request.contextPath}/app/resources/css/index.css" />--%>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
<%--    <style>--%>
<%--        <%@ include file="../lib/css/index.css" %>--%>
<%--    </style>--%>
</head>
<body data-spy="scroll">
<div class="header">
    <div class="navbar-fixed-top">
        <div class="container">
            <div class="navbar navbar-default">
                <a class="navbar-brand" href="">TRAINFOR</a>
                <ul class="nav navbar-nav">
                    <c:if test="${sessionScope.user.role eq ADMIN}">
                        <li><a href="${pageContext.request.contextPath}/app/admin/userList">User List</a></li>
                    </c:if>
                    <c:if test="${sessionScope.user.role ne GUEST}">
                        <li><a href="${pageContext.request.contextPath}/app/user/trainSelection">Train tickets</a></li>
                    </c:if>
                </ul>
                <ul class="nav navbar-nav navbar-right">
<%--                    <c:if test="${sessionScope.user.role eq GUEST}">--%>
                        <li><a href="${pageContext.request.contextPath}/app/login" id="myBtn">
                            <span class="glyphicon glyphicon-log-in"></span> Login </a></li>
                        <%--                        <li><a  id="myBtn1">--%>
                        <%--                            <span class="glyphicon glyphicon-log-in"></span> Login </a></li>--%>
                        <li><a href="${pageContext.request.contextPath}/app/registration">
                            <span class="glyphicon glyphicon-user"></span> Sign up </a></li>
<%--                    </c:if>--%>
<%--                    <li class="locale-flag">--%>
<%--                        <a><img src="http://files.softicons.com/download/internet-cons/flag-icons-by-custom-icon-design/png/256/United-States-Flag.png" style="height: 100%; width: 100%"></a>--%>
<%--                    </li>--%>
<%--                    <li class="locale-flag">--%>
<%--                        <a><img src="https://cdn3.iconfinder.com/data/icons/finalflags/256/Ukraine-Flag.png" style="height: 100%; width: 100%"> </a>--%>
<%--                    </li>--%>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <c:if test="${sessionScope.user.role ne GUEST}">

                        <li><a><span class="glyphicon glyphicon-user"></span> ${sessionScope.user.username}</a></li>
                        <li><a href="${pageContext.request.contextPath}/app/user/profile"> Profile</a></li>
                        <li><a href="${pageContext.request.contextPath}/app/user/account">Account</a></li>
                        <li><a href="${pageContext.request.contextPath}/app/user/history">History</a></li>
                        <li><a href="${pageContext.request.contextPath}/app/user/cart"><span
                                class="glyphicon glyphicon-shopping-cart">Cart<span
                                class="badge pull-right"></span></span></a></li>


                        <li><a href="${pageContext.request.contextPath}/app/logout"><span
                                class="glyphicon glyphicon-log-out"></span> Logout </a></li>
                    </c:if>

                </ul>
            </div>
        </div>
    </div>
</div>
<div class="navbar-fixed-bottom">
    <div class="container">
        <div class="navbar navbar-default">
            <div style=" text-align: center">
                <p>Copyright 2019, Sonya-Sv
                    <br>All Rights Reserved</p></div>
        </div>
    </div>
</div>


<%--<div id="myModal" class="modal">--%>
<%--    <div class="container">--%>
<%--        <div class="row">--%>

<%--            <div class="col-md-offset-3 col-md-6" style="padding-top: 100px">--%>
<%--                <form class="form-horizontal">--%>
<%--                    <span class="close">×</span>--%>
<%--                    <div class="heading">АВТОРИЗАЦИЯ</div>--%>
<%--                    <div>--%>
<%--                        <c:if test="${requestScope.userError ne null}">--%>
<%--                            <div class="alert alert-danger">--%>
<%--                                    ${userError}--%>
<%--                            </div>--%>
<%--                        </c:if>--%>
<%--                        &lt;%&ndash;                    <label id="usernameLabel" style="text-align: left">Username</label>&ndash;%&gt;--%>

<%--                        <div class="form-group">--%>

<%--                            <input type="text" class="form-control" name="username" id="inputEmail"--%>
<%--                                   placeholder="Username">--%>
<%--                            <i class="fa fa-user"></i>--%>
<%--                        </div>--%>
<%--                        &lt;%&ndash;                    <label id="passwordLabel" >Password</label>&ndash;%&gt;--%>
<%--                        <div class="form-group">--%>

<%--                            <input type="password" class="form-control" name="password" id="inputPassword"--%>
<%--                                   placeholder="Password">--%>
<%--                            <i class="fa fa-lock"></i>--%>
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
<%--                            <button type="submit" class="btn btn-default">ВХОД</button>--%>
<%--                        </div>--%>
<%--                    </div>--%>

<%--                </form>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>

<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>--%>
<%--<script type="text/javascript">--%>
<%--    var modal = document.getElementById('myModal');--%>
<%--    var btn = document.getElementById("myBtn1");--%>
<%--    var span = document.getElementsByClassName("close")[0];--%>
<%--    btn.onclick = function () {--%>
<%--        modal.style.display = "block";--%>
<%--    }--%>
<%--    span.onclick = function () {--%>
<%--        modal.style.display = "none";--%>
<%--    }--%>
<%--    window.onclick = function (event) {--%>
<%--        if (event.target == modal) {--%>
<%--            modal.style.display = "none";--%>
<%--        }--%>
<%--    }</script>--%>
</body>

</html>
