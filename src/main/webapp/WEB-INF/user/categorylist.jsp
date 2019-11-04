<%--
  Created by IntelliJ IDEA.
  User: Sonya
  Date: 05.09.2019
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Services</title>
</head>
<body>

<%@ include file="userpart/usernavbar.jsp" %>
<%@ include file="../parts/common.jsp" %>

<div class="container" style="margin-top: 60px">


    <form autocomplete="off" novalidate>
        <a items="${categories}" var="i">
            <c:forEach items="${categories}" var="i">
            <a href="${pageContext.request.contextPath}/beauty-salon/user/procedures?categoryId=${i.id}">
                <img src="data:image/png;base64,${i.image}" alt="..." height="380"></a>
                <input type="hidden" value="${i.id}" name="categoryId">
                <%--    <a href="${pageContext.request.contextPath}/app/user/procedures?categoryId=2"><img src="https://backstage.ua/wp-content/uploads/2018/06/Vizazh-1-601x1024.jpg" alt="..." height="380">--%>
                <%--    <a href="${pageContext.request.contextPath}/app/user/procedures?categoryId=3"><img src="https://backstage.ua/wp-content/uploads/2018/06/Nogti-1-601x1024.jpg" alt="..." height="380">--%>
                <%--    <a href="${pageContext.request.contextPath}/app/user/procedures?categoryId=4"><img src="https://backstage.ua/wp-content/uploads/2018/06/Litso-601x1024.jpg" alt="..." height="380">--%>
                <%--    <a href="${pageContext.request.contextPath}/app/user/procedures?categoryId=5"><img src="https://backstage.ua/wp-content/uploads/2018/06/Telo-1-601x1024.jpg" alt="..." height="380">--%>
            </c:forEach>
    </form>
</div>
<%--<div class="container" style="margin-top: 60px">--%>
<%--    <div class="row">--%>
<%--        <div class="panel-body">--%>
<%--            <form name="form-inline" method="post" autocomplete="off" novalidate--%>
<%--                  ng-submit="form.$valid ">--%>
<%--                <div class="form-group">--%>
<%--                    <label id="departureLabel">Service</label>--%>
<%--                    <select name="service" class="form-control form-control-lg">--%>
<%--                        <option></option>--%>
<%--                        <option selected>Make-up</option>--%>
<%--                        <option>Hair</option>--%>
<%--                        <option>Nails</option>--%>
<%--                        <option>Fade</option>--%>
<%--                        <option>Body</option>--%>
<%--                    </select>--%>
<%--                </div>--%>

<%--                <div class="form-group">--%>
<%--                    <label id="dateLabel">Date</label>--%>
<%--                    <input type="date" name="date" value="2019-10-02" max="2019-10-29" min="2019-09-28">--%>
<%--                </div>--%>
<%--                <div class="form-group">--%>
<%--                    <label id="timeLabel">Time</label>--%>
<%--                    <ul class="hor_nav">--%>
<%--                        <li>9:00</li>--%>
<%--                        <li>10:00</li>--%>
<%--                        <li>11:00</li>--%>
<%--                        <li>12:00</li>--%>
<%--                        <li>13:00</li>--%>
<%--                        <li>14:00</li>--%>
<%--                        <li>15:00</li>--%>
<%--                        <li>16:00</li>--%>
<%--                        <li>17:00</li>--%>
<%--                        <li>18:00</li>--%>
<%--                        <li>19:00</li>--%>
<%--                    </ul>--%>
<%--                </div>--%>

<%--                <button type="submit" class="btn btn-primary" style="margin-top:30px"--%>
<%--                        ng-disabled="form.$invalid">--%>
<%--                    Find--%>
<%--                </button>--%>


<%--            </form>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--        <div class="container" style="padding-top: 10%">--%>
<%--            <div class="row">--%>
<%--                <div class="col-md-16 col-md-offset-1" style="padding-top: 50px">--%>
<%--                    <%@ include file="trainlist.jsp" %>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
</div>
</div>
</body>
</html>
