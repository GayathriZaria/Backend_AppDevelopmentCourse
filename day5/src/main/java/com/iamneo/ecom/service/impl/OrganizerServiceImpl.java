package com.iamneo.ecom.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.iamneo.ecom.dto.request.OrganizerRequest;
import com.iamneo.ecom.dto.response.CountResponse;
import com.iamneo.ecom.dto.response.OrganizerResponse;
import com.iamneo.ecom.model.Organizer;
import com.iamneo.ecom.repository.EventRepository;
import com.iamneo.ecom.repository.OrganizerRepository;
import com.iamneo.ecom.service.OrganizerService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrganizerServiceImpl implements OrganizerService {

    private final OrganizerRepository organizerRepository;
    private final EventRepository eventRepository;

    @Override
    public boolean saveOrganizer(OrganizerRequest request) {
        if (organizerRepository.findByOrganizerName(request.getOrganizerName()).isPresent()) {
            return false;
        }

        Organizer organizer = Organizer.builder()
                .organizerName(request.getOrganizerName())
                .organizerSalary(request.getOrganizerSalary())
                .organizerEvent(request.getOrganizerEvent())
                .event(eventRepository.findByCid(request.getCid()))
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
    public OrganizerResponse getOrganizer(Long pid) {
        Organizer organizer = organizerRepository.findByPid(pid);
        return mapOrganizerToResponse(organizer);
    }

    @Override
    public OrganizerResponse updateOrganizer(OrganizerRequest request, Long pid) {
        Organizer organizer = organizerRepository.findByPid(pid);

        if (organizer != null) {
            organizer.setOrganizerName(request.getOrganizerName());
            organizer.setOrganizerSalary(request.getOrganizerSalary());
            organizer.setOrganizerEvent(request.getOrganizerEvent());
            organizer.setEvent(eventRepository.findByCid(request.getCid()));
            organizerRepository.save(organizer);

            return mapOrganizerToResponse(organizer);
        } else {
            throw new EntityNotFoundException("Organizer with pid " + pid + " not found");
        }
    }

    @Override
    public boolean deleteOrganizer(Long pid) {
        Organizer organizer = organizerRepository.findByPid(pid);

        if (organizer != null) {
            organizerRepository.deleteByPid(pid);
            return true;
        } else {
            return false;
        }
    }

    private OrganizerResponse mapOrganizerToResponse(Organizer organizer) {
        return OrganizerResponse.builder()
                .pid(organizer.getPid())
                .organizerName(organizer.getOrganizerName())
                .organizerSalary(organizer.getOrganizerSalary())
                .organizerEvent(organizer.getOrganizerEvent())
                .build();
    }

    @Override
    public Organizer getOrganizerModelId(Long pid) {
        return organizerRepository.findByPid(pid);
    }

    @Override
    public CountResponse organizerCount() {
        long count = organizerRepository.count();
        return CountResponse.builder().count(count).build();
    }
}
