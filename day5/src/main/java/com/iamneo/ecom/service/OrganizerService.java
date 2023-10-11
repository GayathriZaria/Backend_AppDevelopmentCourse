package com.iamneo.ecom.service;

import java.util.List;

import com.iamneo.ecom.dto.request.OrganizerRequest;
import com.iamneo.ecom.dto.response.CountResponse;
import com.iamneo.ecom.dto.response.OrganizerResponse;
import com.iamneo.ecom.model.Organizer;

public interface OrganizerService {

    boolean saveOrganizer(OrganizerRequest request);

    List<OrganizerResponse> getAllOrganizers();

    OrganizerResponse getOrganizer(Long pid);

    OrganizerResponse updateOrganizer(OrganizerRequest request, Long pid);

    boolean deleteOrganizer(Long pid);

    Organizer getOrganizerModelId(Long pid);

    CountResponse organizerCount();

}
