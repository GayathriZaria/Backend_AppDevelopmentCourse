package com.example.eventManagement.repositor;
import org.springframework.data.repository.CrudRepository;

import com.example.eventManagement.model.EventModel;

import java.util.List;

public interface Repositor extends CrudRepository<EventModel, Integer>
{

	boolean existsByNameIgnoreCase(String name);

    List<EventModel> findByNameContainingIgnoreCase(String name);
}