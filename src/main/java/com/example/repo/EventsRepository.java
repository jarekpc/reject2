package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.model.Events;

public interface EventsRepository extends JpaRepository<Events, Integer>  {

}
