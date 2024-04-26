package com.EcommerceTask.model;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.boot.context.properties.bind.DefaultValue;

import jakarta.annotation.Generated;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Table(name="Item_tb")
@Entity
public class Item {
   @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
 private  int itemid;
  @Nonnull
  private String itemname;
  @Nonnull
  @Size(min = 30, max = 30)
  private String category;
  @Positive
  private int price;
  private Date dop;
  
public Item() {
	super();
	// TODO Auto-generated constructor stub
}
public Item(int itemid, String itemname, @Size(min = 30, max = 30) String category, @Positive int price, Date dop) {
	super();
	this.itemid = itemid;
	this.itemname = itemname;
	this.category = category;
	this.price = price;
	this.dop = dop;
}
public int getItemid() {
	return itemid;
}
public void setItemid(int itemid) {
	this.itemid = itemid;
}
public String getItemname() {
	return itemname;
}
public void setItemname(String itemname) {
	this.itemname = itemname;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public Date getDop() {
	return dop;
}
public void setDop(Date dop) {
	this.dop = dop;
}
//public void setDop(LocalDate now) {
//	this.dop=now.now();
//}

  
}
