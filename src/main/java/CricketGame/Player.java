package CricketGame;

import java.util.ArrayList;

public class Player {
    private String name;
    //String expertise;
    private int bat_total_balls_played;
    private int bat_sixes;
    private int bat_fours;
    private int bat_total_runs;
    private boolean bat_got_out;
    private int bowl_total_overs;
    private int bowl_extra_balls;
    private int bowl_runs;
    private int bowl_wickets;
    private int bowl_maiden_overs;
    private double probability_to_get_out;
    private ArrayList bowl_overs;

    public Player(String n, double p){
        this.name = n;
        this.bat_fours = 0;
        this.bat_sixes = 0;
        this.bat_total_balls_played = 0;
        this.bat_total_runs = 0;
        this.bat_got_out = false;
        this.bowl_runs = 0;
        this.bowl_total_overs = 0;
        this.bowl_wickets = 0;
        this.probability_to_get_out = p;
        this.bowl_maiden_overs = 0;
        this.bowl_extra_balls = 0;
        this.bowl_overs = new ArrayList<Over>();
    }

    public int getBiasedRandomResult(){
        if(Math.random() < probability_to_get_out){
            return 7;
        }
        else{
            return (int) (8*Math.random());
        }
    }

    public void setBattingScore(int runs, int balls, int six, int four){
        this.bat_total_runs = runs;
        this.bat_total_balls_played = balls;
        this.bat_sixes = six;
        this.bat_fours = four;
    }

    public void updateBattingScore(int runs){
        this.bat_total_balls_played += 1;
        this.bat_total_runs += runs;
        if(runs == 4){
            this.bat_fours += 1;
        }
        if(runs == 6){
            this.bat_sixes += 1;
        }
    }

    public void gotOut(){
        this.bat_got_out = true;
    }

    public void updateBowlingScore(int over, int runs, int wickets, boolean maiden, int ex_balls){
        this.bowl_total_overs += over; // over will always be = 1 or 0
        if(ex_balls != 0){
            this.bowl_extra_balls = ex_balls;
        }
        this.bowl_runs += runs;
        this.bowl_wickets += wickets;
        if(maiden){
            this.bowl_maiden_overs += 1;
        }
    }

    public String getName(){
        return name;
    }

    public String getNameForScoreCard(){
        if(this.bat_got_out){
            return name;
        }
        else{
            return name + "(*)";
        }
    }

    public int getBat_total_runs(){return bat_total_runs;}

    public int getBat_sixes(){ return  bat_sixes;}

    public int getBat_fours(){ return bat_fours;}

    public int getBat_total_balls_played(){return bat_total_balls_played;}

    public double getStrike_Rate(){
        if(bat_total_balls_played == 0){
            return 0;
        }
        return (100*bat_total_runs)/bat_total_balls_played;
    }

    public String getBowl_total_overs(){
        if(bowl_extra_balls != 0){
            return this.bowl_total_overs + "." + this.bowl_extra_balls;
        }
        return String.valueOf(this.bowl_total_overs);
    }

    public int getBowl_runs(){ return this.bowl_runs;}

    public int getBowl_wickets(){ return this.bowl_wickets;}

    public double get_bowl_economy(){
        if(this.bowl_wickets == 0){
            return this.bowl_runs;
        }
        return this.bowl_runs/this.bowl_wickets;
    }

    public int getBowl_maiden_overs(){ return this.bowl_maiden_overs;}

    public void addOver(Over o){
        this.bowl_overs.add(o);
    }


}
