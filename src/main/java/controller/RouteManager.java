package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.MatchService;

@RestController
public class RouteManager {

    @RequestMapping("/")
    public String HomePage(){
        return "Welcome to the homepage :)";
    }

}


