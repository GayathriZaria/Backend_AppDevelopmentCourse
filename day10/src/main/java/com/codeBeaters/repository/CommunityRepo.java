package com.codeBeaters.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeBeaters.entity.Community;

public interface CommunityRepo extends JpaRepository<Community, Integer>{

}
