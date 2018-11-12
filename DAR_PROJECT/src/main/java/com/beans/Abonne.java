package com.beans;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;



/**
 * @author Lachachi charaf
 *
 */
/**
 * @author Usuario
 *
 */
/**
 * @author Usuario
 *
 */
@Entity
@Table (name = "ABONNE")
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class Abonne {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ABONNE_ID")
	private int ABONNE_id;
	@Column(name = "USERNAME", unique = true, nullable = true)
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


	@Lob
	@Column(name="PROFILE_IMAGE", nullable = true, columnDefinition="mediumblob")
	private byte[] image;

	/**
	 * Association avec les villes
	 */
	@ManyToMany( fetch=FetchType.EAGER ,cascade = CascadeType.ALL)
	@JoinTable(name="Abonne_Cities",
	joinColumns=@JoinColumn(
			name="id_abo",
			referencedColumnName="ABONNE_ID"),
	inverseJoinColumns=@JoinColumn(
			name="id_city",
			referencedColumnName="CITY_ID"))

	private Set<CitiesOfInterest> cities;


	/**
	 * Association avec les publications
	 */
	@ManyToMany
	@JoinTable(name="AbonnePub",
	joinColumns=@JoinColumn(
			name="abo_id",
			referencedColumnName="ABONNE_ID"),
	inverseJoinColumns=@JoinColumn(
			name="pub_id",
			referencedColumnName="PUB_ID"))

	private Set<Publication> publications;

	
	/**
	 * Associations avec les commentaires 
	 */
	@OneToMany(targetEntity =Commentaire.class,  mappedBy="abonne")
	private List<Commentaire> comments;

	public List<Commentaire> getComments(){
		return this.comments;
	}

	public void setComments(ArrayList<Commentaire> comments){
		this.comments=comments;
	}

	/**
	 * Construtor
	 */

	public Abonne() {
		this.comments = new ArrayList<Commentaire>();
		this.cities = new HashSet<CitiesOfInterest>();
	}


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
	
	@JsonIgnore
	public Set<CitiesOfInterest> getCities() {
		return cities;
	}

	public void setCities(Set<CitiesOfInterest> cities) {
		this.cities = cities;
	}

	@JsonIgnore
	public Set<Publication> getPublications() {
		return publications;
	}

	public void setPublications(Set<Publication> publications) {
		this.publications = publications;
	}
	@JsonIgnore
	public void setComments(List<Commentaire> comments) {
		this.comments = comments;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
