services.factory('AbsenceService', function($resource) {
	return $resource('rest/absenceByResource/:id', {id: '@id'});
});