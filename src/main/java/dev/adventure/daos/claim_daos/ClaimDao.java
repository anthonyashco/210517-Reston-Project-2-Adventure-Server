package dev.adventure.daos.claim_daos;

import dev.adventure.entities.Claim;

import java.util.ArrayList;
import java.util.List;

public interface ClaimDao {

    public Claim createClaim(Claim claim);

    public ArrayList<Claim> getAllClaims();

}
