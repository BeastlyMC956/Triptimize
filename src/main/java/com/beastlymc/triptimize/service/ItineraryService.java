package com.beastlymc.triptimize.service;

import com.beastlymc.triptimize.dto.request.ItineraryRequest;
import com.beastlymc.triptimize.model.Itinerary;
import com.beastlymc.triptimize.model.account.Account;
import com.beastlymc.triptimize.repository.AccountRepository;
import com.beastlymc.triptimize.repository.ItineraryRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for {@link Itinerary} objects.
 */
@RequiredArgsConstructor
@Transactional
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
    public Optional<Itinerary> findById(final Integer id) {
        return itineraryRepository.findById(id);
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
     * Finds all itineraries by a user's id.
     *
     * @param userId the id of the user to search for
     * @return a list of all itineraries by the user
     */
    public List<Itinerary> getItinerariesByUserId(Integer userId) {
        return itineraryRepository.findByAuthorId(userId);
    }

    /**
     * Finds all public itineraries by a user's id.
     *
     * @param userId the id of the user to search for
     * @return a list of all public itineraries by the user
     */
    public List<Itinerary> getPublicItinerariesByUserId(Integer userId) {
        List<Itinerary> itineraries = getItinerariesByUserId(userId);
        System.out.println(itineraries);
        return itineraries.stream().filter(itinerary -> itinerary.isPublic())
            .collect(Collectors.toList());
    }

    /**
     * Saves an itinerary.
     *
     * @param accountId the itinerary to save
     * @param request   the itinerary to save
     * @return the saved itinerary
     */
    public ResponseEntity<Itinerary> saveItinerary(final Integer accountId,
        final ItineraryRequest request) {
        Account author = accountRepository.findById(accountId)
            .orElseThrow(() -> new RuntimeException("Account not found with id " + accountId));
        Itinerary itinerary = Itinerary.builder()
            .author(author.getProfile())
            .name(request.getName())
            .location(request.getLocation())
            .description(request.getDescription())
            .startDate(request.getStartDate())
            .endDate(request.getEndDate())
            .activities(request.getActivities())
            .isPublic(request.isPublic())
            .build();

        author.getProfile().getItineraries().add(itinerary);

        accountRepository.save(author);

        return ResponseEntity.ok(itinerary);
    }

    /**
     * Deletes an itinerary by its id.
     *
     * @param id the id of the itinerary to delete
     */
    public ResponseEntity<Integer> deleteById(Integer id) {
        // Check if the itinerary exists
        if (!itineraryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        itineraryRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }

    /**
     * Updates an itinerary.
     *
     * @param id      the id of the itinerary to update
     * @param request the itinerary to update
     * @return the updated itinerary
     */
    public ResponseEntity<Itinerary> updateItinerary(final Integer id,
        final ItineraryRequest request) {
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
