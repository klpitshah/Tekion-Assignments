package CricketGame;

public class Player {
    String name;
    //String expertise;
    double probability_to_get_out;

    public Player(String n, double p){
        this.name = n;
//        this.expertise = e;
        this.probability_to_get_out = p;
    }

    public int getBiasedRandomResult(){
        if(Math.random() < probability_to_get_out){
            return 7;
        }
        else{
            return (int) (8*Math.random());
        }
    }
}
