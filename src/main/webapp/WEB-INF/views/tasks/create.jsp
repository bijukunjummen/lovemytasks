<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<spring:url value="/tasks" var="submitUrl" />
<spring:message var="projectSelectMessage" code="task.create.project.message" />
<spring:message var="contextSelectMessage" code="task.create.context.message" />
<div class="row-fluid">
  	<div class="span1">
	</div>
  	<div class="span8">
	<form:form action="${submitUrl}" method="POST" modelAttribute="task">
		<form:errors cssClass="error" delimiter="&lt;p/&gt;" />
		<form:hidden id="_id" path="id" />
		<form:hidden id="_version" path="version" />
		<label for="task_title"> <spring:message code="task.create.title" /> : </label>
		<form:input path="title" />
		<form:errors cssClass="error" id="name_error_id" path="title" />
		<br />
		<label for="task_project"> <spring:message code="task.create.project" /> : </label>
		<form:select path="project" multiple="false">
			<form:option value="-1" label="${projectSelectMessage}"/>
			<form:options items="${projects}" itemValue="id" itemLabel="name"/>
		</form:select>
		<form:errors cssClass="error" id="name_error_id" path="project" />
		<br />	
		<label for="task_context"> <spring:message code="task.create.context" /> : </label>
		<form:select path="context" multiple="false">
			<form:option value="-1" label="${contextSelectMessage}"/>
			<form:options items="${contexts}" itemValue="id" itemLabel="name"/>
		</form:select>
		<form:errors cssClass="error" id="name_error_id" path="context" />
		<br />			
		<label for="task_isdone"><spring:message code="task.create.isdone"/></label>
		<form:checkbox path="isDone"/>
		<br />		
		<label for="task_startDate"><spring:message code="task.create.startDate"/></label>	
		<form:input path="startDate"/>
		
		<div class="submit" id="submit">
			<spring:message code="button_save" var="save_button" htmlEscape="false" />
			<input id="proceed" type="submit" value="${fn:escapeXml(save_button)}" />
		</div>
	</form:form>
  	
	</div>
</div>