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
	<form:form action="${submitUrl}" method="PUT" modelAttribute="task" class="form-horizontal">
		<form:errors cssClass="error" delimiter="&lt;p/&gt;" />
		<form:hidden id="_id" path="id" />
		<form:hidden id="_version" path="version" />
		<div class="control-group">
			<label for="task_title" class="control-label"> <spring:message code="task.create.title" /> : </label>
			<div class="controls">
				<form:input path="title" />
				<form:errors cssClass="error" id="name_error_id" path="title" />
			</div>
		</div>
		<br />
		<div class="control-group">
			<label for="task_project" class="control-label"> <spring:message code="task.create.project" /> : </label>
			<div class="controls">
				<form:select path="project" multiple="false">
					<form:option value="-1" label="${projectSelectMessage}"/>
					<form:options items="${projects}" itemValue="id" itemLabel="name"/>
				</form:select>
				<form:errors cssClass="error" id="name_error_id" path="project" />
			</div>
		</div>
		<br />	
		<div class="control-group">
			<label for="task_context" class="control-label"> <spring:message code="task.create.context" /> : </label>
			<div class="controls">
				<form:select path="context" multiple="false">
					<form:option value="-1" label="${contextSelectMessage}"/>
					<form:options items="${contexts}" itemValue="id" itemLabel="name"/>
				</form:select>
				<form:errors cssClass="error" id="name_error_id" path="context" />
			</div>
		</div>
		<br />		
		<div class="control-group">
			<label for="task_isdone" class="control-label"><spring:message code="task.create.isdone"/> : </label>
			<div class="controls">
				<form:checkbox path="isDone"/>
				<form:errors cssClass="error" id="isdone_error_id" path="isDone" />
			</div>
		</div>
		<br />	
		<div class="control-group">	
			<label for="task_startDate" class="control-label"><spring:message code="task.create.startDate"/> : </label>	
			<div class="controls">
				<form:input path="startDate"/>
				<form:errors cssClass="error" id="name_error_id" path="startDate" />
			</div>
		</div>
		<br />			
		<div class="submit" id="submit">
			<spring:message code="button_save" var="save_button" htmlEscape="false" />
			<input id="proceed" type="submit" value="${fn:escapeXml(save_button)}" />
		</div>
		
	</form:form>
  	
	</div>
</div>