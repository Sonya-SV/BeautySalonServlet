<html>
<head>
    <%@ include file="adminpart/adminnavbar.jsp" %>
    <title><fmt:message key="master"/></title>
</head>
<body>
<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-5">
            <img class="card-img" src="data:image/png;base64,${master.photo}" alt="..." height="400" style=" display: block;
                                     margin: 0 auto; margin-top: 5px">
        </div>
        <div class="col-md-7">
            <h1 align="center" class="card-title">${master.user.firstName} ${master.user.lastName}</h1>
            <hr align="center" width="300"/>
            <p class="card-text">
            <h2><fmt:message key="work.schedule"/>: ${master.timeStart} - ${master.timeEnd}</h2> </p>
            <form action="${pageContext.request.contextPath}/beauty-salon/master/booking">
                <c:if test="${procedures ne null}">
                <h2>${procedures[0].category.name}</h2>
                <table class="table table-bordered  table-hover table-sm" style=" margin: auto;">
                    <tr>
                        <th><fmt:message key="name"/></th>
                        <th><fmt:message key="price"/></th>
                        <th><fmt:message key="choose"/></th>
                    </tr>
                    <c:forEach items="${procedures}" var="i">
                        <tr>
                            <td>${i.name}</td>
                            <td>${i.price}</td>
                            <td>
                                <button type="submit" class="btn btn-primary" style="margin-top:0px"
                                         value="${i.id}" name="procedureId">
                                    <fmt:message key="choose"/>
                                </button>
                            </td>
                        </tr>

                    </c:forEach>
                </table>
                </c:if>
                <input type="hidden" value="${master.id}" name="masterId"/>
            </form>
            <div>
                <form action="${pageContext.request.contextPath}/beauty-salon/admin/schedule">
                    <button value="${master.id}" name="masterId"><fmt:message key="show.schedule"/></button>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="comments.jsp" %>
</body>
</html>