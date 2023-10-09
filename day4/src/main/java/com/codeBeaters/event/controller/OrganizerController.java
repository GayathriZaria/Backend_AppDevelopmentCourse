package com.codeBeaters.event.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeBeaters.event.constant.Api;
import com.codeBeaters.event.dto.request.OrganizerRequest;
import com.codeBeaters.event.dto.response.OrganizerResponse;
import com.codeBeaters.event.service.OrganizerService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Api.DOCTOR)
@RequiredArgsConstructor
@Tag(name = "Organizer")
public class OrganizerController {

    private final OrganizerService organizerService;

    @PostMapping("/save")
    public ResponseEntity<String> saveOrganizer(@RequestBody OrganizerRequest request) {
        boolean isSaved = organizerService.saveOrganizer(request);
        return isSaved ? ResponseEntity.status(201).body("Organizer added successfully!")
                : ResponseEntity.badRequest().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrganizerResponse>> getAllOrganizers() {
        List<OrganizerResponse> organizerList = organizerService.getAllOrganizers();
        return !organizerList.isEmpty() ? ResponseEntity.status(200).body(organizerList)
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/{organizerId}")
    public ResponseEntity<OrganizerResponse> getOrganizer(@PathVariable Long organizerId) {
        OrganizerResponse organizerResponse = organizerService.getOrganizer(organizerId);
        return organizerResponse != null ? ResponseEntity.ok().body(organizerResponse) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{organizerId}")
    public ResponseEntity<OrganizerResponse> updateOrganizer(@RequestBody OrganizerRequest request, @PathVariable Long organizerId) {
        OrganizerResponse organizerResponse = organizerService.updateOrganizer(request, organizerId);
        return organizerResponse != null ? ResponseEntity.ok().body(organizerResponse) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{organizerId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long organizerId) {
        boolean isDeleted = organizerService.deleteOrganizer(organizerId);
        return isDeleted ? ResponseEntity.ok().body("Organizer deleted successfully!")
                : ResponseEntity.notFound().build();
    }

}
