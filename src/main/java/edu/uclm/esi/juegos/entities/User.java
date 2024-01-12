package edu.uclm.esi.juegos.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.annotation.Nonnull;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;


@Entity
public class User {
	
	
	@Id
	private String ID; //pk on db
	@Nonnull
	private String email;
	@Nonnull
	private String user_name;
	@Nonnull
	private String pwd;
	
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = Hashing.sha256()
                .hashString(pwd, StandardCharsets.UTF_8)
                .toString();;
	}
	
	
	
	
	
}
