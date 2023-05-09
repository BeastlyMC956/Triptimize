package com.beastlymc.triptimize.security;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.beastlymc.triptimize.itinerary.Itinerary;
import com.beastlymc.triptimize.itinerary.ItineraryService;
import com.beastlymc.triptimize.user.Account;

import lombok.RequiredArgsConstructor;

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
     * Checks if the given authentication has the given permission for the given target domain object.
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if(targetDomainObject instanceof Itinerary) {
            return hasPermission(authentication, ((Itinerary) targetDomainObject).getId(), "Itinerary", permission);
        }

        return false;
    }

    /**
     * Checks if the given authentication has the given permission for the given target domain object id.
     */
    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
            Object permission) {
        
        if ("Itinerary".equalsIgnoreCase(targetType)) {
            Account principalAccount = (Account) authentication.getPrincipal();

            Optional<Itinerary> itineraryOptional = itineraryService.findById((Integer) targetId);

            if(!itineraryOptional.isPresent()) {
                return false;
            }

            Itinerary itinerary = itineraryOptional.get();

            if(itinerary.isPublic()) {
                return true;
            }

            final boolean isAuthor = itinerary.getAuthor().equals(principalAccount);
            final boolean isCollaborator = itinerary.getCollaborators().contains(principalAccount);

            String permissionStr = (String) permission;

            switch (permissionStr.toLowerCase()) {
                case "read" -> { return isAuthor || isCollaborator; }
                case "write", "delete" -> { return isAuthor; }
                default -> { return false; }
            }
        }

        
        return false;
    }
    
}
