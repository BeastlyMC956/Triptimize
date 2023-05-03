package com.beastlymc.triptimize.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * A user account in the database.
 * <p>All users exists in the table 'users'</p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements UserDetails {

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
     * The first name of this user.
     */
    private String firstName;

    /**
     * The last name of this user.
     */
    private String lastName;

    /**
     * The password of this user.
     */
    private String password;

    /**
     * The date of birth of this user.
     */
    private Date dateOfBirth;

    /**
     * The country of this user.
     */
    private String country;

    /**
     * The preferred currency of this user.
     */
    private String preferredCurrency;

    /**
     * The travel preferences of this user.
     */
    private String travelPreferences;

    /**
     * The profile picture URL of this user.
     */
    private String profilePicture;

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
