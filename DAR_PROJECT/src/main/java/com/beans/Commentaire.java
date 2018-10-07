package com.beans;

import javax.persistence.*;


/**
 * 
 * @author thamazgha
 *
 */
@Entity
@Table (name = "COMMENTAIRE")
public class Commentaire {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COMMENT_ID")
	private int COMMENT_id;

	@Column(name = "COMMENT_TXT")
	private int COMMENT_text;

	

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
