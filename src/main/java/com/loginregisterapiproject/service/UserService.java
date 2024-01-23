package com.loginregisterapiproject.service;



import com.loginregisterapiproject.entity.UserDTO;

import com.loginregisterapiproject.user.Status;








public interface UserService {
	
		

		public UserDTO registerUser(UserDTO userDTO);


		public UserDTO loginUser(UserDTO userDTO);

		public Status logUserOut(UserDTO userDTO);


		void deleteAll();


		Status deleteUsers();



		


}
