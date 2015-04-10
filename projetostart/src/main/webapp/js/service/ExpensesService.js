services.factory('ExpensesService', function($resource) {
	return {
		getting: $resource('rest/resource', null, {
		get: {
			method: 'GET',
			isArray: true
		}
	}),
		posting: $resource('rest/expenses') 
	
	};
});





/*services.factory('ExpensesService', function($resource) {
	return $resource('rest/expenses',{
	get: {
		method: 'GET',
		isArray: true
		}
	});
});*/