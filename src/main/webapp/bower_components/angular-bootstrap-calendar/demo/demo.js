'use strict';

angular
  .module('demo', ['mwl.calendar', 'ui.bootstrap', 'ngTouch', 'ngAnimate'])
  .controller('MainCtrl', function ($uibModal, moment,$http) {

	
    var vm = this;
    var tempDate;
    tempDate = moment.tz("Europe/Warsaw");
    $http.defaults.headers.post["Content-Type"] = "application/json";
    vm.events = [];
    vm.firstName; 
    vm.calendarView = 'day';
    vm.calendarDay = new Date();
    $http.get('/calosc').success(function(data){
    		console.log(data);
    		data.forEach(function(entry) {
    			vm.events.push({title: entry.title ,startsAt: entry.startsat, endsAt: entry.endsat });
    		});    	    	 
    });
    
    
    //console.log(vm.events.length);
    
    vm.isCellOpen = true;

   
    function showModal(action, event) {
      $uibModal.open({
        templateUrl: 'modalContent.html',
        controller: function() {
          var vm = this;
          vm.action = action;
          vm.event = event;
        },
        controllerAs: 'vm'
      });
    }
    
   vm.saveEvent = function(event){
	   //save database	   
	   console.log('Wpisales: Imie '+ document.getElementById("exampleInputImie").value + document.getElementById("exampleInputNazwisko").value);
	   vm.events.forEach(function(entry) {		   
		   //console.log('Save date: ' + entry.title + '--' + entry.startsAt+ '--'+ entry.endsAt + ' Typ obiektu ' +  typeof(entry.startsAt));
		   //console.log('Entry ' + entry.event.firstName);
if(typeof entry.startsAt != "number" && typeof entry.endsAt != "number" && typeof entry.startsAt != "undefined" &&  typeof entry.endsAt != "undefined" ){
	console.log('Zapisujesz zdarzenie ' + entry.title + ' start' + entry.startsAt + ' Koniec ' + entry.endsAt);
			   $http.post('/addEvents',{
				   title:  entry.title,
				   startsAt: entry.startsAt,
			       endsAt: entry.endsAt
			       //
			   });
		   }
	   });
	 
   }

    vm.eventClicked = function(event) {
      showModal('Clicked', event);
     
    };

    vm.eventEdited = function(event) {
      showModal('Edited', event);
    };

    vm.eventDeleted = function(event) {
      showModal('Deleted', event);
    };

    vm.eventTimesChanged = function(event) {
      showModal('Dropped or resized', event);
    };

    vm.toggle = function($event, field, event) {
      $event.preventDefault();
      $event.stopPropagation();
      event[field] = !event[field];
      console.log('toggle');
    }

  //});mwl.calendar
   
});