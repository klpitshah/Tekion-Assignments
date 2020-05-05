package CricketGame;

public class BattingStats extends Stats {

    private int runs;
    private int balls;
    private int fours;
    private int sixes;
    private double strike_rate;



    public int getRuns() {
        return runs;
    }
    public int getBalls() {
        return balls;
    }
    public int getFours() {
        return fours;
    }
    public int getSixes() {
        return sixes;
    }
    public double getStrike_rate() {
        return strike_rate;
    }



    public BattingStats(String s, int runs, int balls, int fours, int sixes, double strike_rate){
        super(s);
        this.runs = runs;
        this.balls = balls;
        this.fours = fours;
        this.sixes = sixes;
        this.strike_rate = strike_rate;
    }
}
