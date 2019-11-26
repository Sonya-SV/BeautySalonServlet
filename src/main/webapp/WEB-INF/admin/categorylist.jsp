<html>
<head>
    <%@ include file="adminpart/adminnavbar.jsp" %>
    <title><fmt:message key="procedures"/></title>
</head>
<body>
<div class="container" style="margin-top: 100px">
    <form autocomplete="off" novalidate>
        <div class="menu">
        <c:forEach items="${categories}" var="i">
            <div class="holder">
                <a href="${pageContext.request.contextPath}/beauty-salon/admin/procedures?categoryId=${i.id}">
                    <img src="data:image/png;base64,${i.image}" alt="..." height="380">
                    <input type="hidden" value="${i.id}" name="categoryId">
                </a>
                <div class="block">
                    <h2 style="text-align: center">${i.name}</h2>
                </div>
            </div>
        </c:forEach>
        </div>
    </form>
</div>
</body>
</html>
