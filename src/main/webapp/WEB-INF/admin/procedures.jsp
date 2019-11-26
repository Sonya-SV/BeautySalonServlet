<html>
<head>
    <%@ include file="adminpart/adminnavbar.jsp" %>
    <title><fmt:message key="procedures"/></title>
</head>
<body>

<div class="container" style="margin-top: 100px">
    <div class="row">
        <c:if test="${procedures ne null}">
            <div class="col-sm-6">
                <img class="card-img" src="data:image/png;base64,${procedures[0].category.image}" alt="..." height="400" style=" display: block; margin: 0 auto; margin-top: 5px">
            </div>
            <div class="col-sm-6">
                <h2>${procedures[0].category.name}</h2>
                <table class="table table-bordered  table-hover table-sm" style=" margin: auto;">
                    <tr>
                        <th><fmt:message key="name"/></th>
                        <th><fmt:message key="price"/></th>
                    </tr>
                    <c:forEach items="${procedures}" var="i">
                        <tr>
                            <td>${i.name}</td>
                            <td>${i.price}</td>
                        </tr>
                    </c:forEach>
                </table>
                <input type="hidden" value="${procedure.id}" name="procedureId">
            </div>
        </c:if>
    </div>
</div>
<%@ include file="masterlist.jsp" %>
</body>
</html>
