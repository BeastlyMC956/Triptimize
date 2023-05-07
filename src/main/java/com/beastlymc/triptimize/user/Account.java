package com.beastlymc.triptimize.user;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.beastlymc.triptimize.itinerary.Itinerary;
import com.beastlymc.triptimize.user.profile.Profile;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "accounts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Account implements UserDetails {

    /**
     * The unique identifier of this user account.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The email address of this user.
     */
    private String email;

    /**
     * The username or display name of this user.
     */
    private String username;

    /**
     * The password of this user.
     */
    private String password;

    /**
     * The date when this user account was created.
     */
    private Date accountCreationDate;

    /**
     * The date when this user last logged in.
     */
    private Date lastLoginDate;

    /**
     * The role of this user.
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * The profile of this user.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    /**
     * The itineraries created by this user.
     */
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @Column(nullable = true)
    private Set<Itinerary> authoredItineraries;

    /**
     * The itineraries collaborated on by this user.
     */
    @ManyToMany(mappedBy = "collaborators", cascade = CascadeType.ALL)
    @Column(nullable = true)
    private Set<Itinerary> collaboratedItineraries;

    /**
     * Returns the authorities granted to the user.
     *
     * <p>In this case, it returns a list with a single element, a SimpleGrantedAuthority
     * with the name of the user's role.</p>
     *
     * @return the authorities granted to the user
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * Returns the username used to authenticate the user.
     *
     * <p>In this case, it returns the user's email address.</p>
     *
     * @return the username used to authenticate the user
     */
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
