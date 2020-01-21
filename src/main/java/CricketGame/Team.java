package CricketGame;

import CricketGame.Player;
import java.util.ArrayList;

public class Team {
    private String team_name;
    private int score = 0;
    private int wickets = 0;
    private ArrayList<Player> players;

    public Team(String name){
        team_name = name;
        players = new ArrayList<>();

        //probability of getting out of each player
        double [] out_probs = {0.015, 0.015, 0.025, 0.025, 0.05, 0.05, 0.1, 0.15, 0.2, 0.25, 0.25};

        for(int i=0; i<out_probs.length; i++){
            Player temp = new Player(team_name+i, out_probs[i]);
            players.add(temp);
        }
    }

    public void set_final_score(int s, int w){
        this.score = s;
        this.wickets = w;
    }

    public String getName(){
        return team_name;
    }

    public Player getPlayerAtIndex(int i){
        return players.get(i);
    }

    public int getScore(){
        return score;
    }

    public int getWickets(){ return wickets;}
}
