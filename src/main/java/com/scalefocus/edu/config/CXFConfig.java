package com.scalefocus.edu.config;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.xml.ws.Endpoint;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.Bus;
import org.apache.cxf.feature.LoggingFeature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxws22.EndpointImpl;
import org.apache.cxf.validation.BeanValidationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.scalefocus.edu.api.rs.AdressAPIRSImpl;
import com.scalefocus.edu.api.rs.ClientStoreAPIRSImpl;
import com.scalefocus.edu.api.ws.AdressAPIWSImpl;
import com.scalefocus.edu.api.ws.ClientStoreAPIWSImpl;

import exmappers.BadURLExceptionMapper;
import exmappers.ConstraintViolationExMapper;
import exmappers.EmailAlreadyExistExMapper;
import exmappers.EmailValidationExceptionMapper;
import exmappers.NotExistingAdressExMapper;
import exmappers.NotExistingClientMapper;
import exmappers.NotExistingEmailMapper;
import filters.ServerRequestFilter;
import filters.ServerResponseFilter;

@Configuration
@ImportResource({
	"classpath:META-INF/cxf/cxf.xml",
	"classpath:META-INF/cxf/cxf-servlet.xml",
	"classpath:META-INF/cxf/cxf-extension-*.xml"})

public class CXFConfig {
	
	
	@Autowired
	private Bus bus;
	
	@Autowired
	private ClientStoreAPIWSImpl clientStoreWS;
	
	@Autowired
	private AdressAPIWSImpl adressWS;
	
	@Autowired
	private ClientStoreAPIRSImpl clientStoreRS;
	
	@Autowired
	private AdressAPIRSImpl adressStoreRS;
	
	@PostConstruct
	public void init() {
		//initializing WS Servuce endpoint
		Endpoint endpoint = new EndpointImpl(bus, clientStoreWS);
		endpoint.publish("/ws/clients");
		Endpoint endpoint2 = new EndpointImpl(bus, adressWS);
		endpoint2.publish("/ws/adresses");
		
		// initializes JAX-RS provider and service endpoint
		JAXRSServerFactoryBean factory = new JAXRSServerFactoryBean();
		factory.setBus(bus);
		
		//setting Jackson as a default JSON provider
		factory.setProviders(Arrays.asList(new ServerRequestFilter(),new ServerResponseFilter(), 
				new EmailValidationExceptionMapper(),new BadURLExceptionMapper(), 
				new ConstraintViolationExMapper(), new NotExistingAdressExMapper(),
				new NotExistingClientMapper(), new NotExistingEmailMapper(),
				new EmailAlreadyExistExMapper(),new JacksonJsonProvider()));		
		factory.setFeatures(Arrays.asList(new LoggingFeature(), new BeanValidationFeature()));
		//adding our rest service bean to Jax-rs server
		factory.setServiceBean(clientStoreRS);
		//factory.setAddress("/rs/ClientStore/clients");
		factory.create();
		
		factory.setServiceBean(adressStoreRS);
		factory.setAddress("/rs/ClientStore/adresses");
		factory.create();
	}
}
