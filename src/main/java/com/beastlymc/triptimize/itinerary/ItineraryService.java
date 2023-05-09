package com.beastlymc.triptimize.itinerary;

import java.util.Optional;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.beastlymc.triptimize.user.Account;

import lombok.RequiredArgsConstructor;

/**
 * Service for {@link Itinerary} objects.
 */
@RequiredArgsConstructor
@Transactional
@Service
public class ItineraryService {
    
    private final ItineraryRepository itineraryRepository;

    /**
     * Finds an itinerary by its id.
     *
     * @param id the id to search for
     * @return an Optional containing the itinerary if found, or an empty Optional otherwise
     */
    public Optional<Itinerary> findById(final Integer id) {
        Optional<Itinerary> itinerary = itineraryRepository.findById(id);
        
        if(itinerary.isPresent()) {
            return itinerary;
        } else {
            return Optional.empty();
        }
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
    public ResponseEntity<Itinerary> saveItinerary(final Account author, final ItineraryRequest request) {
        Itinerary itinerary = Itinerary.builder()
        .author(author)
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
    public ResponseEntity<Integer> deleteById(Integer id) {
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
        Optional<Itinerary> itinerary = itineraryRepository.findById(id);

        // Check if the itinerary exists
        if (!itinerary.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Itinerary updatedItinerary = itinerary.get();

        updatedItinerary.setName(request.getName());
        updatedItinerary.setAuthor(itinerary.get().getAuthor());
        updatedItinerary.setDescription(request.getDescription());
        updatedItinerary.setLocation(request.getLocation());
        updatedItinerary.setStartDate(request.getStartDate());
        updatedItinerary.setEndDate(request.getEndDate());
        updatedItinerary.setActivities(request.getActivities());
        updatedItinerary.setPublic(request.isPublic());

        itineraryRepository.saveAndFlush(updatedItinerary);

        return ResponseEntity.ok(updatedItinerary);
    }
    
}
