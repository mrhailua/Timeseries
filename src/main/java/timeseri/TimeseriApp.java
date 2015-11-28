package timeseri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class TimeseriApp {

	public static void main(String[] args) {
		SpringApplication.run(TimeseriApp.class, args);
	}
}
