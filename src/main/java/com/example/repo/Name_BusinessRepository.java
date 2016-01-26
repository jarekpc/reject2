package com.example.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.model.Name_Business;

@RepositoryRestResource
public interface Name_BusinessRepository extends CrudRepository<Name_Business, Long> {

}
