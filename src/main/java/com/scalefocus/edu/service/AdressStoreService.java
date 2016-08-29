package com.scalefocus.edu.service;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scalefocus.edu.api.model.AdressModelAPI;
import com.scalefocus.edu.db.dao.AdressDAO;
import com.scalefocus.edu.db.dao.ClientDAO;
import com.scalefocus.edu.db.model.Adress;
import com.scalefocus.edu.db.model.Client;

import exceptions.NotExistingAdressEx;
import exceptions.NotExistingClientEx;

@Service
public class AdressStoreService {

	@Autowired
	private AdressDAO aDAO;

	@Autowired
	private ClientDAO cDAO;

	@Autowired
	private DozerBeanMapper mapper;


	// map API address object to db address object
	public Adress mapAPIToAdress(AdressModelAPI adressAPI) {
		Adress adress = new Adress();
		try {
			adress = this.mapper.map(adressAPI, adress.getClass());
		} catch (MappingException ex) {
			throw new MappingException("Unable to map!");
		}
		return adress;
	}

	// add address to client
	public Adress addAdress(Long clientId, AdressModelAPI adressModelAPI) {
		Adress adress = this.mapAPIToAdress(adressModelAPI);
		Client client = this.cDAO.findById(clientId);
		if (!checkClientExistence(client)) {
			throw new NotExistingClientEx("Client not exist with id: " + clientId);
		}
		client.addAdress(adress);
		adress.setClient(client);
		adress = this.aDAO.save(adress);
		return adress;
	}

	// get all client addresses
	public List<Adress> getAllAdresses(Long clientId) {
		Client client = this.cDAO.findById(clientId);
		if (!checkClientExistence(client)) {
			throw new NotExistingClientEx("Client not exist with id: " + clientId);
		}
		List<Adress> allAdresses = client.getAdresses();
		if (allAdresses.isEmpty()) {
			throw new NotExistingAdressEx("Client with id: " + clientId + " has no addresses");
		}
		return allAdresses;
	}

	// get adress by clientId and adressId
	public Adress getAdressById(Long clientId, Long adressId) {
		Client client = this.cDAO.findById(clientId);
		if (!checkClientExistence(client)) {
			throw new NotExistingClientEx("Client not exist with id: " + clientId);
		}
		Adress adress = this.aDAO.findById(adressId);
		if (!checkAdressExistence(adress)) {
			throw new NotExistingAdressEx("Address not exist with id: " + adressId);
		}

		return adress;
	}

	// remove client adress
	public Client removeClientAdress(Long clientId, Long adressId) {

		Adress adress = this.aDAO.findById(adressId);
		if (!checkAdressExistence(adress)) {
			throw new NotExistingAdressEx("Address not exist with id: " + adressId);
		}

		Client client = this.cDAO.findById(clientId);
		if (!checkClientExistence(client)) {
			throw new NotExistingClientEx("Client not exist id: " + clientId);
		}
		this.aDAO.delete(adress.getId());
		return client;
	}

	// check if address exist
	public boolean checkAdressExistence(Adress adress) {
		if (adress == null) {
			return false;
		}
		return true;
	}

	// check if client exist
	public boolean checkClientExistence(Client client) {
		if (client == null) {
			return false;
		}
		return true;
	}

	// modify adress
	public Adress updateAdress(Long clientid, long adressId, Adress newAdressInfo) {
		Client client = this.cDAO.findById(clientid);
		Adress adress = this.aDAO.findById(adressId);
		client.addAdress(adress);
		adress = this.aDAO.save(adress);
		return adress;
	}
}
