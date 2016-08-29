package com.scalefocus.edu.api.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;



@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ClientModelAPI {
 	
	@NotNull
	@NotEmpty(message = "First name cannot be empty")
//	@Pattern(regexp="[a-zA-Z]")
	private String firstName;
	
	@NotNull
	@NotEmpty(message = "Last name cannot be empty")
//	@Pattern(regexp="[a-zA-Z]")
	private String lastName;
	
	@NotNull
	@NotEmpty(message = "Email cannot be empty")
	@Email(message="Please provide a valid email address.")
//	@Pattern(regexp="\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(.\\w{2,4})+")	
	private String email;
	
	@Valid
	private List<AdressModelAPI> adresses;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<AdressModelAPI> getAdresses() {
		return this.adresses;
	}
	public void setAdresses(List<AdressModelAPI> adresses) {
		this.adresses = adresses;
	}
	
}
