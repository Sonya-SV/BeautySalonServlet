<%--
  Created by IntelliJ IDEA.
  User: Sonya
  Date: 11/3/2019
  Time: 12:27 PM
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>Schedule</title>
</head>
<body>
<%@ include file="../parts/common.jsp" %>
<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-10 col-md-offset-1" style="padding-top: 50px">
            <h2>
                Schedule <br/>
            </h2>
            <form>
                <div class="form-group">
                    <table class="table table-bordered  table-hover table-sm"
                           style=" margin: auto; empty-cells: show">
                        <tr>
                            <th>Time/Date</th>
                            <c:forEach var="date" items="${dates}">
                                <td>${date}</td>
                            </c:forEach>
                        </tr>
                        <c:forEach var="time" items="${workTime}">

                            <tr>
                                <td>${time}</td>

                                <c:forEach var="date" items="${dates}">
                                    <c:forEach items="${schedule}" var="i">
                                        <c:if test="${time eq i.time && date eq i.date}">
                                            <c:if test="${i.done eq true}">
                                                <td style="text-decoration: line-through">
                                                ${i.user.firstName} ${i.user.lastName}<br>
                                                        ${i.procedure.name} ${i.time} ${i.date}</td>
                                            </c:if>
                                            <c:if test="${i.done eq false}">
                                                <td>
                                                        ${i.user.firstName} ${i.user.lastName}<br>
                                                        ${i.procedure.name} ${i.time} ${i.date}
                                                <button type="submit" name="done" value="${i.id}">Done
                                                </button>
                                                </td>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </form>
            <%--                <div class="modal-content">--%>
            <%--                    <div class="modal-header">--%>
            <%--                        <span class="close">x</span>--%>
            <%--                        <h2>Edit note</h2>--%>
            <%--                    </div>--%>
            <%--                    <div class="modal-body">--%>
            <%--                        <button type="submit">Done</button>--%>
            <%--                    </div>--%>
            <%--                </div>--%>

        </div>
    </div>
</div>

</body>
<%--<script>--%>
<%--    var modal = document.getElementById('myModal');--%>
<%--    var btn = document.getElementById("myBtn");--%>
<%--    var span = document.getElementsByClassName("close")[0];--%>
<%--    btn.onclick = function() {--%>
<%--        modal.style.display = "block";--%>
<%--    }--%>
<%--    span.onclick = function() {--%>
<%--        modal.style.display = "none";--%>
<%--    }--%>
<%--    window.onclick = function(event) {--%>
<%--        if (event.target === modal) {--%>
<%--            modal.style.display = "none";--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>
</html>

