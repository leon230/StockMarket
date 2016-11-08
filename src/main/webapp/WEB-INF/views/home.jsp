<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<sec:authorize access="hasRole('ROLE_USER')">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/style.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/bootstrap.min.css" />" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="<c:url value="/resources/js/main.js" />"></script>
        <script src="<c:url value="/resources/js/LoadData.js" />"></script>
    </head>

    <div class = "headerbar">
        <jsp:include page="header.jsp" />
    </div>

    <c:set var="username" scope = "session" value="${pageContext.request.userPrincipal.name}"/>
    <c:url value="/buyStock" var="buyStock" />
    <c:url value="/sellStock" var="sellStock" />
    <body onload="refreshData()">
    <div id= "connectionError">${connectionErrorMsg}</div>
    <hr>
    <div class="wrapper">
    <c:if test="${empty connectionErrorMsg}">
	<div class ="table1Container">
        <div id ="fpData"></div>
	</div>
    </c:if>
        <br />
    <c:set var="itemValue" value="${walletItem.walletItemAmount * walletItem.walletItemPrice}"/>
        <div class ="table2Container">
        <table class="mainTable">
                <thead>
                    <th>Company</th>
                    <th>Unit price</th>
                    <th>Amount</th>
                    <th>Value</th>
                    <th>Action</th>
                </thead>
                <tbody>
                    <c:forEach var="walletItem" items="${wallItems}">
                       <tr>
                                <td >${walletItem.walletItemStockName}</td>
                                <td >${walletItem.walletItemPrice}</td>
                                <td >${walletItem.walletItemAmount}</td>
                                <td >${walletItem.walletItemValue}</td>
                                <td ><a href ="${sellStock}?walletItemId=${walletItem.walletItemId}&resourceAmount=${walletItem.walletItemValue}&stockAmount=${walletItem.walletItemAmount}&stockName=${walletItem.walletItemStockName}" class="sellConfirm">Sell</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
        </table>
        <a>Available money: ${walletResources} PLN</a>
        </div>
    </div>
    <br />
    </body>
</html>
</sec:authorize>