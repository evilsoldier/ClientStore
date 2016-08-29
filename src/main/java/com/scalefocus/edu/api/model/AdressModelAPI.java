package com.scalefocus.edu.api.model;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AdressModelAPI {

	@NotNull(message = "Country cannot be NULL")
	@NotEmpty(message = "Country cannot be empty")
	private String country;

	@NotNull(message = "City cannot be NULL")
	@NotEmpty(message = "City cannot be empty")
	private String city;

	@NotNull(message = "Zip code cannot be NULL")
	@NotEmpty(message = "Zip code cannot be empty")
	private String zipCode;

	@NotNull(message = "Address line cannot be NULL")
	@NotEmpty(message = "Address line cannot be empty")
	// @JsonProperty(value = "adressLine")
	private String adressLine;

	public String getAddressLine() {
		return adressLine;
	}
	public void setAdressLine(String adressLine) {
		this.adressLine = adressLine;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
