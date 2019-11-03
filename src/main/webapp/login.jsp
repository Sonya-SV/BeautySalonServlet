<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
</head>
<body>

<%@ include file="WEB-INF/parts/common.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6" style="padding-top: 100px">
            <form class="form-horizontal">
                <span class="heading">АВТОРИЗАЦИЯ</span>
                <div>
                    <c:if test="${requestScope.userError ne null}">
                        <div class="alert alert-danger">
                                ${userError}
                        </div>
                    </c:if>
                                        <label id="emailLabel" style="text-align: left">Email</label>
                    <div class="form-group">

                        <input type="text" class="form-control" name="email" id="inputEmail"
                               placeholder="Email">
                        <i class="fa fa-user"></i>
                    </div>
                                        <label id="passwordLabel" >Password</label>
                    <div class="form-group">

                        <input type="password" class="form-control" name="password" id="inputPassword"
                               placeholder="Password">
                        <i class="fa fa-lock"></i>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">ВХОД</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>