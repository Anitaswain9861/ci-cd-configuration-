package com.loginregisterapiproject.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;











@Entity
@Table(name = "users")
public class User {
		
	    public User(Integer id,  String username,  String email, String phno,
				 String password,  boolean loggedIn) {
			super();
			this.id = id;
			this.username = username;
			this.email = email;
			this.phno = phno;
			this.password = password;
			this.loggedIn = loggedIn;
		}

	    
		private @Id @GeneratedValue Integer id;
	   
	    private  String username;
	  

	    private  String email;
	   
	    private  String phno;

	   
	    private  String password;
	   
	    private  boolean loggedIn;

	    public User() {
	    }

	    public User( String username,  String password) {
	        this.username = username;
	        this.password = password;
	        this.loggedIn = true;
	    }
	    public Integer getId() {
	        return id;
	    }
	    public void setId(Integer id) {
	        this.id = id;
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

	    
	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
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

//	    @Override
//	    public boolean equals(Object o) {
//	        if (this == o) return true;
//	        if (!(o instanceof User)) return false;
//	        User user = (User) o;
//	        return Objects.equals(email, user.email) &&
//	                Objects.equals(password, user.password);
//	    }
	//
//	    @Override
//	    public int hashCode() {
//	        return Objects.hash(id, username, password, loggedIn);
//	    }
	//
//	    @Override
//	    public String toString() {
//	        return "User{" +
//	                "id=" + id +
//	                ", username='" + username + '\'' +
//	                ", password='" + password + '\'' +
//	                ", loggedIn=" + loggedIn +
//	                '}';
//	    }


	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        User user = (User) o;
	        return Objects.equals(email, user.email) &&
	                Objects.equals(password, user.password);
	    }
	    
	   
	   // public boolean equals1(Object o) {
	     //   if (this == o) return true;
	      //  if (o == null || getClass() != o.getClass()) return false;
	      //  User user = (User) o;
	      //  return Objects.equals(phno, user.phno) &&
	            //    Objects.equals(password, user.password);
	 //   }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id, username, email, phno, password, loggedIn);
	    }

	    @Override
	    public String toString() {
	        return "User{" +
	                "id=" + id +
	                ", username='" + username + '\'' +
	                ", email='" + email + '\'' +
	                ", phno='" + phno + '\'' +
	                ", password='" + password + '\'' +
	                ", loggedIn=" + loggedIn +
	                '}';
	    }


}
