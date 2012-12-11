<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<spring:url value="/contexts" var="submitUrl" />
<div class="row-fluid">
  	<div class="span1">
	</div>
  	<div class="span8">
	<form:form action="${submitUrl}" method="PUT" modelAttribute="context">
		<form:errors cssClass="error" delimiter="&lt;p/&gt;" />
		<form:hidden id="_id" path="id" />
		<form:hidden id="_version" path="version" />
		<label for="context_name"> <spring:message code="context.create.name" /> : </label>
		<form:input path="name" />
		<form:errors cssClass="error" id="name_error_id" path="name" />
		<br />
		<div class="submit" id="submit">
			<spring:message code="button_save" var="save_button" htmlEscape="false" />
			<input id="proceed" type="submit" value="${fn:escapeXml(save_button)}" />
		</div>
	</form:form>
  	
	</div>
</div>