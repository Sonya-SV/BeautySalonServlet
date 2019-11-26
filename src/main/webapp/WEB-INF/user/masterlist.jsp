<html>
<head>
    <%@ include file="userpart/usernavbar.jsp" %>
    <title><fmt:message key="masters"/></title>
</head>
<body>
<div class="container" style="margin-top: 60px">
    <div class="row">
        <h2 style="text-align: center"><fmt:message key="masters"/></h2>
        <form action="${pageContext.request.contextPath}/beauty-salon/user/master" autocomplete="off" novalidate>
            <c:forEach items="${masters}" var="i">
                <div class="col-sm-3">
                <div class="panel panel-default">
                    <div class="card">
                        <div class="card-body" style="text-align: center">
                        <img class="card-img" src="data:image/png;base64,${i.photo}" alt="..." height="300"
                         style=" display: block;
                     margin: 0 auto; margin-top: 5px">
                        <h5 class="card-title">${i.user.firstName} ${i.user.lastName}</h5>
                        <p class="card-text">${i.timeStart} - ${i.timeEnd}</p>
                        <button type="submit" value="${i.id}" name="masterId" class="btn btn-primary" style="margin-top: 5px; background: goldenrod">
                            <fmt:message key="choose"/>
                        </button>
                        </div>
                    </div>
                    </div>
                </div>
            </c:forEach>
        </form>
    </div>
</div>

<center>
    <nav aria-label="...">
        <ul class="pagination">
            <c:forEach begin="1" var="i" end="${noOfPages}">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active"><a>${i}</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a href="masterlist?page=${i}">${i}</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

        </ul>
    </nav>
</center>
</body>
</html>
