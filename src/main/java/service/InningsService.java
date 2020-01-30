package service;

import beans.Innings;
//import CricketGame.Over;
import beans.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repository.InningsRepo;
import repository.TeamRepo;


@Service
public class InningsService implements InterInningsService {

    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TeamRepo teamRepo;

    @Autowired
    private InningsRepo inningsRepo;

    @Override
    public void startInnings(Innings innings) {
        int runs = 0;
        int wicketsDown = 0;
        int totalBallsPlayed = 0;
        int overRuns = 0;
        int overWickets = 0;
        int bowlerCounter = 0;
        int totalBowler = 5;
        int TOTAL_BALLS = 300;
        int TOTAL_WICKETS = 10;

        Player striker = inningsRepo.getBattingTeam(innings.isChase()).getPlayerAtIndex(0);
        Player nonstriker = inningsRepo.getBattingTeam(innings.isChase()).getPlayerAtIndex(1);


        outerloop:
        for (int i = 0; i < TOTAL_BALLS; i++) {

            totalBallsPlayed = i + 1;
            if (i % 6 == 0) {
                if (i != 0) {
                    Player temp = striker;
                    striker = nonstriker;
                    nonstriker = temp;
                }
                overRuns = 0;
                overWickets = 0;
            }
            int ballResult = playerService.BiasedRandomResult(striker);
            if (ballResult == 7) {
                wicketsDown++;
                overWickets++;
                innings.addRunsToFoW(runs);
                striker.setBatGotOut(true);
                if (wicketsDown == TOTAL_WICKETS) {
                    break outerloop;
                }
                striker = inningsRepo.getBattingTeam(innings.isChase()).getPlayerAtIndex(wicketsDown + 1);
            } else {
                striker.updateBattingScore(ballResult);
                if (ballResult % 2 != 0 && ballResult != 5) {
                    Player temp = striker;
                    striker = nonstriker;
                    nonstriker = temp;
                }
                runs += ballResult;
                overRuns += ballResult;
            }
            if(innings.isChase() && runs>innings.getTarget()){
                break;
            }
        }

        if(!innings.isChase()){
            teamRepo.setBattingTeamScore(runs, wicketsDown);
        }
        else{
            teamRepo.setBowlingTeamScore(runs, wicketsDown);
        }

        return;
    }

}

