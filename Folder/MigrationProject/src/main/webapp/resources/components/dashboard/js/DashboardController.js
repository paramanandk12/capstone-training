'use strict';
var app = angular.module('MigrationApp');

app.controller('DashboardController', function($scope, $rootScope, $http, $q) {
	$scope.apiUrlBase = "http://localhost:8081/migrationaccelerator/api";
	$scope.onPageLoad = onPageLoad;
	$scope.projectJsonArray;
	$scope.projectToBeFetched;
	$scope.getAllDetails = getAllDetails;
	$rootScope.workbookData = [];
	$rootScope.currentProjectID;
	$rootScope.currentProjectName;
	$scope.countryJsonArray;
	$scope.countrySitesJsonArray;
	$scope.getCountryDetails=getCountryDetails;
    $scope.getSiteDetails=getSiteDetails;
    $scope.getSitesReportsBasedOnCountry=getSitesReportsBasedOnCountry;
    $scope.getScheduleReport=getScheduleReport;
    $scope.downloadDetailedReportBtn = true;
//    $scope.hideExportDataTable = false;
    $scope.downloadReportData;
    $scope.downloadReport = downloadReport;
    
    function downloadReport() {
    	var blob = new Blob([document.getElementById('exportable').innerHTML], {
            type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8"
        });
        saveAs(blob, "Report.xls");
    }
    
	function onPageLoad() {
		var request_url = $scope.apiUrlBase + "/project/get-all-projects.view";
		$http({
			method : 'GET',
			url : request_url,
			contentType : 'application/json'
		}).then(function(response) {
			var data = response.data.data;
			$scope.projectJsonArray = data;
		}, function(response) {
			console.log("Error while fetching the record")
		});
	}
	
	
	function getCountryDetails() {
		$rootScope.currentProjectID = $scope.projectInList.id;
		$rootScope.currentProjectName = $scope.projectInList.name;
		var request_url = $scope.apiUrlBase
				+ "/slot/fetchCountriesByProject.view";
		var projectId = $rootScope.currentProjectID;

		$http({
			method : 'GET',
			url : request_url,
			params : {
				"projectId" : projectId
			},
			contentType : 'application/json'
		}).then(function(response) {
			var data = response.data.data;
			$rootScope.countryJsonArray = data;
		}, function(response) {
			console.log("Error while fetching the country records")
		});
	}

	function getSiteDetails() {
		var request_url = $scope.apiUrlBase
				+ "/slot/fetchSitesByProjectCountry.view";
		var projectId = $rootScope.currentProjectID;
		var currentCountryID = $scope.countriesInList.countryId;

		$http({
			method : 'GET',
			url : request_url,
			params : {
				"projectId" : projectId,
				"countryId" : currentCountryID
			},
			contentType : 'application/json'
		}).then(function(response) {
			var data = response.data.data;
			$rootScope.siteJsonArray = data;
		}, function(response) {
			console.log("Error while fetching the site records")
		});
	}
	

		function getSitesReportsBasedOnCountry() {
			$rootScope.currentProjectID = $scope.projectInList.id;
			$rootScope.currentProjectName = $scope.projectInList.name;
			var request_url = $scope.apiUrlBase
					+ "/slot/fetchCountrySitesByProject.view";
			var projectId = $rootScope.currentProjectID;

			$http({
				method : 'GET',
				url : request_url,
				params : {
					"projectId" : projectId
				},
				contentType : 'application/json'
			}).then(function(response) {
				var dataArr = response.data.data;
				$scope.countrySitesJsonArray = dataArr;
				
				var data = new google.visualization.DataTable();
				data.addColumn('string', 'Country');
				data.addColumn('number', 'Available Sites Per Country');
				
				for( let prop in dataArr ){
					var countryName=dataArr[prop].countryName;
					var sitesCount=dataArr[prop].availableSitesCount;
					
					data.addRow([countryName, sitesCount]); 
				}
			//https://stackoverflow.com/questions/12415689/how-to-dynamically-add-rows-columns-to-a-google-column-chart
				
			var options = {
				'title' : 'Available Sites Per Country',
				'width' : 750,
				'height': 550
			};

			var chart = new google.visualization.PieChart(document
					.getElementById('piechart'));
			chart.draw(data, options);
			}, function(response) {
				console.log("Error while fetching the country records")
			});
	}

		function getScheduleReport() {
			
			var request_url = $scope.apiUrlBase
			+ "/dashboard/fetchScheduleReportForUser.view";
			var projectId = $rootScope.currentProjectID;
			var currentCountryID = $scope.countriesInList.countryId;
			var siteID = $scope.sitesInList.siteId;
			$http({
				method : 'POST',
				url : request_url,
				params : {
					"projectId" : projectId,
					"countryId" : currentCountryID,
					"siteId" : siteID
				},
				contentType : 'application/json'
			}).then(function(response) {
				var slotsArrForCurentSite = response.data.data;
				$scope.downloadReportData = response.data.data;

				var data = new google.visualization.DataTable();
				data.addColumn('string', 'Slot Name');
				data.addColumn('number', 'User Capacity Count');
				data.addColumn('number', 'User Occupied Count');
				
				for( let prop in slotsArrForCurentSite ){
					var slotName=slotsArrForCurentSite[prop].slotDTO.slotName;
					var userMaxCount=slotsArrForCurentSite[prop].slotDTO.userCountCapacity;
					var userOccupiedCount=slotsArrForCurentSite[prop].slotDTO.userCountOccupied;

					data.addRow([slotName, userMaxCount, userOccupiedCount]); 
				}
				
				var options = {
					'title' : 'User Count Per Schedule',
					'width' : 750,
					'height': 550
				};
		
				var chart = new google.visualization.ColumnChart(document.getElementById('schedulereport'));
				chart.draw(data, options);
				$scope.downloadDetailedReportBtn = false;
			}, function(response) {
				console.log("Error while fetching the getScheduleReport ")
			});
	}
	
	function getAllDetails() {
		window.location.href = '#!dashboardMasterData';
		var request_url = $scope.apiUrlBase
				+ "/dashboard/fetchAllRecords-project.view";
		var projectId = $scope.project.projectInList.id;
		$http({
			method : 'GET',
			url : request_url,
			params : {
				"projectId" : projectId
			},
			contentType : 'application/json'
		}).then(function(response) {
			var data = response.data.data;
			$rootScope.workbookData = data;
		}, function(response) {
			console.log("Error while fetching the records")
		});
	}
});