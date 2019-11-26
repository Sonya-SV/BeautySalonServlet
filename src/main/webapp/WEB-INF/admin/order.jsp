<html>
<head>
    <%@ include file="adminpart/adminnavbar.jsp" %>
    <title><fmt:message key="order"/></title>
</head>
<body>

<div class="container" style="margin-top: 100px">
    <div class="row">
        <div class="col-md-5">
            <img class="card-img" src="data:image/png;base64,${schedule.master.photo}" alt="..." height="400" style=" display: block;
                                     margin: 0 auto; margin-top: 5px">
        </div>
        <div class="col-md-7" style="margin-top: 20px">
            <form action="${pageContext.request.contextPath}/beauty-salon/admin/save">
                <c:if test="${alreadyBooked ne null}">
                    <div class="alert alert-danger">
                            ${alreadyBooked}

                    </div>
                    <a href="${pageContext.request.contextPath}/beauty-salon/admin/booking?masterId=${schedule.master.id}&procedureId=${schedule.procedure.id}">
                        Choose another date
                    </a>
                </c:if>
                <c:if test="${errorOrder ne null}">
                    <div class="alert alert-danger"> ${errorOrder} </div>
                </c:if>
                <c:if test="${timeError ne null}">
                    <div class="alert alert-danger"> ${timeError} </div>
                </c:if>
                <div class="form-row">
                    <div class="col-6 col-sm-4 form-group">
                        <label><fmt:message key="first.name"/></label>
                        <input required type="text" value="${user.firstName}" class="form-control" name="firstName">
                    </div>
                    <div class="col-6 col-sm-8 form-group">
                        <label><fmt:message key="last.name"/></label>
                        <input required type="text" value="${user.lastName}" class="form-control" name="lastName">
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-4 col-sm-4 form-group">
                        <label><fmt:message key="time"/></label>
                        <p class="form-control-static">${schedule.time}</p>
                    </div>
                    <div class="col-4 col-sm-4 form-group">
                        <label><fmt:message key="date"/></label>
                        <p class="form-control-static">${schedule.date}</p>
                    </div>
                    <div class="col-4 col-sm-4 form-group">
                        <label><fmt:message key="master"/></label>
                        <p class="form-control-static">${schedule.master.user.firstName} ${schedule.master.user.lastName}
                        </p>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-4 col-sm-4 form-group">
                        <label><fmt:message key="procedure"/></label>
                        <p class="form-control-static">${schedule.procedure.name}</p>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-4 col-sm-4 form-group">
                        <label><fmt:message key="price"/></label>
                        <p class="form-control-static">${schedule.procedure.price} <fmt:message key="uah"/></p>

                    </div>
                </div>
                <div class="form-row">
                    <div class="col-4 col-sm-4 form-group">
                        <button type="submit" class="btn btn-primary" style="margin-top:30px">
                            <fmt:message key="book"/>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
