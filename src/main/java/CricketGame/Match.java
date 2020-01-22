package CricketGame;


//private vars cant be read in JSON. all vars MUST have a getter
public class Match {

    //Instance Variables

    private Team team1;
    private Team team2;
    private Innings firstInnings;
    private Innings secondInnings;
    private Team winner;



    // getter functions

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



    // constructor

    public Match(String t1, String t2){
        team1 = new Team(t1);
        team2 = new Team(t2);
    }



    // main function which calls other helper function to fetch the result

    public Match startMatchAndGetWinner() {
        Team tossBattingTeam = team1;
        Team tossBowlingTeam = team2;

        // toss
        if (Math.random() > 0.5) {
            tossBattingTeam = team2;
            tossBowlingTeam = team1;
        }


        //initializing constants

        int TOTAL_BALLS = 300;
        int TOTAL_WICKETS = 10;
        String returnString = "";



        returnString += "<h1><b>Fall of Wickets</b></h1>";
        firstInnings = new Innings(tossBattingTeam, tossBowlingTeam, TOTAL_BALLS);
        returnString += firstInnings.startInnings();
        returnString += "<br><br>";


        secondInnings = new Innings(tossBowlingTeam, tossBattingTeam, TOTAL_BALLS, tossBattingTeam.getScore());
        returnString += secondInnings.startInnings();;
        returnString += "<br><br>";


//        Team winner;
        if(tossBattingTeam.getScore() > tossBowlingTeam.getScore()){
            winner = tossBattingTeam;
            returnString += "<br><br>" + "<h1><b><i>" + winner.getTeam_name() +"</i>"+ " wins the match!" +"</b></h1>";
        }
        else if(tossBowlingTeam.getScore() > tossBattingTeam.getScore()){
            winner = tossBowlingTeam;
            returnString += "<br><br>" + "<h1><b><i>" + winner.getTeam_name() +"</i>"+ " wins the match!" +"</b></h1>";
        }
        else{
            returnString += "<br><br><h1><b>Match is tied</b></h1>";
        }



        returnString += "<br><h1><b>First Innings Stats</b></h1><br>";
        returnString += firstInnings.ScoreCard();
        returnString += "<br><br>";
        returnString += "<br><h1><b>Second Innings Stats</b></h1><br>";
        returnString += secondInnings.ScoreCard();

        // returnString is a HTML formatted response

        return this;

    }


}
