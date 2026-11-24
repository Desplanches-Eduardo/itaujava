package EduardoDesplanches.itaujava;

import EduardoDesplanches.itaujava.estatistica.EstatisticasProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(EstatisticasProperties.class)
public class ItaujavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItaujavaApplication.class, args);
	}

}
