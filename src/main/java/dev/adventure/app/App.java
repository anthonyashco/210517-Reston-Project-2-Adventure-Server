package dev.adventure.app;

import dev.adventure.controllers.ClaimController;
import dev.adventure.daos.claim_daos.ClaimDao;
import dev.adventure.daos.claim_daos.ClaimDaoPostgres;
import dev.adventure.services.claim_services.ClaimService;
import dev.adventure.services.claim_services.ClaimServiceIMPL;
import io.javalin.Javalin;

public class App {
    public static String hello() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.enableDevLogging();
        });

        ClaimDao claimDao = new ClaimDaoPostgres();
        ClaimService claimService = new ClaimServiceIMPL(claimDao);
        ClaimController claimController = new ClaimController(claimService);

        app.get("/hello",claimController.hello );

        // get /books
        app.get("/claims", claimController.getAllClaims);

        // post /books
        app.post("/claim",claimController.createClaim);


        app.start(); // defaults to port 7000
    };
}



