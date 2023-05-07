package com.beastlymc.triptimize.itinerary;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beastlymc.triptimize.util.Constants;

import lombok.RequiredArgsConstructor;

/**
 * A controller class for itinerary-related endpoints in the API.
 */
@RequiredArgsConstructor
@RequestMapping(Constants.DEFAULT_API_PATH + "/itinerary")
@RestController
public class ItineraryController {

    private final ItineraryService itineraryService;

    /**
     * Finds all itineraries.
     *
     * @return a list of all itineraries
     */
    @GetMapping("/findAll")
    public List<Itinerary> findAll() {
        return itineraryService.findAll();
    }

    /**
     * Finds an itinerary by its id.
     *
     * @param id the id to search for
     * @return an Optional containing the itinerary if found, or an empty Optional otherwise
     */
    @GetMapping("/findById/{id}")
    public ResponseEntity<Itinerary> findById(@PathVariable("id") Long id) {
        return itineraryService.findById(id);
    }

    /**
     * Saves an itinerary.
     *
     * @param itinerary the itinerary to save
     * @return the saved itinerary
     */
    @PostMapping("/save")
    public ResponseEntity<Itinerary> save(@RequestBody ItineraryRequest request) {
        return itineraryService.save(request);
    }

    /**
     * Deletes an itinerary by its id.
     *
     * @param id the id of the itinerary to delete
     */
    @PostMapping("/delete/{id}")
    public void deleteById(Long id) {
        itineraryService.deleteById(id);
    }
}
