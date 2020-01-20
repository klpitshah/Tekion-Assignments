package Team;

public class Team {
    String team_name;
    int score = 0;
    int wickets = 0;

    public Team(String name){
        team_name = name;
    }

    public void set_final_score(int s, int w){
        this.score = s;
        this.wickets = w;
    }

    public String getName(){
        return team_name;
    }
}
