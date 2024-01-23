package com.loginregisterapiproject;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


import java.util.ArrayList;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.loginregisterapiproject.entity.User;
import com.loginregisterapiproject.entity.UserDTO;
import com.loginregisterapiproject.repository.UserRepository;
import com.loginregisterapiproject.service.impl.UserServiceImplementor;
import com.loginregisterapiproject.utils.UserMapper;
import com.loginregisterapiproject.user.Status;

@ExtendWith(MockitoExtension.class)
public class LogoutTest {
	

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

   
    @InjectMocks
    private UserServiceImplementor userService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLogUserOut() {
    	String email = "john.doe@example.com";
        String password = "password123";
        // Mocking data
        UserDTO userDTO = new UserDTO(1, "john.doe", "john.doe@example.com", "1234567890", "password123", true); // You should set this up with appropriate data
       // User user = new User(); // You should set this up with appropriate data
        User user = new User(1, null,null,  email, password,false);
        User existingUser = new User(1, "john.doe@example.com", email, "password123", password, false);
        // Mocking behavior
        when(userMapper.DTOToEntity(userDTO)).thenReturn(user);
        when(userRepository.findAll()).thenReturn(new ArrayList<>()); // Empty list to simulate no users initially

        // Method invocation
        Status result = userService.logUserOut(userDTO);
       // UserDTO Status = userService.logUserOut(userDTO);

        // Verifying interactions
        verify(userMapper, times(1)).DTOToEntity(userDTO);
       // verify(userMapper, times(1)).entityToDTO(user);
        verify(userRepository, times(1)).findAll();
        verifyNoMoreInteractions(userMapper, userRepository);

        // Assertions
        assertEquals(Status.GONE, result);
        //assertNotNull(Status);
       // assertTrue(result.islogUserOut());
    }
    
    
    @Test
    void testDeleteAll() {
        // Method invocation
        userService.deleteAll();

        // Verifying interactions
        verify(userRepository, times(1)).deleteAll();
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void testDeleteUsers() {
        // Mocking behavior
        doNothing().when(userRepository).deleteAll();

        // Method invocation
        Status result = userService.deleteUsers();

        // Verifying interactions
        verify(userRepository, times(1)).deleteAll();
        verifyNoMoreInteractions(userRepository);

        // Assertions
        assertEquals(Status.GONE, result);
    }

}



