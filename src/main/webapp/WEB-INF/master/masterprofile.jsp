<html>
<body>
<%@ include file="masterpart/masternavbar.jsp" %>
<div class="container" style="margin-top: 60px">
    <div class="row">
        <div class="col-md-8 col-md-offset-2" style="padding-top: 50px">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title" style="display: inline-block">${user.email}</h3>
                </div>
                <div class="panel-body">
                    <form name="form" action="${pageContext.request.contextPath}/beauty-salon/master/profile">
                        <div class="form-group">
                            <label id="exampleInputFirstNameLabel"><fmt:message key="first.name"/></label>
                            <input type="text" class="form-control" name="firstName" value="${user.firstName}" required>
                        </div>
                        <div class="form-group">
                            <label><fmt:message key="last.name"/></label>
                            <input type="text" class="form-control" name="lastName" value="${user.lastName}" required>
                        </div>
                        <div class="form-group">
                            <label><fmt:message key="password"/></label>
                            <input required type="password" class="form-control" name="password" >
                            <div class="text-danger">
                                ${passwordErrorDiffer}
                            </div>
                        </div>
                        <div class="form-group">
                            <label><fmt:message key="masters"/></label>
                            <input required type="password" class="form-control" name="password2" >
                            <div class="text-danger">
                                ${passwordErrorDiffer}
                            </div>
                        </div>
                        <div class="text-success">
                            ${successSave}
                        </div>
                        <button type="submit" class="btn btn-success" style="margin-top:30px"
                                ng-disabled="form.$invalid">
                            <fmt:message key="save"/>
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>