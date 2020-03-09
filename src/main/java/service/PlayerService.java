package service;

import model.Player;
import org.springframework.stereotype.Service;


@Service
public class PlayerService{

    public int BiasedRandomResult(Player p) {
        int [] helperArray = p.getHelperArray();
        double random = Math.random();
        if(random < p.getProbabilityToGetOut()){
            return 7;
        }
        else{
            return helperArray[(int) ((helperArray.length-1)*random)];
        }
    }
}
