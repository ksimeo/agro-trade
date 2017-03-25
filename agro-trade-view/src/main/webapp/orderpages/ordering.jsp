<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ page isELIgnored="false" %>

<html>
<head lang="ru">
    <!--<meta charset="UTF-8">-->
    <title>ЖивоРост Біогумат - Замовлення</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../resources/css/style.css"/>
    <link rel="shortcut icon" href="../img/worms.ico" type="image/x-icon"/>
</head>
<body onload="onLoad()">
<script type="text/javascript">
    var defaultValue;
    function onLoad() {

        var p_url=location.search.substring(1);
        document.getElementById('number').value=p_url;
    }
</script>
<form action="/doorder" method="post">
        <div style="width: 100%; text-align: -webkit-center;  margin-top: 50; color: #d4ebda;" align="centre">
        <table>
            <tr>
                <td>
                    <h3>Для оформления вашего заказа заполните <br> поля ниже и кликните "Заказать" </h3>
                    <br>
                </td>
            </tr>
            <tr>
                <td>
                    <h4>Ваш контактный номер телефона: &nbsp; <input type="tel" name="telefon" size="9" value="${param.tel}" required/>*</h4>
                </td>
            </tr>
            <tr>
                <td>
                    <h4>Ваш электронный адрес: &nbsp; <input type="email" name="address" value="${param.address}" size="19"/></h4>
                </td>
            </tr>
            <tr>
                <td>
                    <h4>Ваш регион: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                        <select name="region" size="1">
                            <c:forEach items="${param.region}" var="item">
                                <%--<option name="employer" value="${item.roleId}">${item.nameRole}</option>--%>
                                <option value="Крым">Автономная Республика Крым</option>
                                <option value="Винница">Винницкая область</option>
                                <option value="Волынь">Волынская область</option>
                                <option value="Днепр">Днепропетровская область</option>
                                <option value="Донецк">Донецкая область</option>
                                <option value="Житомир">Житомирская область</option>
                                <option value="Закарпатье">Закарпатская область</option>
                                <option value="Запорожье">Запорожская область</option>
                                <option value="Ивано-Франковск">Ивано-Франковская область</option>
                                <option value="Киев">Киевская область</option>
                                <option value="Кировоград">Кировоградская область</option>
                                <option value="Львов">Львовская область</option>
                                <option value="Николаев">Николаевская область</option>
                                <option value="Одесса">Одесская область</option>
                                <option value="Ровно">Ровенская область</option>
                                <option value="Сумы">Сумская область</option>
                                <option value="Тернополь">Тернопольская область</option>
                                <option value="Харьков">Харьковская область</option>
                                <option value="Хмельницк">Хмельницкая область</option>
                                <option value="Херсон">Херсонская область</option>
                                <option value="Черкассы">Черкасская область</option>
                                <option value="Чернигов">Черниговская область</option>
                                <option value="Черновцы">Черновецкая область</option>
                                <option value="Житомир">Житомирская область</option>
                                <option value="" selected></option>
                            </c:forEach>
                        </select>*</h4>
                </td>
            </tr>
            <tr>
                <td>
                    <h4>Укажите тип и количество биогумата, которое<br>
                        вам необходимо: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                        <select id="biogumatType" name="product" onchange="changeText0()">
                            <option value="1">Марка 1</option>
                            <option value="2">Марка 2</option>
                            <option value="3">Марка 3</option>
                        </select>
                        <input type="number" id="number" min="1" max="999999" name="number" value="${param.number}" required/>* л</h4>
                </td>
            </tr>
            <tr>
                <td>
                    <h5>* - Поля обязательные для заполнения</h5>
                    <h3><input type="submit" id="w" value="Заказать" >
                        <p style="color: red">
                            ${ErrorForm}
                        </p>
                        <%--&nbsp; &nbsp; <a href="agrocalc.html"> Вернуться к рассчету количества</a></h3>--%>
                </td>
            </tr>
        </table>
        </div>
</form>
</body>
</html>