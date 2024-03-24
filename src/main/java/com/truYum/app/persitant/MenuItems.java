package com.truYum.app.persitant;

//import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="menuitems")
public class MenuItems {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "contact_generator")
	@SequenceGenerator(name = "contact_generator", sequenceName = "contact_seq", allocationSize = 1)
	private int id;
	private String name;
	private int price;
//	@Column(name="")
	private String availability;
	private String dateOfLaunch;
	private String freeDelivery;
	
	
	public MenuItems(String name, int price, String availability, String dateOfLaunch,
			String freeDelivery) {
		super();
		this.name = name;
		this.price = price;
		this.availability = availability;
		this.dateOfLaunch = dateOfLaunch;
		this.freeDelivery = freeDelivery;
	}
	

	public MenuItems() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public String getDateOfLaunch() {
		return dateOfLaunch;
	}
	public void setDateOfLaunch(String dateOfLaunch) {
		this.dateOfLaunch = dateOfLaunch;
	}
	public String getFreeDelivery() {
		return freeDelivery;
	}

	public void setFreeDelivery(String freeDelivery) {
		this.freeDelivery = freeDelivery;
	}


	@Override
	public String toString() {
		return "MenuItems [id=" + id + ", name=" + name + ", price=" + price + ", availability=" + availability
				+ ", DateOfLaunch=" + dateOfLaunch + ", freeDelivery=" + freeDelivery + "]";
	}
	
	
}
