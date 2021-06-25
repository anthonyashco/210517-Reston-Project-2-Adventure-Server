package dev.adventure.services;

import dev.adventure.entities.Plan;

import java.util.List;

public interface PlanService {

    Plan getPlanByID(int planID);

    List<Plan> getAllPlans();

}
