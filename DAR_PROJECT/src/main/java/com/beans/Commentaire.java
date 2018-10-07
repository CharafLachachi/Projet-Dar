package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author thamazgha
 *
 */
@Entity
@Table (name = "Commentaire")
public class Commentaire {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COMMENT_ID")
	private int COMMENT_id;

	@Column(name = "COMMENT_TXT")
	private int COMMENT_text;

	private Publication publication;

	/** 
	 * Association avec publication
	 * @return
	 */
	
	@ManyToOne
	@JoinColumn(name="PUB_ID") 
	
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
	
	private Abonne abonne;
	@ManyToOne
	@JoinColumn(name="abo_id")
	public Abonne getAbonne() {
		return this.abonne;
	}
	
	
	
	public int getCOMMENT_id() {
		return COMMENT_id;
	}

	public void setCOMMENT_id(int cOMMENT_id) {
		COMMENT_id = cOMMENT_id;
	}

	public int getCOMMENT_text() {
		return COMMENT_text;
	}

	public void setCOMMENT_text(int cOMMENT_text) {
		COMMENT_text = cOMMENT_text;
	}

}
