<%--
  Created by IntelliJ IDEA.
  User: Sonya
  Date: 10/23/2019
  Time: 6:43 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Procedures</title>
</head>
<body>
<%@ include file="../parts/common.jsp" %>
<!-- Карточка с card-img-overlay -->
<div class="container" style="margin-top: 60px">

    <div class="row">


        <div class="col-sm-3">

            <img class="card-img"
                 src="${master.photo}"
                 alt="..." height="600">
            ${master.photo}


        </div>
        <div class="col-sm-6">
            <h5 class="card-title">${master.user.firstName} ${master.user.lastName}</h5>
            <p class="card-text">${master.timeStart} - ${master.timeEnd}</p>
            <c:if test="${procedures ne null}">
                <h2>${procedures[0].category.name}</h2>
                <table class="table table-bordered  table-hover table-sm" style=" margin: auto;">
                    <tr>
                            <%--                                <th>N</th>--%>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Choose</th>
                    </tr>
                    <c:forEach items="${procedures}" var="i">
                        <tr>
                                <%--                                    <td>${i.index}</td>--%>
                            <td>${i.name}</td>
                            <td>${i.price}</td>
                            <td><a href=" ${pageContext.request.contextPath}/app/user/masters">Choose</a></td>
                            <input type="hidden" value="${i.id}" name="procedureId"/>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </div>
</div>
</div>
</div>


</body>
</html>
