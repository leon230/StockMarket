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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script src="<c:url value="/resources/js/LoadData.js" />"></script>
    </head>
<div class = "headerbar">
    <jsp:include page="header.jsp" />
</div>

<c:set var="username" scope = "session" value="${pageContext.request.userPrincipal.name}"/>
<c:url value="home/buyStock" var="buyStock" />
<body>

<hr>
<a href="/home/json">Buy</a>
<div class="wrapper">


		<a href="#" onclick="javascript:printData();">hh</a>
		<a id="msg">aa </a>
</div>
    </body>
</html>
</sec:authorize>




