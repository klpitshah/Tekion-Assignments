
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"beans", "controller", "repository", "service"})
public class CricketGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(CricketGameApplication.class, args);
//		System.out.println("hello");
	}

}
