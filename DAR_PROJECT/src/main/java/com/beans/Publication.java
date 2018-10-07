package com.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * 
 * @author thamazgha
 *
 */
@Entity
@Table (name = "PUBLICATION")
public class Publication {
	public Publication() {
		this.comments = new ArrayList<Commentaire>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PUB_ID")
	private int Pub_id;

	@Column(name = "OWNER")
	private int owner;

	//y aura d'autres attributs 


	@ManyToMany(mappedBy="publications")
	private Set<Abonne> abonnes;

	@OneToMany(mappedBy="publication")
	private List<Commentaire> comments;

	public List<Commentaire> getComments(){
		return this.comments;
	}
}
