'use strict';
var app = angular.module('MigrationApp');

app.controller('ProjectController', function($scope, $http,$rootScope,$q) {
	$scope.apiUrlBase = "http://localhost:8081/migrationaccelerator/api";
	$scope.createProject = createProject;
	$scope.onPageLoad = onPageLoad;
	$scope.projectJsonArray;
	$scope.projectToBeUpdated;
	$scope.updateProject = updateProject;
	$scope.setValueForUpdateProject = setValueForUpdateProject;
	$scope.clearExisitngFields = clearExisitngFields;
	$scope.validateMasterSheet = validateMasterSheet;
	$scope.uploadMasterSheet = uploadMasterSheet;
	$scope.masterSheet;
	$scope.createMessage='';
	$scope.updateMessage ='';
	$rootScope.uploadButton='false';
	$scope.overwriteButton='false';
	$scope.overwriteAndAppendButton='false';
	$scope.appendButton='false';
	$scope.updateProjectBtn = 'true';
	$scope.uploadMasterSheetBtn = 'true';
	$scope.validateProjectSelection = validateProjectSelection;
	$scope.overwriteRecords = overwriteRecords;
	$scope.appendRecords = appendRecords;
	$scope.appendAndOverwriteRecords = appendAndOverwriteRecords;
	
	var fd='';
	
	function validateProjectSelection(){
		if (angular.isDefined($scope.projectInList)) {
			$scope.updateProjectBtn = false;
			$scope.uploadMasterSheetBtn = false;
	    }else{
	    	$scope.updateProjectBtn = true;
			$scope.uploadMasterSheetBtn = true;
	    }
	}
	
	function createProject(project) {
		var request_url = $scope.apiUrlBase + "/project/project-creation.view";
		$http({
			method : 'POST',
			url : request_url,
			data : project,
			contentType : 'application/json'
		}).then(function(response) {
			if (response.data.message === 'OK') {
				$scope.createMessage = "New Project Created Successfully !!!";
				$scope.projectJsonArray = response.data.data;
			} else {
				$scope.createMessage = "Sorry cannot create this project as it is already Exists in the database !!!";
				}
		}, function(response) {
			console.log("Error while fetching the record")
		});
	}
	;

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
			console.log("Error while fetching all project record")
		});
	}

	function updateProject(projectToBeUpdated) {
		var request_url = $scope.apiUrlBase + "/project/update-project.view";
		$http({
			method : 'POST',
			url : request_url,
			data : projectToBeUpdated,
			contentType : 'application/json'
		}).then(function(response) {
			var data = response.data.data;
			$scope.projectJsonArray = data;
			if (response.data.message === "OK") {
				$scope.updateMessage= "Project Details Updated Successfully !!!";
			} else {
				// Error message
							}
				}, function(response) {
			console.log("Error while updating project record")
		});
	}

	function setValueForUpdateProject() {
		$scope.projectToBeUpdated = $scope.projectInList;
	}

	function clearExisitngFields() {
		$scope.uploadSheetMessage='';
		angular.element(document.querySelector('#masterSheetFile')).val(null);
	}
	
	function validateMasterSheet(files) {
		$scope.fileNameMasterSheet = document.getElementById('masterSheetFile').files[0];
		var mastersheetName = $scope.fileNameMasterSheet.name;
		var masterSheetNameSplitted = mastersheetName.split('_');
		//var masterSheetCodeAndNameUI = masterSheetNameSplitted[0];
		var masterSheetCodeUI = masterSheetNameSplitted[0];
		//var projectCodeAndName = "PROJ" + $scope.projectInList.id + $scope.projectInList.name;
		var projectCode = "PROJ" + $scope.projectInList.id;
		//var isValid = isValidSheetForClient(masterSheetCodeAndNameUI,projectCodeAndName);
        var isValid = isValidSheetForClient(masterSheetCodeUI,
		projectCode);
		
        $rootScope.uploadButton = isValid;
    	$scope.overwriteButton=true;
		$scope.appendButton=true;
		$scope.overwriteAndAppendButton=true;
        function appendFileData() {
            /*perform some asynchronous operation,resolve or reject the promise when appropriate.*/	
            return $q(function(resolve, reject) {
            	var projectCode=$scope.projectInList.id;
            	var projectName= $scope.projectInList.name;
             if (files.length > 0) {
              setTimeout(function() {
               fd = new FormData();
               // Take the first selected file
               fd.append("file", files[0]);
               //Add selected projects code and name to blob data.
               fd.append('project', new Blob([JSON.stringify({
                   "id": projectCode,
                   "name": projectName
               })], {
                   type: "application/json"
               }));
               resolve();
              }, 10);
             } else {
              reject();
             }
            });
           }

           var promise = appendFileData();
           promise.then(function() {     
           });
	}
	
	function isValidSheetForClient(masterSheetCodeUI , projectCode) {
		if (masterSheetCodeUI === projectCode) {
			$scope.uploadSheetMessage="";
			return false;
		} else {
			$scope.uploadSheetMessage="File name violation! Make sure the file name you are uploading has the valid project code!!! (eg : PROJID_Anything)";
			$scope.overwriteButton=true;
			$scope.appendButton=true;
			$scope.overwriteAndAppendButton=true;
			return true;
			
		}
	}

	function uploadMasterSheet() {
		var request_url = $scope.apiUrlBase
				+ "/project/uploading-MasterSheet.view";
		var masterFile = document.getElementById('masterSheetFile').files[0];
		$http({
			method : 'POST',
			url : request_url,
			data : fd,
	        headers : {
	            'Content-Type' : undefined
	           },
	           transformRequest : angular.identity

		}).then(function(response) {
			var data = response.data.data;
			if (data.eventStatus===true && data.recordsStatus==="NEW" && data.existingRecordsCount===0) {
				$scope.uploadSheetMessage=data.eventMessage; 
				$rootScope.uploadButton=true;
			}else if (data.eventStatus===false && data.recordsStatus==="INVALID" ) {
				$scope.uploadSheetMessage=data.eventMessage; 
				$rootScope.uploadButton=true;
			}
			//Overwrite Case
			else if (data.eventStatus===true && data.recordsStatus==="EXIST" && data.existingRecordsCount>0 && data.newRecordsCount === 0) {
				$scope.uploadSheetMessage=data.eventMessage; 
				$rootScope.uploadButton=true;
				$scope.overwriteButton=false;
				$scope.appendButton=true;
			}
			//Append Case
			else if (data.eventStatus===true && data.recordsStatus==="NEW" && data.existingRecordsCount===0 && data.newRecordsCount>0) {
				$scope.uploadSheetMessage=data.eventMessage; 
				$rootScope.uploadButton=true;
				$scope.overwriteButton=true;
				$scope.appendButton=false;
			}
			//Overwrite and Append Case
			else if(data.eventStatus===true && data.recordsStatus==="COMBINATION" && data.existingRecordsCount>0 && data.newRecordsCount>0) {
				$scope.uploadSheetMessage=data.eventMessage; 
				$rootScope.uploadButton=true;
				$scope.overwriteAndAppendButton=false;
				$scope.appendButton=false;
				$scope.overwriteButton=false;
			}
		}, function(response) {
			console.log("Error while uploading the mastersheet the record")
		});
	}
	
	function appendRecords() {
		var request_url = $scope.apiUrlBase
		+ "/project/appendingRecords-MasterSheet.view";
		var masterFile = document.getElementById('masterSheetFile').files[0];
		$http({
			method : 'POST',
			url : request_url,
			data : fd,
	        headers : {
	            'Content-Type' : undefined
	           },
	           transformRequest : angular.identity

		}).then(function(response) {
			var data = response.data.data;
			$scope.uploadSheetMessage=data.eventMessage;
		}, function(response) {
			console.log("Error while appending the mastersheet the record")
		});

	}
	
	function overwriteRecords() {
		var request_url = $scope.apiUrlBase
		+ "/project/overwrittingRecords-MasterSheet.view";
		var masterFile = document.getElementById('masterSheetFile').files[0];
		$http({
			method : 'POST',
			url : request_url,
			data : fd,
	        headers : {
	            'Content-Type' : undefined
	           },
	           transformRequest : angular.identity

		}).then(function(response) {
			var data = response.data.data;
			$scope.uploadSheetMessage=data.eventMessage; 
		}, function(response) {
			console.log("Error while ovderwrittng the mastersheet the record")
		});

	}
	function appendAndOverwriteRecords() {
		var request_url = $scope.apiUrlBase
		+ "/project/appendAndOverwrittingRecords-MasterSheet.view";
		var masterFile = document.getElementById('masterSheetFile').files[0];
		$http({
			method : 'POST',
			url : request_url,
			data : fd,
	        headers : {
	            'Content-Type' : undefined
	           },
	           transformRequest : angular.identity

		}).then(function(response) {
			var data = response.data.data;
			$scope.uploadSheetMessage=data.eventMessage; 
		}, function(response) {
			console.log("Error while ovderwrittng and appending the mastersheet the record")
		});

	}
});
