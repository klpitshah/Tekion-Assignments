package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;


public class Innings {
    @JsonIgnore
    private Player striker;
    @JsonIgnore
    private Player nonstriker;
    private String battingTeamName;
    private String bowlingTeamName;
    private boolean chase;
    private int target;
    @JsonIgnore
    private int TOTALBALLS;
    @JsonIgnore
    private static final int TOTALWICKETS = 10;
    private ArrayList FoW;


    public Innings(){}
    //(for first innings which will not have anything to chase)
    public Innings(String bat, String bowl, int total_balls){
        this.battingTeamName = bat;
        this.bowlingTeamName = bowl;
        this.chase = false;
        this.target = Integer.MAX_VALUE;
        this.TOTALBALLS = total_balls;
        this.FoW = new ArrayList<Integer>();
    }


    // for second innings, which will have the score of first team to chase
    public Innings(String bat, String bowl, int total_balls, int tgt){
        this.battingTeamName = bat;
        this.bowlingTeamName = bowl;
        this.chase = true;
        this.target = tgt;
        this.TOTALBALLS = total_balls;
        this.FoW = new ArrayList<Integer>();
    }

    public Player getStriker() {
        return striker;
    }

    public Player getNonstriker() {
        return nonstriker;
    }

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

    public ArrayList getFoW() {
        return FoW;
    }

    public void addRunsToFoW(int a){
        this.FoW.add(a);
    }

}
