package edu.uclm.esi.juegos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
	
	
	@Id
	private Integer ID; //pk on db
	private Integer num_wins;
	private Integer num_loses;
	private String email;
	private String user_name;
	
	
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public Integer getNum_wins() {
		return num_wins;
	}
	public void setNum_wins(Integer num_wins) {
		this.num_wins = num_wins;
	}
	public Integer getNum_loses() {
		return num_loses;
	}
	public void setNum_loses(Integer num_loses) {
		this.num_loses = num_loses;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
	
}
