package tekion.assignments.CricketGame;


import CricketGame.Match;
import org.springframework.web.bind.annotation.*;

@RestController
public class RouteManager {

    @RequestMapping("/")
    public String HomePage(){
        return "Welcome to the homepage :)";
    }

    @GetMapping(value="/startMatch")
    public Match startMatchWithParams(@RequestParam("team1") String team1, @RequestParam("team2") String team2){
        return new Match(team1, team2).startMatchAndGetWinner();
//        return new Match();
    }
}
