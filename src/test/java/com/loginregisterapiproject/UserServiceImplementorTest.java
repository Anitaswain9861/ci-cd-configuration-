package com.loginregisterapiproject;



import org.junit.jupiter.api.BeforeEach;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UserServiceImplementorTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImplementor userService;

	
	
	
	 
    
    @Test
    void testRegisterUser() {
        // Arrange
    	UserDTO userDTO = new UserDTO(1, "john.doe", "john.doe@example.com", "1234567890", "password123", true);
        User userEntity = new User(1, "john.doe", "john.doe@example.com", "1234567890", "password123", true);
        when(userMapper.DTOToEntity(userDTO)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.entityToDTO(userEntity)).thenReturn(userDTO);

        // Act
        UserDTO result = userService.registerUser(userDTO);

        // Assert
        assertEquals(userDTO, result);

        // Verify interactions with mocks
        verify(userMapper, times(1)).DTOToEntity(userDTO);
        verify(userRepository, times(1)).save(userEntity);
        verify(userMapper, times(1)).entityToDTO(userEntity);
    }
    
    @Test
    void testRegisterUser_SuccessfulRegistration() {
        // Arrange
    	 // Arrange
    	UserDTO userDTO = new UserDTO(1, "john.doe", "john.doe@example.com", "1234567890", "password123", true);
        User userEntity = new User(1, "john.doe", "john.doe@example.com", "1234567890", "password123", true);
        when(userMapper.DTOToEntity(userDTO)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userMapper.entityToDTO(userEntity)).thenReturn(userDTO);

        // Act
        UserDTO result = userService.registerUser(userDTO);

        // Assert
        assertNotNull(result);
        assertEquals(userDTO.getUsername(), result.getUsername());
        assertEquals(userDTO.getEmail(), result.getEmail());
        assertEquals(userDTO.getPhno(), result.getPhno());
        assertEquals(userDTO.getPassword(), result.getPassword());
        assertEquals(userDTO.isLoggedIn(), result.isLoggedIn());
        

        // Verify interactions with mocks
        verify(userMapper, times(1)).DTOToEntity(userDTO);
        verify(userRepository, times(1)).save(userEntity);
        verify(userMapper, times(1)).entityToDTO(userEntity);
    }

	/*
	 * @Test void testRegisterUser_NullInput() { // Act and Assert
	 * IllegalArgumentException exception =
	 * assertThrows(IllegalArgumentException.class, () ->
	 * userService.registerUser(null)); assertEquals("UserDTO must not be null",
	 * exception.getMessage());
	 * 
	 * // Verify interactions with mocks (should not be called) //
	 * verifyZeroInteractions(userMapper, userRepository); }
	 */

    @Test
    void testRegisterUser_SaveException() {
        // Arrange
    	 UserDTO userDTO = new UserDTO(1, "john.doe", "john.doe@example.com", "1234567890", "password123", true);
         User userEntity = new User(1, "john.doe", "john.doe@example.com", "1234567890", "password123", true);
        when(userMapper.DTOToEntity(userDTO)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenThrow(new RuntimeException("Save failed"));

        // Act and Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.registerUser(userDTO));
        assertEquals("Save failed", exception.getMessage());

        // Verify interactions with mocks
        verify(userMapper, times(1)).DTOToEntity(userDTO);
        verify(userRepository, times(1)).save(userEntity);
        verifyNoMoreInteractions(userMapper); // userRepository.save failed, so no DTO conversion
    }

    // Other test methods...
}



