var nameManagerModule = angular.module('nameManagerApp',['toaster','ngAnimate']);

nameManagerModule.controller('nameManagerController',function ($scope,toaster,$http) {
	
	var urlBase="";
	$scope.wynik = "testowa";
	
	$scope.toggle=true;
	$scope.selection = [];
	$http.defaults.headers.post["Content-Type"] = "application/json";
	//findAll
	$scope.loadName = function(){
		return $http.get(urlBase + '/list').success(function(data){
			console.log(data);
			$scope.list = angular.fromJson(data);			
		});
	};
	
	//$scope.loadName();
	//
	$scope.addTask = function addTask() {		
			if($scope.name == undefined || $scope.city == undefined || $scope.post_code == undefined || $scope.street == undefined){
				toaster.pop('error', "Uwaga", "Brak danych!");
			}
			else {
				//
				$http.post(urlBase + '/PostService', { 
					name: $scope.name,
					//nip: $scope.nip,
					//regon: $scope.regon,
					city: $scope.city,
					post_code: $scope.post_code,
					street: $scope.street
				}).
				success(function(data, status,headers){
					toaster.pop('success', "Ok", "Biznes został dodany!");					
				});
			}
			//clear
			$scope.name = "";
	        $scope.nip = "";
	        $scope.regon = "";
	        $scope.city = "";
	        $scope.post_code ="";
	        $scope.street = ""
	};
});
//});
//dyrektywa ograniczajaca input number
nameManagerModule.directive('inputMaxLengthNumber', function() {
	  return {
	    require: 'ngModel',
	    restrict: 'A',
	    link: function (scope, element, attrs, ngModelCtrl) {
	      function fromUser(text) {
	        var maxlength = Number(attrs.maxlength);
	        if (String(text).length > maxlength) {
	          ngModelCtrl.$setViewValue(ngModelCtrl.$modelValue);
	          ngModelCtrl.$render();
	          return ngModelCtrl.$modelValue;
	        }
	        return text;
	      }
	      ngModelCtrl.$parsers.push(fromUser);
	    }
	  };
	})
