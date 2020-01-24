package CricketGame;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;


public class Innings {

    // Instance variables

    private int runs;
    private int wickets_down;
    private int total_balls_played;
    @JsonIgnore
    private Player striker;
    @JsonIgnore
    private Player nonstriker;
    @JsonIgnore
    private Team battingTeam;
    @JsonIgnore
    private Team bowlingTeam;
    private String battingTeamName;
    private String bowlingTeamName;
    private boolean chase;
    private int target;
    @JsonIgnore
    private int TOTAL_BALLS;
    @JsonIgnore
    private final int TOTAL_WICKETS = 10;
    private ScoreCard sc;
    private ArrayList FoW;



    // Getter functions

    public int getRuns() {
        return runs;
    }
    public int getWickets_down() {
        return wickets_down;
    }
    public int getTotal_balls_played() {
        return total_balls_played;
    }
    public Player getStriker() {
        return striker;
    }
    public Player getNonstriker() {
        return nonstriker;
    }
    public Team getBattingTeam() {
        return battingTeam;
    }
    public Team getBowlingTeam() {
        return bowlingTeam;
    }
    public boolean isChase() {
        return chase;
    }
    public int getTarget() {
        return target;
    }
    public ArrayList getFoW() {
        return FoW;
    }
    public ScoreCard getSc() {
        return sc;
    }
    public String getBattingTeamName() {
        return battingTeamName;
    }
    public String getBowlingTeamName() {
        return bowlingTeamName;
    }



    // Constructor/s

    //(for first innings which will not have anything to chase)
    public Innings(Team bat, Team bowl, int total_balls){
        this.battingTeam = bat;
        this.bowlingTeam = bowl;
        this.battingTeamName = battingTeam.getTeam_name();
        this.bowlingTeamName = bowlingTeam.getTeam_name();
        this.runs = 0;
        this.wickets_down = 0;
        this.total_balls_played = 0;
        this.striker = battingTeam.getPlayerAtIndex(0);
        this.nonstriker = battingTeam.getPlayerAtIndex(1);
        this.chase = false;
        this.target = Integer.MAX_VALUE;
        this.TOTAL_BALLS = total_balls;
        this.sc = new ScoreCard();
        this.FoW = new ArrayList<Integer>();
    }
    // for second innings, which will have the score of first team to chase
    public Innings(Team bat, Team bowl, int total_balls, int tgt){
        this.battingTeam = bat;
        this.bowlingTeam = bowl;
        this.battingTeamName = battingTeam.getTeam_name();
        this.bowlingTeamName = bowlingTeam.getTeam_name();
        this.runs = 0;
        this.wickets_down = 0;
        this.total_balls_played = 0;
        this.striker = battingTeam.getPlayerAtIndex(0);
        this.nonstriker = battingTeam.getPlayerAtIndex(1);
        this.chase = true;
        this.target = tgt;
        this.TOTAL_BALLS = total_balls;
        this.sc = new ScoreCard();
        this.FoW = new ArrayList<Integer>();
    }



    // helper function which starts the match
    public String startInnings(){
        runs = 0;
        wickets_down = 0;
        total_balls_played = 0;
        int over_runs = 0;
        int over_wickets = 0;
        int bowler_counter = 0;
        int total_bowler = 5;

        Over currentOver = new Over();
        Player bowler = bowlingTeam.getPlayerAtIndex(0); // temp initialization
        String returnString = "";
        returnString += "<h3><b>" + battingTeam.getTeam_name() +"</b></h3><br>";


        outerloop:
        for(int i=0; i<TOTAL_BALLS; i++){
            total_balls_played = i+1;
            if(i%6==0){
                if(i != 0){
                    Player temp = striker;
                    striker = nonstriker;
                    nonstriker = temp;
                    bowler.updateBowlingScore(1, over_runs, over_wickets, over_runs==0, 0);
                    bowler.addOver(currentOver);
                    bowler.setBowlingStats();
                }
                currentOver = new Over();
                bowler = bowlingTeam.getPlayerAtIndex(10 - (i/6)%total_bowler);
                over_runs = 0;
                over_wickets = 0;
            }
            int ball_result = striker.BiasedRandomResult();
            if(ball_result == 7){
                wickets_down++;
                over_wickets++;
                returnString += runs + "/" + wickets_down + "<br>";
                FoW.add(runs);
                striker.gotOut();
                striker.setBattingStats();
                if(wickets_down == TOTAL_WICKETS){
                    bowler.addOver(currentOver);
                    bowler.updateBowlingScore(0, over_runs, over_wickets, false, (i+1)%6);
                    nonstriker.setBattingStats();
                    break outerloop;
                }
                striker = battingTeam.getPlayerAtIndex(wickets_down+1);
            }
            else {
                striker.updateBattingScore(ball_result);
                if(ball_result % 2 != 0 && ball_result!=5){
                    Player temp = striker;
                    striker = nonstriker;
                    nonstriker = temp;
                }
                runs += ball_result;
                over_runs += ball_result;
            }
            currentOver.addBall(ball_result);
            if(chase && runs>target){
                striker.setBattingStats();
                nonstriker.setBattingStats();
                returnString += "------ <br>" +runs + "/" + wickets_down + " (won)<br>";
                break;
            }
        }

        battingTeam.set_final_score(runs, wickets_down);
        return returnString;
    }



