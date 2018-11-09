package com.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;


/**
 * 
 * @author thamazgha
 *
 */
@Entity
@Table (name = "COMMENTAIRE")
public class Commentaire  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COMMENT_ID")
	private int comment_id;

	@Column(name = "COMMENT_TXT")
	private String comment_text;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;
	
	
	/** 
	 * Association avec publication
	 * @return
	 */
	
	@ManyToOne
	@JoinColumn(name="PUB_ID") 
	private Publication publication;
	
	public Publication getPublication() {
		return publication;
	}

	public void setPublication(Publication publication) {
		this.publication = publication;
	}

	/**
	 * Assocition avec Abonne 
	 * @return
	 */
	@ManyToOne
	@JoinColumn(name="ABONNE_ID")
	private Abonne abonne;
	
	public Abonne getAbonne() {
		return this.abonne;
	}

	public int getComment_id() {
		return comment_id;
	}

	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public String getComment_text() {
		return comment_text;
	}

	public void setComment_text(String comment_text) {
		this.comment_text = comment_text;
	}

	public void setAbonne(Abonne abonne) {
		this.abonne = abonne;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	
	
	
}
