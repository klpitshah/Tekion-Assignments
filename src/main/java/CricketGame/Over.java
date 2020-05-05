package CricketGame;

import java.util.ArrayList;

public class Over {
    public ArrayList getOver() {
        return over;
    }

    private ArrayList over;

    public Over(){
        over = new ArrayList<Integer>();
    }

    public void addBall(int result){
        over.add(result);
    }
}
