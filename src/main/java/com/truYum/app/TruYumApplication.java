package com.truYum.app;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class TruYumApplication{
	@Autowired
//	private AdminRepo repo;
	public static void main(String[] args) {
		SpringApplication.run(TruYumApplication.class, args);
		System.out.println("Successfully Running the program...");
	}

	
}
