<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib tagdir="/WEB-INF/tags/util" prefix="util" %>

<spring:url var="resources" value="/resources" />
<spring:url var="contextsHomeUrl" value="/contexts" />
<spring:url var="tasksHomeUrl" value="/tasks" />
<spring:url var="projectsHomeUrl" value="/projects" />

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Love My Tasks</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">

  <util:load-scripts />
  <style type="text/css">
  body {
    padding-top: 60px;
    padding-bottom: 40px;
  }
  .sidebar-nav {
    padding: 9px 0;
  }
  </style>
</head>
<body>
  <div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container-fluid">
        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </a>
        <a class="brand" href="#"><spring:message code="app.name"/></a>
        <div class="nav-collapse">
          <ul class="nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
          </ul>
          <p class="navbar-text pull-right">Logged in as <c:out value="${principalname}"/></p>
        </div>
      </div>
    </div>
  </div>
  <c:if test="${feature=='tasks'}">
  <c:set var="tasksclass" value="active"/>
</c:if>
<c:if test="${feature=='contexts'}">
<c:set var="contextsclass" value="active"/>
</c:if>
<c:if test="${feature=='projects'}">
<c:set var="projectsclass" value="active"/>
</c:if>
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span2">
      <div class="well sidebar-nav">
        <ul class="nav nav-list">
          <li class="nav-header"></li>
          <li class="${tasksclass}"><a href="${tasksHomeUrl}"><spring:message code="sidebar.tasks"/></a></li>
          <li class="${contextsclass}"><a href="${contextsHomeUrl}"><spring:message code="sidebar.contexts"/></a></li>
          <li class="${projectsclass}"><a href="${projectsHomeUrl}"><spring:message code="sidebar.projects"/></a></li>
        </ul>
      </div><!--/.well -->
    </div><!--/span-->
    <tiles:insertAttribute name="body"/>        
  </div><!--/row-->
  <hr>

  <footer>
    <p>&copy; <spring:message code="app.copyright"/></p>
  </footer>

</div>
</body>
</html>
