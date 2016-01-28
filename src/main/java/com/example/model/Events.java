package com.example.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Events implements Serializable {

	
	@Column(name="ID_EVENT")
	private Long id_event;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="STARTSAT")
	private Date startsat;
	
	@Column(name="ENDSAT")
	private Date endsat;
	
	@Column(name="COST")
	private Integer cost;
	
	private Client client;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "events")
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId_event() {
		return id_event;
	}

	public void setId_event(Long id_event) {
		this.id_event = id_event;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartsat() {
		return startsat;
	}

	public void setStartsat(Date startsat) {
		this.startsat = startsat;
	}

	public Date getEndsat() {
		return endsat;
	}

	public void setEndsat(Date endsat) {
		this.endsat = endsat;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}
	
	public Events() {
		// TODO Auto-generated constructor stub
	}

	public Events(String title, Date startsat, Date endsat, Integer cost, Client client) {
		//super();
		this.title = title;
		this.startsat = startsat;
		this.endsat = endsat;
		this.cost = cost;
		this.client = client;
		
	}

	public Events(String title, Date startsat, Date endsat, Integer cost) {
		//super();
		this.title = title;
		this.startsat = startsat;
		this.endsat = endsat;
		this.cost = cost;
	}
	
	
	
}
