<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="authorizations" class="table-responsive">
	<table id="authorizations-table" class="table table-hover">
		<tr>
			<th>#</th>
			<th>Email</th>
			<th>Application</th>
			<th>Role</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${authorizations}" var="auth">
			<tr id="authorization_${ auth.id }">
				<td>${ auth.id }</td>
				<td>${ auth.user.email }</td>
				<td>${ auth.app.name }</td>
				<td>${ auth.role.name }</td>
				<td>
					<a class="btn btn-default" href="<c:url value="/admin/authorizations/${auth.id}/update"/>" role="button">Modifier</a>
					<a class="btn btn-danger" href="<c:url value="/admin/authorizations/${auth.id}/delete"/>" role="button">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<div id="authorizations-action-bar" class="text-right">
	<a class="btn btn-primary" href="<c:url value="/admin/authorizations/create"/>" role="button">Ajouter</a>
</div>