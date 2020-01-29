'use strict';
var app = angular.module('MigrationApp');

app.controller('UserController', function($scope, $http, $rootScope) {
	$scope.apiUrlBase = "http://localhost:8081/migrationaccelerator/api";
	$scope.userLogin = userLogin;
	$rootScope.userDetails;
	$scope.userDetailsUI = $rootScope.userDetails;
	$scope.submitMessage = '';
	$scope.createMessage='';
	$scope.projectJsonArray;
	$scope.userBookedScheduleJsonArray;
	$scope.submitUserDetails = submitUserDetails;
	$scope.availableScheduleJsonArray;
	$scope.bookSchedule = bookSchedule;
	$scope.getAvailableScheduleList = getAvailableScheduleList;
	$scope.loadBookedSlotsByUser = loadBookedSlotsByUser;
	$scope.formatDate = formatDate;
	$scope.assignCurrentUserSlotSelectionForUpdate = assignCurrentUserSlotSelectionForUpdate;
	$scope.assignCurrentUserSlotSelectionForDelete = assignCurrentUserSlotSelectionForDelete;
	$rootScope.currentBookedSlot;
	$rootScope.currentBookedSlotID;
	$scope.bookedScheduleDeletePopUp = bookedScheduleDeletePopUp;
	$scope.bookedScheduleModificationPopUp = bookedScheduleModificationPopUp;
	
	function bookedScheduleModificationPopUp(schedule) {
		
		if(schedule.bookingCounter === 3){
			$scope.createMessage = "You have reached maximun attempts to reschedule. Please delete/cancel this schedule and create new one.";
			return;
		}
		
		var request_url = $scope.apiUrlBase
		+ "/user/updateUserSlotBooking.view";
		//Time Parsed to Date Object
		var startDateTemp = new Date("Thu Jan 01 1970 " + schedule.slotDTO.startTime);
		schedule.slotDTO.startTime = startDateTemp;
		
		var endDateTemp = new Date("Thu Jan 01 1970 " + schedule.slotDTO.endTime);
		schedule.slotDTO.endTime = endDateTemp;
		
		$http({
			method : 'POST',
			url : request_url,
			data : schedule,
			contentType : 'application/json'
		}).then(function(response) {
			if (response.data.message === 'OK') {
				$scope.createMessage = response.data.data;
				loadBookedSlotsByUser();
			} else {
				$scope.createMessage = "Updation of new slots is failed.. !!!";
			}
		}, function(response) {
			console.log("Error while fetching the records")
		});
	}

	function bookedScheduleDeletePopUp(slotId) {
		var request_url = $scope.apiUrlBase
		+ "/user/deleteBookedSlot.view";
		
		$http({
			method : 'GET',
			url : request_url,
			params : {
				"userSlotSelectionID" : slotId,
			},
			contentType : 'application/json'
		}).then(function(response) {
			if (response.data.message === 'OK') {
				$scope.createMessage = response.data.data;
				loadBookedSlotsByUser();
			} else {
				$scope.createMessage = "Deletion of new slots is failed.. !!!";
			}
		}, function(response) {
			console.log("Error while deleting slot records")
		});
	}

	//Disable start time/end time if user is occupied seats, then admin can only update slot name name and capacity
	function assignCurrentUserSlotSelectionForUpdate(schedule){
		$scope.createMessage = '';
		$rootScope.currentBookedSlot = schedule;
	}
	
	function assignCurrentUserSlotSelectionForDelete(scheduleID){
		$scope.createMessage = '';
		$rootScope.currentBookedSlotID = scheduleID;
	}
	
	function loadBookedSlotsByUser() {
		var request_url = $scope.apiUrlBase
				+ "/user/loadBokedSlotsByUser.view";

		$http({
			method : 'POST',
			url : request_url,
			data : $rootScope.userDetails,
			contentType : 'application/json'
		}).then(function(response) {
				var data = response.data.data;
				$scope.userBookedScheduleJsonArray = data;
				
				if((data != null || data != undefined) && Object.keys(data).length > 0){
					$scope.bookSlotButton=true;
					$scope.dateSelection=true;
					$scope.slotSelection=true;
				} else{
					$scope.createMessage = '';
					$scope.bookSlotButton=false;
					$scope.dateSelection=false;
					$scope.slotSelection=false;
				}
			},
			function(response) {
				console.log("Error while fetching the slot records")
			});
	}
	
	function getAvailableScheduleList(selectedDate) {
		var request_url = $scope.apiUrlBase + "/user/fetchSlotsByDate.view";
		var startDateObj = new Date(selectedDate);

		$http({
			method : 'POST',
			url : request_url,
			data : startDateObj,
			contentType : 'application/json'
		}).then(function(response) {
			if (response.data.message === 'OK') {
				var data = response.data.data;
				$scope.availableScheduleJsonArray = data;
				
				if($scope.availableScheduleJsonArray.length <= 0){
					$scope.createMessage = "No Schedules created by Admin for Selected Date.. !!!";
				} else{
					$scope.createMessage = '';
				}
				
			} else {
				console.log("Error while submiting user data")
			}
		});
	}

	function userLogin(user) {
		var request_url = $scope.apiUrlBase + "/user/auth-user.view";
		
		$http({
			method : 'POST',
			url : request_url,
			data : user,
			contentType : 'application/json'
		}).then(function successCallback(response) {
			var data = response.data.data;
			if (data.userStatus === true) {
				window.location.href = '#!userHome';
				$rootScope.userDetails = data;

			} else {
				$scope.errorMessage = $scope.errorMessage = true;
			}
		}, function(response) {
			$scope.errorMessage = $scope.errorMessage = true;
			console.log("Error while fetching the record")
		});
	}
	

	function bookSchedule() {
		var seletedSlot = $scope.availableSchedulesInList;
		
		//Validate if Schedules are fully occupied or not
		if(seletedSlot.userCountOccupied === seletedSlot.userCountCapacity){
			$scope.createMessage = 'Schedule is fully occupied, you can not book this schedule. Please try with other schedules avaialble.';
			return;
		}else{
			$scope.createMessage = '';
		}
		
		var slotFreezDays = seletedSlot.scheduleFreezDays;
		var one_day=1000*60*60*24;
		
		var slotEndDate = new Date(seletedSlot.endDate);
		slotEndDate = slotEndDate.getTime();
		
		var todayDate = new Date();
		todayDate.setHours(0,0,0,0);
		todayDate = todayDate.getTime();
		
		var dayInMillis = (slotEndDate - todayDate);
		var diffNumOfDays = Math.round(dayInMillis/one_day);
		
		if(diffNumOfDays > slotFreezDays){
			$scope.createMessage = '';
			var userSlotSelectionDTO = {};

			userSlotSelectionDTO.migrationDate = $scope.selectedDate;
			userSlotSelectionDTO.slotDTO = seletedSlot;
			
			//Time Parsed to Date Object
			var startDateTemp = new Date("Thu Jan 01 1970 " + userSlotSelectionDTO.slotDTO.startTime);
			userSlotSelectionDTO.slotDTO.startTime = startDateTemp;
			
			var endDateTemp = new Date("Thu Jan 01 1970 " + userSlotSelectionDTO.slotDTO.endTime);
			userSlotSelectionDTO.slotDTO.endTime = endDateTemp;
			
			var request_url = $scope.apiUrlBase + "/user/book-slot.view";
			$http({
				method : 'POST',
				url : request_url,
				data : userSlotSelectionDTO,
				contentType : 'application/json'
			}).then(function(response) {
				if (response.data.message === 'OK') {
					$scope.createMessage = "Your slot has been booked Successfull !!!";
					loadBookedSlotsByUser();
				} else {
					$scope.createMessage = "Your slot booking is Failed !!!";
					console.log("Error while submiting user data");
				}
			});
		}else{
			$scope.createMessage = 'You can not book this schedule. It is freezed now. Please try with other schedules avaialble..!';
		}
	}

	function submitUserDetails(userDetailsUI) {
		var request_url = $scope.apiUrlBase + "/user/submit-userdata.view";
		$http({
			method : 'POST',
			url : request_url,
			data : userDetailsUI,
			contentType : 'application/json'
		}).then(function(response) {
			if (response.data.message === 'OK') {
				$scope.createMessage = "Your slot has been booked Successfull !!!";
			} else {
				$scope.createMessage = "Your slot booking is Failed !!!";
				console.log("Error while submiting user data");
			}
		});
	}
	
	function formatDate(date){
		if(date == null || date == undefined){
			return;
		}
		return date;
	}

});