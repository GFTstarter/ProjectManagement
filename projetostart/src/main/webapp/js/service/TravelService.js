services.factory('TravelService', function($resource) {
	return $resource('rest/travel/:id', {id: '@id'});
});