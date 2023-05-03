package com.beastlymc.triptimize.auth;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A request object for registering a new user in the system.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private Date dateOfBirth;
    private String country;
    private String preferredCurrency;
    private String travelPreferences;
    private String profilePicture;
}
