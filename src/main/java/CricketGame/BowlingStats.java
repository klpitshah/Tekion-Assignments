package CricketGame;

public class BowlingStats extends Stats {


    private int runs;
    private String totalOvers;
    private int wickets;
    private int maiden;
    private double economy;



    public int getRuns() {
        return runs;
    }
    public String getTotalOvers() {
        return totalOvers;
    }
    public int getWickets() {
        return wickets;
    }
    public int getMaiden() {
        return maiden;
    }
    public double getEconomy() {
        return economy;
    }



    public BowlingStats(String s, int r, String overs, int wickets, int maiden, double economy){
        super(s);
        this.runs = r;
        this.totalOvers = overs;
        this.wickets = wickets;
        this.maiden = maiden;
        this.economy = economy;
    }
}
