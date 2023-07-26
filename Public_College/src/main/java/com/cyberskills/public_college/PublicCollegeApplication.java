package com.cyberskills.public_college;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
@SpringBootApplication

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
public class PublicCollegeApplication {
//	private static final Logger log = LogManager.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(PublicCollegeApplication.class, args);

//		log.debug("Debug log message");
//		log.info("Info log message");
//		log.error("Error log message");
//		log.warn("Warn log message");
//		log.trace("Trace log message");
	}

}
