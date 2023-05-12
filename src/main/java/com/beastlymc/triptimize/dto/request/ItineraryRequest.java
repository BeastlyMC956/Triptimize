package com.beastlymc.triptimize.dto.request;

import java.sql.Date;
import java.util.List;

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
    private Date startDate;

    /**
     * The end date of the itinerary.
     */
    private Date endDate;

    /**
     * The activities of the itinerary.
     */
    private List<String> activities;

    /**
     * If the itinerary is public.
     */
    private boolean isPublic;
}
