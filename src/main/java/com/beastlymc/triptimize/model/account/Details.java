package com.beastlymc.triptimize.model.account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <h1>Details Table</h1>
 * The details of a user.
 *
 * <p>
 * This table stores optional public information about a user. such as their first name, last name,
 * bio, date of birth, and country.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "details")
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ToString.Exclude
    @OneToOne(mappedBy = "details")
    private Profile profile;

    private String firstName;

    private String lastName;

    private String bio;

    private Date dateOfBirth;

    private String country;
}
