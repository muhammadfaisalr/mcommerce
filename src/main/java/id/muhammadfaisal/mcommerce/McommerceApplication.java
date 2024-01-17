package id.muhammadfaisal.mcommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class McommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(McommerceApplication.class, args);
	}

}
