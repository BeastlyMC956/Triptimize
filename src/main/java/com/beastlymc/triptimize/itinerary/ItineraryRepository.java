package com.beastlymc.triptimize.itinerary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional(readOnly = true)
public interface ItineraryRepository extends JpaRepository<Itinerary, Integer> {

}
