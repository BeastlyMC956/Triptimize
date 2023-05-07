package com.beastlymc.triptimize.user.profile;

import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;

/**
 * The location of a user.
 */
@Data
@Builder
@Embeddable
public class Location {

    /**
     * The country of this user.
     */
    private String country;

    /**
     * The preferred currency of this user.
     */
    private String preferredCurrency;
}
