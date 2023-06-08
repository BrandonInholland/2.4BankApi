package bank.api.bankapi;


import bank.api.bankapi.test.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankapiApplication.class, args);
	}

	public void somethingitdoesntmatter(){

		var test = new Test("2");

	}



}
