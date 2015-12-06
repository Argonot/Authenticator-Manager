<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="springForm"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
	<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<title>Admin :: Applications</title>
</head>
<body>
	<div id="admin-header" class="row">
		<div id="admin-header-left-side" class="col-md-3"></div>
		<div id="admin-header-container" class="col-md-6">
			<jsp:include page="includes/Header.jsp" />
		</div>
		<div id="admin-header-right-side" class="col-md-3"></div>
	</div>
	<div id="admin-menu" class="row">
		<div id="admin-menu-left-side" class="col-md-3"></div>
		<div id="admin-menu-container" class="col-md-6">
			<ul id="menu-admin" class="nav nav-tabs">
				<li role="presentation"><a href="<c:url value="/admin"/>">Autorisations</a></li>
				<li role="presentation" class="active"><a href="<c:url value="/admin/applications"/>">Applications</a></li>
				<li role="presentation"><a href="<c:url value="/admin/users"/>">Utilisateurs</a></li>
				<li role="presentation"><a href="<c:url value="/admin/roles"/>">Roles</a></li>
				<li role="presentation"><a href="<c:url value="/admin/logout"/>">Déconnexion</a></li>
			</ul>
		</div>
		<div id="admin-menu-right-side" class="col-md-3"></div>
	</div>
	<div id="admin-page" class="row">
		<div id="admin-page-left-side" class="col-md-3"></div>
		<div id="admin-page-container" class="col-md-6">
			<c:choose>
				<c:when test="${ action == 'update' }">
					<c:url var="postUrl"  value="/admin/applications/${application.id}/${action}" />
					<c:set var="readonly" value="true"/>
				</c:when>
				<c:otherwise>
    				<c:url var="postUrl"  value="/admin/applications/create" />
    				<c:set var="readonly" value="false"/>
				</c:otherwise>
			</c:choose>
			<form:form action="${postUrl}" method="post" commandName="applicationVO">
			  <div class="form-group">
			    <label for="auid">AUID</label>
			    <form:input type="text" class="form-control" id="auid" placeholder="Application Unique ID" path="id" value="${application.id}" readonly="${readonly}"/>
			    <form:errors path="id" cssClass="alert alert-danger"/>
			  </div>
			  <div class="form-group">
			    <label for="name">Nom</label>
			    <form:input type="text" class="form-control" id="name" placeholder="Nom" path="name" value="${application.name}"/>
			    <form:errors path="name" cssClass="alert alert-danger"/>
			  </div>
			  <a class="btn btn-default" href="<c:url value="/admin/applications"/>" role="button">Retour</a>
			  <button type="submit" class="btn btn-primary">
			  	<c:choose>
					<c:when test="${ action == 'update' }">
						Modifier
					</c:when>
					<c:otherwise>
       					Créer
  					</c:otherwise>
				</c:choose>
			  </button>
			</form:form>
		</div>
		<div id="admin-page-right-side" class="col-md-3"></div>
	</div>
</body>
</html>