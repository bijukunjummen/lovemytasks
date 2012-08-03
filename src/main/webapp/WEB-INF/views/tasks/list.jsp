<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>


<spring:url var="ajaxUrl" value="/tasks/listLoad"/>
<script type="text/javascript">
$(document).ready(function(){
	var oTable = $('#tasklist').dataTable({
 		
 		bServerSide:true,
 		"bJQueryUI": true,
 		sAjaxSource:"${ajaxUrl}",
 			aoColumns: [
	            { mDataProp: "title" },
	            { mDataProp: "project", sDefaultContent:'', fnRender: function(o, val){if (val) return val.name} },
	            { mDataProp: "context", sDefaultContent:'', fnRender: function(o, val){if (val) return val.name} },
	            { mDataProp: "startDate" },
	            { mDataProp: "completedDate" },
	            { mDataProp: "isDone" },
	            { mDataProp: "status" },
	            { mDataProp: null },
	            { mDataProp: null }
           	]
	});

});
</script>

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
					<th></th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
	
		<spring:url var="createUrl" value="/tasks">
			<spring:param name="form"></spring:param>
		</spring:url>
		<spring:message code="task.new_task" var="add_message" htmlEscape="false" />
		<div>
			<a href="${createUrl}">${fn:escapeXml(add_message)}</a>
		</div>
	</div>
</div>



