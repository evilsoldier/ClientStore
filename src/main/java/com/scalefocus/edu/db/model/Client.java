package com.scalefocus.edu.db.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="clients")
@NamedQuery(name="Client.findAll", query="SELECT c FROM Client c")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "client", targetEntity = Adress.class)
	private List<Adress> adresses;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="email")
	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;
//
//	public Client() {
//	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void addAdress(Adress adress) {
		this.adresses.add(adress);
	}
	
	public void removeAdress(Adress adress) {
		this.adresses.remove(adress);
	}
	
	public List<Adress> getAdresses(){
		return this.adresses;
	}
	
	@Override
    public boolean equals(Object o) {

        if (o == this) return true;
        
        if (!(o instanceof Client)) {
            return false;
        }
        Client otherObj = (Client) o;
		if (!this.email.equals(otherObj.getEmail())) {
			return false;
		}
		if (!this.firstName.equals(otherObj.getFirstName())) {
			return false;
		}
		if (!this.lastName.equals(otherObj.getLastName())) {
			return false;
		}
		if (!this.adresses.equals(otherObj.getAdresses())) {
			return false;
		}
		if (!this.id.equals(otherObj.getId())) {
			return false;
		}
		return true;
    }
	
	@Override
	   public int hashCode(){
	       int result = 1;
	       result = 31*result + (firstName != null ? firstName.hashCode() : 0);
	       result = 31*result + (lastName !=null ? lastName.hashCode() : 0);
	       result = 31*result + (email !=null ? email.hashCode() : 0);
	       result = 31*result + (id  !=null ? id.hashCode() : 0);
	       return result;
	   }

}