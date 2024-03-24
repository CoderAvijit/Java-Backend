package com.truYum.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.truYum.app.Repository.AdminRepo;
import com.truYum.app.persitant.MenuItems;
import com.truYum.app.response.ResponseHandler;


@RestController
@RequestMapping("/admin")
@ResponseBody
@CrossOrigin("*")
public class AdminController {
	@Autowired
	private AdminRepo repo;
	
	@PostMapping("/additem")
	public MenuItems AddItem(@RequestParam String name, @RequestParam int price, @RequestParam String isavail,@RequestParam String dateoflaunch,@RequestParam String isfreedel) {
		MenuItems item = new MenuItems(name,price,isavail,dateoflaunch,isfreedel);
		repo.saveItem(item);
		System.out.println("Item Saved successfully......");
		return item;
	}

	@PutMapping("/updateItem")
	public ResponseEntity<Object> updateItem(@RequestParam int id, @RequestParam String name,@RequestParam int price,@RequestParam String availability,@RequestParam String dateOfLaunch,
			@RequestParam	String freeDelivery) {
	    // Retrieve the existing item from the database based on the provided 'id'
//		System.out.println("Updated user : "+updatedItem);
	    MenuItems existingItem = repo.getItemById(id);
	    System.out.println("Existing user : "+existingItem);

	    // Update the existing item's properties with the new values
	    existingItem.setName(name);
	    existingItem.setPrice(price);
	    existingItem.setDateOfLaunch(dateOfLaunch);
	    existingItem.setAvailability(availability);
	    existingItem.setFreeDelivery(freeDelivery);

	    // Save the updated item back to the database
	    repo.saveItem(existingItem);

	    
	    return ResponseHandler.generateResponseList(repo.getAllItems(), HttpStatus.OK);
	}
	
	@GetMapping("/getallitems")
	public ResponseEntity<Object> getAll(){
		return ResponseHandler.generateResponseList(repo.getAllItems(),HttpStatus.OK);
	}
	
	@DeleteMapping("/delteitembyid")
	public List<MenuItems> deleteItemById(@RequestParam int id){
		repo.deleteItemsById(id);
		return repo.getAllItems();
	}
	@GetMapping("/getitembyid")
	public MenuItems getItemById(@RequestParam int id) {
		return repo.getItemById(id);
	}
	
}
