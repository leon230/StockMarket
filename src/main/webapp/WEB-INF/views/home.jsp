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
        <title>Stock Market Home</title>
    </head>

<div class = "headerbar">
<jsp:include page="header.jsp" />
</div>
    <body>

	<hr>

    	<div class="wrapper">
	        <h1>${title}</h1>
	        <a>You are in stock market</a>
    	</div>
    </body>
</html>
</sec:authorize>