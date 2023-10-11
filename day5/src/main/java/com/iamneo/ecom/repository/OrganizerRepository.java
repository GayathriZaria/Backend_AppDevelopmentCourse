package com.iamneo.ecom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iamneo.ecom.model.Organizer;

public interface OrganizerRepository extends JpaRepository<Organizer, Long> {

    Optional<Organizer> findByOrganizerName(String OrganizerName);

    Organizer findByPid(Long pid);

    void deleteByPid(Long pid);

}
