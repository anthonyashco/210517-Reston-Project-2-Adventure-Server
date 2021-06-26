package dev.adventure.controllers;

import com.google.gson.Gson;
import dev.adventure.entities.Claim;
import dev.adventure.services.claim_services.ClaimService;
import io.javalin.http.Handler;
import dev.adventure.exceptions.ResourceNotFound;
import java.util.List;
import java.util.Set;
import com.google.gson.Gson;
import io.javalin.http.Handler;
import java.util.Set;

public class ClaimController {

    private Gson gson = new Gson();
    private ClaimService claimService;


    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    public Handler hello = (ctx) -> {System.out.println("Hello world");
    String s ="hello";
    s=gson.toJson(s);
    ctx.result(s);};

    public Handler createClaim = (ctx) -> {
        try {
            Claim claim = this.gson.fromJson(ctx.body(), Claim.class);
            int user_id = claim.getUserId();
            claim = this.claimService.registerClaim(claim);
            if (claim == null) {
                throw new ResourceNotFound("Can not create claim with the user_id : " + user_id);
            }
            String claimJSON = gson.toJson(claim);
            ctx.status(201);
            ctx.result(claimJSON);
        } catch (ResourceNotFound resourceNotFound) {
            ctx.result(resourceNotFound.getMessage());
            ctx.status(404);
        }
    };


    public Handler getAllClaims = (ctx) -> {
        try {
            List<Claim> claims = this.claimService.retriveAllClaims();
            if (claims.size() == 0) {
                throw new ResourceNotFound("There is not any claim exsist. ");
            }
            String claimJSON = this.gson.toJson(claims);
            ctx.result(claimJSON);
        } catch (ResourceNotFound resourceNotFound) {
            ctx.result(resourceNotFound.getMessage());
            ctx.status(404);
        }
    };
}