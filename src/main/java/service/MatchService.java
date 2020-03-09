package service;

import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.MatchRepo;


@Service
public class MatchService{



    @Autowired
    InningsService inningsService;

    @Autowired
    MatchRepo matchRepo;


    public Match startMatchAndGetWinner(String teamName1, String teamName2) {

        String tossWinner = performTossAndGetWinner(teamName1, teamName2);

        Match match = new Match();

        Team tossBattingTeam = new Team(tossWinner);
        Team tossBowlingTeam = new Team(getOtherTeam(teamName1, teamName2, tossWinner));


        match.setTeam1(tossBattingTeam);
        match.setTeam2(tossBowlingTeam);


        //initializing constants

        int TOTAL_BALLS = 300;

        Innings firstInnings = new Innings(tossBattingTeam.getTeamName(), tossBowlingTeam.getTeamName(), TOTAL_BALLS);
        int [] score = inningsService.startInnings(firstInnings, tossBattingTeam);
        tossBattingTeam.setScore(score[0], score[1]);


        match.setFirstInnings(firstInnings);


        Innings secondInnings = new Innings(tossBowlingTeam.getTeamName(), tossBattingTeam.getTeamName(), tossBattingTeam.getScore(), TOTAL_BALLS);
        score = inningsService.startInnings(secondInnings, tossBowlingTeam);
        tossBowlingTeam.setScore(score[0], score[1]);

        match.setSecondInnings(secondInnings);

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


        match.setWinner(winner);
        match.setWinnerName(winner.getTeamName());


        matchRepo.save(match);


        return match;

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
