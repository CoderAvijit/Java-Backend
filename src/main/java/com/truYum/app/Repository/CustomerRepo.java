package com.truYum.app.Repository;

import java.awt.MenuItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.truYum.app.persitant.MenuItems;
import jakarta.transaction.Transactional;

@Repository
public class CustomerRepo {
	public static int price=0;
	@Autowired
	private AdminRepo repo=new AdminRepo();
	public static Map<String,List<MenuItems>> cartMap=new HashMap<>();
	@Transactional
	public List<MenuItems> addToCart(String name,String email) {
		List<MenuItems> itemList = repo.getAllItems();
		int id=0;
			for(MenuItems item:itemList) {
				if(item.getName().equals(name) && item.getAvailability().equalsIgnoreCase("Yes")) {
					id=item.getId();
					break;
				}
			}
			MenuItems item = repo.getItemById(id);
			if(cartMap.containsKey(email)) {
				cartMap.get(email).add(item);
			}else {
				List<MenuItems> list = new ArrayList<>();
				list.add(item);
				cartMap.put(email, list);
			}
			
			System.out.println("Item added succesfully....");
			price+=item.getPrice();
		return itemList;
	}
	@Transactional
	public List<MenuItems> removeFromCart(String name,String email) {
		int id=0;
		int idx=0;
		if(!cartMap.get(email).isEmpty()) {
		for(MenuItems item:cartMap.get(email)) {
			if(item.getName().equalsIgnoreCase(name)) {
				id=item.getId();
				break;
			}
			idx++;
		}
		MenuItems item = repo.getItemById(id);
		price-=item.getPrice();
		System.out.println("Item to be delete : "+item);
		System.out.println("Before delete : "+cartMap.size());
//		cartlist.clear();
		cartMap.get(email).remove(idx);
		System.out.println(cartMap.get(email)+"Item removed succesfully....");
		System.out.println("After delete : "+cartMap.size());
	}else {
		System.out.println("Cart is empty");
	}
		return cartMap.get(email);
	}
	@Transactional
	public List<MenuItems> viewCart(String email) {
		return cartMap.get(email);
	}
	@Transactional
	public List<MenuItems> ShowMenuItems(){
		List<MenuItems> showitemList=new ArrayList<>();
		List<MenuItems> itemList = repo.getAllItems();
		for(MenuItems item:itemList) {
			if(item.getAvailability().equalsIgnoreCase("Yes")) {
				showitemList.add(item);
			}
		}
		return showitemList;
	}
}
