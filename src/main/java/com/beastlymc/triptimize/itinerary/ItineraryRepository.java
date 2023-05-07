package com.beastlymc.triptimize.itinerary;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(readOnly = true)
public interface ItineraryRepository {

    /**
     * Finds all itineraries.
     *
     * @return a list of all itineraries
     */
    List<Itinerary> findAll();
    
    /**
     * Finds an itinerary by its id.
     *
     * @param id the id to search for
     * @return an Optional containing the itinerary if found, or an empty Optional otherwise
     */
    Optional<Itinerary> findById(Long id);

    /**
     * Saves an itinerary.
     *
     * @param itinerary the itinerary to save
     * @return the saved itinerary
     */
    Itinerary save(Itinerary itinerary);

    /**
     * Deletes an itinerary by its id.
     *
     * @param id the id of the itinerary to delete
     */
    void deleteById(Long id);

}
