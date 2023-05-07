package com.beastlymc.triptimize.itinerary;

import com.beastlymc.triptimize.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryRequest {
    
    /**
     * The name of the itinerary.
     */
    private User owner;

    /**
     * The name of the itinerary.
     */
    private String name;

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
