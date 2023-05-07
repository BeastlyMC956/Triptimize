package com.beastlymc.triptimize.itinerary;

import java.util.Optional;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

/**
 * Service for {@link Itinerary} objects.
 */
@AllArgsConstructor
@Service
public class ItineraryService {
    
    private final ItineraryRepository itineraryRepository;

    /**
     * Finds an itinerary by its id.
     *
     * @param id the id to search for
     * @return an Optional containing the itinerary if found, or an empty Optional otherwise
     */
    public ResponseEntity<Itinerary> findById(Long id) {
        Optional<Itinerary> itinerary = itineraryRepository.findById(id);
        if (itinerary.isPresent()) {
            return ResponseEntity.ok(itinerary.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Finds all itineraries.
     *
     * @return a list of all itineraries
     */
    public List<Itinerary> findAll() {
        return itineraryRepository.findAll();
    }

    /**
     * Saves an itinerary.
     *
     * @param itinerary the itinerary to save
     * @return the saved itinerary
     */
    public ResponseEntity<Itinerary> save(ItineraryRequest request) {
        Itinerary itinerary = new Itinerary();

        itinerary.setName(request.getName());
        itinerary.setDescription(request.getDescription());
        itinerary.setStartDate(request.getStartDate());
        itinerary.setEndDate(request.getEndDate());
        itinerary.setActivities(request.getActivities());
        itinerary.setPublic(true);

        return ResponseEntity.ok(itineraryRepository.save(itinerary));
    }

    /**
     * Deletes an itinerary by its id.
     *
     * @param id the id of the itinerary to delete
     */
    public ResponseEntity<Long> deleteById(Long id) {
        itineraryRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }

    
}
