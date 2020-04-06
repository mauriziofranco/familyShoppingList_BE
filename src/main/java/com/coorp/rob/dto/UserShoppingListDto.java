package com.coorp.rob.dto;

import com.coorp.rob.model.Item;

public class UserShoppingListDto {

	private String email;
	
	private Item items[];
	
	public UserShoppingListDto() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public Item[] getItems() {
		return items;
	}

	public void setItems(Item[] items) {
		this.items = items;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserShoppingList [email=" + email + ", items="  + "]";
	}
}
