package com.iamneo.ecom.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iamneo.ecom.constant.Api;
import com.iamneo.ecom.dto.response.EventResponse;
import com.iamneo.ecom.service.EventService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Api.CATEGORY)
@RequiredArgsConstructor
@Tag(name = "Event")
public class EventController {

    private final EventService eventService;

    @GetMapping("/get")
    public ResponseEntity<List<EventResponse>> getAllCategories() {
        List<EventResponse> eventResponse = eventService.getAllCategories();
        return !eventResponse.isEmpty() ? ResponseEntity.ok().body(eventResponse)
                : ResponseEntity.noContent().build();
    }
}
