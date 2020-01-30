package repository;

import beans.Team;
import org.springframework.stereotype.Repository;

@Repository
public class TeamRepo {
    Team battingTeam;
    Team bowlingTeam;

    public void createBattingTeam(String a){
        battingTeam = new Team(a);
    }

    public void createBowlingTeam(String a){
        bowlingTeam = new Team(a);
    }

    public Team getBattingTeam() {
        return this.battingTeam;
    }

    public Team getBowlingTeam() {
        return this.bowlingTeam;
    }

    public void setBattingTeamScore(int r, int w){
        battingTeam.setScore(r, w);
    }

    public void setBowlingTeamScore(int r, int w){
        bowlingTeam.setScore(r, w);
    }
}
