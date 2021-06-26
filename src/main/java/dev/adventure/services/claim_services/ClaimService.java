package dev.adventure.services.claim_services;

import dev.adventure.entities.Claim;

import java.util.List;

public interface ClaimService {

    public Claim registerClaim(Claim claim);

    public List<Claim> retriveAllClaims();
}
