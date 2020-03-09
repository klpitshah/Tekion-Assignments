package repository;

import model.Match;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepo extends MongoRepository<Match, String> {

    @Query("{$and : [{\"winner.teamName\" : {$ne : \"?0\"}}, {$or : [{\"team1.teamName\" : \"?0\"}, {\"team2.teamName\" : \"?0\"}]}]}")
    List<Match> matchesLost(String teamName);

    @Query("{$and : [{\"winner.teamName\" : \"?0\"}, {$or : [{\"team1.teamName\" : \"?0\"}, {\"team2.teamName\" : \"?0\"}]}]}")
    List<Match> matchesWon(String teamName);

    @Query("{$or : [{\"team1.teamName\" : \"?0\"}, {\"team2.teamName\" : \"?0\"}]}")
    List<Match> allMatches(String teamName);

}



//db.Match.find({$and : [{"winner.teamName" : {$ne : "India"}}]}).count()
//db.Match.find({$and : [{"winner.teamName" : "India"}]}).count()
//db.Match.find({$or : [{"team1.teamName" : "India"}, {"team2.teamName" : "India"}]}).count()