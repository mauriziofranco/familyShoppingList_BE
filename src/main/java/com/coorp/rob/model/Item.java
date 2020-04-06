package com.coorp.rob.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Item {

	private String itemName;
	
	private boolean itemTaken;
	
	public Item() {
		super();
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public boolean isItemTaken() {
		return itemTaken;
	}

	public void setItemTaken(boolean itemTaken) {
		this.itemTaken = itemTaken;
	}

	@Override
	public String toString() {
		return "Item [name=" + this.itemName + ", taken=" + this.itemTaken + "]";
	}
}
