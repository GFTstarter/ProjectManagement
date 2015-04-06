services.factory('ExpensesService', function($resource) {
	return $resource('rest/expenses/:id', {id: '@id'},{
	get: {
		method: 'GET',
		isArray: true
		}
	});
});