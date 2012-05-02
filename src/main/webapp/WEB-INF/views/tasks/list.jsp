<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row-fluid">
  	<div class="span1">
	</div>
  	<div class="span8">
		<table class="table table-condensed table-bordered">
			<thead>
				<tr>
					<th><spring:message code="task.name" /></th>
					<th><spring:message code="task.project" /></th>
					<th><spring:message code="task.context" /></th>
					<th><spring:message code="task.startDate" /></th>
					<th><spring:message code="task.completedDate" /></th>
					<th><spring:message code="task.isDone" /></th>
					<th><spring:message code="task.status" /></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${tasks}" var="task">
				<tr>
					<td><c:out value="${task.title}" /></td>
					<td><c:out value="${task.project.name}" /></td>
					<td><c:out value="${task.context.name}" /></td>
					<td><fmt:formatDate value="${task.startDate}" type="date" /></td>
					<td><fmt:formatDate value="${task.completedDate}" type="date" /></td>
					<td><c:out value="${task.isDone}" /></td>
					<td><c:out value="${task.status}" /></td>
					<td><spring:url value="/tasks/${task.id}" var="update_form_url">
							<spring:param name="form" />
						</spring:url> 
						<spring:message code="tasks.edit_task" var="edit_message" htmlEscape="false" /> 
						<a href="${update_form_url}">${fn:escapeXml(edit_message)}</a>
					</td>
					<td>
						<spring:url value="/tasks/${task.id}" var="delete_form_url" /> 
						<form:form action="${delete_form_url}" method="DELETE" id="${context.id}_DELETE_FORM">
							<input name="page" type="hidden" value="${param.page}" />
							<input name="size" type="hidden" value="${param.size}" />
							<spring:message code="task.delete_task" var="delete_message" htmlEscape="false" />
							<input type="submit" value="${fn:escapeXml(delete_message)}" class="btn-small" />
						</form:form>						
					
					</td>
				</tr>
		
			</c:forEach>
			<tr>
			</tbody>
		</table>
		<util:pagination maxPages="${maxPages}" page="${param.page}" size="${param.size}"></util:pagination>
		
		<spring:url var="createUrl" value="/tasks">
			<spring:param name="form"></spring:param>
		</spring:url>
		<spring:message code="task.new_task" var="add_message" htmlEscape="false" />
		<div>
			<a href="${createUrl}">${fn:escapeXml(add_message)}</a>
		</div>
	</div>
</div>



