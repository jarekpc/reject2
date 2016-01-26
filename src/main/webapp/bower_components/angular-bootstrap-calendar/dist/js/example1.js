angular.module('mwl.calendar.docs', ['mwl.calendar', 'ngAnimate', 'ui.bootstrap']);
angular
  .module('mwl.calendar.docs') //you will need to declare your module with the dependencies ['mwl.calendar', 'ui.bootstrap', 'ngAnimate']
  .controller('KitchenSinkCtrl', function(moment, alert,$http) {

    var vm = this;
	moment.locale("pl");
    //These variables MUST be set as a minimum for the calendar to work
    vm.calendarView = 'day';
    vm.viewDate = new Date();
	
    vm.events = [
      {
        title: 'An event',
        type: 'warning',
        startsAt: moment().startOf('week').subtract(2, 'days').add(8, 'hours').toDate(),
        endsAt: moment().startOf('week').add(1, 'week').add(9, 'hours').toDate(),
        draggable: true,
        resizable: true
      }, {
        title: '<i class="glyphicon glyphicon-asterisk"></i> <span class="text-primary">Another event</span>, with a <i>html</i> title',
        type: 'info',
        startsAt: moment().subtract(1, 'day').toDate(),
        endsAt: moment().add(5, 'days').toDate(),
        draggable: true,
        resizable: true
      }, {
        title: 'This is a really long event title that occurs on every year',
        type: 'important',
        startsAt: moment().startOf('day').add(7, 'hours').toDate(),
        endsAt: moment().startOf('day').add(19, 'hours').toDate(),
        recursOn: 'year',
        draggable: true,
        resizable: true
      }
    ];

    vm.isCellOpen = true;

    vm.eventClicked = function(event) {
      alert.show('Clicked', event);
    };

    vm.eventEdited = function(event) {
      alert.show('Edited', event);
    };

    vm.eventDeleted = function(event) {
      alert.show('Deleted', event);
    };

    vm.eventTimesChanged = function(event) {
      alert.show('Dropped or resized', event);
    };

    vm.toggle = function($event, field, event) {
      $event.preventDefault();
      $event.stopPropagation();
      event[field] = !event[field];
    };
    //save date
    vm.saveEvent = function(event){
    	console.log('zapis');
    	//last index
    	vm.events.forEach(function(entry,i) {
    		if(i==vm.events.length-1){
    			console.log('id ' + i +  'entry ' + entry.title + ' ' + entry.name+''+entry.surname
    						+' '+entry.email+' '+entry.number_phone+ ' Czas rozpoczecia ' + entry.startsAt + ' Czas zakonczenia ' + entry.endsAt);
    			//
    			$http.post('/addEvents',{
 				   title:  entry.title,
 				   startsAt: entry.startsAt,
 			       endsAt: entry.endsAt,
 			       cost: entry.cost,
 			       //
 			      name: entry.name,
	  			  surname: entry.surname,
	  			  email: entry.email,
	  			  number_phone: entry.number_phone
 			   });
    			//
    			/*
    			$http.post('/addClient', {
    				name: entry.name,
    				surname: entry.surname,
    				email: entry.email,
    				number_phone: entry.number_phone
    			});
    			*/
    			//
    		}
    	 });
    	 /*
    	 for(var i = 0; i < vm.events.length; i++){
    		 if(i==vm.events.length-1){
    			 console.log('Licznik ' + i + ' tytul ' + vm.events[i].title);
    		 }
    	 }
    	 */
    };
  });
