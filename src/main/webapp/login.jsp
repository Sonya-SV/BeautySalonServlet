<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="login"/></title>
</head>
<body>
<%@ include file="WEB-INF/parts/common.jsp" %>
<div class="panel panel-default">
<div class="login-page">
    <div class="col-md-offset-4 col-md-3" style="padding-top: 100px">
        <div class="login-block">
            <img src="https://www.domzamkad.ru/images/no-avatar.png" width="200" alt="Scanfcode">
            <h1><fmt:message key="put.your.data"/></h1>
            <c:if test="${requestScope.userError ne null}">
                <div class="alert alert-danger">
                        ${userError}
                </div>
            </c:if>
            <form >
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-user ti-user"></i></span>
                        <input type="text" class="form-control" name="email" placeholder="<fmt:message key="email"/>">
                    </div>
                </div>
                <hr class="hr-xs">
                <div class="form-group">
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-lock ti-unlock"></i></span>
                        <input type="password" class="form-control" name="password" placeholder="<fmt:message key="password"/>">
                    </div>
                </div>
                <button class="btn btn-primary btn-block" type="submit"><fmt:message key="login"/></button>
            </form>
        </div>
        <div class="login-links">
            <p class="text-center"><fmt:message key="do.not.have.account"/>
                <a class="txt-brand" href="${pageContext.request.contextPath}/beauty-salon/registration">
                    <font color=#29aafe><fmt:message key="registration"/></font>
                </a>
            </p>
        </div>
    </div>
</div>
</div>
</body>
</html>