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
	<title>Admin :: Authorizations</title>
</head>
<body>
	<div id="admin-header" class="row">
		<div id="admin-header-left-side" class="col-md-2"></div>
		<div id="admin-header-container" class="col-md-8">
			<jsp:include page="includes/Header.jsp" />
		</div>
		<div id="admin-header-right-side" class="col-md-2"></div>
	</div>
	<div id="admin-menu" class="row">
		<div id="admin-menu-left-side" class="col-md-2"></div>
		<div id="admin-menu-container" class="col-md-8">
			<ul id="menu-admin" class="nav nav-tabs">
				<li role="presentation" class="active"><a href="<c:url value="/admin"/>">Autorisations</a></li>
				<li role="presentation"><a href="<c:url value="/admin/applications"/>">Applications</a></li>
				<li role="presentation"><a href="<c:url value="/admin/users"/>">Utilisateurs</a></li>
				<li role="presentation"><a href="<c:url value="/admin/roles"/>">Roles</a></li>
				<li role="presentation"><a href="<c:url value="/admin/logout"/>">Déconnexion</a></li>
			</ul>
		</div>
		<div id="admin-menu-right-side" class="col-md-2"></div>
	</div>
	<div id="admin-page" class="row">
		<div id="admin-page-left-side" class="col-md-2"></div>
		<div id="admin-page-container" class="col-md-8">
			<c:choose>
				<c:when test="${ action == 'update' }">
					<c:url var="postUrl"  value="/admin/authorizations/${authorization.id}/${action}" />
				</c:when>
				<c:otherwise>
    				<c:url var="postUrl"  value="/admin/authorizations/create" />
				</c:otherwise>
			</c:choose>
			<form:form action="${postUrl}" method="post" commandName="authorizationVO">
			  <div class="form-group">
			    <label for="user">User</label>
			    <form:select class="form-control" id="user" path="user.id" value="${authorization.user.id}">
					<c:forEach items="${users}" var="user">
						<c:choose>
	                        <c:when test="${ user.id == authorization.user.id }">
	                           <c:set var="selected" scope="request" value="selected"/>
	                        </c:when>
	                        <c:otherwise>
	                           <c:set var="selected" scope="request" value=""/>
	                        </c:otherwise>
                       </c:choose>
						<form:option value="${user.id}" selected="${ selected }">${user.email}</form:option>
					</c:forEach>
				</form:select>
			    <form:errors path="user.id" cssClass="alert alert-danger"/>
			  </div>
			  <div class="form-group">
			    <label for="application">Application</label>
			    <form:select class="form-control" id="application" path="app.id" value="${authorization.app.id}">
					<c:forEach items="${applications}" var="app">
						<c:choose>
	                        <c:when test="${ app.id == authorization.app.id }">
	                           <c:set var="selected" scope="request" value="selected"/>
	                        </c:when>
	                        <c:otherwise>
	                           <c:set var="selected" scope="request" value=""/>
	                        </c:otherwise>
                       </c:choose>
						<form:option value="${app.id}" selected="${ selected }">${app.name}</form:option>
					</c:forEach>
				</form:select>
			    <form:errors path="app.id" cssClass="alert alert-danger"/>
			  </div>
			  <div class="form-group">
			    <label for="role">Role</label>
			    <form:select class="form-control" id="role" path="role.id" value="${authorization.role.id}">
			    	<c:forEach items="${roles}" var="role">
			    	<c:choose>
	                        <c:when test="${ role.id == authorization.role.id }">
	                           <c:set var="selected" scope="request" value="selected"/>
	                        </c:when>
	                        <c:otherwise>
	                           <c:set var="selected" scope="request" value=""/>
	                        </c:otherwise>
                       </c:choose>
						<form:option value="${role.id}" selected="${ selected }">${role.name}</form:option>
					</c:forEach>
			    </form:select>
			    <form:errors path="role.id" cssClass="alert alert-danger"/>
			  </div>
			  <a class="btn btn-default" href="<c:url value="/admin/"/>" role="button">Retour</a>
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
		<div id="admin-page-right-side" class="col-md-2"></div>
	</div>
</body>
</html>