package com.supermy.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.document.mongodb.mapping.Document;

@Document
public class User {
		
	@Id
	private String id;
	private String username;
	private String passwd;
	private String email;

	public User(){};
	
	public User(String id, String username, String passwd, String email) {
		super();
		this.id = id;
		this.username = username;
		this.passwd = passwd;
		this.email = email;
	}
		
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", passwd="
				+ passwd + ", email=" + email + "]";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}

	/**
	 * @param passwd the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
