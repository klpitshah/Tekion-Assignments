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

    public String startMatchAndGetWinner(){
        Team battingTeam = team1;
        Team bowlingTeam = team2;

        // toss
        if(Math.random() > 0.5){
            battingTeam = team2;
            bowlingTeam = team1;
        }


        //initializing constants

        int TOTAL_BALLS = 300;
        int TOTAL_WICKETS = 10;



        int wickets_down = 0;
        int runs = 0;
        Player striker = battingTeam.players.get(0);
        Player nonstriker = battingTeam.players.get(1);


        String returnString = "";


        returnString += battingTeam.getName() + "<br>";
        outerloop:
        for(int i=0; i<TOTAL_BALLS; i++){
//            int ball_result = (int)(8*Math.random());
            int ball_result = striker.getBiasedRandomResult();
            if(ball_result == 7){
                wickets_down++;
                returnString += runs + "/" + wickets_down + "<br>";
                if(wickets_down == TOTAL_WICKETS){
                    break outerloop;
                }
                striker = battingTeam.players.get(wickets_down+1);
            }
            else {
                if(ball_result % 2 != 0 && ball_result!=5){
                    changeStrike(striker, nonstriker);
                }
                runs += ball_result;
            }
            if(i%6 == 0){
                changeStrike(striker, nonstriker);
            }
        }

        battingTeam.set_final_score(runs, wickets_down);
        returnString += "<br><br>" + bowlingTeam.getName() + "<br>";



        wickets_down = 0;
        runs = 0;
        striker = bowlingTeam.players.get(0);
        nonstriker = bowlingTeam.players.get(1);

        outerloop2:
        for(int i=0; i<TOTAL_BALLS; i++){
//            int ball_result = (int)(8*Math.random());
            int ball_result = striker.getBiasedRandomResult();
            if(ball_result == 7){
                wickets_down++;
                returnString += runs + "/" + wickets_down + "<br>";
                if(wickets_down == TOTAL_WICKETS){
                    break outerloop2;
                }
                striker = bowlingTeam.players.get(wickets_down+1);
            }
            else {
                if(ball_result % 2 != 0 && ball_result!=5){
                    changeStrike(striker, nonstriker);
                }
                runs += ball_result;
            }

            if(runs > battingTeam.score){
                returnString += "------ <br>" +runs + "/" + wickets_down + " (won)<br>";
                break;
            }

            if(i%6 == 0){
                changeStrike(striker, nonstriker);
            }
        }


        team2.set_final_score(runs, wickets_down);

        Team winner;
        if(battingTeam.score > bowlingTeam.score){
            winner = battingTeam;
        }
        else if(bowlingTeam.score > battingTeam.score){
            winner = bowlingTeam;
        }
        else{
            return "match is tied";
        }

        return returnString + "<br><br>" + winner.getName() + " wins the match!";
    }

}
