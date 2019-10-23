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

<%@ include file="../parts/common.jsp" %>

<div class="container" style="margin-top: 60px">

    <a href="#"><img src="https://backstage.ua/wp-content/uploads/2018/06/Volosy-1-601x1024.jpg" alt="..." height="380"></a>
    <a href="#"><img src="https://backstage.ua/wp-content/uploads/2018/06/Vizazh-1-601x1024.jpg" alt="..." height="380"></a>
    <a href="#"><img src="https://backstage.ua/wp-content/uploads/2018/06/Nogti-1-601x1024.jpg" alt="..." height="380"></a>
    <a href="#"> <img src="https://backstage.ua/wp-content/uploads/2018/06/Litso-601x1024.jpg" alt="..." height="380"></a>
    <a href="#"><img src="https://backstage.ua/wp-content/uploads/2018/06/Telo-1-601x1024.jpg" alt="..." height="380"></a>

</div>
<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="panel-body">
            <form name="form-inline" method="post" autocomplete="off" novalidate
                  ng-submit="form.$valid ">
                <div class="form-group">
                    <label id="departureLabel">From</label>
                    <select name="service" class="form-control form-control-lg">
                        <option></option>
                        <option selected>Kyiv</option>
                        <option>Hair</option>
                        <option>Nail</option>
                        <option>Skin</option>
                        <option>Body</option>
                    </select>
                </div>

                <div class="form-group">
                    <label id="dateLabel">Date</label>
                    <input type="date" name="date" value="2019-10-02" max="2019-10-29" min="2019-09-28">
                </div>
                <div class="form-group">
                    <label id="timeLabel">Time</label>
                    <input class="form-control" type="time" name="time" value="00:00" id="example-time-input">
                </div>

                <button type="submit" class="btn btn-primary" style="margin-top:30px"
                        ng-disabled="form.$invalid">
                    Find
                </button>


            </form>
        </div>
    </div>
</div>
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
