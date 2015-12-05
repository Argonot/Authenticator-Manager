$(document).ready(function(){
	$('.btn-danger').confirmModal({
	    confirmTitle     : 'Confirmation',
	    confirmMessage   : 'Êtes-vous sûr de vouloir supprimer ?',
	    confirmOk        : 'Supprimer',
	    confirmCancel    : 'Retour',
	    confirmStyle     : 'primary'
	});
});