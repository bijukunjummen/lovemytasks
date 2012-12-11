<%@ page trimDirectiveWhitespaces="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>

<script type="text/javascript">

<spring:url value="/tasks/" var="delete_form_url" /> 
function deleteTask(id, deleteUrl){
	var form = $('<form/>', {
        action: deleteUrl + id,
        method: "POST"
    });
	
	form.append($('<input/>', {
        type: 'hidden',
        name: "_method",
        value: "DELETE"
    }));
	form.append($('<input/>', {
        type: 'hidden',
        name: "page",
        value: "${param.page}"
    }));
	form.append($('<input/>', {
        type: 'hidden',
        name: "size",
        value: "${param.size}"
    }));
	
	form.appendTo('body').submit();
}
</script>
<spring:message code="project.delete_project" var="delete_message" htmlEscape="false" />
<spring:message code="tasks.edit_task" var="edit_message" htmlEscape="false" /> 

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row-fluid">
  	<div class="span1">
	</div>
  	<div class="span8">
		<table class="table table-condensed table-bordered" id="tasklist">
			<thead>
				<tr>
					<th><spring:message code="task.name" /></th>
					<th><spring:message code="task.project" /></th>
					<th><spring:message code="task.context" /></th>
					<th><spring:message code="task.startDate" /></th>
					<th><spring:message code="task.completedDate" /></th>
					<th><spring:message code="task.isDone" /></th>
					<th><spring:message code="task.status" /></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${tasks}" var="task">
				<tr>
					<td id="task_title_${task.id}"><c:out value="${task.title}" /></td>
					<td><c:out value="${task.project.name}" /></td>
					<td><c:out value="${task.context.name}" /></td>
					<td><fmt:formatDate value="${task.startDate}" type="date" /></td>
					<td><fmt:formatDate value="${task.completedDate}" type="date" /></td>
					<td><c:out value="${task.isDone}" /></td>
					<td><c:out value="${task.status}" /></td>
					<td><spring:url value="/tasks/${task.id}" var="update_form_url">
						<spring:param name="form" />
					</spring:url> 
					<a href="${update_form_url}">${fn:escapeXml(edit_message)}</a>
					 | 
					<a href="#" onclick="javascript:deleteTask(${task.id}, '${delete_form_url}')" >${fn:escapeXml(delete_message)} </a>					
				</td>					
				</tr>
			</c:forEach>
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



