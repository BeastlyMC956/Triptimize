package com.beastlymc.triptimize.user.profile;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The location of a user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
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
