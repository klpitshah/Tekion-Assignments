package CricketGame;

public class Match {
    Team team1;
    Team team2;

    public Match(String t1, String t2){
        team1 = new Team(t1);
        team2 = new Team(t2);
    }

    public void changeStrike(Player striker, Player nonstriker){
        Player temp = striker;
        striker = nonstriker;
        nonstriker = temp;
    }

    public String startMatchAndGetWinner() {
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
        Innings firstInnings = new Innings(tossBattingTeam, tossBowlingTeam, TOTAL_BALLS);
        returnString += firstInnings.startInnings();
        returnString += "<br><br>";


        Innings secondInnings = new Innings(tossBowlingTeam, tossBattingTeam, TOTAL_BALLS, tossBattingTeam.getScore());
        returnString += secondInnings.startInnings();;
        returnString += "<br><br>";


        Team winner;
        if(tossBattingTeam.getScore() > tossBowlingTeam.getScore()){
            winner = tossBattingTeam;
            returnString += "<br><br>" + "<h1><b><i>" + winner.getName() +"</i>"+ " wins the match!" +"</b></h1>";
        }
        else if(tossBowlingTeam.getScore() > tossBattingTeam.getScore()){
            winner = tossBowlingTeam;
            returnString += "<br><br>" + "<h1><b><i>" + winner.getName() +"</i>"+ " wins the match!" +"</b></h1>";
        }
        else{
            returnString += "<br><br><h1><b>Match is tied</b></h1>";
        }



        returnString += "<br><h1><b>First Innings Stats</b></h1><br>";
        returnString += firstInnings.getScoreCard();
        returnString += "<br><br>";
        returnString += "<br><h1><b>Second Innings Stats</b></h1><br>";
        returnString += secondInnings.getScoreCard();

        return returnString;
    }

}
