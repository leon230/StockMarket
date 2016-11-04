<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<head>
		<link href="<c:url value="/resources/bootstrap.min.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/style.css" />" rel="stylesheet">

</head>
<sec:authorize access="hasRole('ROLE_USER')">
		<!-- For login user -->
		<c:url value="/logout" var="logoutUrl" />
		<c:url value="/home" var="urlHome" />
        <c:url value="/home/newTicket" var="urlAddTicket" />
        <c:url value="/charts" var="urlCharts" />

        <nav class="headerbar">
        	<div class="container">
        		<div class="navbar-header">
        			<a class="navbar-brand" href="${urlHome}">Home</a>
        		</div>
        		<div class="navbar-header">
                    <a class="navbar-brand" href="${urlCharts}">Charts</a>
                </div>

        		<div id="navbar">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active">
                        <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <a href="javascript:formSubmit()"> Logout</a>
                         </c:if>
                         <form action="${logoutUrl}" method="post" id="logoutForm">
                            <input type="hidden" name="${_csrf.parameterName}"
                            value="${_csrf.token}" />
                        </form>
                         </li>
                    </ul>
                </div>
                <div id="navbar">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active"><a href="${urlAddTicket}">Add Ticket</a></li>
                    </ul>
                </div>
        	</div>
        </nav>


		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

	</sec:authorize>





