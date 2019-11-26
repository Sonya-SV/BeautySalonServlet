<%@ page language="java" isErrorPage="true"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <%@ include file="parts/common.jsp" %>
    <title>Error Page</title>
</head>
    <body>
<%--    <%@ include file="parts/common.jsp" %>--%>
        <div class="jumbotron" style="text-align: center">
        <h2>
            Error<br/>
            <i>Error <%= exception %></i>
        </h2>
        </div>

    </body>
</html>