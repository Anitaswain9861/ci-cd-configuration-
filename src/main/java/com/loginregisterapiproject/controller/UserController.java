package com.loginregisterapiproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.loginregisterapiproject.entity.UserDTO;
import com.loginregisterapiproject.service.UserService;

import com.loginregisterapiproject.user.Status;



@RestController
public class UserController {

	@Autowired
	private UserService userService;

	
	
	@PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO registeredUser = userService.registerUser(userDTO);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle registration failure, e.g., duplicate username or other validation errors
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

	
	    @CrossOrigin()
	    @PostMapping(path = "/login",produces = {MediaType.APPLICATION_JSON_VALUE})
	    	  public ResponseEntity<?> loginUser(@Valid @RequestBody UserDTO userDTO) {
	    	
	    	
	    	UserDTO loggedInUser = userService.loginUser(userDTO);
	       
	    
	    	 if (loggedInUser != null) {
	    	            return ResponseEntity.ok(loggedInUser);
	    	        } else {
	    	           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
	    	        }
	          
	      }

    
 	    

	    
	    
	    
	   
	    


	    @CrossOrigin()
	    @PostMapping("/users/logout")
	    public Status logUserOut(@Valid @RequestBody UserDTO userDTO) {
	      
	    	Status status  = userService.logUserOut(userDTO);

	        return Status.GONE;
	    }

	    @CrossOrigin()
	    @DeleteMapping("/users/all")
	    public Status deleteUsers() {
	        return userService.deleteUsers();
	    }

}
