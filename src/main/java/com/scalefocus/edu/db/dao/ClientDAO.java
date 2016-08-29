package com.scalefocus.edu.db.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.scalefocus.edu.db.model.Client;


@Service
public interface ClientDAO extends CrudRepository<Client, Long> {

	Client findById(Long id);

	Client findByEmail(String email);

	List<Client> findAll();
}
