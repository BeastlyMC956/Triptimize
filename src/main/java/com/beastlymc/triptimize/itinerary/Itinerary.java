package com.beastlymc.triptimize.itinerary;

import com.beastlymc.triptimize.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * An itinerary in the database.
 * <p>All itineraries exists in the table 'itinerary'</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "itinerary")
public class Itinerary {
    
    /**
     * The id of the itinerary.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the itinerary.
     */
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    /**
     * The name of the itinerary.
     */
    private String name;

    /**
     * The location of the itinerary.
     */
    private String location;

    /**
     * The description of the itinerary.
     */
    private String description;

    /**
     * The start date of the itinerary.
     */
    private String startDate;

    /**
     * The end date of the itinerary.
     */
    private String endDate;

    /**
     * The activities of the itinerary.
     */
    private String activities;

    /**
     * If the itinerary is public.
     */
    private boolean isPublic;
    
}
