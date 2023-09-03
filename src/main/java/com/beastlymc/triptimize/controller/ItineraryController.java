package com.beastlymc.triptimize.controller;

import com.beastlymc.triptimize.dto.request.ItineraryRequest;
import com.beastlymc.triptimize.model.Itinerary;
import com.beastlymc.triptimize.model.account.Account;
import com.beastlymc.triptimize.service.ItineraryService;
import com.beastlymc.triptimize.util.Util;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller class for itinerary-related endpoints in the API.
 */
@RequiredArgsConstructor
@RequestMapping(Util.DEFAULT_API_PATH + "itinerary")
@RestController
public class ItineraryController {

    /**
     * The service for {@link Itinerary} objects.
     */
    private final ItineraryService itineraryService;

    /**
     * Finds all itineraries.
     * <p>
     * Only available to admins.
     *
     * @return a list of all itineraries
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public List<Itinerary> findAll() {
        return itineraryService.findAll();
    }

    /**
     * Finds an itinerary by its id.
     * <p>
     * You have to have 'read' permissions to find the itinerary, or be an admin.
     *
     * @param id the id of the itinerary to find
     * @return the itinerary with the given id
     */
    @PreAuthorize("hasRole('ADMIN') or hasPermission(#itineraryId, 'Itinerary', 'read')")
    @GetMapping("{itineraryId}")
    public ResponseEntity<Itinerary> findById(@PathVariable("itineraryId") Integer itineraryId) {
        Optional<Itinerary> itineraryOptional = itineraryService.findById(itineraryId);

        return itineraryOptional.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());

    }

    /**
     * Finds all itineraries by a user's id.
     * <p>
     * You have to have 'read' permissions to find the itinerary, or be an admin.
     *
     * @param userId the id of the user to search for
     * @return a list of all itineraries by the user
     */
    @PreAuthorize("hasRole('ADMIN') or hasPermission(#accountId, 'Account', 'read')")
    @GetMapping("users/{accountId}/itineraries")
    public ResponseEntity<List<Itinerary>> getItinerariesByUserId(
        @PathVariable("accountId") Integer accountId) {
        return ResponseEntity.ok(itineraryService.getItinerariesByUserId(accountId));
    }

    /**
     * Saves an itinerary.
     * <p>
     * You have to be authenticated to save an itinerary.
     *
     * @param request the itinerary to save
     * @return the saved itinerary
     */
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @PostMapping()
    public ResponseEntity<Itinerary> saveItinerary(@RequestBody ItineraryRequest request,
        Authentication authentication) {
        return itineraryService.saveItinerary(((Account) authentication.getPrincipal()).getId(),
            request);
    }

    /**
     * Deletes an itinerary by its id.
     * <p>
     * You have to have the 'delete' permissions to delete the itinerary, or be an admin.
     *
     * @param itineraryId the id of the itinerary to delete
     */
    @PreAuthorize("hasRole('ADMIN') or hasPermission(#itineraryId, 'Itinerary', 'delete')")
    @DeleteMapping("{itineraryId}")
    public void deleteById(@PathVariable("itineraryId") Integer itineraryId) {
        itineraryService.deleteById(itineraryId);
    }

    /**
     * Updates an itinerary by its id.
     * <p>
     * You have to have the 'write' permissions to update the itinerary, or be an admin.
     *
     * @param itineraryId the id of the itinerary to update
     */
    @PreAuthorize("hasRole('ADMIN') or hasPermission(#itineraryId, 'Itinerary', 'write')")
    @PutMapping("{itineraryId}")
    public ResponseEntity<Itinerary> updateItinerary(
        @PathVariable("itineraryId") Integer itineraryId, @RequestBody ItineraryRequest request) {
        return itineraryService.updateItinerary(itineraryId, request);
    }
}
