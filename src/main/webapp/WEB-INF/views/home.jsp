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
    </head>

<div class = "headerbar">
    <jsp:include page="header.jsp" />
</div>

<c:set var="username" scope = "session" value="${pageContext.request.userPrincipal.name}"/>
<c:url value="home/buyStock?stockId=" var="buyStock" />
<body>

<hr>

<div class="wrapper">
    <table class="mainTable">
        <thead>
            <th>Company</th>
            <th>Value</th>
            <th>Action</th>
        </thead>
        <tbody>

            <c:forEach var="stock" items="${stockList}">
               <tr>
                        <td >${stock.stockCompany}</td>
                        <td >${stock.stockValue}</td>
                        <td>
                            <a href="${buyStock}${stock.stockId}">Buy</a>
                        </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <br />
<c:set var="itemValue" value="${walletItem.walletItemAmount * walletItem.walletItemPrice}"/>
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
                            <td >${walletId}</td>

                    </tr>
                </c:forEach>
            </tbody>
    </table>
</div>
    </body>
</html>
</sec:authorize>