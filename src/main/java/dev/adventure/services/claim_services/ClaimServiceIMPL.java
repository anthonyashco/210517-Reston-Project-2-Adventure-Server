package dev.adventure.services.claim_services;

import dev.adventure.daos.claim_daos.ClaimDao;
import dev.adventure.daos.claim_daos.ClaimDaoPostgres;
import dev.adventure.entities.Claim;

import java.util.ArrayList;
import java.util.List;

public class ClaimServiceIMPL implements ClaimService {
    ClaimDao claimDao;

    public ClaimServiceIMPL(ClaimDao claimDao) {
        this.claimDao = claimDao;
    }

    @Override
    public Claim registerClaim(Claim claim) {
        return this.claimDao.createClaim(claim);
    }

    @Override
    public ArrayList<Claim> retriveAllClaims() {
        return this.claimDao.getAllClaims();
    }

    @Override
    public ArrayList<Claim> getAllClaimsByUserId(int user_id){
        return this.claimDao.getAllClaimsByUserId(user_id);
    }

}

