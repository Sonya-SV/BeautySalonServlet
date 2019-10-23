<html>
<body>
<%@ include file="../parts/common.jsp" %>
    <div class="container" style="margin-top: 60px">
        <div class="row">
            <div class="col-md-8 col-md-offset-2" style="padding-top: 50px">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title" style="display: inline-block">${user.username}</h3>
                    </div>
                    <div class="panel-body">
                        <form name="form" action="${pageContext.request.contextPath}/app/user/profile" autocomplete="off" novalidate
                              ng-submit="form.$valid ">

                            <div class="form-group">
                                <label id="exampleInputFirstNameLabel"
                                       for="firstName">First name</label>
                                <input type="text"
                                       class="form-control"
                                       name="firstName"
                                       id="firstName"
                                       placeholder=""
                                       value="${user.firstName}"
                                       required>
                            </div>
                            <div class="form-group">
                                <label id="exampleInputLastNameLabel"
                                       for="lastName">Last name</label>
                                <input type="text"
                                       class="form-control"
                                       id="lastName"
                                       name="lastName"
                                       placeholder=""
                                       value="${user.lastName}"
                                       required>
                            </div>
                            <div class="form-group">
                                <label id="passwordLabel" for="password">Pasword</label>
                                <input type="password"
                                       class="form-control"
                                       id="password"
                                       name="password"
                                       placeholder=""
                                       required>
<%--                                <div class="text-danger">--%>
<%--                                    ${password2Error!}--%>
<%--                                    ${passwordErrorDiffer!}--%>
<%--                                </div>--%>
                            </div>
                            <div class="form-group">
                                <label id="password2Label" for="password2">Password</label>
                                <input type="password"
                                       class="form-control"
                                       id="password2"
                                       name="password2"
                                       placeholder=""
                                       value=""
                                       required>
<%--                                <div class="text-danger">--%>
<%--                                    ${password2Error!}--%>
<%--                                    ${passwordErrorDiffer!}--%>
<%--                                </div>--%>
                            </div>
                            <div class="text-success">
                                ${successSave}
                            </div>
                            <button type="submit" class="btn btn-success" style="margin-top:30px"
                                    ng-disabled="form.$invalid">
                             Save
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>