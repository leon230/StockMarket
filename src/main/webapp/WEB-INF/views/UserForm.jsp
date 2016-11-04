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

    <title>New/Edit User</title>
</head>

<jsp:include page="header.jsp" />
<body>
	<div class="container">
		<h1>New/Edit User</h1>
		<form:form action="saveUser" method="post" modelAttribute="UserForm">
		<form:errors path="*" class="errorblock" element="div"/>

            <form:hidden path="userWalletId"/>
            <form:hidden path="userId"/>

			<spring:bind path="userName">
			<div class="form-group">
				<label class="col-sm-2 control-label">Username</label>
				<div class="col-sm-10">
					<form:input path="userName" type="text" class="form-control " id="userName" placeholder="Username" />
					<form:errors path="userName" class="control-label" />
				</div>
			</div>
			</spring:bind>

			<spring:bind path="userPass">
			<div class="form-group">
			<div class="form-group">
				<label class="col-sm-2 control-label">Password</label>
				<div class="col-sm-10">
					<form:input path="userPass" type="text" class="form-control " id="userPass" placeholder="Password" />
					<form:errors path="userPass" class="control-label" />
				</div>
			</div>
			</spring:bind>

			<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<input type="submit" value="Save">
				<a href="./"> Cancel </a>
			</div>
		    </div>



		</form:form>
	</div>
</body>
</html>