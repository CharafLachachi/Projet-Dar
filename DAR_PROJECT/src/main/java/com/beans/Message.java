package com.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @author thamazgha
 * 
 */
@Entity
@Table (name = "Message")
public class Message {

	@Column(name = "SENDER_ID")
	private int sender_id;
	
	@Column(name = "RECEIVER_ID")
	private int receiver_id;
	
	@Column(name = "MSG_ID")
	private int Msg_id;

	@Column(name = "MSG_TEXT")
	private int Msg_txt;


	

}
