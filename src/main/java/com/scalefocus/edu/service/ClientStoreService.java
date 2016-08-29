package com.scalefocus.edu.service;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.scalefocus.edu.api.model.AdressModelAPI;
import com.scalefocus.edu.api.model.ClientModelAPI;
import com.scalefocus.edu.db.dao.AdressDAO;
import com.scalefocus.edu.db.dao.ClientDAO;
import com.scalefocus.edu.db.model.Adress;
import com.scalefocus.edu.db.model.Client;
import exceptions.EmailAlreadyExistEx;
import exceptions.NotExistingClientEx;
import exceptions.NotExistingEmailEx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ClientStoreService {

	public String sayHi() {
		return "Hi!";
	}

	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private ClientDAO cDAO;

	@Autowired
	private AdressDAO aDAO;

	Logger logger = LoggerFactory.getLogger(ClientStoreService.class);

	// map API Client model to DB Client model
	public Client mapAPIToClient(ClientModelAPI clientAPI) {
		Client client;
		try {
			client = this.mapper.map(clientAPI, Client.class);
		} catch (MappingException ex) {
			logger.warn("Unable to map!");
			throw new MappingException("Unable to map!");
		}
		return client;
	}

	// create Client
	public Client addClient(ClientModelAPI clientModelAPI) {
		Client client = this.cDAO.findByEmail(clientModelAPI.getEmail());
		client = this.mapAPIToClient(clientModelAPI);
		try {
			client = this.cDAO.save(client);
		} catch (Exception ex) {
			logger.warn("Client with email: " + clientModelAPI.getEmail() + " already exist");
			throw new EmailAlreadyExistEx("Client with email: " + clientModelAPI.getEmail() + " already exist");
		}
		Client c1 = cDAO.findByEmail(clientModelAPI.getEmail());
		System.out.println(c1.getAdresses());		
		if (clientModelAPI.getAdresses() != null) {
			for (AdressModelAPI iterable_element : clientModelAPI.getAdresses()) {
				Adress a = this.mapper.map(iterable_element, Adress.class);
				c1.addAdress(a);
				a.setClient(c1);
				this.aDAO.save(a);
			}
		}
		// System.out.println("Client created with id: " + client.getId());
		logger.info("Client created with id: " + client.getId());
		return c1;
	}

	// update a client
	public Client updateClient(Long id, ClientModelAPI newClienInfo) {
		Client client = this.cDAO.findById(id);
		if (!this.checkIfExist(client)) {
			logger.warn("Client with Id: " + id + " does not exist");
			throw new NotExistingClientEx("Client with Id: " + id + " does not exist");
		}
		if (this.cDAO.findByEmail(newClienInfo.getEmail()) != null) {
			throw new EmailAlreadyExistEx("This email is already in use");
		}
		// add check if properties are empty
		if (newClienInfo.getEmail() != null) {
			client.setEmail(newClienInfo.getEmail());
		}
		if (newClienInfo.getFirstName() != null) {
			client.setFirstName(newClienInfo.getFirstName());
		}
		if (newClienInfo.getLastName() != null) {
			client.setLastName(newClienInfo.getLastName());
		}		

		if (newClienInfo.getAdresses() != null) {
			for (AdressModelAPI iterable_element : newClienInfo.getAdresses()) {
				Adress a = this.mapper.map(iterable_element, Adress.class);
				client.getAdresses().add(a);
				a.setClient(client);
				this.aDAO.save(a);
			}
		}	
		client = this.cDAO.save(client);
		logger.info("Updated client with id: " + id);
		// System.out.println("Updated client with id: " + id);
		return client;
	}

	// delete Client
	public Client deleteClientById(Long id) {
		Client client = this.cDAO.findById(id);
		if (!checkIfExist(client)) {
			logger.warn("No Client with id: " + id);
			// System.out.println("No Client with id: " + id);
			throw new NotExistingClientEx("Client with Id: " + id + " does not exist");
		}
		this.cDAO.delete(client);
		logger.info("Client has been deleted id: " + id);
		// System.out.println("Client has been deleted id: " + id);
		return client;
	}

	// read by Id
	public Client readClientById(Long id) {
		Client client = this.cDAO.findById(id);
		if (!checkIfExist(client)) {
			logger.warn("No client with id: " + id);
			// System.out.println("No client with id: " + id);
			throw new NotExistingClientEx("No client with id: " + id);
		}
		logger.info("Client reviewed with id: " + id);
		// System.out.println("Client reviewed with id: " + id);
		return client;
	}

	// read by Email
	public Client readClientByEmail(String email) {
		Client client = this.cDAO.findByEmail(email);
		if (!checkIfExist(client)) {
			logger.warn("No client with email: " + email);
			// System.out.println("No client with email: " + email);
			throw new NotExistingEmailEx("No client with email: " + email);
		}
		logger.info("Client reviewed with email: " + email);
		// System.out.println("Client reviewed with email: " + email);
		return client;
	}

	// show all Clients
	public List<Client> retreiveAllClients() {
		List<Client> clientList = this.cDAO.findAll();
		if (clientList.isEmpty()) {
			logger.warn("Client list is empty");
			// System.out.println("Client list is empty");
			throw new NotExistingClientEx("Client list is empty");
		}
		logger.info("All clients retreived");
		// System.out.println("All clients retrieved");
		return clientList;
	}
	
	// check client existence
	public boolean checkIfExist(Client client) {
		if (client == null) {
			return false;
		}
		return true;
	}
}
