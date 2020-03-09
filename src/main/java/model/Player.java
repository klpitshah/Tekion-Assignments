package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Random;

public class Player {

    private String name;
    @JsonIgnore
    protected String expertise;
    @JsonIgnore
    private int batTotalBallsPlayed;
    @JsonIgnore
    private int batSixes;
    @JsonIgnore
    private int batFours;
    @JsonIgnore
    private int batTotalRuns;
    @JsonIgnore
    private boolean batGotOut;
    @JsonIgnore
    private int bowlTotalOvers;
    @JsonIgnore
    private int bowlExtraBalls;
    @JsonIgnore
    private int bowlRuns;
    @JsonIgnore
    private int bowlWickets;
    @JsonIgnore
    private int bowlMaidenOvers;
    @JsonIgnore
    private double probabilityToGetOut;
    @JsonIgnore
    private int [] helperArray = {0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,2,2,2,3,3,4,4,4,6};

    public Player(){}

    public Player(String n, double p){
        this.name = n;
        this.batFours = 0;
        this.batSixes = 0;
        this.batTotalBallsPlayed = 0;
        this.batTotalRuns = 0;
        this.batGotOut = false;
        this.bowlRuns = 0;
        this.bowlTotalOvers = 0;
        this.bowlWickets = 0;
        this.probabilityToGetOut = p;
        this.bowlMaidenOvers = 0;
        this.bowlExtraBalls = 0;
        this.shuffle();
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

    public String getName() {
        return name;
    }

    public String getExpertise() {
        return expertise;
    }

    public int getBatTotalBallsPlayed() {
        return batTotalBallsPlayed;
    }

    public int getBatSixes() {
        return batSixes;
    }

    public int getBatFours() {
        return batFours;
    }

    public int getBatTotalRuns() {
        return batTotalRuns;
    }

    public boolean isBatGotOut() {
        return batGotOut;
    }

    public int getBowlTotalOvers() {
        return bowlTotalOvers;
    }

    public int getBowlExtraBalls() {
        return bowlExtraBalls;
    }

    public int getBowlRuns() {
        return bowlRuns;
    }

    public int getBowlWickets() {
        return bowlWickets;
    }

    public int getBowlMaidenOvers() {
        return bowlMaidenOvers;
    }

    public double getProbabilityToGetOut() {
        return probabilityToGetOut;
    }

    public int[] getHelperArray() {
        return helperArray;
    }

    public void setBatGotOut(boolean batGotOut) {
        this.batGotOut = batGotOut;
    }

    public void updateBattingScore(int runs){
        this.batTotalBallsPlayed += 1;
        this.batTotalRuns += runs;
        if(runs == 4){
            this.batFours += 1;
        }
        if(runs == 6){
            this.batSixes += 1;
        }
    }
}
