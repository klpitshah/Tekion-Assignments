package repository;

import beans.Innings;
import beans.Match;
import beans.Team;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class MatchRepo {
    private Match match;

    public void createMatch(){
        this.match = new Match();
    }

    public Match getMatch() {
        return match;
    }

    public void setTeam1(Team t){
        this.match.setTeam1(t);
    }

    public void setTeam2(Team t){
        this.match.setTeam2(t);
    }

    public void setFirstInnings(Innings innings){
        match.setFirstInnings(innings);
    }

    public void setSecondInnings(Innings innings){
        match.setSecondInnings(innings);
    }

    public void setMatchWinner(Team team){
        match.setWinner(team);
        if(team == null){
            match.setWinnerName("match tied");
            return;
        }
        match.setWinnerName(team.getTeamName());
    }



    public Team getTeam1(){
        return match.getTeam1();
    }

    public Team getTeam2(){
        return match.getTeam2();
    }
}
