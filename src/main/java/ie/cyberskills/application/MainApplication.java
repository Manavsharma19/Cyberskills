package ie.cyberskills.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication

public class MainApplication {
	//	private static final Logger logger = LogManager.getLogger(Application.class);
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);


//		logger.debug("Debug log message");
//		logger.info("Info log message");
//		logger.error("Error log message");
//		logger.warn("Warn log message");
//		logger.trace("Trace log message");
	}

}

