package com.beastlymc.triptimize.security;

import com.beastlymc.triptimize.model.Itinerary;
import com.beastlymc.triptimize.model.account.Account;
import com.beastlymc.triptimize.service.AccountService;
import com.beastlymc.triptimize.service.ItineraryService;
import jakarta.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * A permission evaluator for Triptimize.
 */
@RequiredArgsConstructor
@Component
public class TriptimizePermissionEvaluator implements PermissionEvaluator {

    /**
     * The service for {@link Itinerary} objects.
     */
    private final ItineraryService itineraryService;

    /**
     * The service for {@link Account} objects.
     */
    private final AccountService accountService;

    /**
     * Checks if the given authentication has the given permission for the given target domain
     * object.
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject,
        Object permission) {
        if (targetDomainObject instanceof Itinerary) {
            return hasPermission(authentication, ((Itinerary) targetDomainObject).getId(),
                "Itinerary", permission);
        }

        return false;
    }

    /**
     * Checks if the given authentication has the given permission for the given target domain
     * object id.
     */
    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId,
        String targetType, Object permission) {
        if (authentication == null || targetId == null || targetType == null
            || permission == null) {
            throw new IllegalArgumentException("None of the arguments can be null");
        }

        TriptimizePermissions permissions;

        try {
            permissions = TriptimizePermissions.valueOf(((String) permission).toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(
                "The permission must be a valid TriptimizePermission");
        }

        Account principalAccount = (Account) authentication.getPrincipal();

        if (principalAccount == null) {
            return false;
        }

        switch (targetType.toLowerCase()) {
            case "itinerary" -> {

                Optional<Itinerary> itineraryOptional = itineraryService.findById(
                    (Integer) targetId);

                if (!itineraryOptional.isPresent()) {
                    throw new EntityNotFoundException(
                        "Itinerary with id " + targetId + " not found");
                }

                Itinerary itinerary = itineraryOptional.get();

                if (itinerary.isPublic()) {
                    return true;
                }

                boolean isAuthor = itinerary.getAuthor().equals(principalAccount);

                switch (permissions) {
                    case READ, WRITE, DELETE -> {
                        return isAuthor;
                    }

                    default -> {
                        return false;
                    }
                }
            }
            case "account" -> {
                if (accountService.findById((Integer) targetId).isEmpty()) {
                    throw new EntityNotFoundException("Account with id " + targetId + " not found");
                }

                // TODO: Fix the permissions because all return true and does not handle each separately.
                switch (permissions) {
                    case READ, WRITE, DELETE -> {
                        return true;
                    }
                    //case "write", "delete" -> { return principalAccount.isAdmin(); }
                    default -> {
                        return false;
                    }
                }
            }

            default -> {
                return false;
            }
        }
    }

}
