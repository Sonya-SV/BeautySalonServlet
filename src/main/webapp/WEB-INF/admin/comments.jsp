<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="container" style="margin-top: 60px">
    <div class="col-md-10    col-md-offset-1" style="padding-top: 50px">
        <h2 align="center"><fmt:message key="reviews"/></h2>
        <div class="comments">
            <form action="${pageContext.request.contextPath}/beauty-salon/admin/comment">
                <c:if test="${successSend ne null}">
                    <div class="alert alert-success">
                            ${successSend}
                    </div>
                </c:if>
                <textarea rows="4" cols="100%" name="comment" placeholder="Put your comment"></textarea>
                <input type="hidden" name="masterId" value="${master.id}">
                <button type="submit" class="btn btn-primary" style="margin-top:30px"
                        ng-disabled="form.$invalid">
                    Send
                </button>
            </form>
            <h5>${fn:length(comments)} <fmt:message key="comments"/></h5>
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
                                <div class="autor"><h4></h4>${com.user.firstName} ${com.user.lastName}</h4></div>
                                <div class="time" style="text-align: right"><h4>
                                    <fmt:parseDate value="${ com.dateTime }" pattern="yyyy-MM-dd'T'HH:mm"
                                                   var="parsedDateTime" type="both"/>
                                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${ parsedDateTime }"/></h4>
                                </div>
                            </div>
                            <div class="media-text text-justify"><p style="text-indent: 25px;">${com.comment}</p></div>

                        </div>
                    </li>
                </ul>
                <hr align="center" width="100%" style="border-color: slategrey"/>
            </c:forEach>
        </div>
    </div>
</div>