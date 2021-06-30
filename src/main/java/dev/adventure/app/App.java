package dev.adventure.app;

import dev.adventure.controllers.ClaimController;
import dev.adventure.controllers.PlanController;
import dev.adventure.controllers.UserController;
import dev.adventure.daos.UserDao;
import dev.adventure.daos.UserDaoImpl;
import dev.adventure.daos.claim_daos.ClaimDao;
import dev.adventure.daos.claim_daos.ClaimDaoPostgres;
import dev.adventure.daos.PlanDao;
import dev.adventure.daos.PlanDaoImp;
import dev.adventure.services.UserService;
import dev.adventure.services.UserServiceImp;
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
        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImp(userDao);
        UserController userController = new UserController(userService);


        app.get("/hello",claimController.hello);

        app.get("/claims", claimController.getAllClaims);

        app.get("/claims/:user_id", claimController.getAllClaimsByUserId);

        app.post("/claims",claimController.createClaim);

        app.get("/plans", planController.getAllPlans);

        app.get("/plans/:id", planController.getPlanByID);

        app.post("/login", userController.login);

        app.get("/users", userController.getAllUsers);

        app.get("/users/:id", userController.getUserById);

        app.start();

    };
};
