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
	<script type="text/javascript" src="<c:url value="/resources/js/confirm-bootstrap.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/authenticator-manager.js"/>"></script>
	<title>Admin :: Utilisateurs</title>
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
				<li role="presentation"><a href="<c:url value="/admin/applications"/>">Applications</a></li>
				<li role="presentation" class="active"><a href="<c:url value="/admin/users"/>">Utilisateurs</a></li>
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
					<c:url var="postUrl"  value="/admin/users/${user.id}/${action}" />
				</c:when>
				<c:otherwise>
    				<c:url var="postUrl"  value="/admin/users/create" />
				</c:otherwise>
			</c:choose>
			<form:form action="${postUrl}" method="post" commandName="userVO">
			  <div class="form-group">
			    <label for="name">Nom</label>
			    <form:input type="text" class="form-control" id="name" placeholder="Nom" path="name" value="${user.name}"/>
			    <form:errors path="name" cssClass="alert alert-danger"/>
			  </div>
			  <div class="form-group">
			    <label for="surname">Surname</label>
			    <form:input type="text" class="form-control" id="surname" placeholder="Surname" path="surname" value="${user.surname}"/>
			    <form:errors path="surname" cssClass="alert alert-danger"/>
			  </div>
			  <div class="form-group">
			    <label for="email">Email</label>
			    <form:input type="email" class="form-control" id="email" placeholder="Email" path="email" value="${user.email}"/>
			    <form:errors path="email" cssClass="alert alert-danger"/>
			  </div>
			  <div class="form-group">
			    <label for="password">Password</label>
			    <form:input type="password" class="form-control" id="password" placeholder="Password" path="password"/>
			    <form:errors path="password" cssClass="alert alert-danger"/>
			  </div>
			  <a class="btn btn-default" href="<c:url value="/admin/users"/>" role="button">Retour</a>
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