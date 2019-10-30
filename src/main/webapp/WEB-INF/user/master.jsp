<html>
<head>
    <title>Master</title>
</head>
<body>
<%@ include file="../parts/common.jsp" %>
<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-5">
            <img class="card-img"
                 src="data:image/png;base64,${master.photo}"
                 alt="..." height="400">
        </div>
        <div class="col-md-7">
            <h1 align="center" class="card-title">${master.user.firstName} ${master.user.lastName}</h1>
            <hr align="center" width="300"/>
            <p class="card-text">
            <h2> Work schedule: ${master.timeStart} - ${master.timeEnd}</h2> </p>

            <c:if test="${procedures ne null}">
                <h2>${procedures[0].category.name}</h2>
                <table class="table table-bordered  table-hover table-sm" style=" margin: auto;">
                    <tr>
                        <th>Name</th>
                        <th>Price</th>
                    </tr>
                    <c:forEach items="${procedures}" var="i">
                        <tr>
                            <td>${i.name}</td>
                            <td>${i.price}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </div>
</div>
<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8">
            <form action="${pageContext.request.contextPath}/app/user/booking">
                <input type="hidden" value="${master.id}" name="masterId"/>
                <button type="submit" class="btn btn-primary" style="margin-top:30px"
                        ng-disabled="form.$invalid">
                    Book
                </button>
                <textarea cols="280" name="comment" placeholder="Put your comment"></textarea>
                <button type="submit" class="btn btn-primary" style="margin-top:30px"
                        ng-disabled="form.$invalid">
                    Send
                </button>
            </form>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#rateMe4').mdbRate();
    });
</script>
</body>
</html>