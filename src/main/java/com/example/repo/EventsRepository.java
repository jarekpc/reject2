package com.example.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.model.Events;

@RepositoryRestResource
public interface EventsRepository extends CrudRepository<Events,Long> {

}
