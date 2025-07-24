package com.td.honse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;



@SpringBootApplication
public class HonseAnalysisApplication {

	private static final Logger log = LogManager.getLogger(HonseAnalysisApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HonseAnalysisApplication.class, args);
	}
	@Bean
	ApplicationRunner applicationRunner(Environment environment) {
		return args -> {
			log.info("message from application.properties " + environment.getProperty("message-from-application-properties"));


		};
	}

}
