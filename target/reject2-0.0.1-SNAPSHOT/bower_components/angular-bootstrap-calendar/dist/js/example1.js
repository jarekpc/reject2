angular.module('mwl.calendar.docs', ['mwl.calendar', 'ngAnimate', 'ui.bootstrap']);
angular
  .module('mwl.calendar.docs') //you will need to declare your module with the dependencies ['mwl.calendar', 'ui.bootstrap', 'ngAnimate']
  //
  
  .config(function(calendarConfig) {

    console.log(calendarConfig); //view all available config

    //calendarConfig.templates.calendarMonthView = 'path/to/custom/template.html'; //change the month view template to a custom template
    
    calendarConfig.dateFormatter = 'moment'; //use either moment or angular to format dates on the calendar. Default angular. Setting this will override any date formats you have already set.
    
    calendarConfig.allDateFormats.moment.date.hour = 'HH:mm'; //this will configure times on the day view to display in 24 hour format rather than the default of 12 hour

    calendarConfig.allDateFormats.moment.title.day = 'dddd D MMMM'; //this will configure the day view title to be shorter
    
    calendarConfig.allDateFormats.moment.title.search = 'D MMMM'; //

    calendarConfig.i18nStrings.eventsLabel = 'Zdarzenia'; //This will set the events label on the day view

    calendarConfig.i18nStrings.timeLabel = 'Czas';//
    
    calendarConfig.displayAllMonthEvents = true; //This will display all events on a month view even if they're not in the current month. Default false.

    calendarConfig.displayEventEndTimes = true; //This will display event end times on the month and year views. Default false.

    calendarConfig.showTimesOnWeekView = true; //Make the week view more like the day view, with the caveat that event end times are ignored.

  })
  
  //
  .controller('KitchenSinkCtrl', function(moment, alert,$http) {

    var vm = this;
    //These variables MUST be set as a minimum for the calendar to work
    vm.calendarView = 'day';
    //
    vm.viewDate = new Date();
    
    vm.n = vm.viewDate.getTime();
	
    vm.events = [
      /*
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
      */
    ];
    //
    vm.greaterThan = function(prop, val){
        return function(item){
          return item[prop] < val;
        }
    };
    //fill array events
    
    //$http.get('/calosc').success(function(data){
    $http.get('/caloscAll').success(function(data){
		console.log(data);
		data.forEach(function(entry) {
			//vm.events.push({title: entry.title ,startsAt: entry.startsat, endsAt: entry.endsat });
			vm.events.push({title: entry.title ,startsAt: entry.startsat, endsAt: entry.endsat, cost: entry.cost, name: entry.client.name, surname: entry.client.surname, phone: entry.client.phone, email: entry.client.email});
		});    	    	 
    });    
    
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
	  			  phone: entry.phone
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
