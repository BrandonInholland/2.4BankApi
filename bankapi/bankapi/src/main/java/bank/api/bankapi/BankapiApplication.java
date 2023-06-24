package bank.api.bankapi;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi

public class BankapiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BankapiApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		if (args.length > 0 && args[0].equals("exitcode")) {
			throw new Exception();
		}
	}
}
