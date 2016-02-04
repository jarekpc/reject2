package com.example.controller;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
	
	//@Autowired
	//ClientRepository clientRepository;
	
	
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
		
		Client client = new Client(map.get("name").toString(),map.get("surname").toString(),map.get("email").toString(),map.get("phone").toString());
		List<Events> events = new ArrayList<>();
		events.add(new Events(map.get("title").toString(), formatter.parse(formatter.format(cal_start.getTime())), formatter.parse(formatter.format(cal_stop.getTime())), new Integer(map.get("cost").toString()),client));
		eventsRepository.save(events);
		
	    //map.get("name").toString(),map.get("surname").toString(),map.get("email").toString(),map.get("phone").toString()
		//System.out.println(map.get("surname").toString()+"--" + map.get("name").toString()+"--"+map.get("phone").toString());
		
		
	}
	
	@RequestMapping(value = "/addClient", method = RequestMethod.POST)
	public @ResponseBody void addClient(@RequestBody Map map) {
		//System.out.println(map.get("name") + " "+map.get("surname") + " "+map.get("email")+ " "+map.get("number_phone"));
	}
	
	@RequestMapping(value = "/calosc", method = RequestMethod.GET)
	public @ResponseBody List<Events> listCalosc(){
		List<Events> wynik = new ArrayList<Events>();
		for(Events e: eventsRepository.findAll()){
			wynik.add(e);
		}
		System.out.println("Liczba elem " + wynik.size());
		return wynik;
	}
	@RequestMapping(value="/caloscAll")
	public @ResponseBody List<Events> listAll(){
		List<Events> w = new ArrayList<Events>();
		for (Events e : eventsRepository.findAll()) {
            //System.out.println(e.toString());
			w.add(e);
        }
		return w;
	}
	@RequestMapping(value="/CheckVisit")
	public void CheckVisit(@RequestBody Map map) throws ParseException{
		System.out.println("Wybrales " + map.get("on_day"));
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal_start = Calendar.getInstance();
		cal_start.setTime(formatter.parse(map.get("on_day").toString()));
		cal_start.add(Calendar.HOUR, 1);
		//Date now = new Date(map.get("on_day"));
		//find events on day
		//Wybrales 2016-02-04T14:16:50.367Z
		/*
		for(Events e: eventsRepository.findByStartsat(formatter.parse(formatter.format(cal_start.getTime())))){
			System.out.println(e.getTitle());
		}
		*/
		for(Events e: eventsRepository.findByStartsat(formatter.parse(formatter.format(cal_start.getTime())))){
			System.out.println(e.getTitle());
		}
		
	}
}
