<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>
<spring:message code="context.delete_context" var="delete_message" htmlEscape="false" />
<spring:url value="/contexts/${context.id}" var="delete_form_url" />

<script type="text/javascript">

function deleteContext(deleteUrl){
	var form = $('<form/>',{
		action: deleteUrl,
		method: "POST"
	});
	
	form.append($('<input/>',{
		type: 'hidden',
		name: '_method',
		value: 'DELETE'
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
<div class="row-fluid">
	<div class="span1">
	</div>
	<div class="span8">
		<table class="table table-condensed table-bordered" id="contextlist">
			<thead>
				<tr>
					<th><spring:message code="context.name" /></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${contexts}" var="context">
				<tr>
				<td><c:out value="${context.name}" /></td>
				<td><spring:url value="/contexts/${context.id}" var="update_form_url">
						<spring:param name="form" />
					</spring:url> 
					<spring:message code="context.edit_context" var="edit_message" htmlEscape="false" />
					<spring:url value="/contexts/${context.id}" var="delete_form_url" /> 
					 
					<a href="${update_form_url}">${fn:escapeXml(edit_message)}</a> | <a href="#" onclick="javascript:deleteContext('${delete_form_url}')">${delete_message}</a>
				</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<util:pagination maxPages="${maxPages}" page="${param.page}" size="${param.size}"></util:pagination>
		<spring:url var="createUrl" value="/contexts">
		<spring:param name="form"></spring:param>
		</spring:url>
		<spring:message code="context.new_context" var="add_message" htmlEscape="false" />
		<div>
			<a href="${createUrl}">${fn:escapeXml(add_message)}</a>
		</div>
	</div>
</div>



