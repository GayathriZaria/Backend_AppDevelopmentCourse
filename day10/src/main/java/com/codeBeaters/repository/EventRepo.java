package com.codeBeaters.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeBeaters.entity.Event;

public interface EventRepo extends JpaRepository<Event, Integer>{

}
