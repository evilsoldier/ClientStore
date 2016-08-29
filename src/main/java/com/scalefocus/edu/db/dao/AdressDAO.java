package com.scalefocus.edu.db.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scalefocus.edu.db.model.Adress;

@Repository
public interface AdressDAO extends CrudRepository<Adress, Long> {
	
	public Adress findById(Long id);
}
