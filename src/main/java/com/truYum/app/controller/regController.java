package com.truYum.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.truYum.app.Repository.RegRepo;
import com.truYum.app.persitant.Customer;
import com.truYum.app.validator.PasswordConstraintValidator;
import com.truYum.app.validator.UserIdValidator;

@RestController
@RequestMapping("/registration")
public class regController {
	@Autowired
	private RegRepo repo;
	@CrossOrigin(origins = "*")
//	PasswordConstraintValidator validator = new PasswordConstraintValidator();
	@PostMapping("/regcontroller")
	public String doReg(@RequestParam String name,
	        @RequestParam String email,
	        @RequestParam String location,
	        @RequestParam String phone,
	        @RequestParam String password) {
		if(!UserIdValidator.isValidUserName(email)) {
			return "Not a valid userName"+"\nUser id should be a valid email address";
		}
		else if(PasswordConstraintValidator.isValidPassword(password)) {
			System.out.println("Registration done");
			return repo.doRegistration(name,email, password,location,phone); 
		}else {
			return "Password requirements missmatch"+"\n Should 8-20 char size"+"\n Should 1 digit"+"\n Should one lowercase & Uppercase"+"\n Should a special char";
		}
		
	}
}
