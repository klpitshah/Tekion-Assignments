package model;


// lombok getter not working

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tempCollection")
public class Team {

    private String teamName;
    private int score = 0;
    private int wickets = 0;
    private Player [] players;


    public Team(){}


    public Team(String name){
        teamName = name;
        players = new Player[11];

        //probability of getting out for each(11) player

        // use a function to get the probabilty_to_get_out & probability_to_take_wickets of the batsman from his ratings
        double [] probabilityToGetOut = {0.02, 0.02, 0.025, 0.025, 0.03, 0.03, 0.04, 0.45, 0.45, 0.45, 0.5};
        double [] prob_to_take_wickets = {0.001, 0.001, 0.001, 0.001, 0.001, 0.001, 0.01, 0.15, 0.15, 0.15, 0.2};

//        double [] temp = prob_to_take_wickets + probabilityToGetOut;

        for(int i=0; i<probabilityToGetOut.length; i++){
            players[i] = (new Player(teamName+i, probabilityToGetOut[i]));

        }
    }


    public String getTeamName() {
        return teamName;
    }

    public int getScore() {
        return score;
    }

    public int getWickets() {
        return wickets;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getPlayerAtIndex(int i){
        return players[(i)];
    }

    public void setScore(int r, int w){
        this.score = r;
        this.wickets = w;
    }
}
