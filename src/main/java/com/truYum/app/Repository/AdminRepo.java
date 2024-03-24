package com.truYum.app.Repository;

import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.truYum.app.persitant.MenuItems;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
//import java.util.*;
@Repository 
// used to indicate that a class is a repository, which is a component responsible 
//for accessing and manipulating data from a database
public class AdminRepo {
	
	@Autowired
	//dependency injection that injects EntityManager in the bean
	private EntityManager em;
	//Store logs of the program
	Logger logger = LoggerFactory.getLogger(MenuItems.class);
	
	@Transactional
	public void saveItem(MenuItems MI) {
		em.persist(MI);
		logger.info("We have saved succesfully: "+MI);
	}
	@Transactional
	public List<MenuItems> getAllItems(){
		String jpqlQuery = "select c from MenuItems c";
		TypedQuery<MenuItems> query =em.createQuery(jpqlQuery,MenuItems.class);
		List<MenuItems> items = query.getResultList();
		Collections.sort(items, (p1,p2) -> {
			return (p1.getPrice()-p2.getPrice());
		});
		System.out.println(items);
		return items;
	}
	@Transactional
	public MenuItems getItemById(int id) {
		return em.find(MenuItems.class, id);
	}
	@Transactional
	public MenuItems deleteItemsById(int id){
		MenuItems item = em.find(MenuItems.class, id);
		em.remove(item);
		logger.info("Here we have deleted the object: "+item);
		return item;
	}
	@Transactional
	public MenuItems updateItem(String name,int price,String date,String avail,String freeDel) {
		List<MenuItems> itemsList =getAllItems();
		MenuItems item = null;
		for(MenuItems itemItr:itemsList) {
			if(itemItr.getName().equals(name)) {
				item=itemItr;
				break;
			}
		}
		item.setPrice(price);
		item.setAvailability(avail);
		item.setDateOfLaunch(date);
		item.setFreeDelivery(freeDel);
		logger.info("We have updated the object "+item);
		return item;
		
	}
	@Transactional
	public MenuItems updateByPrice(int id, int price) {
		MenuItems item = em.find(MenuItems.class, id);
		item.setPrice(price);
		logger.info("We have modified the object :"+item);
		return item;
	}
	@Transactional
	public void updateByAvail(int id,String isAvail ) {
		MenuItems item = em.find(MenuItems.class, id);
		item.setAvailability(isAvail);
		logger.info("Here we have set availability as "+isAvail+" for this object "+item);
	}
}
