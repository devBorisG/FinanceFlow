package finance.corp.financeflowinfrastructure.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"finance.corp"})
@EnableJpaRepositories(basePackages = {"finance.corp"})
@EntityScan(basePackages = {"finance.corp"})
public class FinanceFlowInfrastructureApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceFlowInfrastructureApplication.class, args);
	}

}
