package com.codeBeaters.event.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.codeBeaters.event.dto.request.OrganizerRequest;
import com.codeBeaters.event.dto.response.OrganizerResponse;
import com.codeBeaters.event.model.Organizer;
import com.codeBeaters.event.repository.OrganizerRepository;
import com.codeBeaters.event.service.OrganizerService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrganizerServiceImpl implements OrganizerService {

    private final OrganizerRepository organizerRepository;

    @Override
    public boolean saveOrganizer(OrganizerRequest request) {
        if (organizerRepository.findByOrganizerName(request.getOrganizerName()).isPresent()) {
            return false;
        }

        Organizer organizer = Organizer.builder()
                .organizerName(request.getOrganizerName())
                .organizerSalary(request.getOrganizerSalary())
                .yearsofExp(request.getYearsofExp())
                .organizerDept(request.getOrganizerDept())
                .organizerSpec(request.getOrganizerSpec())
                .build();

        organizerRepository.save(organizer);
        return true;
    }

    @Override
    public List<OrganizerResponse> getAllOrganizers() {
        List<Organizer> organizerList = organizerRepository.findAll();

        return organizerList.stream()
                .map(this::mapOrganizerToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrganizerResponse getOrganizer(Long organizerId) {
        Organizer organizer = organizerRepository.findByOrganizerId(organizerId);
        return mapOrganizerToResponse(organizer);
    }

    @Override
    public OrganizerResponse updateOrganizer(OrganizerRequest request, Long organizerId) {
        Organizer organizer = organizerRepository.findByOrganizerId(organizerId);

        if (organizer != null) {
            organizer.setOrganizerName(request.getOrganizerName());
            organizer.setOrganizerSalary(request.getOrganizerSalary());
            organizer.setYearsofExp(request.getYearsofExp());
            organizer.setOrganizerDept(request.getOrganizerDept());
            organizer.setOrganizerSpec(request.getOrganizerSpec());

            organizerRepository.save(organizer);

            return mapOrganizerToResponse(organizer);
        } else {
            throw new EntityNotFoundException("Organizer with organizerId " + organizerId + " not found");
        }
    }

    @Override
    public boolean deleteOrganizer(Long organizerId) {
        Organizer organizer = organizerRepository.findByOrganizerId(organizerId);

        if (organizer != null) {
            organizerRepository.deleteByOrganizerId(organizerId);
            return true;
        } else {
            return false;
        }
    }

    private OrganizerResponse mapOrganizerToResponse(Organizer organizer) {
        return OrganizerResponse.builder()
                .organizerId(organizer.getOrganizerId())
                .organizerName(organizer.getOrganizerName())
                .organizerSalary(organizer.getOrganizerSalary())
                .yearsofExp(organizer.getYearsofExp())
                .organizerDept(organizer.getOrganizerDept())
                .organizerSpec(organizer.getOrganizerSpec())
                .build();
    }

    @Override
    public Organizer getOrganizerModelId(Long organizerId) {
        return organizerRepository.findByOrganizerId(organizerId);
    }
}