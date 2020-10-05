package service;

import model.Innings;
import model.Player;
import model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InningsService{

    @Autowired
    private PlayerService playerService;

    public int[] startInnings(Innings innings, Team battingTeam) {
        int runs = 0;
        int wicketsDown = 0;
        int TOTAL_BALLS = 300;
        int TOTAL_WICKETS = 10;



        Player striker = battingTeam.getPlayerAtIndex(0);
        Player nonstriker = battingTeam.getPlayerAtIndex(1);


        outerloop:
        for (int i = 0; i < TOTAL_BALLS; i++) {

            if (i % 6 == 0) {
                if (i != 0) {
                    Player temp = striker;
                    striker = nonstriker;
                    nonstriker = temp;
                }
            }
            int ballResult = playerService.BiasedRandomResult(striker);
            if (ballResult == 7) {
                wicketsDown++;
                innings.addRunsToFoW(runs);
                striker.setBatGotOut(true);
                if (wicketsDown == TOTAL_WICKETS) {
                    break outerloop;
                }
                striker = battingTeam.getPlayerAtIndex(wicketsDown + 1);
            } else {
                striker.updateBattingScore(ballResult);
                if (ballResult % 2 != 0 && ballResult != 5) {
                    Player temp = striker;
                    striker = nonstriker;
                    nonstriker = temp;
                }
                runs += ballResult;
            }
            if(innings.isChase() && runs>innings.getTarget()){
                break;
            }
        }

        return new int[]{runs, wicketsDown};
    }
    private void printWinner(int teamNo) {
        if(teamNo == 1) System.out.println("Team A is winner");
        else System.out.println("Team B is winner");
    }
}

