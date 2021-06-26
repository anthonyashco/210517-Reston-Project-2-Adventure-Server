package dev.adventure.app;

import dev.adventure.controllers.PlanController;
import dev.adventure.daos.PlanDao;
import dev.adventure.daos.PlanDaoImp;
import dev.adventure.services.PlanService;
import dev.adventure.services.PlanServiceImp;
import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {

        Javalin app = Javalin.create(config ->{
           config.enableCorsForAllOrigins();
           config.enableDevLogging();
        });

        PlanDao planDao = new PlanDaoImp();
        PlanService planService = new PlanServiceImp(planDao);
        PlanController planController = new PlanController(planService);

        app.get("/plans", planController.getAllPlans);

        app.get("plans/:id", planController.getPlanByID);

        app.start();

    }

    public static String hello(){
        return "Hello world";
    }
}
