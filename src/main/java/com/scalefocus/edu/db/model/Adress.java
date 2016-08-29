package com.scalefocus.edu.db.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="adresses")
@NamedQuery(name="Adress.findAll", query="SELECT a FROM Adress a")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Adress implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne(targetEntity = Client.class)
	@JoinColumn(referencedColumnName = "id")
	@XmlTransient
	@JsonIgnore
	private Client client;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "adressLine")
//	@Mapping(value = "adressLine")
	private String adressLine;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "zipCode")
	private String zipCode;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getCity() {
		return this.city;
	}

	public String getAddressLine() {
		return this.adressLine;
	}

	public void setAddressLine(String adressLine) {
		this.adressLine = adressLine;
	}


	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	public void setClient(Client client){
		this.client = client;
	}
	
	public Client getCLient() {
		return this.client;
	}
	
	@Override
	public boolean equals(Object o) {

		if (o == this)
			return true;
		if (!(o instanceof Adress)) {
			return false;
		}
		
		Adress otherObj = (Adress) o;
		
		if (!this.adressLine.equals(otherObj.getAddressLine())) {
			return false;
		}
		if (!this.country.equals(otherObj.getCountry())) {
			return false;
		}
		if (!this.client.equals(otherObj.getCLient())) {
			return false;
		}
		if (!this.zipCode.equals(otherObj.getZipCode())) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result = 31 * result + (id != null ? id.hashCode() : 0);
		result = 31 * result + (country != null ? country.hashCode() : 0);
		result = 31 * result + (adressLine != null ? adressLine.hashCode() : 0);
		result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
		return result;
	}
}