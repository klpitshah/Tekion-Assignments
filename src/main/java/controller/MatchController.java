package controller;


import model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import repository.MatchRepo;
import service.MatchService;

import java.util.List;


@RestController
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    MatchRepo matchRepo;

    @GetMapping("/startMatch/{team1}vs{team2}")
    public Match startMatchWithParams(@PathVariable("team1") String team1, @PathVariable("team2") String team2) {
        return matchService.startMatchAndGetWinner(team1, team2);
    }

    @GetMapping("/summary/won/{team1}")
    public List<Match> matchesWon(@PathVariable("team1")String team1) {
        return matchRepo.matchesWon(team1);
    }

    @GetMapping("/summary/lost/{team1}")
    public List<Match> matchesLost(@PathVariable("team1")String team1) {
        return matchRepo.matchesLost(team1);
    }

    @GetMapping("/summary/allMatches/{team1}")
    public List<Match> matches(@PathVariable("team1")String team1) {
        return matchRepo.allMatches(team1);
    }

    @GetMapping("/summary/stats/{team1}")
    public int[] teamStats(@PathVariable("team1")String team1){
        List<Match> won = matchRepo.matchesWon(team1);
        List<Match> lost = matchRepo.matchesLost(team1);
        List<Match> allMatches = matchRepo.allMatches(team1);

        return new int[]{won.size(), lost.size(), allMatches.size() - won.size()-lost.size()};
    }

}

