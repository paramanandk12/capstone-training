'use strict';
var app = angular.module('MigrationApp');

app.controller('AdminController', function($scope, $http) {
	$scope.apiUrlBase = "http://localhost:8081/migrationaccelerator/api";
	$scope.adminLogin = adminLogin;

	function adminLogin(admin) {
		$scope.errorMessage = false;
		var request_url = $scope.apiUrlBase + "/admin/auth-admin.view";
		$http({
			method : 'POST',
			url : request_url,
			data : admin,
			contentType : 'application/json'
		}).then(function(response) {
			if (response.data === true) {
				window.location.href = '#!adminHome';
			} else {
			$scope.errorMessage = true;
			}
		}, function(response) {
			console.log("Error while fetching the record")
		});
	}
	;
});