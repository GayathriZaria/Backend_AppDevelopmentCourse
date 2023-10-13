package com.codeBeaters.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.codeBeaters.Modal.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
	

	
	@Query("select u from User u where u.email = ?1")
	public List<User> checkPassword(@Param("email") String email);
	
	
	@Transactional
	@Query("select count(u) from User u where u.email = ?1") 
	public List<Integer> isEmailExist(@Param("email") String email);
	
	



}
