package com.beastlymc.triptimize.controller.global;

import com.beastlymc.triptimize.model.Itinerary;
import com.beastlymc.triptimize.service.ItineraryService;
import com.beastlymc.triptimize.util.Util;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The controller for public {@link Itinerary} objects.
 */
@RequiredArgsConstructor
@PreAuthorize("permitAll()")
@RequestMapping(Util.PUBLIC_API_PATH + "itinerary")
@RestController
public class PublicItineraryController {

    /**
     * The service for {@link Itinerary} objects.
     */
    private final ItineraryService itineraryService;

    /**
     * Finds all public itineraries by a user's id.
     * <p>
     * You don't need to be authenticated to find public itineraries.
     *
     * @param userId the id of the user to search for
     * @return a list of all public itineraries by the user
     */
    @GetMapping("users/{userId}/itineraries/public")
    public ResponseEntity<List<Itinerary>> getPublicItinerariesByUserId(
        @PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(itineraryService.getPublicItinerariesByUserId(userId));
    }
}
