package beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import service.TeamService;
//import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.ArrayList;


public class Innings {

    @Autowired
    TeamService teamService;

//    private int runs;
//    private int wicketsDown;
//    private int totalBallsPlayed;
    @JsonIgnore
    private Player striker;
    @JsonIgnore
    private Player nonstriker;
//    @JsonIgnore
//    private Team battingTeam;
//    @JsonIgnore
//    private Team bowlingTeam;
    private String battingTeamName;
    private String bowlingTeamName;
    private boolean chase;
    private int target;
    @JsonIgnore
    private int TOTALBALLS;
    @JsonIgnore
    private final int TOTALWICKETS = 10;
//    private ScoreCard sc;
    private ArrayList FoW;


    //(for first innings which will not have anything to chase)
    public Innings(String bat, String bowl, int total_balls){
//        this.battingTeam = bat;
//        this.bowlingTeam = bowl;
        this.battingTeamName = bat;
        this.bowlingTeamName = bowl;
//        this.runs = 0;
//        this.wicketsDown = 0;
//        this.totalBallsPlayed = 0;
//        this.striker = battingTeam.getPlayerAtIndex(0);
//        this.nonstriker = battingTeam.getPlayerAtIndex(1);
        this.chase = false;
        this.target = Integer.MAX_VALUE;
        this.TOTALBALLS = total_balls;
//        this.sc = new ScoreCard();
        this.FoW = new ArrayList<Integer>();
    }

    // for second innings, which will have the score of first team to chase
    public Innings(String bat, String bowl, int total_balls, int tgt){
//        this.battingTeam = bat;
//        this.bowlingTeam = bowl;
        this.battingTeamName = bat;
        this.bowlingTeamName = bowl;
//        this.runs = 0;
//        this.wicketsDown = 0;
//        this.totalBallsPlayed = 0;
//        this.striker = battingTeam.getPlayerAtIndex(0);
//        this.nonstriker = battingTeam.getPlayerAtIndex(1);
        this.chase = true;
        this.target = tgt;
        this.TOTALBALLS = total_balls;
//        this.sc = new ScoreCard();
        this.FoW = new ArrayList<Integer>();
    }

//    public int getRuns() {
//        return runs;
//    }
//
//    public int getWicketsDown() {
//        return wicketsDown;
//    }
//
//    public int getTotalBallsPlayed() {
//        return totalBallsPlayed;
//    }

    public Player getStriker() {
        return striker;
    }

    public Player getNonstriker() {
        return nonstriker;
    }

//    public Team getBattingTeam() {
//        return battingTeam;
//    }
//
//    public Team getBowlingTeam() {
//        return bowlingTeam;
//    }

    public String getBattingTeamName() {
        return battingTeamName;
    }

    public String getBowlingTeamName() {
        return bowlingTeamName;
    }

    public boolean isChase() {
        return chase;
    }

    public int getTarget() {
        return target;
    }

    public int getTOTALBALLS() {
        return TOTALBALLS;
    }

    public int getTOTALWICKETS() {
        return TOTALWICKETS;
    }

//    public ScoreCard getSc() {
//        return sc;
//    }

    public ArrayList getFoW() {
        return FoW;
    }

    public void addRunsToFoW(int a){
        this.FoW.add(a);
    }


//    public void setRuns(int runs) {
//        this.runs = runs;
//    }
//
//    public void setWicketsDown(int wicketsDown) {
//        this.wicketsDown = wicketsDown;
//    }
//
//    public void setTotalBallsPlayed(int totalBallsPlayed) {
//        this.totalBallsPlayed = totalBallsPlayed;
//    }
}
