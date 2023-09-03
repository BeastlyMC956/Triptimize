package com.beastlymc.triptimize.model.account;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <h1>Account Table</h1>
 * The account of a user.
 * <p>
 * This table stores private information about a user. such as their email, password, role, and
 * profile.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
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
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * The password of this user.
     */
    @Column(nullable = false)
    private String password;

    /**
     * The role of this user.
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * The date when this user last logged in.
     */
    private Timestamp lastLoginDate;

    /**
     * The date when this user account was created.
     */
    @Column(updatable = false)
    private Timestamp accountCreationDate;

    /**
     * The profile of this user.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    private Profile profile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "verification_id", referencedColumnName = "id")
    private Verification verification;

    @ColumnDefault("false")
    private boolean locked;

    @ColumnDefault("false")
    private boolean verified;

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
     * Returns the email used to authenticate the user.
     *
     * <p>In this case, it returns the user's email address.</p>
     *
     * @return the username used to authenticate the user
     */
    @Override
    public String getUsername() {
        return profile.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isVerified();
    }
}
