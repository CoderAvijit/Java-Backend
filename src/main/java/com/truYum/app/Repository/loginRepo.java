package com.truYum.app.Repository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.truYum.app.persitant.Admin;
import com.truYum.app.persitant.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class loginRepo {
	@Autowired
	private EntityManager em;
//	@RequestMapping(value = {"addmenu"}, method = RequestMethod.GET)
	public String doLogin(String role,String userName,String password) {
			String jpql=null;
			System.out.println("Role :"+role+" User Name :"+userName+" Password :"+password);
		if(role.equalsIgnoreCase("customer")) {
			System.out.println("Executed inside "+role);
			jpql = "select c from Customer c";
			TypedQuery<Customer> query = em.createQuery(jpql,Customer.class);
			List<Customer> customerList = query.getResultList();
				for(Customer cust:customerList) {
					if(cust.getUserId().equalsIgnoreCase(userName) && cust.getPassword().equals(password)) {
						return "customer";
					}
				}
		}else if(role.equalsIgnoreCase("Admin")) {
			jpql = "select c from Admin c";
			TypedQuery<Admin> query = em.createQuery(jpql,Admin.class);
			List<Admin> adminList = query.getResultList();
				for(Admin admin:adminList) {
					if(admin.getUser_id().equalsIgnoreCase(userName) && admin.getPassword().equals(password)) {
						return "admin";
					}
				}
			}
		return "UserName not found or Password mismatch"+" or Please register yoursels as "+role+" first";		
	}
}
