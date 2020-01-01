package com.sqlite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.sqlite.utils.FileWatcher;

@ComponentScan({"com.impinj.octane", "com.sqlite"})
@EnableAutoConfiguration
@SpringBootApplication
public class RZERPApplication {

	public static ApplicationContext ctx;
	public static FileWatcher fileWatcher = new FileWatcher();

	public static Logger logger = LoggerFactory.getLogger("Application");

	public static Object getBean(String name) {
		return ctx.getBean(name);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/invoices").allowedOrigins("*");
			}
		};
	}

	public static void main(String[] args) throws Exception {
		
		try {
			ctx = new SpringApplicationBuilder(RZERPApplication.class).run(args);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		//init();

		// checkLicense();

		//startListenOnOrderFiles();

		//startInBrowser();

		logger.warn("Starting application ...");
	}


}
