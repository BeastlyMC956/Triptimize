package com.beastlymc.triptimize.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beastlymc.triptimize.model.Itinerary;

/**
 * A repository for {@link Itinerary} objects.
 */
@Repository
@Transactional(readOnly = true)
public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {

    /**
     * Finds all itineraries by the author's id.
     *
     * @param authorId the id of the author
     * @return a list of all itineraries by the author
     */
    List<Itinerary> findByAuthorId(Integer authorId);
}
