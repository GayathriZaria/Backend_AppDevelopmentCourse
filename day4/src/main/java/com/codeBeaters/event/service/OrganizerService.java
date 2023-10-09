package com.codeBeaters.event.service;

import java.util.List;

import com.codeBeaters.event.dto.request.OrganizerRequest;
import com.codeBeaters.event.dto.response.OrganizerResponse;
import com.codeBeaters.event.model.Organizer;

public interface OrganizerService {

    boolean saveOrganizer(OrganizerRequest request);

    List<OrganizerResponse> getAllOrganizers();

    OrganizerResponse getOrganizer(Long organizerId);

    OrganizerResponse updateOrganizer(OrganizerRequest request, Long organizerId);

    boolean deleteOrganizer(Long organizerId);

    Organizer getOrganizerModelId(Long organizerId);

}
