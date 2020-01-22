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
        double [] out_probs = {0.001, 0.001, 0.005, 0.005, 0.015, 0.015, 0.025, 0.15, 0.15, 0.25, 0.25};

        for(int i=0; i<out_probs.length; i++){
            Player temp = new Player(team_name+i, out_probs[i]);
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
