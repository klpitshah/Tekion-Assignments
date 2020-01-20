package tekion.assignments.CricketGame;


import CricketGame.Match;
import org.springframework.web.bind.annotation.*;

@RestController
public class RouteManager {

    @RequestMapping("/")
    public String HomePage(){
        return "Welcome to the homepage :)";
    }

    @RequestMapping("/startMatch")
    public String startMatch(){
        return new Match("India", "Pakistan").startMatchAndGetWinner();
    }

    @RequestMapping(value="/startMatch", method = RequestMethod.GET)
    public String startMatchWithParams(@RequestParam("team1") String team1, @RequestParam("team2") String team2){
        return new Match(team1, team2).startMatchAndGetWinner();
    }
}
