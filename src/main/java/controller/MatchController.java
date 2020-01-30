package controller;


import beans.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import service.MatchService;


@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/startMatch/{team1}vs{team2}")
    public Match startMatchWithParams(@PathVariable("team1") String team1, @PathVariable("team2") String team2){
        return matchService.startMatchAndGetWinner(team1, team2);
    }

}
