package bf.lonab.banque;

import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.convert.Jsr310Converters;



@SpringBootApplication
@EntityScan(basePackageClasses = {BanqueBackendApplication.class,Jsr310Converters.class})
public class BanqueBackendApplication implements CommandLineRunner {
	
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(BanqueBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
