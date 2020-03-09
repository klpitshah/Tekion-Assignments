package tekion.assignments.CricketGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

<<<<<<< Updated upstream:src/main/java/tekion/assignments/CricketGame/CricketGameApplication.java
@SpringBootApplication
=======
@SpringBootApplication(scanBasePackages = {"model", "controller", "repository", "service"})
@EnableMongoRepositories
>>>>>>> Stashed changes:src/main/java/CricketGameApplication.java
public class CricketGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(CricketGameApplication.class, args);
<<<<<<< Updated upstream:src/main/java/tekion/assignments/CricketGame/CricketGameApplication.java
=======

>>>>>>> Stashed changes:src/main/java/CricketGameApplication.java
	}

}
