<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#projectlist').dataTable({
 		"bPaginate": false,
        "bLengthChange": false,
        "bFilter": false,
        "bSort": false,
        "bInfo": false,
        "bAutoWidth": false		
	});
});
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row-fluid">
	<div class="span1">
	</div>
	<div class="span8">
		<table class="table table-condensed table-bordered" id="projectlist">
			<thead>
				<tr>
					<th><spring:message code="project.name" /></th>
					<th><spring:message code="project.startDate"/></th>
					<th><spring:message code="project.completedDate"/></th>
					<th><spring:message code="project.isDone"/></th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${projects}" var="project">
				<tr>
					<td><c:out value="${project.name}" /></td>
					<td><fmt:formatDate value="${project.startDate.time}" type="date" /></td>
					<td><fmt:formatDate value="${project.completedDate.time}" type="date" /></td>
					<td><c:out value="${project.isDone}" /></td>
					<td><spring:url value="/projects/${project.id}" var="update_form_url">
						<spring:param name="form" />
					</spring:url> 
					<spring:message code="project.edit_project" var="edit_message" htmlEscape="false" /> 
					<a href="${update_form_url}">${fn:escapeXml(edit_message)}</a>
				</td>
				<td>
					<spring:url value="/projects/${project.id}" var="delete_form_url" /> 
					<form:form action="${delete_form_url}" method="DELETE" id="${project.id}_DELETE_FORM">
					<input name="page" type="hidden" value="${param.page}" />
					<input name="size" type="hidden" value="${param.size}" />
					<spring:message code="project.delete_project" var="delete_message" htmlEscape="false" />
					<input type="submit" value="${fn:escapeXml(delete_message)}" class="btn-small" />
				</form:form>						
			</td>
		</tr>
		
	</c:forEach>
	<tr>
	</tbody>
</table>
<util:pagination maxPages="${maxPages}" page="${param.page}" size="${param.size}"></util:pagination>

<spring:url var="createUrl" value="/projects">
<spring:param name="form"></spring:param>
</spring:url>
<spring:message code="project.new_project" var="add_message" htmlEscape="false" />
<div>
	<a href="${createUrl}">${fn:escapeXml(add_message)}</a>
</div>
</div>
</div>



