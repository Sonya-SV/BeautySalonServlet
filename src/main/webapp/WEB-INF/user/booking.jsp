<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="userpart/usernavbar.jsp" %>
<div class="container" style="margin-top: 100px">
    <div class="row">
        <div class="col-md-5">
            <img class="card-img"
                 src="data:image/png;base64,${schedule.master.photo}"
                 alt="..." height="400">

            <div class="form-group">
                <h3>${schedule.master.user.firstName} ${schedule.master.user.lastName}</h3>
            </div>

        </div>
        <div class="col-md-7">
            <c:forEach items="${scheduleDate}" var="date">
                <form action="${pageContext.request.contextPath}/beauty-salon/user/order" role="form">
                    <ul class="media-list">
                        <li class="media">
                            <div class="media-body">
                                <div class="medi-heading">
                                    <div class="autor"><h4>${date}</h4></div>
                                </div>
                                <div class="media-text text-justify">
<%--                                    <p style="text-indent: 25px;">--%>
                                        <c:forEach var="time" items="${availableTime}">
                                            <c:set var="count" value="0"/>
                                            <c:forEach var="busy" items="${busySchedule}">
                                                <c:if test="${time eq busy.time && date eq busy.date && schedule.master.id eq busy.master.id}">
                                                    <label class="radio-label" style="text-decoration: line-through;">
                                                        <div class="radio1" >
                                                            <input class="radio-input" type="submit"
                                                                   value=${time} name="time" disabled/>${time}
                                                        </div>
                                                    </label>
                                                    <c:set var="count" value="1"/>
                                                </c:if>
                                            </c:forEach>
                                            <c:if test="${count <1}">
                                                <label class="radio-label">
                                                    <div class="radio1">
                                                        <input class="radio-input" type="submit"
                                                               value=${time} name="time"/>${time}
                                                    </div>
                                                </label>
                                            </c:if>
                                        </c:forEach>
<%--                                    </p>--%>
                                </div>
                            </div>
                        </li>
                    </ul>
                    <input type="hidden" value="${date}" name="dateOrder"/>
                </form>
                <hr align="center" width="100%" style="border-color: lightgray"/>
            </c:forEach>


        </div>
    </div>
</div>
</body>
</html>
