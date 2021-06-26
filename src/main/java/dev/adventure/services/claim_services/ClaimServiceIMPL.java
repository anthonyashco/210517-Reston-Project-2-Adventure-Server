package dev.adventure.services.claim_services;

import dev.adventure.daos.claim_daos.ClaimDao;
import dev.adventure.daos.claim_daos.ClaimDaoPostgres;
import dev.adventure.entities.Claim;

import java.util.List;

public class ClaimServiceIMPL implements ClaimService {
    ClaimDao claimDao= new ClaimDaoPostgres();

    public ClaimServiceIMPL(ClaimDao claimDao) {
        this.claimDao = claimDao;
    }

    @Override
    public Claim registerClaim(Claim claim) {
        return this.claimDao.createClaim(claim);
    }

    @Override
    public List<Claim> retriveAllClaims() {
        return this.claimDao.getAllClaims();
    }
}
