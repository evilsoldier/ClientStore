package com.scalefocus.edu.api;

import java.util.List;

import com.scalefocus.edu.api.model.ClientModelAPI;
import com.scalefocus.edu.db.model.Client;

public interface ClientStoreAPI_WS {
	
	public Client addClient(ClientModelAPI clientModelAPI);
	
	public Client deleteClientById(Long id);
	
	public Client getClientById(Long id);
	
	public Client getClientByEmail(String email);
	
	public List<Client> retreiveAllClients();
	
	public Client updateClient(Long id, ClientModelAPI clientModelAPI);
	
	public String sayHi();
	
	
}
