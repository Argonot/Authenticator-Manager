<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="roles" class="table-responsive">
	<table id="roles-table" class="table table-hover">
		<tr>
			<th>#</th>
			<th>Role</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${roles}" var="role">
			<tr id="role_${ role.id }">
				<td>${ role.id }</td>
				<td>${ role.name }</td>
				<td>
					<a class="btn btn-primary" href="<c:url value="/admin/roles/${role.id}/update"/>" role="button">Modifier</a>
					<a class="btn btn-danger" href="<c:url value="/admin/roles/${role.id}/delete"/>" role="button">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<div id="roles-action-bar" class="text-right">
	<a class="btn btn-primary" href="<c:url value="/admin/roles/create"/>" role="button">Ajouter</a>
</div>