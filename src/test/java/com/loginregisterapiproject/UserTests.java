package com.loginregisterapiproject;






import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.loginregisterapiproject.entity.User;
import com.loginregisterapiproject.entity.UserDTO;
import com.loginregisterapiproject.repository.UserRepository;
import com.loginregisterapiproject.service.impl.UserServiceImplementor;
import com.loginregisterapiproject.utils.UserMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserTests {

	
	
	  @Mock 
	  private UserRepository userRepository;
	 

    @Mock
    private UserMapper userMapper;
    
    @InjectMocks
    private UserServiceImplementor userService;


    
    @Test
    void testLoginUser_SuccessfulLogin() {
        // Arrange
        String email = "john.doe@example.com";
        String password = "password123";
        UserDTO userDTO = new UserDTO(1, "john.doe", "john.doe@example.com", "1234567890", "password123", false);
        User user = new User(1, null,null,  email, password,true);
        User existingUser = new User(1, "john.doe@example.com", email, "password123", password, true);
        when(userRepository.findByEmail(email)).thenReturn(existingUser);
        when(userMapper.DTOToEntity(userDTO)).thenReturn(existingUser);
        when(userMapper.entityToDTO(existingUser)).thenReturn(new UserDTO(existingUser.getId(), existingUser.getUsername(), existingUser.getEmail(), existingUser.getPhno(), existingUser.getPassword(), existingUser.isLoggedIn()));
        // Act
        UserDTO result = userService.loginUser(userDTO);

        // Assert
        assertNotNull(result);
        assertTrue(result.isLoggedIn());

        // Verify interactions with mocks
        verify(userRepository, times(1)).findByEmail(email);
        verify(userRepository, times(1)).save(existingUser);
        verify(userMapper, times(1)).entityToDTO(existingUser);
       
    }

   @Test
    void testLoginUser_UnsuccessfulLogin_WrongPassword() {
        // Arrange
        String email = "john.doe@example.com";
        String password = "password123";
        UserDTO userDTO = new UserDTO(1, "john.doe", "john.doe@example.com", "1234567890", "password123", false);
        User user = new User(1, null,null,  email, password,false);
        User existingUser = new User(1, "john.doe@example.com", email, "password123",password, false);
        when(userRepository.findByEmail(email)).thenReturn(existingUser);
        when(userMapper.DTOToEntity(userDTO)).thenReturn(existingUser);
        // Act
        UserDTO result = userService.loginUser(userDTO);

        // Assert
        assertNull(result);

        // Verify interactions with mocks
        verify(userRepository, times(1)).findByEmail(email);
      //  verifyNoMoreInteractions(userRepository, userMapper);
    }

   @Test
   void testLoginUser_UserNotFound() {
       // Arrange
       String email = "existent@example.com";
       String password = "password123";
       
       // Mocking behavior
      // when(userRepository.findByEmail(email)).thenReturn(null);

       // Creating a UserDTO for testing
       UserDTO userDTO = new UserDTO(1, "john.doe", "john.doe@example.com", "1234567890", "password123", false);

       // Creating a User instance to be returned by the UserMapper
       User user = new User(1, null, null, email, password, false);

       // Creating an existing user (should not match the provided email)
       User existingUser = new User(1, "existent@example.com", email, "password123", password, false);

       // Mocking behavior of UserMapper
       when(userMapper.DTOToEntity(userDTO)).thenReturn(user);

       // Act
       UserDTO result = userService.loginUser(userDTO);

       // Assert
       assertNull(result);

       // Verify interactions with mocks
       //verify(userRepository, times(1)).findByEmail(email);
       verify(userMapper, times(1)).DTOToEntity(userDTO);
      // verifyNoMoreInteractions(userRepository, userMapper);
   }


 /** @Test
    void testLoginUser_NullInput() {
        // Act and Assert
        assertNull(userService.loginUser(null));

        // Verify interactions with mocks (should not be called)
        //verifyZeroInteractions(userRepository, userMapper);
    }**/
	/*
	 * @Test void testLoginUser_NullInput() { // Act and Assert
	 * IllegalArgumentException exception =
	 * assertThrows(IllegalArgumentException.class, () ->
	 * userService.loginUser(null)); assertEquals("UserDTO must not be null",
	 * exception.getMessage());
	 * 
	 * // Verify interactions with mocks (should not be called) //
	 * verifyZeroInteractions(userMapper, userRepository); }
	 */

   
}

   

	
	
		
	  





