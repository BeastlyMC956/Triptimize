package com.beastlymc.triptimize.itinerary;

import java.util.Optional;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.beastlymc.triptimize.user.AccountRepository;

import lombok.RequiredArgsConstructor;

/**
 * Service for {@link Itinerary} objects.
 */
@RequiredArgsConstructor
@Service
public class ItineraryService {
    
    private final ItineraryRepository itineraryRepository;
    private final AccountRepository accountRepository;

    /**
     * Finds an itinerary by its id.
     *
     * @param id the id to search for
     * @return an Optional containing the itinerary if found, or an empty Optional otherwise
     */
    public ResponseEntity<Itinerary> findById(final Integer id) {
        Optional<Itinerary> itinerary = itineraryRepository.findById(id);
        // Check if the itinerary exists
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
    public ResponseEntity<Itinerary> saveItinerary(final ItineraryRequest request) {
        // Check if the owner exists
        if(accountRepository.existsById(request.getOwnerId()) == false) {
            return ResponseEntity.badRequest().build();
        }

        Itinerary itinerary = Itinerary.builder()
        .author(accountRepository.getReferenceById(request.getOwnerId()))
        .name(request.getName())
        .location(request.getLocation())
        .description(request.getDescription())
        .startDate(request.getStartDate())
        .endDate(request.getEndDate())
        .activities(request.getActivities())
        .isPublic(request.isPublic())
        .build();

        itineraryRepository.save(itinerary);

        return ResponseEntity.ok(itinerary);
    }

    /**
     * Deletes an itinerary by its id.
     *
     * @param id the id of the itinerary to delete
     */
    public ResponseEntity<Integer> deleteById(final Integer id) {
        // Check if the itinerary exists
        if(!itineraryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        itineraryRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }

    /**
     * Updates an itinerary.
     *
     * @param itinerary the itinerary to update
     * @return the updated itinerary
     */
    public ResponseEntity<Itinerary> updateItinerary(final Integer id, final ItineraryRequest request) {
        Optional<Itinerary> updatedItinerary = itineraryRepository.findById(id);

        // Check if the itinerary exists
        if (!updatedItinerary.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Check if the owner exists
        if(accountRepository.existsById(request.getOwnerId()) == false) {
            return ResponseEntity.badRequest().build();
        }

        Itinerary updated = updatedItinerary.get();
        updated.setName(request.getName());
        updated.setAuthor(accountRepository.getReferenceById(request.getOwnerId()));
        updated.setDescription(request.getDescription());
        updated.setLocation(request.getLocation());
        updated.setStartDate(request.getStartDate());
        updated.setEndDate(request.getEndDate());
        updated.setActivities(request.getActivities());
        updated.setPublic(request.isPublic());

        itineraryRepository.saveAndFlush(updated);

        return ResponseEntity.ok(updated);
    }
    
}
