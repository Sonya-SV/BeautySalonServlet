<html>
<head>
    <title>Order</title>
</head>
<body>
<%@ include file="userpart/usernavbar.jsp" %>
<div class="container" style="margin-top: 100px">
    <div class="row">
        <div class="col-md-5">
            <img class="card-img"
                 src="data:image/png;base64,${schedule.master.photo}"
                 alt="..." height="400" style=" display: block;
                                     margin: 0 auto; margin-top: 5px">
        </div>
        <div class="col-md-7" style="margin-top: 20px">
            <form action="${pageContext.request.contextPath}/beauty-salon/user/save" role="form">
                <c:if test="${alreadyBooked ne null}">
                <div class="alert alert-danger">
                    ${alreadyBooked}
                </div>
                </c:if>
                <div class="form-row">
                    <div class="col-6 col-sm-4 form-group">
                        <label>First name</label>
                        <input type="text" value="${user.firstName}" class="form-control" name="firstName">
                    </div>
                    <div class="col-6 col-sm-8 form-group">
                        <label>Last name</label>
                        <input type="text" value="${user.lastName}" class="form-control" name="lastName">
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-4 col-sm-4 form-group">
                        <label>Time</label>
                        <input type="text" value="${schedule.time}" class="form-control" name="time">
                    </div>
                    <div class="col-4 col-sm-4 form-group">
                        <label>Date</label>
                        <input type="text" value="${schedule.date}" class="form-control" name="date">
                    </div>
                    <div class="col-4 col-sm-4 form-group">
                        <label>Master</label>
                        <p class="form-control-static">${schedule.master.user.firstName} ${schedule.master.user.lastName}
                        </p>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-4 col-sm-4 form-group">
                        <label>Procedure</label>
                        <p class="form-control-static">${schedule.procedure.name}</p>
                    </div>
                </div>
                <div class="form-row">
                    <div class="col-4 col-sm-4 form-group">
                        <label>Price</label>
                        <p class="form-control-static">${schedule.procedure.price} UAH</p>

                    </div>
                </div>
                <div class="form-row">
                    <div class="col-4 col-sm-4 form-group">
                        <button type="submit" class="btn btn-primary" style="margin-top:30px"
                                ng-disabled="form.$invalid">
                            Book
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<form name="address" action="#">
    Date:
    <select name="street" onChange="MkHouseValues(this.selectedIndex)">
        <c:forEach var="date" items="${dates}">
            <option value="${date}">${date}</option>
        </c:forEach>
    </select>
    &nbsp;Time:
    <select name="house">
        <option value="N/A">N/A</option>
    </select>
</form>





</body>
<script type="text/javascript">
    <!--
    // Формируем массив городов
    var aHouseValues = new Array(
        "11, 12, 12, 14",
        "11, 12",
        "12, 13",
        "11",
        "12, 14"
    );

    // var aHouseValues = {};
    // ф-ция, возвращающая массив городов по заданной стране
    function getHouseValuesByStreet(index){
        var sHouseValues = aHouseValues[index];
        return sHouseValues.split(","); // преобразуем строку в массив городов
    }

    // ф-ция, выводящая динамически список городов
    function MkHouseValues(index){
        var aCurrHouseValues = getHouseValuesByStreet(index);
        var nCurrHouseValuesCnt = aCurrHouseValues.length;
        var oHouseList = document.forms["address"].elements["house"];
        var oHouseListOptionsCnt = oHouseList.options.length;
        oHouseList.length = 0; // удаляем все элементы из списка городов
        for (i = 0; i < nCurrHouseValuesCnt; i++){
            // далее мы добавляем необходимые города в список
            if (document.createElement){
                var newHouseListOption = document.createElement("OPTION");
                newHouseListOption.text = aCurrHouseValues[i];
                newHouseListOption.value = aCurrHouseValues[i];
                // тут мы используем для добавления элемента либо метод IE, либо DOM
                (oHouseList.options.add) ? oHouseList.options.add(newHouseListOption) : oHouseList.add(newHouseListOption, null);
            }else{
                // для NN3.x-4.x
                oHouseList.options[i] = new Option(aCurrHouseValues[i], aCurrHouseValues[i], false, false);
            }
        }
    }

    // инициируем изменение элементов в списке городов, в зависимости от текущей страны
    MkHouseValues(document.forms["address"].elements["street"].selectedIndex);
    //-->
</script>
</html>
