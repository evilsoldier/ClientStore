package com.scalefocus.edu.api;
import java.util.List;

import com.scalefocus.edu.api.model.AdressModelAPI;
import com.scalefocus.edu.db.model.Adress;
import com.scalefocus.edu.db.model.Client;


public interface AdressAPI_WS {
	
	public List<Adress> getAllAdresses(Long clientId);
	
	public Adress addAdress(Long clientId, AdressModelAPI address);
	
//	public Response updateAdress(Long clientId, Long adressId, @Valid @RequestBody AdressModelAPI address, Request request);
	
	public Client deleteAdress(Long clientId, Long addressId);

}