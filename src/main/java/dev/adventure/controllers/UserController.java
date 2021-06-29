package dev.adventure.controllers;

import com.google.gson.Gson;
import dev.adventure.entities.User;
import dev.adventure.exceptions.EntityNotFoundException;
import dev.adventure.services.UserService;
import io.javalin.http.Handler;

import java.util.List;
import java.util.Map;


public class UserController {
    private UserService userService;
    static private Gson gson = new Gson();

    public UserController(UserService userService){
        this.userService = userService;
    }

    public Handler login = ctx ->{
        String body = ctx.body();

        Map<String, String> credentials = gson.fromJson(body, Map.class);
        try {
         int id = this.userService.login(credentials.get("username"), credentials.get("password"));
         ctx.result("{\"id\":"+id+"}");
         ctx.status(200);
        }catch (EntityNotFoundException e){
            e.printStackTrace();
            ctx.result("{\"id\":"+(-1)+"}");
            ctx.status(200);
        }
    };

    public Handler getUserById = ctx ->{
        try{
            int id = Integer.parseInt(ctx.pathParam("id"));
            User user = this.userService.getUserById(id);
            String userJSON = gson.toJson(user);
            ctx.result(userJSON);
            ctx.status(200);
        } catch(EntityNotFoundException e){
            e.printStackTrace();
        }
    };

    public Handler getAllUsers = ctx ->{
        List<User> userList = this.userService.getAllUsers();
        ctx.result(gson.toJson(userList));
        ctx.status(200);
    };

    public Handler createUser = ctx->{
        String body = ctx.body();
        User user = gson.fromJson(body, User.class);
        userService.createNewUser(user);
        ctx.result(gson.toJson(user));
        ctx.status(201);
    };


}
