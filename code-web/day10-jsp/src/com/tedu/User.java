package com.tedu;
/**
 *	封装用户信息的实体类 
 */
public class User {
	private String username;
	private String pwd;
	private String email;
	
	
	public User() {}
	
	public User(String username, String pwd, String email) {
		this.username = username;
		this.pwd = pwd;
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
