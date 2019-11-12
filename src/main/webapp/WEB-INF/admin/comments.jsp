<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>All comments</title>
</head>
<body>
<%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" />--%>
<%@ include file="adminpart/adminnavbar.jsp" %>

<div class="container" style="margin-top: 60px">
    <div class="col-md-10    col-md-offset-1" style="padding-top: 50px">
        <div class="comments">
            <h5>${fn:length(comments)} comments</h5>
            <c:forEach items="${comments}" var="com">
                <ul class="media-list">
                    <li class="media">
                        <div class="media-left">
                            <a href="#">
                                <img src="https://www.domzamkad.ru/images/no-avatar.png" class="media-object img-circle"
                                     alt="avatar" height="60">
                            </a>
                        </div>
                        <div class="media-body">
                            <div class="medi-heading">
                                <div class="autor"><h4><a data-toggle="collapse" href="${pageContext.request.contextPath}/app/user/master?masterId=${com.master.id}">
                                        ${com.master.user.firstName} ${com.master.user.lastName}</a></h4></div>
                            </div>
                            <div class="media-text text-justify"><p style="text-indent: 25px;">${com.comment}</p>
                            </div>
                            <div class="footer-comment">
                                <span class="comment-reply">
                                    <i>${com.user.firstName}</i>
                                </span>
                            </div>
                            <div id="collapseOne" class="panel-collapse collapse">
                                <div class="media-body">
                                    <textarea class="form-control" name="comments" rows="3"
                                              placeholder="Join the discussion"></textarea>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
                <hr align="center" width="100%" color="black"/>
            </c:forEach>
        </div>
    </div>
</div>

</body>
</html>
