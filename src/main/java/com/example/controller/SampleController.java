package com.example.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.repo.EventsRepository;
import com.example.repo.Name_BusinessRepository;
import com.example.model.Client;
import com.example.model.Events;
import com.example.model.Name_Business;

@Controller
public class SampleController {

	@Autowired
	Name_BusinessRepository name_BusinessRepository;
	
	@Autowired
	EventsRepository eventsRepository;
	
	
	@RequestMapping(value = "/PostService", method = RequestMethod.POST)
	public @ResponseBody void PostService(@RequestBody Map map) {
		System.out.println(map.get("name") + " "+map.get("city"));
		System.out.println("Size Map " +map.size());
		if(map.size() == 4){
			Name_Business biznes = new Name_Business();
			biznes.setName((String)map.get("name"));
			biznes.setCity((String)map.get("city"));
			biznes.setPost_code((Integer)map.get("post_code"));
			biznes.setStreet((String)map.get("street"));			
			name_BusinessRepository.save(biznes);
		}
	}
	/*
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<Name_Business> listAll(){
		Iterable<Name_Business> list_biznes =  name_BusinessRepository.findAll();
		List<Name_Business> lista = new ArrayList<Name_Business>();
		for(Name_Business n: list_biznes){
			lista.add(n);
		}
		return lista;
	}
	*/
	
	@RequestMapping(value = "/addEvents", method = RequestMethod.POST)
	public @ResponseBody void addEvents(@RequestBody Map map) throws ParseException{
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Calendar cal_start = Calendar.getInstance();
		Calendar cal_stop = Calendar.getInstance();
		cal_start.setTime(formatter.parse(map.get("startsAt").toString()));
		cal_start.add(Calendar.HOUR, 1);
		//
		cal_stop.setTime(formatter.parse(map.get("endsAt").toString()));
		cal_stop.add(Calendar.HOUR, 1);
		System.out.println("Start time " + formatter.parse(formatter.format(cal_start.getTime())));
		System.out.println("End time " +   formatter.parse(formatter.format(cal_stop.getTime())));
		System.out.println(map.get("title") + "--" +map.get("startsAt") +"--"+map.get("endsAt")+"--"+map.get("cost"));
		/*
		Events events = new Events();
		events.setTitle(map.get("title").toString());
		events.setStartsat(formatter.parse(formatter.format(cal_start.getTime())));
		events.setEndsat(formatter.parse(formatter.format(cal_stop.getTime())));
		if(map.get("cost") == null){
			events.setCost(0);
		}
		events.setCost(new Integer(map.get("cost").toString()));
		*/
		//System.out.println(map.get("name") + " "+map.get("surname") + " "+map.get("email")+ " "+map.get("number_phone"));
		
		Client client = new Client();
		client.setName(map.get("name").toString());
		client.setSurname(map.get("surname").toString());
		client.setEmail(map.get("email").toString());
		client.setPhone(map.get("number_phone").toString());
		//events.setClient(client);
		
		Events events = new Events(map.get("title").toString(), formatter.parse(formatter.format(cal_start.getTime())), formatter.parse(formatter.format(cal_stop.getTime())), new Integer(map.get("cost").toString()), client);
		eventsRepository.save(events);
	}
	
	@RequestMapping(value = "/addClient", method = RequestMethod.POST)
	public @ResponseBody void addClient(@RequestBody Map map) {
		//System.out.println(map.get("name") + " "+map.get("surname") + " "+map.get("email")+ " "+map.get("number_phone"));
	}
}
