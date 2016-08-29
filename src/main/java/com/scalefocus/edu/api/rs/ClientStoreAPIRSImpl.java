package com.scalefocus.edu.api.rs;

import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.scalefocus.edu.api.ClientStoreAPI_RS;
import com.scalefocus.edu.api.model.ClientModelAPI;
import com.scalefocus.edu.db.model.Client;
import com.scalefocus.edu.service.ClientStoreService;

@Produces("application/json")
@Consumes("application/json")
@Path("")
@Controller
public class ClientStoreAPIRSImpl implements ClientStoreAPI_RS {

	@Autowired
	private ClientStoreService clientStoreService;

	@GET
	@Path("/hi")
	public String sayHi() {
		return clientStoreService.sayHi();
	}

	@Context
	UriInfo uri;

	// Create new Client
	@POST
	@Consumes("application/json")
	public Response addClient(ClientModelAPI clientModelAPI) {
		Client client = clientStoreService.addClient(clientModelAPI);
		URI uri = URI.create(client.getId().toString());
		return Response.created(uri).entity(client).build();
	}

	// Update existing Client by Id
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public Response updateClient(@PathParam("id") Long id, ClientModelAPI clientModelAPI, @Context Request request) {

		Client client = clientStoreService.readClientById(id);
		client = clientStoreService.updateClient(id, clientModelAPI);
		return Response.ok().entity(client).build();
	}

	// Delete existing Client
	@DELETE
	@Path("/{id}")
	@Consumes("application/json")
	public Response deleteClientById(@PathParam("id") Long id, @Context Request request) {
		Client client = clientStoreService.readClientById(id);
		 client = clientStoreService.deleteClientById(id);
		return Response.ok().entity(client).build();
	}

	// Retrieve client by Id
	@GET
	@Path("/{id}")
//	@Cacheable(time = 10, unit = TimeUnit.MINUTES)
	public Response getClientById(@PathParam("id") Long id, @Context Request request) {
		Client client = clientStoreService.readClientById(id);
		return Response.ok().entity(client).build();
		
	}

	// Retrieve client by email address
	@GET
	@Path("/mail/{email}")
	public Response getClientByEmail(@PathParam("email") String email, @Context Request request) {
		Client client = clientStoreService.readClientByEmail(email);
		return Response.ok().entity(client).build();
	}

	// Retrieve all clients
	@GET
	@Path("/all")
	public Response retreiveAllClients(@Context Request request) {
		List<Client> clients = clientStoreService.retreiveAllClients();
		return Response.ok().entity(clients).build();
	}
}
