<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="users" class="table-responsive">
	<table id="users-table" class="table table-hover">
		<tr>
			<th>#</th>
			<th>Nom</th>
			<th>Prénom</th>
			<th>Email</th>
			<th>Essais</th>
			<th>Bloqué</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr id="user_${ user.id }">
				<td>${ user.id }</td>
				<td>${ user.name }</td>
				<td>${ user.surname }</td>
				<td>${ user.email }</td>
				<td>${ user.tries }</td>
				<td>
					<c:choose>
						<c:when test="${ user.locked }">
							Oui
						</c:when>
						<c:otherwise>
        					Non
   						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<a class="btn btn-default" href="<c:url value="/admin/users/${user.id}/update"/>" role="button">Modifier</a>
					<a class="btn btn-success" href="<c:url value="/admin/users/${user.id}/unlock"/>" role="button">Débloquer</a>
					<a class="btn btn-danger" href="<c:url value="/admin/users/${user.id}/delete"/>" role="button">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<div id="users-action-bar" class="text-right">
	<a class="btn btn-primary" href="<c:url value="/admin/users/create"/>" role="button">Nouveau</a>
</div>

<div id="confirm" class="modal hide fade">
  <div class="modal-body">
    Are you sure?
  </div>
  <div class="modal-footer">
    <button type="button" data-dismiss="modal" class="btn btn-primary" id="delete">Delete</button>
    <button type="button" data-dismiss="modal" class="btn">Cancel</button>
  </div>
</div>