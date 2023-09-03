package com.beastlymc.triptimize.model.account;

import com.beastlymc.triptimize.model.Itinerary;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <h1>Profile Table</h1>
 * The profile of a user.
 * <p>
 * This table stores public and optional private information about a user. such as their username,
 * profile picture, and itineraries.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ToString.Exclude
    @OneToOne(mappedBy = "profile")
    private Account account;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(length = 1000)
    private String profilePicture;

    @JsonManagedReference
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Itinerary> itineraries;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id", referencedColumnName = "id")
    private Details details;
}
