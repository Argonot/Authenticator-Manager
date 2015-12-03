<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div id="applications" class="table-responsive">
	<table id="applications-table" class="table table-hover">
		<tr>
			<th>#</th>
			<th>Application</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${applications}" var="app">
			<tr id="application_${ app.id }">
				<td>${ app.id }</td>
				<td>${ app.name }</td>
				<td>
					<a class="btn btn-primary" href="#" role="button">Modifier</a>
					<a class="btn btn-danger" href="#" role="button">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
<div id="applications-action-bar" class="text-right">
	<a class="btn btn-primary" href="#" role="button">Ajouter</a>
</div>