package dev.adventure.services.claim_services;

import dev.adventure.entities.Claim;

import java.util.ArrayList;
import java.util.List;

public interface ClaimService {

    public Claim registerClaim(Claim claim);

    public ArrayList<Claim> retriveAllClaims();
    public ArrayList<Claim> getAllClaimsByUserId(int user_id);
}
