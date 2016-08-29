package com.scalefocus.edu.api.rs;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import javax.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.scalefocus.edu.api.AdressAPI_RS;
import com.scalefocus.edu.api.model.AdressModelAPI;
import com.scalefocus.edu.db.dao.AdressDAO;
import com.scalefocus.edu.db.dao.ClientDAO;
import com.scalefocus.edu.db.model.Adress;
import com.scalefocus.edu.service.AdressStoreService;

import exceptions.NotExistingAdressEx;

@Produces("application/json")
@Consumes("appilication/json")
@Path("/")
@Controller
public class AdressAPIRSImpl implements AdressAPI_RS {

	AdressDAO aDAO;
	ClientDAO cDAO;
	String hash = "";

	@Autowired
	private AdressStoreService ads;

	@Context
	UriInfo uri;

	// Add address to existing client
	@POST
	@Path("/{clientId}/adresses")
	@Consumes("application/json")
	public Response addAdress(@PathParam("clientId") Long clientId, AdressModelAPI adressModelAPI) {
		Adress adress = ads.addAdress(clientId, adressModelAPI);
		return Response.ok().entity(adress).build();
	}

	// Retrieve client addresses by client Id
	@GET
	@Path("/{id}/adresses")
	public Response getAllAdresses(@PathParam("id") Long clientId, @Context Request request) {
		List<Adress> allAdresses = ads.getAllAdresses(clientId);
		if (allAdresses.isEmpty()) {
			throw new NotExistingAdressEx("No addresses");
		}
		String hashString = "";
		for (Adress adress : allAdresses) {
			hashString += adress.hashCode();
		}	
		try {
			hash = DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5")
					.digest(String.valueOf(hashString.hashCode()).getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("stroshi se");
		} catch (UnsupportedEncodingException ex) {
			System.out.println("stroshi se 2");
		}		
		URI uri = URI.create(clientId.toString());
		return Response.created(uri).entity(allAdresses).tag(hash).build();
	}

	// delete address by clientId and adressId and return the deleted address
	@DELETE
	@Path("/{clientId}/adresses/{adressId}")
	@Consumes("application/json")
	public Response deleteAdress(@PathParam("clientId") Long clientId, @PathParam("adressId") Long addressId,
			@Context Request request) {
		Adress adress = ads.getAdressById(clientId, addressId);
		this.ads.removeClientAdress(clientId, addressId);
		return Response.ok().entity(adress).build();
	}
}
