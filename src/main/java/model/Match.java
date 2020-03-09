package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(collection = "Match")
public class Match {

    @Id
    private String Id;

    private String winnerName;

    private Team team1;
    private Team team2;
    private Innings firstInnings;
    private Innings secondInnings;
    @JsonIgnore
    private Team winner;
    private Date time;


    //constructor
    public Match(){
        this.time = new Date();
        this.Id = time.toString();
    }


    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public Innings getFirstInnings() {
        return firstInnings;
    }

    public Innings getSecondInnings() {
        return secondInnings;
    }

    public Team getWinner() {
        return winner;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setFirstInnings(Innings firstInnings) {
        this.firstInnings = firstInnings;
    }

    public void setSecondInnings(Innings secondInnings) {
        this.secondInnings = secondInnings;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }
}
