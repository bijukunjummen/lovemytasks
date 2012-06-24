<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<spring:url value="/registrations" var="submitUrl" />
<spring:message code="button_save" var="save_button" htmlEscape="false" />
<spring:message code="registration.submit" var="register_button" htmlEscape="false"/>
<div class="row-fluid">
  	<div class="span1">
	</div>
  	<div class="span8">
	<form:form action="${submitUrl}" method="POST" modelAttribute="registration">
		<form:errors cssClass="error" delimiter="&lt;p/&gt;" />
		<label for="user_name"> <spring:message code="registration.username" /> : </label>
		<form:input path="username" /> <form:errors cssClass="error" id="name_error_id" path="username" />
		<br />
		<label for="password"> <spring:message code="registration.password" /> : </label>
		<form:password path="password" /> <form:errors cssClass="error" id="password_error_id" path="password" />
		<br />
		<label for="confirmPassword"> <spring:message code="registration.confirmpassword" /> : </label>
		<form:password path="confirmPassword" /> <form:errors cssClass="error" id="confirm_password_error_id" path="confirmPassword" />
		<br />		
		<label for="name"> <spring:message code="registration.fullname" /> : </label>
		<form:input path="fullname" /> <form:errors cssClass="error" id="fullname_error_id" path="fullname" />
		<br />
		<div class="submit" id="submit">	
			<input id="proceed" type="submit" value="${fn:escapeXml(register_button)}" />
		</div>
	</form:form>
  	
	</div>
</div>