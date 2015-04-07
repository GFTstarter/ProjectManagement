services.factory('ExpensesService', function($resource) {
	return $resource('rest/expenses',{
	get: {
		method: 'GET',
		isArray: true
		}
	});
});