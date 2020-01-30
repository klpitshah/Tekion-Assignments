package repository;

import beans.Innings;
import beans.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class InningsRepo {
    Innings firstInnings;
    Innings secondInnings;

    @Autowired
    TeamRepo teamRepo;

    public void createFirstInnings(String tossBattingTeamName, String tossBowlingTeamName, int TOTAL_BALLS){
        this.firstInnings = new Innings(tossBattingTeamName, tossBowlingTeamName, TOTAL_BALLS);
    }

    public void createSecondInnings(String tossBowlingTeam, String tossBattingTeam, int chase,int TOTAL_BALLS){
        this.secondInnings = new Innings(tossBowlingTeam, tossBattingTeam, TOTAL_BALLS, chase);
    }

    public Innings getFirstInnings(){
        return firstInnings;
    }

    public Innings getSecondInnings(){
        return secondInnings;
    }

    public Team getBattingTeam(boolean chase){
        if(!chase){
            return teamRepo.getBattingTeam();
        }
        return teamRepo.getBowlingTeam();
    }


}
