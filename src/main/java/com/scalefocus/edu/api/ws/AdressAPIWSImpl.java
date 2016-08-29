package com.scalefocus.edu.api.ws;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.scalefocus.edu.api.AdressAPI_WS;
import com.scalefocus.edu.api.model.AdressModelAPI;
import com.scalefocus.edu.db.model.Adress;
import com.scalefocus.edu.db.model.Client;
import com.scalefocus.edu.service.AdressStoreService;

@Controller
@WebService(targetNamespace = "http://localhost:8080/ClientStore/ws/adresses?wsdl")
public class AdressAPIWSImpl implements AdressAPI_WS{
	
	@Autowired
	AdressStoreService adressStoreService;

	// get all Client addresses by client Id
	@WebMethod
	@WebResult(name = "adress")
	public List<Adress> getAllAdresses(@WebParam(name = "clientId")Long clientId) {
		List<Adress> allAdresses = adressStoreService.getAllAdresses(clientId); 
		return allAdresses;
	}

	// delete address from existing Client
		@WebMethod
		@WebResult(name = "client")
		public Client deleteAdress(@WebParam(name = "clientId") Long clientId, @WebParam(name = "adressId") Long adressId) {
			Client client = adressStoreService.removeClientAdress(clientId, adressId);
			return client;
		}
		
	//add address to existing Client
	@WebMethod
	@WebResult(name = "adress")
	public Adress addAdress(@WebParam(name = "clientId")Long clientId, @WebParam(name = "adress") AdressModelAPI adressModelAPI) {
		Adress adress = adressStoreService.addAdress(clientId, adressModelAPI);
		return adress;
	}
}
