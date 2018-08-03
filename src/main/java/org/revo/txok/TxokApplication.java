package org.revo.txok;

import org.revo.txok.Config.Env;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableConfigurationProperties(Env.class)
@EnableMongoAuditing
public class TxokApplication {

	public static void main(String[] args) {
		SpringApplication.run(TxokApplication.class, args);
	}
}
