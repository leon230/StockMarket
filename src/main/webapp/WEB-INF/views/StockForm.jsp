<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="<c:url value="/resources/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/style.css" />" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
            <script src="<c:url value="/resources/js/main.js" />"></script>
            <script src="<c:url value="/resources/js/LoadData.js" />"></script>

    <title>Buy Stock</title>
</head>

<jsp:include page="header.jsp" />
<body>
	<div class="container">
        <h1>Stock details</h1>
            <form:form name="StockForm" action="addStock?walletId=${walletId}" method="post" modelAttribute="StockForm">
            <form:errors path="*" class="errorblock" element="div"/>


                <spring:bind path="walletItemStockName">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Stock name</label>
                        <div class="col-sm-10">
                            <form:input  path="walletItemStockName" type="text" class="form-control " id="walletItemStockName" placeholder="Stock name" readonly="true" />
                        </div>
                    </div>
                </spring:bind>

                <div class="form-group">
                    <label class="col-sm-2 control-label">Stock unit</label>
                    <div class="col-sm-10">
                        <input  type="text" class="form-control " id="stockUnit" value="${stockUnit}" readonly="true" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">Available Stock amount</label>
                    <div class="col-sm-10">
                        <input  type="text" class="form-control " id="stockUnit" value="${stockAmount}" readonly="true" />
                    </div>
                </div>
                <spring:bind path="walletItemPrice">
                    <div class="form-group">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Unit price</label>
                        <div class="col-sm-10">
                            <form:input path="walletItemPrice" type="test" class="form-control " id="walletItemPrice" placeholder="Item Price" readonly="true" />
                            <form:errors path="walletItemPrice" class="control-label" />
                        </div>
                    </div>
                </spring:bind>


                <spring:bind path="walletItemAmount">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Amount</label>
                    <div class="col-sm-10">
                        <form:input  path="walletItemAmount" type="text" class="form-control " id="walletItemAmount" placeholder="Amount" />
                        <form:errors path="walletItemAmount" class="control-label" />
                    </div>
                </div>
                </spring:bind>


            <br />
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" value="Submit" class = "buyConfirm" onclick="validateForm()">
                    <a href="./"> Cancel </a>
                </div>
            </div>
            </form:form>
	</div>
</body>
</html>