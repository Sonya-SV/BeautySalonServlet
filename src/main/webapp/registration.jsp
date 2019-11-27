<html>
<head>
    <%@ include file="WEB-INF/parts/common.jsp" %>
    <title><fmt:message key="registration"/></title>
</head>
<body>

<div class="login-page">
    <div class="col-md-offset-4 col-md-3" style="padding-top: 100px">
        <div class="login-block">
            <img src="https://www.domzamkad.ru/images/no-avatar.png" width="200" alt="Scanfcode">
            <h1><fmt:message key="put.your.data"/></h1>
            <c:if test="${errorMessage ne null}">
                <div class="alert alert-danger">
                        ${errorMessage}
                </div>
            </c:if>
            <form>
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user ti-user"></i></span>
                        <input required type="text" class="form-control" name="email" placeholder="<fmt:message key="email"/>">
                    </div>
                </div>
                <hr class="hr-xs">
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-check"></i></span>
                        <input type="text" class="form-control" name="firstName"
                               placeholder="<fmt:message key="first.name"/>">
                    </div>
                </div>
                <hr class="hr-xs">
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-check"></i></span>
                        <input type="text" class="form-control" name="lastName"
                               placeholder="<fmt:message key="last.name"/>">
                    </div>
                </div>
                <hr class="hr-xs">
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock ti-unlock"></i></span>
                        <input required type="password" class="form-control" name="password"
                               placeholder="<fmt:message key="password"/>">
                    </div>
                </div>
                <button class="btn btn-primary btn-block" type="submit"><fmt:message key="registration"/></button>
            </form>
        </div>
        <div class="login-links">
            <p class="text-center">
                <fmt:message key="already.have.account"/>
                <a class="txt-brand" href="${pageContext.request.contextPath}/beauty-salon/login">
                    <font color=#29aafe><fmt:message key="login"/></font>
                </a>
            </p>
        </div>
    </div>
</div>
</body>
</html>