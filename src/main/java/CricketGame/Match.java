package CricketGame;

import Team.Team;

public class Match {
    protected String team1;
    protected String team2;
    Team T1;
    Team T2;

    public Match(String t1, String t2){
        team1 = t1;
        team2 = t2;
        T1 = new Team(t1);
        T2 = new Team(t2);
    }

    public String startMatchAndGetWinner(){
        Team battingTeam = T1;
        Team bowlingTeam = T2;

        // toss
        if(Math.random() > 0.5){
            battingTeam = T2;
            bowlingTeam = T1;
        }

        //initialising constants

        int TOTAL_BALLS = 300;
        int TOTAL_WICKETS = 10;


        int wickets_down = 0;
        int runs = 0;


        String returnString = "";

        System.out.println(battingTeam);
        returnString += battingTeam + "<br>";
        for(int i=0; i<TOTAL_BALLS; i++){
            int ball_result = (int)(8*Math.random());
            if(ball_result == 7){
                wickets_down++;
                System.out.println(runs + "/" + wickets_down);
                returnString += runs + "/" + wickets_down + "<br>";
                if(wickets_down == TOTAL_WICKETS){
                    break;
                }
            }
            runs += ball_result;
        }

        T1.set_final_score(runs, wickets_down);
        int team1_score = runs;
        System.out.println();
        System.out.println();
        returnString += "<br><br>" + bowlingTeam + "<br>";
        System.out.println(bowlingTeam);

        wickets_down = 0;
        runs = 0;

        for(int i=0; i<TOTAL_BALLS; i++){
            int ball_result = (int)(8*Math.random());
            if(ball_result == 7){
                wickets_down++;
                returnString += runs + "/" + wickets_down + "<br>";
                System.out.println(runs + "/" + wickets_down);
                if(wickets_down == TOTAL_WICKETS){
                    break;
                }
            }
            runs += ball_result;
            if(runs > team1_score){
                returnString += runs + "/" + wickets_down + "<br>";
                System.out.println(runs + "/" + wickets_down);
                break;
            }
        }

        int team2_score = runs;
        T2.set_final_score(runs, wickets_down);

        Team winner;
        if(team1_score > team2_score){
            winner = battingTeam;
        }
        else if(team2_score > team1_score){
            winner = bowlingTeam;
        }
        else{
            return "match is tied";
        }

        return returnString + "<br><br>" + winner.getName() + " wins the match!";
    }

}
