'use strict';
var app = angular.module('MigrationApp');

app
		.controller(
				'SlotController',
				function($scope, $rootScope, $http, $q) {
					$scope.apiUrlBase = "http://localhost:8081/migrationaccelerator/api";
					$scope.onPageLoad = onPageLoad;
					$scope.projectJsonArray;
					$scope.countryJsonArray;
					$scope.siteJsonArray;
					$scope.timzoneJsonArray;
					$scope.projectToBeFetched;
					$rootScope.workbookData = [];
					$scope.addRow = addRow;
					$scope.remove = remove;
					$scope.checkAll = checkAll;
					$rootScope.countryList = [];
					$rootScope.currentProjectName;
					$rootScope.currentProjectID;
					$scope.currentCountry;
					$scope.createSlots = createSlots;
					$scope.getCountryDetails = getCountryDetails;
					$scope.getAllTimezones = getAllTimezones;
					$scope.getSiteDetails = getSiteDetails;
					$scope.getProjectDetails = getProjectDetails;
					$scope.createMessage = '';
					$scope.loadSlots = loadSlots;
					$scope.scheduleModificationPopUp = scheduleModificationPopUp;
					$scope.scheduleDeletePopUp = scheduleDeletePopUp;
					$rootScope.slotsJsonArray;
					$scope.slotDetails = [ {
						'slotName' : "",
						'startTime' : "",
						'endTime' : "",
						'userCount' : ""
					} ];
					$rootScope.currentSlot;
					$scope.formatDate = formatDate;
					$scope.assignCurrentSlotForUpdate = assignCurrentSlotForUpdate;
					$scope.assignCurrentSlotForDelete = assignCurrentSlotForDelete;
					$rootScope.currentSlotID;
					$scope.updateStartDate = true;
					$scope.updateEndDate = true;
					$scope.updateStartTime = true;
					$scope.updateEndTime = true;
					$scope.disableDeleteBtn = true;
					
					function assignCurrentSlotForUpdate(slot){
						$scope.createMessage = '';
						$rootScope.currentSlot = slot;
						
						var userOccupied = slot.userCountOccupied
						if(userOccupied != null && userOccupied > 0){
							$scope.updateStartDate = true;
							$scope.updateEndDate = true;
							$scope.updateStartTime = true;
							$scope.updateEndTime = true;
						}
						else{
							$scope.updateStartDate = false;
							$scope.updateEndDate = false;
							$scope.updateStartTime = false;
							$scope.updateEndTime = false;
						}
					}
					
					function assignCurrentSlotForDelete(slot){
						$scope.createMessage = '';
						$rootScope.currentSlotID = slot.slotId;
						
						var userOccupied = slot.userCountOccupied
						if(userOccupied != null && userOccupied > 0){
							$scope.disableDeleteBtn = true;
							$scope.createMessage = "You can not delete schedule becuase users are already alloted to it..!";
						}else{
							$scope.disableDeleteBtn = false;
							$scope.createMessage = "";
						}
					}
					
					function loadSlots(currentProjectID) {
						var request_url = $scope.apiUrlBase
								+ "/slot/fetchSlotsByProject.view";
						var currentProjectId = $rootScope.currentProjectID;

						$http({
							method : 'GET',
							url : request_url,
							params : {
								"projectId" : currentProjectId,
							},
							contentType : 'application/json'
						})
								.then(
										function(response) {
											var data = response.data.data;
											$rootScope.slotsJsonArray = data;
										},
										function(response) {
											console
													.log("Error while fetching the slot records")
										});
					}

					function createSlots(slotDetails) {
						var request_url = $scope.apiUrlBase
								+ "/slot/createSlots.view";
						var currentProjectId = $rootScope.currentProjectID;
						var currentCountryID = $scope.countriesInList.countryId;
						var currentSiteID = $scope.sitesInList.siteId;

						var slotDetailsDTO = {};
						
						slotDetailsDTO.slotDetails = slotDetails;
						slotDetailsDTO.projectId = currentProjectId;
						slotDetailsDTO.countryId = currentCountryID;
						slotDetailsDTO.siteId = currentSiteID;
						slotDetailsDTO.startDate = $scope.startDate;
						slotDetailsDTO.endDate = $scope.endDate;
						slotDetailsDTO.timezone = $scope.timezoneInList;
						
						$http({
							method : 'POST',
							url : request_url,
							data : slotDetailsDTO,
							contentType : 'application/json'
						})
								.then(
										function(response) {
											if (response.data.message === 'OK') {
												$scope.createMessage = response.data.data;
												loadSlots(currentProjectId);
											} else {
												$scope.createMessage = "Creation of new slots is failed.. !!!";
											}
										},
										function(response) {
											console
													.log("Error while creating slot record");
										});
					}

					function onPageLoad() {
						var request_url = $scope.apiUrlBase
								+ "/project/get-all-projects.view";
						$http({
							method : 'GET',
							url : request_url,
							contentType : 'application/json'
						}).then(function(response) {
							var data = response.data.data;
							$scope.projectJsonArray = data;
						}, function(response) {
							console.log("Error while fetching all project record")
						});
					}

					function addRow(slotDetail) {
						$scope.createMessage = '';
						$scope.slotDetails.push({
							'slotName' : "",
							'startTime' : "",
							'endTime' : "",
							'userCount' : "",
						});
					}

					function remove() {
						$scope.createMessage = '';
						var newDataList = [];
						$scope.selectedAll = false;
						angular.forEach($scope.slotDetails, function(selected) {
							if (!selected.selected) {
								newDataList.push(selected);
							}
						});
						$scope.slotDetails = newDataList;
					}

					function checkAll() {
						$scope.createMessage = '';
						if (!$scope.selectedAll) {
							$scope.selectedAll = true;
						} else {
							$scope.selectedAll = false;
						}
						angular.forEach($scope.slotDetails,
								function(slotDetail) {
									slotDetail.selected = $scope.selectedAll;
								});
					}

					function getCountryDetails() {
						window.location.href = '#!createNewSlots';
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

					function getAllTimezones() {
						var request_url = $scope.apiUrlBase
								+ "/slot/fetchAllTimezones.view";
						$http({
							method : 'GET',
							url : request_url,
							contentType : 'application/json'
						}).then(function(response) {
							var data = response.data.data;
							$rootScope.timzoneJsonArray = data;
						}, function(response) {
							console.log("Error while fetching the timezones")
						});
					}

					function getProjectDetails() {
						window.location.href = '#!slotModification';
						$rootScope.currentProjectID = $scope.projectInList.id;
						$rootScope.currentProjectName = $scope.projectInList.name;

						loadSlots($rootScope.currentProjectID);
					}

					function scheduleModificationPopUp(slot) {
						var request_url = $scope.apiUrlBase
						+ "/slot/updateSlot.view";
						
						$http({
							method : 'POST',
							url : request_url,
							data : slot,
							contentType : 'application/json'
						}).then(function(response) {
							if (response.data.message === 'OK') {
								$scope.createMessage = response.data.data;
								loadSlots($rootScope.currentProjectID);
							} else {
								$scope.createMessage = "Updation of new slots is failed.. !!!";
							}
						}, function(response) {
							console.log("Error while fetching the records")
						});
					}

					function scheduleDeletePopUp(slotId) {
						var request_url = $scope.apiUrlBase
						+ "/slot/deleteSlot.view";
						
						$http({
							method : 'GET',
							url : request_url,
							params : {
								"slotId" : slotId,
							},
							contentType : 'application/json'
						}).then(function(response) {
							if (response.data.message === 'OK') {
								$scope.createMessage = response.data.data;
								loadSlots($rootScope.currentProjectID);
							} else {
								$scope.createMessage = "Deletion of new slots is failed.. !!!";
							}
						}, function(response) {
							console.log("Error while deleting slot records")
						});
					}

					function formatDate(date){
						if(date == null || date == undefined){
							return;
						}
						return date;
					}
					
				});