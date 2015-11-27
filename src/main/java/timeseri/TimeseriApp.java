package timeseri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration
public class TimeseriApp {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TimeseriApp.class, args);
	}
}
