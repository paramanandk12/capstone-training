'use strict';
var index = 0;
var warName = window.location.pathname.substr(0, window.location.pathname
		.lastIndexOf('/'));

var app = angular.module("MigrationApp", [ "ngRoute" ]);

app.config(function($routeProvider) {
	data: 'application/json';
	$routeProvider.when("/", {
		templateUrl : "resources/components/landing/templates/Landing.html"
	}).when("/adminLogin", {
		templateUrl : "resources/components/admin/templates/AdminLogin.html",
		controller : "AdminController"
	}).when("/adminHome", {
		templateUrl : "resources/components/admin/templates/AdminHome.html",
		controller : "ProjectController"
	}).when("/prjMgmt", {
		templateUrl : "resources/components/project/templates/ProjectManagement.html",
		controller : "ProjectController"
	}).when("/slotMgmt", {
		templateUrl : "resources/components/slot/templates/SlotManagement.html",
		controller : "SlotController"
	}).when("/slotCreationProjectSelect", {
		templateUrl : "resources/components/slot/templates/SlotCreationProjectSelect.html",
		controller : "SlotController"
	}).when("/createNewSlots", {
		templateUrl : "resources/components/slot/templates/CreateNewSlotsProjectSelect.html",
		controller : "SlotController" 
	}).when("/modifySlotProjectSelect", {
		templateUrl : "resources/components/slot/templates/ModifySlotProjectSelect.html",
		controller : "SlotController"
	}).when("/slotModification", {
		templateUrl : "resources/components/slot/templates/SlotModification.html",
		controller : "SlotController"
	}).when("/userLogin", {
		templateUrl : "resources/components/user/templates/UserLogin.html",
		controller : "UserController"
	}).when("/userHome", {
		templateUrl : "resources/components/user/templates/UserHome.html",
		controller : "UserController"
	}).when("/userSlotSelection", {
		templateUrl : "resources/components/user/templates/UserSlotSelection.html",
		controller : "UserController"
	}).when("/dashboard", {
		templateUrl : "resources/components/dashboard/templates/Dashboard.html",
		controller : "DashboardController"
	}).when("/dashboardMasterData", {
		templateUrl : "resources/components/dashboard/templates/DashboardMasterdata.html",
		controller : "DashboardController"
	}).when("/reportHome", {
		templateUrl : "resources/components/dashboard/templates/ReportHome.html",
		controller : "DashboardController"
	}).when("/scheduleReport", {
		templateUrl : "resources/components/dashboard/templates/ScheduleReport.html",
		controller : "DashboardController"
	}).when("/userReport", {
		templateUrl : "resources/components/dashboard/templates/UserReport.html",
		controller : "DashboardController"
	})
	
	
	
});