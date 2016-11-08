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
<c:url value="home/buyStock" var="buyStock" />
<c:url value="home/sellStock" var="sellStock" />
    <body>

    <hr>
    <div class="wrapper">
    <h1> Please define your initial wallet</h1>
    <form:form method="post" action="saveInitialWallet" modelAttribute="InitialWallet">
    <form:errors path="*" class="errorblock" element="div"/>
        <table class = "mainTable">
        <tr>
    <th>No.</th>
    <th>Company</th>
    <th>Stock amount</th>
    <th>Unit price</th>
        </tr>
        <c:forEach items="${InitialWallet.walletStockList}" var="contact" varStatus="status">
            <tr>
                <td align="center">${status.count}</td>
                <td><input name="walletStockList[${status.index}].walletItemStockName" value="${contact.walletItemStockName}" readonly="true" /></td>
                <td><input name="walletStockList[${status.index}].walletItemAmount" value="${contact.walletItemAmount}"/></td>
                <td><input name="walletStockList[${status.index}].walletItemPrice" value="${contact.walletItemPrice}"/></td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <input type="submit" value="Save" />

    </form:form>




    </div>
    <br />
    </body>
</html>
</sec:authorize>