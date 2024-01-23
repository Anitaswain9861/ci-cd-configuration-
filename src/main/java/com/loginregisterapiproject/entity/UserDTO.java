package com.loginregisterapiproject.entity;

public class UserDTO {
	
	private  Integer id;
	   
    private  String username;
  

    private  String email;
   
    private  String phno;

   
    private  String password;
   
    private  boolean loggedIn;
    
    public UserDTO() {
		super();
	}

	public UserDTO(Integer id, String username, String email,String phno, String password, boolean loggedIn) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.phno = phno;
		this.password = password;
		this.loggedIn = loggedIn;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	

}
