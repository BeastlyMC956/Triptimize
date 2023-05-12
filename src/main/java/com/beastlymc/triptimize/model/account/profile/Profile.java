package com.beastlymc.triptimize.model.account.profile;

import java.sql.Date;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "profiles")
public class Profile {

    /**
     * The id of this user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The first name of this user.
     */
    private String firstName;

    /**
     * The last name of this user.
     */
    private String lastName;

    /**
     * The date of birth of this user.
     */
    private Date dateOfBirth;

    /**
     * The travel preferences of this user.
     */
    private String travelPreferences;

    /**
     * The profile picture URL of this user.
     */
    private String profilePicture;

    /**
     * The location based information of this user.
     */
    @Embedded
    private Location location;
}
