"use strict";

/******************************************************************************************

Expenses controller

******************************************************************************************/

var app = angular.module("expenses.controller", []);

app.controller("ctrlExpenses", ["$rootScope", "$scope", "config", "restalchemy", "$http", function ExpensesCtrl($rootScope, $scope, $config, $restalchemy, $http) {
	// Update the headings
	$rootScope.mainTitle = "Expenses";
	$rootScope.mainHeading = "Expenses";

	// Update the tab sections
	$rootScope.selectTabSection("expenses", 0);

	var restExpenses = $restalchemy.init({ root: $config.apiroot }).at("expenses");
	//var restCurrency = $restalchemy.init({ root: $config.apirate }).at("latest?symbols=GBP");

	$scope.dateOptions = {
		changeMonth: true,
		changeYear: true,
		dateFormat: "dd/mm/yy"
	};

	var loadExpenses = function () {
		// Retrieve a list of expenses via REST
		restExpenses.get().then(function (response) {
			$scope.expenses = response._embedded.expenses;
		});
	}

	$scope.calculateVat = function () {
		var amount = 0;
		if ($scope.newExpense.amount.toUpperCase().includes('EUR')) {
			amount = parseFloat($scope.newExpense.amount.substring(0, ($scope.newExpense.amount.length - 3)));
			// Get a real time rate EUR - GBP
			$http.get('https://api.fixer.io/latest?symbols=GBP').then(function (response) {
				amount = amount * response.data.rates.GBP;
				$scope.newExpense.vat = (amount * 20) / 100;
			});

		} else {
			// Calculate VAT
			$scope.newExpense.vat = ($scope.newExpense.amount * 20) / 100;
		}
	};

	$scope.saveExpense = function () {
		if ($scope.expensesform.$valid) {
			var amount = 0;
			if ($scope.newExpense.amount.toUpperCase().includes('EUR')) {
				amount = parseFloat($scope.newExpense.amount.substring(0, ($scope.newExpense.amount.length - 3)));

				// Get a real time rate EUR - GBP
				$http.get('https://api.fixer.io/latest?symbols=GBP').then(function (response) {
					amount = amount * response.data.rates.GBP;
					$scope.newExpense.amount = amount.toString();
					// Post the expense via REST
					restExpenses.post($scope.newExpense).then(function () {
						// Reload new expenses list
						loadExpenses();
					});

				});

			} else {
				// Post the expense via REST
				restExpenses.post($scope.newExpense).then(function () {
					// Reload new expenses list
					loadExpenses();
				});
			}
		}
	};

	$scope.clearExpense = function () {
		$scope.newExpense = {};
	};

	// Initialise scope variables
	loadExpenses();
	$scope.clearExpense();
}]);
