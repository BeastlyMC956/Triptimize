package com.beastlymc.triptimize.itinerary;

import java.util.List;
import java.util.Optional;

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

import com.beastlymc.triptimize.user.Account;
import com.beastlymc.triptimize.util.Util;

import lombok.RequiredArgsConstructor;

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
     * 
     * Only avaliable to admins.
     *
     * @return a list of all itineraries
     */
    @GetMapping("/find/all")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Itinerary> findAll() {
        return itineraryService.findAll();
    }

    /**
     * Finds an itinerary by its id.
     * 
     * You have to have 'read' permissions to find the itinerary, 
     * or be an admin.
     *
     * @param id the id of the itinerary to find
     * @return the itinerary with the given id
     */
    @PreAuthorize("hasRole('ADMIN') or hasPermission(#id, 'Itinerary', 'read')")
    @GetMapping("/find/{id}")
    public ResponseEntity<Itinerary> findById(@PathVariable("id") Integer id) {
        Optional<Itinerary> itineraryOptional = itineraryService.findById(id);

        if (itineraryOptional.isPresent()) {
            return ResponseEntity.ok(itineraryOptional.get());
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * Saves an itinerary.
     * 
     * You have to be authenticated to save an itinerary.
     *
     * @param itinerary the itinerary to save
     * @return the saved itinerary
     */
    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Itinerary> saveItinerary(@RequestBody ItineraryRequest request, Authentication authentication) {
        return itineraryService.saveItinerary((Account) authentication.getPrincipal(), request);
    }

    /**
     * Deletes an itinerary by its id.
     * 
     * You have to have the 'delete' permissions to delete the itinerary, 
     * or be an admin.
     *
     * @param id the id of the itinerary to delete
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasPermission(#id, 'Itinerary', 'delete')")
    public void deleteById(@PathVariable("id") Integer id) {
        itineraryService.deleteById(id);
    }

    /**
     * Updates an itinerary by its id.
     * 
     * You have to have the 'write' permissions to update the itinerary,
     * or be an admin.
     *
     * @param id the id of the itinerary to update
     */
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasPermission(#id, 'Itinerary', 'write')")
    public ResponseEntity<Itinerary> updateItinerary(@PathVariable("id") Integer id, @RequestBody ItineraryRequest request) {
        return itineraryService.updateItinerary(id, request);
    }
}
