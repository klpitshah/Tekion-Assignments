package CricketGame;

import java.util.ArrayList;

public class Over {
    private ArrayList over;

    public Over(){
        over = new ArrayList<Integer>();
    }

    public void addBall(int result){
        over.add(result);
    }
}
