<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
	<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.4.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/confirm-bootstrap.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/authenticator-manager.js"/>"></script>
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
			<jsp:include page="panels/ApplicationsPanel.jsp" />
		</div>
		<div id="admin-page-right-side" class="col-md-3"></div>
	</div>
</body>
</html>