services.factory('ProjectService', function($resource) {
	return $resource('rest/project/:id', {id: '@id'});
});