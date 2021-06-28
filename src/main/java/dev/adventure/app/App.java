package dev.adventure.app;

import dev.adventure.controllers.ClaimController;
import dev.adventure.controllers.PlanController;
import dev.adventure.daos.claim_daos.ClaimDao;
import dev.adventure.daos.claim_daos.ClaimDaoPostgres;
import dev.adventure.daos.PlanDao;
import dev.adventure.daos.PlanDaoImp;
import dev.adventure.services.claim_services.ClaimService;
import dev.adventure.services.claim_services.ClaimServiceIMPL;
import dev.adventure.services.PlanService;
import dev.adventure.services.PlanServiceImp;
import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.enableCorsForAllOrigins();
            config.enableDevLogging();
        });

        PlanDao planDao = new PlanDaoImp();
        PlanService planService = new PlanServiceImp(planDao);
        PlanController planController = new PlanController(planService);
        ClaimDao claimDao = new ClaimDaoPostgres();
        ClaimService claimService = new ClaimServiceIMPL(claimDao);
        ClaimController claimController = new ClaimController(claimService);

        app.get("/hello",claimController.hello);

        app.get("/claims", claimController.getAllClaims);

        app.post("/claims",claimController.createClaim);

        app.get("/plans", planController.getAllPlans);

        app.get("/plans/:id", planController.getPlanByID);

        app.start();

    };
};