    // helper function which calculates the scorecard and sets it, and returns an HTML formatted string
    public String ScoreCard(){
        String myTable = "";
        myTable += "<h3><b>Batting (" + this.runs + "/" + this.wickets_down +")</b></h3>";
        myTable += "<table>";
        myTable += "<tr>" +
                "<th>Player Name</th>" +
                "<th>Runs</th>" +
                "<th>Balls</th>" +
                "<th>Fours</th>" +
                "<th>Sixes</th>" +
                "<th>Stirke Rate</th>" +
                "</tr>";



        for(int i=0; i<battingTeam.getWickets()+2 && i<11; i++){
            Player currentPlayer = battingTeam.getPlayerAtIndex(i);

            sc.addBattingStats(new BattingStats(currentPlayer.NameForScoreCard(),
                    currentPlayer.getBat_total_runs(),
                    currentPlayer.getBat_total_balls_played(),
                    currentPlayer.getBat_fours(),
                    currentPlayer.getBat_sixes(),
                    currentPlayer.Strike_Rate()));
            myTable += "<tr>" +
                    "<td>" +
                    currentPlayer.NameForScoreCard() +
                    "</td>" +
                    "<td>" +
                    currentPlayer.getBat_total_runs() +
                    "</td>" +
                    "<td>" +
                    currentPlayer.getBat_total_balls_played() +
                    "</td>" +
                    "<td>" +
                    currentPlayer.getBat_fours() +
                    "</td>" +
                    "<td>" +
                    currentPlayer.getBat_sixes() +
                    "</td>" +
                    "<td>" +
                    currentPlayer.Strike_Rate() +
                    "</td>" +
                    "</tr>";
        }

        myTable += "</table><br>";

        myTable += "<h3><b>Bowling (" + (int)(this.total_balls_played/6) + "." + this.total_balls_played%6 +")</b></h3>";
        myTable += "<table>";
        myTable += "<tr>" +
                "<th>Player Name</th>" +
                "<th>Runs</th>" +
                "<th>Overs</th>" +
                "<th>Wickets</th>" +
                "<th>Maiden</th>" +
                "<th>Economy Rate</th>" +
                "</tr>";

        for(int i=11-5; i<11; i++){
            Player currentPlayer = bowlingTeam.getPlayerAtIndex(i);
            sc.addBowlingStats(new BowlingStats(currentPlayer.getName(),
                    currentPlayer.getBowl_runs(),
                    currentPlayer.getBowl_total_overs(),
                    currentPlayer.getBowl_wickets(),
                    currentPlayer.getBowl_maiden_overs(),
                    currentPlayer.bowl_economy()));
            myTable += "<tr>" +
                    "<td>" +
                    currentPlayer.getName() +
                    "</td>" +
                    "<td>" +
                    currentPlayer.getBowl_runs() +
                    "</td>" +
                    "<td>" +
                    currentPlayer.getBowl_total_overs() +
                    "</td>" +
                    "<td>" +
                    currentPlayer.getBowl_wickets() +
                    "</td>" +
                    "<td>" +
                    currentPlayer.getBowl_maiden_overs() +
                    "</td>" +
                    "<td>" +
                    currentPlayer.bowl_economy() +
                    "</td>" +
                    "</tr>";
        }

        myTable += "</table>";
        return myTable;
    }

}
