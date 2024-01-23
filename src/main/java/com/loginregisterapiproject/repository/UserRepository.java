package com.loginregisterapiproject.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loginregisterapiproject.entity.User;


@Repository
public interface UserRepository  extends JpaRepository<User, Integer>{

	

	

	User findByEmail(String email);

}
