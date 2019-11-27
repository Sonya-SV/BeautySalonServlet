<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" isErrorPage="true"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <c:if test="${sessionScope.user.role eq null} ">
        <%@ include file="parts/common.jsp" %>
    </c:if>
    <c:if test="${sessionScope.user.role eq 'ADMIN'}">
        <%@ include file="admin/adminpart/adminnavbar.jsp" %>
    </c:if>
    <c:if test="${sessionScope.user.role eq 'MASTER'}">
        <%@ include file="master/masterpart/masternavbar.jsp" %>
    </c:if>
    <c:if test="${sessionScope.user.role eq 'USER'}">
        <%@ include file="user/userpart/usernavbar.jsp" %>
    </c:if>
    <title>Error Page</title>
</head>
    <body>
        <div class="jumbotron" style="text-align: center">
        <h2><br/>
            <i><fmt:message key="page.not.found"/> </i>
        </h2>
        </div>

    </body>
</html>