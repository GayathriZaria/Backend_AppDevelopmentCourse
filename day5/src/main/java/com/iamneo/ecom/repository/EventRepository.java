package com.iamneo.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iamneo.ecom.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

    Event findByCid(Long cid);

}
