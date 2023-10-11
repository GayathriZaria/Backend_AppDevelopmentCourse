package com.iamneo.ecom.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.iamneo.ecom.dto.response.EventResponse;
import com.iamneo.ecom.dto.response.OrganizerResponse;
import com.iamneo.ecom.model.Event;
import com.iamneo.ecom.repository.EventRepository;
import com.iamneo.ecom.service.EventService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public List<EventResponse> getAllCategories() {
        List<Event> eventList = eventRepository.findAll();

        return eventList.stream()
                .map(event -> {
                    EventResponse eventResponse = new EventResponse();
                    eventResponse.setCid(event.getCid());
                    eventResponse.setEventName(event.getEventName());
                    List<OrganizerResponse> organizerResponses = event.getOrganizers().stream()
                            .map(organizer -> {
                                OrganizerResponse organizerResponse = new OrganizerResponse();
                                organizerResponse.setPid(organizer.getPid());
                                organizerResponse.setOrganizerName(organizer.getOrganizerName());
                                organizerResponse.setOrganizerSalary(organizer.getOrganizerSalary());
                                organizerResponse.setOrganizerEvent(organizer.getOrganizerEvent());
                                return organizerResponse;
                            })
                            .collect(Collectors.toList());
                    eventResponse.setOrganizers(organizerResponses);
                    return eventResponse;
                })
                .collect(Collectors.toList());
    }

}
