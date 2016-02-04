package com.example.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.example.model.Events;

@RepositoryRestResource
public interface EventsRepository extends JpaRepository<Events, Integer>  {

	//List<Events> findByStartsat(Date start);
	
	List<Events> findByStartsat(@Param("Startsat") @DateTimeFormat(pattern="yyyy-MM-dd") Date date);
}
