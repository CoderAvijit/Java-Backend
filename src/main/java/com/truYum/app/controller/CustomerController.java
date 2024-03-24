package com.truYum.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.truYum.app.Repository.CustomerRepo;
import com.truYum.app.persitant.MenuItems;
import com.truYum.app.response.ResponseHandler;
@RestController
@ResponseBody
@CrossOrigin("*")
@RequestMapping("/customercontroller")
public class CustomerController{
	
	/**
	 * 
	 */
	
	@Autowired
	private CustomerRepo repo;
	
	@PostMapping("/addtocart")
	public ResponseEntity<Object> addToCart(@RequestParam String name,@RequestParam String email) {
	    List<MenuItems> response = repo.addToCart(name,email);
	    return ResponseHandler.generateResponseList(response, HttpStatus.OK);
	}
	@DeleteMapping("/removefromcart")
	public ResponseEntity<Object>  RemoveFromCart(@RequestParam String name,@RequestParam String email) {
		System.out.println("Before remove : "+repo.viewCart(email).size());
		List<MenuItems> response = repo.removeFromCart(name,email);
		System.out.println("After remove : "+response.size());
		return ResponseHandler.generateResponseList(response,HttpStatus.OK);
	}

	@GetMapping("/viewcart")
	public ResponseEntity<Object> ViewCart(@RequestParam String email) {
		System.out.println("Inside viewcart");
		return ResponseHandler.generateResponseList(repo.viewCart(email), HttpStatus.OK);
	}
	@GetMapping("/showitems")
	public ResponseEntity<Object>viewItems(){
		System.out.println(repo.ShowMenuItems());
		return ResponseHandler.generateResponseList(repo.ShowMenuItems(), HttpStatus.OK);	}
}
