<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util"%>


<spring:url var="ajaxUrl" value="/tasks/listLoad"/>
<spring:url var="cellSaveUrl" value="/tasks/cellSave"/>
<script type="text/javascript">
$.extend( $.fn.dataTableExt.oStdClasses, {
    "sWrapper": "dataTables_wrapper form-inline"
} );
$(document).ready(function(){
	
	var store = {
			columns:[
			         {type:"text",name:"title"},
			         {type:"text",name:"project"},
			         {type:"text",name:"context"},
			         {type:"date",name:"startDate"},
			         {type:"date",name:"completedDate"},
			         {type:"checkbox",name:"isDone"},
			         {type:"text",name:"status"}
			]
	};
	
	var oTable = $('#tasklist').dataTable({
 		bServerSide:true,
 		"sPaginationType": "full_numbers",
 		sAjaxSource:"${ajaxUrl}",
 		aoColumns: [
	            { mDataProp: "title" },
	            { mDataProp: "project", sDefaultContent:'', fnRender: function(o, val){if (val) return val.name} },
	            { mDataProp: "context", sDefaultContent:'', fnRender: function(o, val){if (val) return val.name} },
	            { mDataProp: "startDate" },
	            { mDataProp: "completedDate" },
	            { mDataProp: "isDone", fnRender:function(o, val){
	            	if (val===true) 
	            		return "<input type='checkbox' value='true'/>" ;
	            	else 
	            		return "<input type='checkbox' value='false'/>" ;
	            }},
	            { mDataProp: "status" }
        ],
        "fnDrawCallback": function () {
        	var alltrs = $('#tasklist').dataTable().fnGetNodes();
        	$(alltrs).each(function(index, tr){
        		$(tr).children().each(function(index, td){
        			if (index<2){
            			$(td).editable( '${cellSaveUrl}', {
                        	indicator : 'Saving...',
                            tooltip   : 'Click to edit...',
                            submit    : 'OK',
                            "callback": function( sValue, y ) {
                                oTable.fnDraw();
                            },
                            "submitdata": function ( value, settings ) {
                            	console.log("hello..");
                    			return {
                    				"row_id": this.parentNode.getAttribute('id'),
                    				"column": oTable.fnGetPosition( this )[2]
                    			};
                    	}});
        			}

        		});
        		
        	});
//            $('#tasklist tbody td').editable( '${cellSaveUrl}', {
//            	indicator : 'Saving...',
//                tooltip   : 'Click to edit...',
//                submit    : 'OK',
//                "callback": function( sValue, y ) {
//                    oTable.fnDraw();
//                },
//                "submitdata": function ( value, settings ) {
//                	console.log("hello..");
//        			return {
//        				"row_id": this.parentNode.getAttribute('id'),
//        				"column": oTable.fnGetPosition( this )[2]
//        			};
//        		}
//            } );
        }
	});
	
});
</script>

<div class="row-fluid">
  	<div class="span1">
	</div>
  	<div class="span9">
		<table class="table table-striped table-bordered" id="tasklist">
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

			</tbody>
		</table>
		<br/>
		<spring:url var="createUrl" value="/tasks">
			<spring:param name="form"></spring:param>
		</spring:url>
		<spring:message code="task.new_task" var="add_message" htmlEscape="false" />
		<div>
			<a href="${createUrl}">${fn:escapeXml(add_message)}</a>
		</div>
	</div>
</div>



