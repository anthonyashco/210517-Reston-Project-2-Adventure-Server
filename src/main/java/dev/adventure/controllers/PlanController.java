package dev.adventure.controllers;

import com.google.gson.Gson;
import dev.adventure.entities.Plan;
import dev.adventure.exceptions.EntityNotFoundException;
import dev.adventure.services.PlanService;
import io.javalin.http.Handler;

import java.util.ArrayList;
import java.util.List;


public class PlanController {

    private PlanService planService = null;

    public PlanController (PlanService planService){
        this.planService = planService;
    }

    public Handler getAllPlans = ctx->{
        try {
            List<Plan> plans = new ArrayList<>();
            plans = this.planService.getAllPlans();
            Gson gson = new Gson();
            String plansJSON = gson.toJson(plans);
            ctx.result(plansJSON);
            ctx.status(200);
            ctx.contentType("application/json");
        } catch (EntityNotFoundException e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

    public Handler getPlanByID = ctx->{
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Plan plan = this.planService.getPlanByID(id);
            Gson gson = new Gson();
            String planJSON = gson.toJson(plan);
            ctx.result(planJSON);
            ctx.status(200);
        } catch (EntityNotFoundException e){
            ctx.result(e.getMessage());
            ctx.status(404);
        }
    };

}
