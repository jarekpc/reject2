package com.example.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.model.Client;

@RepositoryRestResource
public interface ClientRepository  extends CrudRepository<Client,Long>{

}
