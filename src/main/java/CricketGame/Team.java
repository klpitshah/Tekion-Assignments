package CricketGame;

import java.util.ArrayList;


public class Team {

    // Instance variables

    private String team_name;
    private int score = 0;
    private int wickets = 0;
    private ArrayList<Player> players;



    // getters

    public String getTeam_name() {
        return team_name;
    }
    public int getScore() {
        return score;
    }
    public int getWickets() {
        return wickets;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }



    // constructor
    public Team(String name){
        team_name = name;
        players = new ArrayList<>();

        //probability of getting out for each(11) player

        // use a function to get the probabilty_to_get_out & probability_to_take_wickets of the batsman from his ratings
        double [] probability_to_get_out = {0.02, 0.02, 0.025, 0.025, 0.03, 0.03, 0.04, 0.45, 0.45, 0.45, 0.5};
        double [] prob_to_take_wickets = {0.001, 0.001, 0.001, 0.001, 0.001, 0.001, 0.01, 0.15, 0.15, 0.15, 0.2};

//        double [] temp = prob_to_take_wickets + probability_to_get_out;

        for(int i=0; i<probability_to_get_out.length; i++){
            Player temp;
            if(i<6){
                temp = new Batsman(team_name+i, probability_to_get_out[i]);
            }
            else{
                temp = new Bowler(team_name+i, probability_to_get_out[i]);
            }

            players.add(temp);
        }
    }



    // helper functions
    public void set_final_score(int s, int w){
        this.score = s;
        this.wickets = w;
    }
    public Player getPlayerAtIndex(int i){
        return players.get(i);
    }

}
