package CricketGame;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Random;


public class Player {

    private String name;
    @JsonIgnore
    private int bat_total_balls_played;
    @JsonIgnore
    private int bat_sixes;
    @JsonIgnore
    private int bat_fours;
    @JsonIgnore
    private int bat_total_runs;
    @JsonIgnore
    private boolean bat_got_out;
    @JsonIgnore
    private int bowl_total_overs;
    @JsonIgnore
    private int bowl_extra_balls;
    @JsonIgnore
    private int bowl_runs;
    @JsonIgnore
    private int bowl_wickets;
    @JsonIgnore
    private int bowl_maiden_overs;
    @JsonIgnore
    private double probability_to_get_out;
    @JsonIgnore
    private ArrayList bowl_overs;
    @JsonIgnore
    private BattingStats battingStats;
    @JsonIgnore
    private BowlingStats bowlingStats;
    @JsonIgnore
    private int [] helperArray = {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,2,2,2,3,3,4,4,4,6};




    // getters
    public boolean isBat_got_out() {
        return bat_got_out;
    }
    public int getBowl_extra_balls() {
        return bowl_extra_balls;
    }
    public double getProbability_to_get_out() {
        return probability_to_get_out;
    }
    public ArrayList getBowl_overs() {
        return bowl_overs;
    }
    public String getName(){
        return name;
    }
    public int getBat_total_runs(){return bat_total_runs;}
    public int getBat_sixes(){ return  bat_sixes;}
    public int getBat_fours(){ return bat_fours;}
    public int getBat_total_balls_played(){return bat_total_balls_played;}
    public int getBowl_runs(){ return this.bowl_runs;}
    public int getBowl_wickets(){ return this.bowl_wickets;}
    public double bowl_economy(){
        if(this.bowl_wickets == 0){
            return this.bowl_runs;
        }
        return this.bowl_runs/this.bowl_wickets;
    }
    public int getBowl_maiden_overs(){ return this.bowl_maiden_overs;}




    //  setter
    public void setBattingStats(){
//        set this
//        e int runs;
//        private int balls;
//        private int fours;
//        private int sixes;
//        private double strike_rate;
        this.battingStats = new BattingStats(this.NameForScoreCard(), this.bat_total_runs, this.bat_total_balls_played,
                this.bat_fours, this.bat_sixes, this.Strike_Rate());
    }
    public void setBowlingStats(){
//        private int runs;
//        private String totalOvers;
//        private int wickets;
//        private int maiden;
//        private double economy;
        this.bowlingStats = new BowlingStats(this.name, this.bowl_runs, this.getBowl_total_overs(), this.bowl_wickets,
                this.bowl_maiden_overs, this.bowl_economy());
    }



    // constructor
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
        this.shuffle();
    }



    // helper functions

    // biased outcome of the ball depending upon the probability of getting out of the player
    public int BiasedRandomResult(){
        double random = Math.random();
        if(random < probability_to_get_out){
            return 7;
        }
        else{
            return helperArray[(int) ((helperArray.length-1)*random)];
        }
    }
    public void set_probabilities_from_ratings(){

    }
    private void shuffle(){
        Random rand = new Random();

        for (int i = 0; i < helperArray.length; i++) {
            int randomIndexToSwap = rand.nextInt(helperArray.length);
            int temp = helperArray[randomIndexToSwap];
            helperArray[randomIndexToSwap] = helperArray[i];
            helperArray[i] = temp;
        }
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
    // add an asterisk if the player is not out
    public String NameForScoreCard(){
        if(this.bat_got_out){
            return name;
        }
        else{
            return name + "(*)";
        }
    }
    public double Strike_Rate(){
        if(bat_total_balls_played == 0){
            return 0;
        }
        return (100*bat_total_runs)/bat_total_balls_played;
    }
    // return 4.5 or something like this if the over is incomplete
    public String getBowl_total_overs(){
        if(bowl_extra_balls != 0){
            return this.bowl_total_overs + "." + this.bowl_extra_balls;
        }
        return String.valueOf(this.bowl_total_overs);
    }
    public void addOver(Over o){
        this.bowl_overs.add(o);
    }



}
