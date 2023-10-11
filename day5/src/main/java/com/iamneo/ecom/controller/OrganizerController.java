package com.iamneo.ecom.controller;

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

import com.iamneo.ecom.constant.Api;
import com.iamneo.ecom.dto.request.OrganizerRequest;
import com.iamneo.ecom.dto.response.CountResponse;
import com.iamneo.ecom.dto.response.OrganizerResponse;
import com.iamneo.ecom.service.OrganizerService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(Api.PRODUCT)
@RequiredArgsConstructor
@Tag(name = "Organizer")
public class OrganizerController {

    private final OrganizerService organizerService;

    @PostMapping("/add")
    public ResponseEntity<String> saveOrganizer(@RequestBody OrganizerRequest request) {
        boolean isSaved = organizerService.saveOrganizer(request);
        return isSaved ? ResponseEntity.status(201).body("Organizer added successfully!")
                : ResponseEntity.badRequest().build();
    }

    @GetMapping("/get")
    public ResponseEntity<List<OrganizerResponse>> getAllOrganizers() {
        List<OrganizerResponse> organizerList = organizerService.getAllOrganizers();
        return !organizerList.isEmpty() ? ResponseEntity.status(200).body(organizerList)
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/getCount")
    public ResponseEntity<CountResponse> organizerCount() {
        CountResponse countResponse = organizerService.organizerCount();
        return countResponse.getCount() != 0 ? ResponseEntity.ok().body(countResponse)
                : ResponseEntity.noContent().build();
    }

    @GetMapping("/find/{pid}")
    public ResponseEntity<OrganizerResponse> getOrganizer(@PathVariable Long pid) {
        OrganizerResponse organizerResponse = organizerService.getOrganizer(pid);
        return organizerResponse != null ? ResponseEntity.ok().body(organizerResponse) : ResponseEntity.notFound().build();
    }

    @PutMapping("/edit/{pid}")
    public ResponseEntity<OrganizerResponse> updateOrganizer(@RequestBody OrganizerRequest request, @PathVariable Long pid) {
        OrganizerResponse organizerResponse = organizerService.updateOrganizer(request, pid);
        return organizerResponse != null ? ResponseEntity.ok().body(organizerResponse) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{pid}")
    public ResponseEntity<String> deleteOrganizer(@PathVariable Long pid) {
        boolean isDeleted = organizerService.deleteOrganizer(pid);
        return isDeleted ? ResponseEntity.ok().body("Organizer deleted successfully!")
                : ResponseEntity.notFound().build();
    }

}
