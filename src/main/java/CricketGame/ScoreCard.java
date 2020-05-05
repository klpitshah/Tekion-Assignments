package CricketGame;

import java.util.ArrayList;

public class ScoreCard {

    // Instance variables
    private ArrayList<BattingStats> battingScoreCard;
    private ArrayList<BowlingStats> bowlingScoreCard;



    // getters
    public ArrayList<BattingStats> getBattingScoreCard() {
        return battingScoreCard;
    }
    public ArrayList<BowlingStats> getBowlingScoreCard() {
        return bowlingScoreCard;
    }



    // constructor
    public ScoreCard(){
        battingScoreCard = new ArrayList<>();
        bowlingScoreCard = new ArrayList<>();
    }



    // helper functions
    public void addBattingStats(BattingStats b){
        battingScoreCard.add(b);
    }
    public void addBowlingStats(BowlingStats b){
        bowlingScoreCard.add(b);
    }
}
