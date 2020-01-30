package service;

import beans.Player;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class PlayerService implements InterPlayerService{
    @Override
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

//    @Autowired
//    private PlayerRepo repo;
//
//    Player palyer = new Player();
//    int score =


}
