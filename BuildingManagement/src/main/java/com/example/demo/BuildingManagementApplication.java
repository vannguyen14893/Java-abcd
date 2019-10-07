package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.controller.ApiRoleController;
import com.example.demo.service.EmailService;
import com.example.demo.untils.Mail;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class BuildingManagementApplication implements ApplicationRunner {

	
	private static final Logger logger = Logger.getLogger(ApiRoleController.class);
//	@Autowired
//	private EmailService emailService;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BuildingManagementApplication.class, args);
		
	}

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		logger.info("Sending Email with Thymeleaf HTML Template Example");

		/*
		 * Mail mail = new Mail(); mail.setFrom("ducvan14893@gmail.com");
		 * mail.setTo("nguyenvan14893@gmail.com");
		 * mail.setSubject("Sending Email with Thymeleaf HTML Template Example");
		 * 
		 * Map model = new HashMap(); model.put("name", "Memorynotfound.com");
		 * model.put("location", "Belgium"); model.put("signature",
		 * "https://memorynotfound.com"); mail.setModel(model);
		 * 
		 * emailService.sendSimpleMessage(mail);
		 */
	}
//	@Scheduled(fixedRate = 5000, initialDelay = 10000)
//    public void printFixedRate() {
//        System.out.println("hello 1");
//    }

//    @Scheduled(cron = "0/10 * * * * ?")
//    public void printCron() {
//    	  System.out.println("hello 2");
//    }
}
