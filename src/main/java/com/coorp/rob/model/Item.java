package com.coorp.rob.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Item {

	private String name;
	
	public Item() {
		super();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.getName();
	}

	@Override
	public String toString() {
		return "Item [name=" + name + "]";
	}
}
