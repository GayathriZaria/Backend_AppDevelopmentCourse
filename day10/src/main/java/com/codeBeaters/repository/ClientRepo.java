package com.codeBeaters.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.codeBeaters.entity.Client;

import jakarta.transaction.Transactional;

public interface ClientRepo extends JpaRepository<Client, Long>{
	
	@Query("select count(*) from Client s where s.client_id = ?1")
	public List<Integer> clientExist(long Id);
	
	@Transactional
	@Modifying
	@Query(value="insert into client_enrolled_events values(?1 , ?2)",nativeQuery = true)
	public void addEvents(long Uid,int Cid);

}
