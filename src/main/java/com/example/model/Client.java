package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.repository.CrudRepository;

@Entity
@Table(name="Client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="SURNAME")
	private String surname;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phone")
	private String phone;
	//@Id
	/*
	@OneToOne
	@JoinColumn(name = "ID_EVENT")
	Events events;
	*/
	//@Column(name="ID_EVENT")
	//@Id
	/*
	@OneToOne
	@JoinColumn(name = "ID_EVENT")
	private Long id_event;
	
	
	public Long getId_event() {
		return id_event;
	}
	
	public void setId_event(Long id_event) {
		this.id_event = id_event;
	}
	*/
	//@Id
	//@Column(name="ID_EVENT")
	/*
	private Long id_event;
	
	public Long getId_event() {
		return id_event;
	}
	
	public void setId_event(Long id_event) {
		this.id_event = id_event;
	}
	*/
	
	Events events;
	//
	//@Id
	@OneToOne
	@JoinColumn(name = "ID_EVENT")
	public Events getEvents() {
		return events;
	}

	public void setEvents(Events events) {
		this.events = events;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Client() {
		// TODO Auto-generated constructor stub
	}
}
