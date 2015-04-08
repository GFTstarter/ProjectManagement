services.factory('AbsenceService', function($resource) {
	return {
		getting: $resource('rest/resource', null, {
		get: {
			method: 'GET',
			isArray: true
		}
	}),
		posting: $resource('rest/absenceByResource') 
	
	};
});

