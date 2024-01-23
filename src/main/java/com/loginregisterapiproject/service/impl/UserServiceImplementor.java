package com.loginregisterapiproject.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.loginregisterapiproject.entity.UserDTO;
import com.loginregisterapiproject.repository.UserRepository;
import com.loginregisterapiproject.service.UserService;
import com.loginregisterapiproject.user.Status;
import com.loginregisterapiproject.utils.UserMapper;
import com.loginregisterapiproject.controller.Valid;
import com.loginregisterapiproject.entity.User;







@Service
public  class UserServiceImplementor  implements UserService{
	

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private UserMapper userMapper;
	
	@Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = userMapper.DTOToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.entityToDTO(user);
    }

	   

	

	 public  UserDTO loginUser(UserDTO userDTO){
		UserDTO authenticatedUserDTO = null;
	        User user = userMapper.DTOToEntity(userDTO);

	        // Retrieve existingUser from the repository based on the username or unique identifier
	        User existingUser = userRepository.findByEmail(user.getEmail());

	        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
	            existingUser.setLoggedIn(true);
	            userRepository.save(existingUser);

	            // Map User entity to UserDTO before returning as JSON response
	             authenticatedUserDTO = userMapper.entityToDTO(existingUser);
	          //  return ResponseEntity.ok(authenticatedUserDTO); // Return the UserDTO object as JSON
	        } 
	        
	        
			return authenticatedUserDTO;
	 }
	        
	        
	        public Status logUserOut(@Valid UserDTO userDTO) {
	        User user = userMapper.DTOToEntity(userDTO);

	        // Retrieve all users from the repository
	        List<User> users = userRepository.findAll();

	        for (User other : users) {
	            if (other.equals(user)) {
	                other.setLoggedIn(false);
	                userRepository.save(other); // Save the updated user entity
	                return Status.SAVING;
	            }
	        }

	        return Status.GONE;
	    }
	       

	        @Override
	        public void deleteAll() {
	            userRepository.deleteAll(); // Assuming your repository supports a deleteAll method
	        }
	        public Status deleteUsers() {
	            deleteAll();
	            return Status.GONE;
	        }





			
			
		


}
