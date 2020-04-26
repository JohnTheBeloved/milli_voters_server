package com.nzenweoforgroup.millivoters.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class MilliVotersApp {

	public static void main(String[] args) {
		SpringApplication.run(MilliVotersApp.class, args);
	}

	@Bean(name="messageSource")//wont work without the bean name
	public ResourceBundleMessageSource bundleMessageSource() {
			ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
			messageSource.setBasename("messages");
			return messageSource;
	}


}
