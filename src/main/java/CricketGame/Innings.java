package CricketGame;

public class Innings {
    private int runs;
    private int wickets_down;
    private int total_balls_played;
    private Player striker;
    private Player nonstriker;
    private Team battingTeam;
    private Team bowlingTeam;
    private boolean chase;
    private int target;
    private int TOTAL_BALLS;
    private final int TOTAL_WICKETS = 10;

    public Innings(Team bat, Team bowl, int total_balls){
        this.battingTeam = bat;
        this.bowlingTeam = bowl;
        this.runs = 0;
        this.wickets_down = 0;
        this.total_balls_played = 0;
        this.striker = battingTeam.getPlayerAtIndex(0);
        this.nonstriker = battingTeam.getPlayerAtIndex(1);
        this.chase = false;
        this.target = Integer.MAX_VALUE;
        this.TOTAL_BALLS = total_balls;
    }

    public Innings(Team bat, Team bowl, int total_balls, int tgt){
        this.battingTeam = bat;
        this.bowlingTeam = bowl;
        this.runs = 0;
        this.wickets_down = 0;
        this.total_balls_played = 0;
        this.striker = battingTeam.getPlayerAtIndex(0);
        this.nonstriker = battingTeam.getPlayerAtIndex(1);
        this.chase = true;
        this.target = tgt;
        this.TOTAL_BALLS = total_balls;
    }

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
//        returnString += battingTeam.getName() + "<br>";
        returnString += "<h3><b>" + battingTeam.getName() +"</b></h3><br>";


        outerloop:
        for(int i=0; i<TOTAL_BALLS; i++){
            total_balls_played = i+1;
            if(i%6==0){
                if(i != 0){
//                    changeStrike(striker, nonstriker);
                    Player temp = striker;
                    striker = nonstriker;
                    nonstriker = temp;
                    bowler.updateBowlingScore(1, over_runs, over_wickets, over_runs==0, 0);
                }
                bowler.addOver(currentOver);
                currentOver = new Over();
                bowler = bowlingTeam.getPlayerAtIndex(10 - (i/6)%total_bowler);
                over_runs = 0;
                over_wickets = 0;
            }
            int ball_result = striker.getBiasedRandomResult();
            if(ball_result == 7){
                wickets_down++;
                over_wickets++;
                returnString += runs + "/" + wickets_down + "<br>";
                striker.gotOut();
                if(wickets_down == TOTAL_WICKETS){
                    bowler.addOver(currentOver);
                    bowler.updateBowlingScore(0, over_runs, over_wickets, false, (i+1)%6);
                    break outerloop;
                }
                striker = battingTeam.getPlayerAtIndex(wickets_down+1);
            }
            else {
                striker.updateBattingScore(ball_result);
                if(ball_result % 2 != 0 && ball_result!=5){
//                    changeStrike(striker, nonstriker);
                    Player temp = striker;
                    striker = nonstriker;
                    nonstriker = temp;
                }
                runs += ball_result;
                over_runs += ball_result;
            }
            currentOver.addBall(ball_result);
            if(chase && runs>target){
                returnString += "------ <br>" +runs + "/" + wickets_down + " (won)<br>";
                break;
            }
        }

        battingTeam.set_final_score(runs, wickets_down);
//        return;
        return returnString;
    }

    public void changeStrike(Player striker, Player nonstriker){
        Player temp = striker;
        striker = nonstriker;
        nonstriker = temp;
    }

    public String getScoreCard(){
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
            myTable += "<tr>" +
                    "<td>" +
                    currentPlayer.getNameForScoreCard() +
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
                    currentPlayer.getStrike_Rate() +
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
            System.out.println(currentPlayer.getName());
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
                    currentPlayer.get_bowl_economy() +
                    "</td>" +
                    "</tr>";
        }

        myTable += "</table>";
        return myTable;
    }


}
