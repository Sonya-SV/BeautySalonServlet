<html>
<head>
    <%@ include file="adminpart/adminnavbar.jsp" %>
    <title><fmt:message key="user.list"/></title>
</head>
<body>
<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-6 col-md-offset-3" style="padding-top: 50px">
            <h2>
                <fmt:message key="user.list"/> <br/>
            </h2>
            <table class="table table-bordered  table-hover table-sm" style=" margin: auto;">
                <tr>
                    <th><fmt:message key="email"/></th>
                    <th><fmt:message key="first.name"/></th>
                    <th><fmt:message key="last.name"/></th>
                    <th><fmt:message key="role"/></th>
                </tr>
                <c:forEach items="${users}" var="i">
                    <tr>
                        <td>${i.email}</td>
                        <td>${i.firstName}</td>
                        <td>${i.lastName}</td>
                        <td>${i.role}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
</body>
</html>
