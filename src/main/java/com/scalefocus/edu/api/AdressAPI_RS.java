package com.scalefocus.edu.api;

import javax.validation.Valid;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import com.scalefocus.edu.api.model.AdressModelAPI;

import org.springframework.web.bind.annotation.RequestBody;

public interface AdressAPI_RS {
	
	public Response getAllAdresses(Long clientId, Request request);
	
	public Response addAdress(Long clientId, @Valid @RequestBody AdressModelAPI address);
	
//	public Response updateAdress(Long clientId, Long adressId, @Valid @RequestBody AdressModelAPI address, Request request);
	
	public Response deleteAdress(Long clientId, Long addressId, Request request);

}
