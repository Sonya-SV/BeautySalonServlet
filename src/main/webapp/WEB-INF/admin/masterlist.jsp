
<html>
<head>
    <title>Masters</title>
</head>
<body>
<%@ include file="adminpart/adminnavbar.jsp" %>
<!-- Карточка с card-img-overlay -->
<div class="container" style="margin-top: 60px">
    <div class="row">
        <form action="${pageContext.request.contextPath}/beauty-salon/admin/master" autocomplete="off"
              novalidate >

            <c:forEach items="${masters}" var="i">
                <div class="col-sm-3">
                    <div class="panel panel-default">
                        <div class="card">
                            <div class="card-body" style="text-align: center">

                                <img class="card-img"
                                     src="data:image/png;base64,${i.photo}"
                                     alt="..." height="300" style=" display: block;
                                     margin: 0 auto; margin-top: 5px"    >

                                <h5 class="card-title">${i.user.firstName} ${i.user.lastName}</h5>
                                <p class="card-text">${i.timeStart} - ${i.timeEnd}</p>
                                <button type="submit" value="${i.id}" name = "masterId" class="btn btn-primary" style="margin-top: 5px; background: goldenrod"
                                        ng-disabled="form.$invalid">
                                    Choose
                                </button>

                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </form>
        <%--        ${photo}--%>
    </div>
</div>


</body>
</html>
