services.factory('HomeService', function($resource) {	
	return $resource('rest/project/byUser/:id', {id: '@id'}, {
		get: {
			method: 'GET',
			isArray: true
		}
	});
});