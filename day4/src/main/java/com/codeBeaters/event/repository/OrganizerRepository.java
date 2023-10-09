package com.codeBeaters.event.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeBeaters.event.model.Organizer;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

    Optional<Organizer> findByOrganizerName(String organizerName);

    Organizer findByOrganizerId(Long organizerId);

    void deleteByOrganizerId(Long organizerId);

}
