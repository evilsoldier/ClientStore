package com.scalefocus.edu.api.ws;

import java.util.List;
import java.util.Set;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.scalefocus.edu.api.ClientStoreAPI_WS;
import com.scalefocus.edu.api.model.ClientModelAPI;
import com.scalefocus.edu.db.model.Client;
import com.scalefocus.edu.service.ClientStoreService;

@Controller
@WebService(targetNamespace = "http://localhost:8080/CientStore/ws/clients?wsdl")
public class ClientStoreAPIWSImpl implements ClientStoreAPI_WS {

	@Autowired
	private ClientStoreService clientStoreService;

	private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
	private Validator validator = validatorFactory.getValidator();
	private static Set<ConstraintViolation<ClientModelAPI>> constraintViolations;

	// add Client
	@WebMethod
	@WebResult(name = "clients")
	public Client addClient(@WebParam(name = "client") ClientModelAPI clientModelAPI) {
		constraintViolations = validator.validate(clientModelAPI);
		if (constraintViolations.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (ConstraintViolation<ClientModelAPI> constraintViolation : constraintViolations) {
				sb.append(constraintViolation.getMessage() + " ");
			}
			throw new RuntimeException(sb.toString());
		}
		Client client = clientStoreService.addClient(clientModelAPI);
		return client;
	}

	// update existing Client
	@WebMethod
	@WebResult(name = "clients")
	public Client updateClient(@WebParam(name = "clientId") Long id,
			@WebParam(name = "client") ClientModelAPI newClientInfo) {
		constraintViolations = validator.validate(newClientInfo);
		if (constraintViolations.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (ConstraintViolation<ClientModelAPI> constraintViolation : constraintViolations) {
				sb.append(constraintViolation.getMessage() + " ");
			}
			throw new RuntimeException(sb.toString());
		}
		Client client = clientStoreService.updateClient(id, newClientInfo);
		return client;
	}

	// delete Client by Id
	@WebMethod
	@WebResult(name = "client")
	public Client deleteClientById(@WebParam(name = "clientId") Long id) {
		Client client = clientStoreService.deleteClientById(id);
		return client;
	}

	// retrieving Client by Id
	@WebMethod
	@WebResult(name = "client")
	public Client getClientById(@WebParam(name = "clientId") Long id) {
		Client client = clientStoreService.readClientById(id);
		return client;
	}

	// retrieve Client by email
	@WebMethod
	@WebResult(name = "client")
	public Client getClientByEmail(@WebParam(name = "email") String email) {
		Client client = clientStoreService.readClientByEmail(email);
		return client;
	}

	// retrieve all Clients
	@WebMethod
	@WebResult(name = "clients")
	public List<Client> retreiveAllClients() {
		List<Client> clients = clientStoreService.retreiveAllClients();
		return clients;
	}

	@WebMethod
	public String sayHi() {
		return "Hi!";
	}

}
