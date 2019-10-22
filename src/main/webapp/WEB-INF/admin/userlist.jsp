<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User List</title>
</head>
<body>
<%@ include file="../parts/common.jsp" %>

<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-6 col-md-offset-3" style="padding-top: 50px">
            <h2>
                List Users <br/>
            </h2>

            <table class="table table-bordered  table-hover table-sm" style=" margin: auto;">
                <tr>
                    <th>Email</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Role</th>
                </tr>
                <c:forEach items="${users}" var="i">
                    <tr>
                        <td>${i.email}</td>
                        <td>${i.firstName}</td>
                        <td>${i.lastName}</td>
                        <td> ${i.role}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>

</body>
</html>
