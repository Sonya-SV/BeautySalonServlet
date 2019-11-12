<html>
<head>
    <title>Schedule</title>
</head>
<body>
<%@ include file="adminpart/adminnavbar.jsp" %>

<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-10 col-md-offset-1" style="padding-top: 50px">
            <h2>
                Schedule <br/>
            </h2>
            <%--            <form action="${pageContext.request.contextPath}/beauty-salon/master/sendemail">--%>
            <div class="form-group">
                <table class="calendar table table-bordered" style=" margin: auto; empty-cells: show">

                    <tr>
                        <th>Time/Date</th>
                        <c:forEach var="date" items="${dates}">
                            <td width="13%">${date}</td>
                        </c:forEach>
                    </tr>

                    <c:forEach var="time" items="${workTime}">

                        <tr>
                            <td>${time}</td>

                            <c:forEach var="date" items="${dates}">
                                <c:set var="count" value="0"/>
                                <c:forEach items="${schedule}" var="i">
                                    <c:if test="${time eq i.time && date eq i.date}">
                                        <c:if test="${i.done eq true}">
                                            <td class="has-events" rowspan="1"
                                                style="text-decoration: line-through">
                                                <div class="row-fluid practice" style="width: 99%; height: 100%;">
                                                    <span class="title"> ${i.procedure.name} </span>
                                                        ${i.user.firstName} ${i.user.lastName}<br>
<%--                                                        ${i.time} ${i.date}--%>
                                                </div>
                                            </td>
                                            <c:set var="count" value="1"/>
                                        </c:if>
                                        <c:if test="${i.done eq false}">
                                            <td class="has-events" rowspan="1"
                                                style="text-decoration: line-through">
                                                <div class="row-fluid practice" style="width: 99%; height: 100%;">
                                                    <span class="title"> ${i.procedure.name} </span>
                                                        ${i.user.firstName} ${i.user.lastName}<br>
<%--                                                        ${i.time} ${i.date}--%>
                                                </div>
                                            </td>
                                            <c:set var="count" value="1"/>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${count<1}">
                                    <td class="no-events" rowspan="1"></td>
                                </c:if>
                            </c:forEach>

                        </tr>
                    </c:forEach>
                </table>
            </div>
            <%--            </form>--%>
        </div>
    </div>
</div>
</body>

</html>

