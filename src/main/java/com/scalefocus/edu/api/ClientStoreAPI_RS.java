package com.scalefocus.edu.api;


import javax.validation.Valid;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.RequestBody;



import com.scalefocus.edu.api.model.ClientModelAPI;


public interface ClientStoreAPI_RS {
	
	public Response addClient(@Valid @RequestBody ClientModelAPI clientModelAPI); 
	
	public Response deleteClientById(Long id, Request request);
	
	public Response getClientById(Long id, Request request);
	
	public Response getClientByEmail(String email, Request request);
	
	public Response retreiveAllClients(Request request);
	
	public Response updateClient(Long id, @Valid @RequestBody ClientModelAPI clientModelAPI,  Request request);
}
