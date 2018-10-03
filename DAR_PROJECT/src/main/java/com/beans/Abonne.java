package com.beans;
import javax.persistence.*;



/**
 * @author Lachachi charaf
 *
 */
/**
 * @author Usuario
 *
 */
@Entity
@Table (name = "ABONNE")
public class Abonne {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ABONNE_ID")
	private int ABONNE_id;
	@Column(name = "USERNAME", unique = true, nullable = false)
	private String username;
	@Column(name = "EMAIL", unique = true, nullable = false)
	private String email;
	@Column(name = "FIRSTNAME")
	private String firstname;
	@Column(name = "LASTNAME")
	private String lastname;
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "ADRESS")
	private String adress;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "PASSWORD")
	private String password;
	
	public Abonne() {}

	
	/**
	 * Getters & Setters
	 * */
	
	public int getABONNE_id() {
		return ABONNE_id;
	}

	public void setABONNE_id(int aBONNE_id) {
		ABONNE_id = aBONNE_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "Abonne [username=" + username + ", email=" + email + ", firstname=" + firstname + ", lastname="
				+ lastname + ", gender=" + gender + ", adress=" + adress + ", description=" + description
				+ ", password=" + password + "]";
	}
	
	
}
