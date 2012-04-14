<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util" %>


<spring:url value="/resources/j_spring_security_check" var="form_url" />

<div class="row-fluid">
  	<div class="span4">
		<form:form action="${form_url}" method="POST">
			<label for="username"><spring:message code="login.username" /> : </label> <input type="text" name="j_username"/>
			<label for="password"><spring:message code="login.password" /> : </label> <input type="password" name="j_password"/>
			<input type="submit"/>
		</form:form> 
    	<p><a class="btn" href="#">View details &raquo;</a></p>
	</div><!--/span-->
</div>
