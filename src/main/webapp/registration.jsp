<html>
<head>
    <title>Registration form</title>
</head>
<body>
<%@ include file="WEB-INF/parts/common.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6" style="padding-top: 100px">
            <form class="form-horizontal">
                <span class="heading">Registration</span>
                <div>


                    <div class="form-group">

                        <input type="text" class="form-control"  name="firstName" placeholder="First name">
                        <i class="fa fa-user"></i>
                    </div>
                    <div class="form-group">

                        <input type="text" class="form-control"  name="lastName" placeholder="Last name">
                        <i class="fa fa-user"></i>
                    </div>


                    <div class="form-group">

                        <input type="text" class="form-control" name="email" id="inputEmail"
                               placeholder="Email">
                        <i class="fa fa-user"></i>
                    </div>
<%--                                        <label id="passwordLabel" >Password</label>--%>
                    <div class="form-gro    up">

                        <input type="password" class="form-control" name="password" id="inputPassword"
                               placeholder="Password">
                        <i class="fa fa-lock"></i>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-default">Registration</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>