<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="../parts/common.jsp" %>
<!-- Карточка с card-img-overlay -->
<div class="container" style="margin-top: 60px">

    <div class="row">

        <form
<%--                action="${pageContext.request.contextPath}/app/user/order"--%>
                role="form">

            <div class="form-inline">
                <div class="form-group">
                    <label id="exampleInputFirstNameLabel" for="firstName">First name</label>
                    <input type="text" value="${user.firstName}"
                           class="form-control"
                           name="firstName"
                           id="firstName"
                           placeholder=""
                           required>

                </div>

                <div class="form-group">
                    <label id="exampleInputLastNameLabel" for="lastName">Last name</label>
                    <input type="text" value="${user.lastName}"
                           class="form-control"
                           id="lastName"
                           name="lastName"
                           placeholder=""
                           required>
                </div>
            </div>
            <div class="form-group">
                <label id="exampleInputProcedureLabel">Procedure</label>
                <select name="procedureId">
                    <c:forEach items="${procedures}" var="procedure">
                        <option value="${procedure.id}">
                            <table>
                                <tr>
                                    <td>${procedure.name}</td>
                                    <td class="text-right">${procedure.price}</td>
                                </tr>
                            </table>
                        </option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label id="dateLabel">Date</label>
                <input type="date" name="date" value="${dateNow}" max="${maxDate}" min="${dateNow}">
            </div>
            <%--                <div class="form-group">--%>
            <%--                    <label  class="col-sm-2 control-label">Date:</label>--%>
            <%--                    <div class="col-sm-4 input-group date">--%>
            <%--                        <input type="date" name="date" class="form-control inputstl" value="${dateNow}"--%>
            <%--                               max="${maxDate}" min="${dateNow}">--%>
            <%--&lt;%&ndash;                        <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>&ndash;%&gt;--%>
            <%--    --%>
            <%--                    </div>--%>
            <%--                </div>--%>
            <div class="form-group">
                <label id="timeLabel">Time</label>
                <ul class="hor_nav">
                    <select name="time">
                        <c:forEach var="time" items="${schedule}">
                            <option value="${time}">${time}</option>
                        </c:forEach>

                    </select>
                    <%--            <c:forEach var="time" items="${schedule}" >--%>
                    <%--                <li name="time" value="${time}">${time}</li>--%>
                    <%--            </c:forEach>--%>
                </ul>
            </div>

            <input type="hidden" value="${master.id}" name="masterId"/>
            <button type="submit" class="btn btn-primary" style="margin-top:30px"
                    ng-disabled="form.$invalid">
                Book
            </button>

        </form>
    </div>
</div>


<%--<script type="text/javascript">--%>
<%--    $(function () {--%>
<%--        $('#datetimepicker12').datetimepicker({--%>
<%--            inline: true,--%>
<%--            sideBySide: true--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>
<script type='text/javascript'>
    $(function () {
        $('.col-sm-4.date').datepicker({});
    });

</script>
</body>
</html>
