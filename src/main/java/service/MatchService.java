package service;

import beans.Innings;
import beans.Match;
import beans.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repository.InningsRepo;
import repository.MatchRepo;
import repository.TeamRepo;


@Service
public class MatchService implements InterMatchService {


    @Autowired
    TeamRepo teamRepo;

    @Autowired
    InningsService inningsService;

    @Autowired
    MatchRepo matchRepo;

    @Autowired
    InningsRepo inningsRepo;

    @Override
    public Match startMatchAndGetWinner(String teamName1, String teamName2) {

        String tossWinner = performTossAndGetWinner(teamName1, teamName2);
        // assumed that toss winner will always choose to bat first


        matchRepo.createMatch();
        teamRepo.createBattingTeam(tossWinner);
        teamRepo.createBowlingTeam(getOtherTeam(teamName1, teamName2, tossWinner));


        Team tossBattingTeam = teamRepo.getBattingTeam();
        Team tossBowlingTeam = teamRepo.getBowlingTeam();

        matchRepo.setTeam1(tossBattingTeam);
        matchRepo.setTeam2(tossBowlingTeam);


        //initializing constants

        int TOTAL_BALLS = 300;
        int TOTAL_WICKETS = 10;

        inningsRepo.createFirstInnings(tossBattingTeam.getTeamName(), tossBowlingTeam.getTeamName(), TOTAL_BALLS);
        Innings firstInnings = inningsRepo.getFirstInnings();
        inningsService.startInnings(firstInnings);
        matchRepo.setFirstInnings(firstInnings);



        inningsRepo.createSecondInnings(tossBowlingTeam.getTeamName(), tossBattingTeam.getTeamName(), tossBattingTeam.getScore() ,TOTAL_BALLS);
        Innings secondInnings = inningsRepo.getSecondInnings();
        inningsService.startInnings(secondInnings);
        matchRepo.setSecondInnings(secondInnings);


        Team winner;

        if(tossBattingTeam.getScore() > tossBowlingTeam.getScore()){
            winner = tossBattingTeam;
        }
        else if(tossBowlingTeam.getScore() > tossBattingTeam.getScore()){
            winner = tossBowlingTeam;
        }
        else{
            winner = null;
        }

        matchRepo.setMatchWinner(winner);

        return matchRepo.getMatch();

    }

    private String getOtherTeam(String teamName1, String teamName2, String tossWinner) {
        if(tossWinner.equals(teamName1)){
            return teamName2;
        }
        return teamName1;
    }

    private String performTossAndGetWinner(String a, String b) {
        if(Math.random() < 0.5){
            return a;
        }
        else{
            return b;
        }
    }
}
