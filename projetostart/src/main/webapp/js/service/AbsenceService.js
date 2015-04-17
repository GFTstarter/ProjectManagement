services.factory('AbsenceService', function($resource) {
	return {
		getting: $resource('rest/resource', null, {
			get: {
				method: 'GET',
				isArray: true
			}
		}),
		
		posting: $resource('rest/absenceByResource') ,
		
		gettingMotive: $resource('rest/absence', null, {
			get: {
				method: 'GET',
				isArray: true
			}
		})
	
	};
});

